public class Game extends ObjectPlus{

  public String currentBoard;
  public char moveLeadingToState;

  // description of the state of the game puzzle and the move leading to it
  Game (String board, char move){
    currentBoard = board;
    moveLeadingToState = move;
  }

  public String currentState(){
    return currentBoard;
  }

  //the step cost is always 1
  public int getStepCost(){
    return 1;
  }

  public char getMove(){
    return moveLeadingToState;
  }

  //prints out the current board and the directional arrow of the move leading to it
  public void show(){
    char c = '\0';

    if(moveLeadingToState == 'l'){
      c = '<';
    }
    else if(moveLeadingToState == 'r'){
      c = '>';
    }
    else if(moveLeadingToState == 'u'){
      c = '^';
    }
    else if(moveLeadingToState == 'd'){
      c = 'v';
    }

    System.out.println("  "+currentBoard.charAt(0)+" "+currentBoard.charAt(1)+" "+currentBoard.charAt(2)+" "+c);
    System.out.println("  "+currentBoard.charAt(3)+" "+currentBoard.charAt(4)+" "+currentBoard.charAt(5));
    System.out.println("  "+currentBoard.charAt(6)+" "+currentBoard.charAt(7)+" "+currentBoard.charAt(8));
  }

  //didnt know what is wanted for this
  public void showPart(int index){

  }

  //checks if the board is in the goal state
  // it simply checks the heuristicvalue function as this returns 0 in goal state
  public boolean isGoalState(String goalBoard){
    if(heuristicValue(goalBoard) > 0){
      return false;
    }
    else{
      return true;
    }
  }

  //returns true or false for each move at each position of the board
  public boolean isMoveLegal(char c){
    int i = 0;
    int position = 0;

    while(i < 9){
      if(currentBoard.charAt(i) == '0'){
        position = i;
      }
      i++;
    }

    switch (position) {
      case 0:
        if(c == 'r' || c == 'd'){
          return true;
        }
        else{
          return false;
        }
      case 1:
        if(c == 'l' || c == 'r' || c == 'd'){
          return true;
        }
        else{
          return false;
        }
      case 2:
        if(c == 'l' || c == 'd'){
          return true;
        }
        else{
          return false;
        }
      case 3:
        if(c == 'r' || c == 'u' || c == 'd'){
          return true;
        }
        else{
          return false;
        }
      case 4:
        if(c == 'l' || c == 'r' || c == 'u' || c == 'd'){
          return true;
        }
        else{
          return false;
        }
      case 5:
        if(c == 'l' || c == 'u' || c == 'd'){
          return true;
        }
        else{
          return false;
        }
      case 6:
        if(c == 'r' || c == 'u'){
          return true;
        }
        else{
          return false;
        }
      case 7:
        if(c == 'l' || c == 'r' || c == 'u'){
          return true;
        }
        else{
          return false;
        }
      case 8:
        if(c == 'l' || c == 'u'){
          return true;
        }
        else{
          return false;
        }
      default:
        return false;
    }
  }

// this is a rather long method that returns the new game board. It adjusted the game board string accordingly depending
// on the position 0 is at and the giving direction to move.
// Takes chars 'l', 'r', 'u', 'd' and returns an instance of the next board.
  public Game nextState(char c){
    String newBoard = "";
    int i = 0;
    int position = 0;

    while(i < 9){
      if(currentBoard.charAt(i) == '0'){
        position = i;
      }
      i++;
    }

    switch (position) {
      case 0:
        if(c == 'r'){
          newBoard += currentBoard.charAt(1);
          newBoard += currentBoard.charAt(0);
          newBoard += currentBoard.substring(2,9);
          Game next = new Game(newBoard, 'r');
          return next;
        }
        else if(c == 'd'){
          // System.out.println(currentBoard.charAt(3) + currentBoard.substring(1,3) + currentBoard.charAt(0) + currentBoard.substring(4,9));
          newBoard += currentBoard.charAt(3);
          newBoard += currentBoard.substring(1,3);
          newBoard += currentBoard.charAt(0);
          newBoard += currentBoard.substring(4,9);
          Game next = new Game(newBoard, 'd');
          return next;
        }
      case 1:
        if(c == 'l'){
          newBoard += currentBoard.charAt(1);
          newBoard += currentBoard.charAt(0);
          newBoard += currentBoard.substring(2,9);
          Game next = new Game(newBoard, 'l');
          return next;
        }
        else if(c == 'r'){
          newBoard += currentBoard.charAt(0);
          newBoard += currentBoard.charAt(2);
          newBoard += currentBoard.charAt(1);
          newBoard += currentBoard.substring(3,9);
          Game next = new Game(newBoard, 'r');
          return next;
        }
        else if(c == 'd'){
          newBoard += currentBoard.charAt(0);
          newBoard += currentBoard.charAt(4);
          newBoard += currentBoard.charAt(2);
          newBoard += currentBoard.charAt(3);
          newBoard += currentBoard.charAt(1);
          newBoard += currentBoard.substring(5,9);
          Game next = new Game(newBoard, 'd');
          return next;
        }
      case 2:
        if(c == 'l'){
          newBoard += currentBoard.charAt(0);
          newBoard += currentBoard.charAt(2);
          newBoard += currentBoard.charAt(1);
          newBoard += currentBoard.substring(3,9);
          Game next = new Game(newBoard, 'l');
          return next;
        }
        else if(c == 'd'){
          newBoard += currentBoard.substring(0,2);
          newBoard += currentBoard.charAt(5);
          newBoard += currentBoard.substring(3,5);
          newBoard += currentBoard.charAt(2);
          newBoard += currentBoard.substring(6,9);
          Game next = new Game(newBoard, 'd');
          return next;
        }
      case 3:
        if(c == 'r'){
          newBoard += currentBoard.substring(0,3);
          newBoard += currentBoard.charAt(4);
          newBoard += currentBoard.charAt(3);
          newBoard += currentBoard.substring(5,9);
          Game next = new Game(newBoard, 'r');
          return next;
        }
        else if(c == 'u'){
          newBoard += currentBoard.charAt(3);
          newBoard += currentBoard.substring(1,3);
          newBoard += currentBoard.charAt(0);
          newBoard += currentBoard.substring(4,9);
          Game next = new Game(newBoard, 'u');
          return next;
        }
        else if(c == 'd'){
          newBoard += currentBoard.substring(0,3);
          newBoard += currentBoard.charAt(6);
          newBoard += currentBoard.substring(4,6);
          newBoard += currentBoard.charAt(3);
          newBoard += currentBoard.substring(7,9);
          Game next = new Game(newBoard, 'd');
          return next;
        }
      case 4:
        if(c == 'l'){
          newBoard += currentBoard.substring(0,3);
          newBoard += currentBoard.charAt(4);
          newBoard += currentBoard.charAt(3);
          newBoard += currentBoard.substring(5,9);
          Game next = new Game(newBoard, 'l');
          return next;
        }
        else if(c == 'r'){
          newBoard += currentBoard.substring(0,4);
          newBoard += currentBoard.charAt(5);
          newBoard += currentBoard.charAt(4);
          newBoard += currentBoard.substring(6,9);
          Game next = new Game(newBoard, 'r');
          return next;
        }
        else if(c == 'u'){
          newBoard += currentBoard.charAt(0);
          newBoard += currentBoard.charAt(4);
          newBoard += currentBoard.substring(2,4);
          newBoard += currentBoard.charAt(1);
          newBoard += currentBoard.substring(5,9);
          Game next = new Game(newBoard, 'u');
          return next;
        }
        else if(c == 'd'){
          newBoard += currentBoard.substring(0,4);
          newBoard += currentBoard.charAt(7);
          newBoard += currentBoard.substring(5,7);
          newBoard += currentBoard.charAt(4);
          newBoard += currentBoard.charAt(8);
          Game next = new Game(newBoard, 'd');
          return next;
        }
      case 5:
        if(c == 'l'){
          newBoard += currentBoard.substring(0,4);
          newBoard += currentBoard.charAt(5);
          newBoard += currentBoard.charAt(4);
          newBoard += currentBoard.substring(6,9);
          Game next = new Game(newBoard, 'l');
          return next;
        }
        else if(c == 'u'){
          newBoard += currentBoard.substring(0,2);
          newBoard += currentBoard.charAt(5);
          newBoard += currentBoard.substring(3,5);
          newBoard += currentBoard.charAt(2);
          newBoard += currentBoard.substring(6,9);
          Game next = new Game(newBoard, 'u');
          return next;
        }
        else if(c == 'd'){
          newBoard += currentBoard.substring(0,5);
          newBoard += currentBoard.charAt(8);
          newBoard += currentBoard.substring(6,8);
          newBoard += currentBoard.charAt(5);
          Game next = new Game(newBoard, 'd');
          return next;
        }
      case 6:
        if(c == 'r'){
          newBoard += currentBoard.substring(0,6);
          newBoard += currentBoard.charAt(7);
          newBoard += currentBoard.charAt(6);
          newBoard += currentBoard.charAt(8);
          Game next = new Game(newBoard, 'r');
          return next;
        }
        else if(c == 'u'){
          newBoard += currentBoard.substring(0,3);
          newBoard += currentBoard.charAt(6);
          newBoard += currentBoard.substring(4,6);
          newBoard += currentBoard.charAt(3);
          newBoard += currentBoard.substring(7,9);
          Game next = new Game(newBoard, 'u');
          return next;
        }
      case 7:
        if(c == 'l'){
          newBoard += currentBoard.substring(0,6);
          newBoard += currentBoard.charAt(7);
          newBoard += currentBoard.charAt(6);
          newBoard += currentBoard.charAt(8);
          Game next = new Game(newBoard, 'l');
          return next;
        }
        else if(c == 'r'){
          newBoard += currentBoard.substring(0,7);
          newBoard += currentBoard.charAt(8);
          newBoard += currentBoard.charAt(7);
          Game next = new Game(newBoard, 'r');
          return next;
        }
        else if(c == 'u'){
          newBoard += currentBoard.substring(0,4);
          newBoard += currentBoard.charAt(7);
          newBoard += currentBoard.substring(5,7);
          newBoard += currentBoard.charAt(4);
          newBoard += currentBoard.charAt(8);
          Game next = new Game(newBoard, 'u');
          return next;
        }
      case 8:
        if(c == 'l'){
          newBoard += currentBoard.substring(0,7);
          newBoard += currentBoard.charAt(8);
          newBoard += currentBoard.charAt(7);
          Game next = new Game(newBoard, 'l');
          return next;
        }
        else if(c == 'u'){
          newBoard += currentBoard.substring(0,5);
          newBoard += currentBoard.charAt(8);
          newBoard += currentBoard.substring(6,8);
          newBoard += currentBoard.charAt(5);
          Game next = new Game(newBoard, 'u');
          return next;
        }
        default:
          return null;
    }
  }

  //calculates the heuristic value by comparing the current board against the goal board
  //h(n) = the number of misplaced tiles (not hole) at state n
  public int heuristicValue(String goalBoard){
    int i = 0;
    int heuristicVal = 0;

    while(i < 9){
      if(currentBoard.charAt(i) != goalBoard.charAt(i)){
        heuristicVal += 1;
      }
      i++;
    }

    return heuristicVal;
  }


}
