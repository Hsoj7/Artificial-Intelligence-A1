import java.util.LinkedList;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Do8Puzzle extends SearchAgent{

  String goalStateHolder;

  //incomplete function
  public void showTree(){
    LinkedList<Node> tree = this.tree;
    LinkedList<Node> solution = this.solution;
    int treeSize = tree.size();
    int depth = solution.size();
    ArrayList<Integer> depths = new ArrayList<Integer>();
    depths.add(0);

    System.out.println("");
    System.out.println("Search tree size = "+treeSize+" nodes");
    System.out.println(" -Hole movements are shown as <, >, ^ and v.");
    System.out.println(" -g(), h() and f() values are shown for each node.");
    System.out.println("Due to time constraints, the showTree() function is incomplete");


    for(int i = 0; i < treeSize; i++){

      Node tempNode = tree.get(i);


      //this is just to add spaces lmao
      int currentDepth = tempNode.getDepth();
      int found = 0;
      // System.out.println("depth = "+""+chartemp);
      for(int j = 0; j < depths.size(); j++){
        int temp = depths.get(j);
        if(temp == currentDepth){
          found = 1;
        }
      }
      if(found == 0){
        depths.add(currentDepth);
        for(int k = 0; k < depths.size(); k++){
          // System.out.print(" depth = "+depths.get(k));
        }
        // depths.toString();
        // System.out.println("");
        // System.out.println("");
        // System.out.println("");
        // System.out.println("");
      }
    }
  }

  //prints out the solution that got to the goal board
  public void showSolution(){
    LinkedList<Node> tree = this.tree;
    LinkedList<Node> solution = this.solution;
    String sequence = new String();
    sequence += "(";


    System.out.println("");
    System.out.println("Solution to 8-puzzle:");
    for(int i = 0; i < solution.size(); i++){
      System.out.println(" Node at depth "+i+" of state:");
      Node tempNode = solution.get(i);
      Game tempGame = (Game) tempNode.getState();
      tempGame.show();

      if(tempGame.getMove() == 'l'){
        sequence += "Left";
      }
      else if(tempGame.getMove() == 'r'){
        sequence += "Right";
      }
      else if(tempGame.getMove() == 'u'){
        sequence += "Up";
      }
      else if(tempGame.getMove() == 'd'){
        sequence += "Down";
      }
      if( (solution.size() - i != 1) && (i > 0) ){
        sequence += ",";
      }
    }

    sequence += ")";
    System.out.println("");
    System.out.println("Action sequence for solution:");
    System.out.println("  "+sequence);
  }

  //method to insert nodes into the fringe
  // this is sorted so we visit smallest evalutation function values first
  // nodes have an instance of the game state. Within the node, we will know the depth. Add depth of node + heuristic value for f(n) value
  public void insertFringe(Node node, LinkedList<Node> fringe){
    //if the list is empty, simply insert
    if(fringe.isEmpty() == true){
      fringe.add(node);
    }
    else{
      boolean inserted = false;
      //get heuristic value of the node possed in
      int heuristicValPassed = 0;
      Game game = (Game) node.getState();
      heuristicValPassed = game.heuristicValue(goalStateHolder);
      heuristicValPassed += node.getPathCost();
      // System.out.println("heur1 = " + heuristicValPassed);

      //iterates through the list and gets the heuristic value of each node to
      //determine where to insert
      int heuristicValCurrent = 0;
      for(int i = 0; i < fringe.size(); i++){
        //gets heurstic val of the node we're looking at
        Node tempNode = fringe.get(i);
        Game tempGame = (Game) tempNode.getState();
        heuristicValCurrent = tempGame.heuristicValue(goalStateHolder);
        heuristicValCurrent += tempNode.getPathCost();
        // System.out.println("heur2 = " + heuristicValCurrent);

        //if the passed node has a better heuristic val + path cost, insert it
        //before the current node we are looking at
        if(heuristicValPassed < heuristicValCurrent){
          fringe.add(i, node);
          inserted = true;
          break;
        }
      }

      //check if still not inserted, make sure to add it in
      // this is the case where the node is greater than all other nodes
      if(inserted == false){
        fringe.addLast(node);
      }
    }
  }

  //helper function
  public void setGoalState(String goalState){
    goalStateHolder = goalState;
  }

  // main contains an instance of the puzzle spec class
  // first load the initial and goal state
  // create and initialize an instance of puzzle spec with this information
  //after everything is initialized, do8Puzzle.search() is called which handles the bulk of the assignment
  //then the results are printed to the console
  public static void main(String argv[]) {
    Do8Puzzle do8Puzzle = new Do8Puzzle();
    String initState = new String();
    String goalState = new String();

    File file = new File(argv[0]);

    try (FileReader fr = new FileReader(file)) {
      int content;
      while ((content = fr.read()) != -1) {
        char c = (char) content;
        if(Character.isDigit(c) && initState.length() < 9){
          initState += (char) content;
        }
        else if(Character.isDigit(c) && initState.length() == 9){
          goalState += (char) content;
        }
      }

    } catch (IOException e) {
        e.printStackTrace();
    }

    do8Puzzle.setGoalState(goalState);

    PuzzleSpec puzzle = new PuzzleSpec(initState, goalState);
    Game firstGame = new Game(initState, '\0');
    Game finalState = new Game(goalState, '\0');
    puzzle.setInitialState(firstGame);
    puzzle.setGoalState(finalState);
    puzzle.setStrategy("h(n) = number of misplaced tiles (not hole) at state n");
    do8Puzzle.setProblem(puzzle);

    do8Puzzle.search();
    do8Puzzle.showTree();
    do8Puzzle.showSolution();

  }
}
