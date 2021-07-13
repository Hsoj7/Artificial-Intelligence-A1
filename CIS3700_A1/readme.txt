Joshua Stone
1013900
CIS*3700
Assignment 1

To run the program, type the specified compile and execute commands from the assignment outline.

javac -cp .;search.jar *.java
java -cp .;search.jar Do8Puzzle InitGoalFile

Note: the semi colon before search.jar is used for windows compilation. On unix, use a colon. The InitGoalFile argument should
be replaced with a text file such as InitGoal1.txt.

Upon running the program, it reads in the file and creates a new board and problem space. The algorithm then runs through
each possible move and adds nodes to the tree accordingly. New nodes are calculated using the heuristic function where
h(n) = the number of misplaced nodes. The program will print the final solution with the moves leading up to it. The function
showTree() is incomplete due to time restrictions.

Happy marking :)
