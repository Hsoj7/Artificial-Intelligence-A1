import java.util.LinkedList;

//defines an instance of the 8-puzzle problem
public class PuzzleSpec extends Problem{

  //contains initial state and goal state. make string here for them
  String initState;
  String goalState;

  PuzzleSpec(String inState, String gState){
    initState = inState;
    goalState = gState;
  }

  // given some state, this tells us all the possible actions we can do and given those actions what are the output states
  public LinkedList getSuccessor(ObjectPlus obj){
    // this is a linked list of game instances
    LinkedList<Game> successors = new LinkedList<Game>();

    if(((Game)obj).isMoveLegal('l') == true){
      Game next = ((Game)obj).nextState('l');
      successors.add(next);
    }

    if(((Game)obj).isMoveLegal('r') == true){
      Game next = ((Game)obj).nextState('r');
      successors.add(next);
    }

    if(((Game)obj).isMoveLegal('u') == true){
      Game next = ((Game)obj).nextState('u');
      successors.add(next);
    }

    if(((Game)obj).isMoveLegal('d') == true){
      Game next = ((Game)obj).nextState('d');
      successors.add(next);
    }

    return successors;
  }

  //here we can use the goal test within the game object and the goalState from the puzzle instance
  public boolean isGoalState(ObjectPlus s){
    if( ((Game)s).isGoalState(goalState) == true ){
      return true;
    }
    else{
      return false;
    }
  }



}
