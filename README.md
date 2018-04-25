# Connect4

This project is a simple command line implementation of Connect 4. The player picks columns to drop tokens in and plays against an AI. 
The AI is an alpha-beta pruned minimax algorithm. Here is an example of the board state at win time.

```
Computer is thinking...

Turn #25: O placed a token at 4,1
  1   2   3   4   5   6   7 
-----------------------------
| O | X |   |   |   |   |   |
-----------------------------
| O | O |   |   |   |   |   |
-----------------------------
| X | O | O | O | O |   |   |
-----------------------------
| O | O | X | X | X |   |   |
-----------------------------
| O | X | O | X | X |   |   |
-----------------------------
| X | X | O | O | X | X | X |
-----------------------------



Computer Won!
```
### Downloading and Playing
The game is located in the runnable jar connect-four.jar you can play this by executing `java -jar connect-four.jar`in the same directory as the jar file.

### AI
The most interesting class in this project is `AlphaBetaMinimaxComputerPlayer` which is an implementation of a `ComputerPlayer`. It uses a default ply count of 13. The current evaluation function revolves around the number of win conditions that are still available to player X minus those that are available to player O. Additionally any wins have a `Integer.MAX_VALUE` or `Integer.MIN_VALUE` score.
