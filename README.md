CS611: Assignment 3 Quoridor

Teammates: Antony Ponomarev and Olivia Ma

This project extends our Java-based terminal game engine to implement Quoridor, a two-player strategic board game that combines pawn movement with tactical wall placement. Like previous assignments, the system is built to support grid-based games through a modular engine that can be extended for other games.

Currently, this game engine has 3 games.
- Sliding puzzle
- Dots and boxes
- Quoridor

In Quoridor, each player begins on opposite sides of a 9×9 grid.
- The goal is to move your pawn to the opposite edge of the board before your opponent.
- On your turn, you may either move your pawn or place a wall to block your opponent’s progress.
- Walls are limited in number and cannot completely cut off a player’s access to their goal.
This implementation runs entirely in the terminal, providing visual output of the board, walls, and player turns using ASCII characters.

The project builds on the same grid framework from Assignment 2 but introduces new logic for:
- Wall placement validation (preventing overlap, crossing, or total blockades)
- Pawn movement legality (accounting for walls and opponent jumps)
- Dynamic pathfinding checks (ensuring both players always have a route to their goals)
All game logic, rendering, and user interaction are handled through the console.

To use this game engine it is quite simple. Open a terminal and navigate to the directory in which the files exist. From there, you need to compile the Main.java as so:

```javac Main.java```

Upon finishing compilation, you can then simply run the code using the following command:

```java Main```

Upon running it with this command you should be prompted with an introduction and all the information you need to be able to play either game. Have fun playing!


Prominent Files: 

- Main.java – Entry point for the application. Loads player info and starts the runqr game.
- runqr.java – Manages the overall Quoridor gameplay loop: prompting actions, validating input, handling turns, and printing the board.
- qrGame.java – Core Quoridor logic. Handles valid pawn moves, wall placement, BFS path checking, and enforcing game rules.
- qrDisplay.java – Responsible for rendering the pawn and wall boards as a single ASCII display.
- qrPlayerPiece.java – Represents a pawn, tracking its current position, goal row, and remaining wall count.
- qrWallPiece.java – Represents a wall segment, storing its orientation (horizontal or vertical).
- qrWallBoard.java – Tracks all wall placements and ensures legality (no crossing, overlap, or blocking).
- Board.java – Generalized grid class reused across games.
- Player.java – Stores player names, symbols, and colors.
- Config.java – Holds configuration options such as board size and player metadata.
- ConsoleInput.java – Handles validated user input with helpful range checks.

Design Features
- Modular structure for easy future extensions to other grid-based games.
- BFS validation to ensure legal wall placement.
- Readable ASCII visualization for pawns and walls.
- Robust input validation and error handling for invalid moves.

Input/Output Example (Summary)

Everything ending with a ‘?’ or ‘:’ is an input prompt, and everything else is printed output.

```
(base) PS C:\Users\17815\Desktop\CS611\611-ps3> java Main      
Welcome! What game would you like to play?
Enter 1 for sliding game, 2 for dots and boxes, and 3 for quoridor     
Enter 1,2, or 3: 3
What is player 1's' name? Tony
What is player 2's' name? Olivia
   01234567890123456
 0 . . . . A . . . .  0
 1  + + + + + + + +   1
 2 . . . . . . . . .  2
 3  + + + + + + + +   3
 4 . . . . . . . . .  4
 5  + + + + + + + +   5
 6 . . . . . . . . .  6
 7  + + + + + + + +   7
 8 . . . . . . . . .  8
 9  + + + + + + + +   9
10 . . . . . . . . . 10
11  + + + + + + + +  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Tony  (Walls left: 10)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 1
Legal moves:  1:0,5  2:1,4  3:0,3
Pick a move index (0 to cancel): 2
   01234567890123456
 0 . . . . . . . . .  0
 1  + + + + + + + +   1
 2 . . . . A . . . .  2
 3  + + + + + + + +   3
 4 . . . . . . . . .  4
 5  + + + + + + + +   5
 6 . . . . . . . . .  6
 7  + + + + + + + +   7
 8 . . . . . . . . .  8
 9  + + + + + + + +   9
10 . . . . . . . . . 10
11  + + + + + + + +  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Olivia  (Walls left: 10)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 2
Orientation: 1 = H, 2 = V, 0 = cancel
Choose orientation: 1
Anchor row (0..7): 1
Anchor col (0..6): 4
   01234567890123456
 0 . . . . . . . . .  0
 1  + + + + + + + +   1
 2 . . . . A . . . .  2
 3  + + + +-+-+ + +   3
 4 . . . . . . . . .  4
 5  + + + + + + + +   5
 6 . . . . . . . . .  6
 7  + + + + + + + +   7
 8 . . . . . . . . .  8
 9  + + + + + + + +   9
10 . . . . . . . . . 10
11  + + + + + + + +  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Tony  (Walls left: 10)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 1
Legal moves:  1:0,4  2:1,5  3:1,3
Pick a move index (0 to cancel): 2
   01234567890123456
 0 . . . . . . . . .  0
 1  + + + + + + + +   1
 2 . . . . . A . . .  2
 3  + + + +-+-+ + +   3
 4 . . . . . . . . .  4
 5  + + + + + + + +   5
 6 . . . . . . . . .  6
 7  + + + + + + + +   7
 8 . . . . . . . . .  8
 9  + + + + + + + +   9
10 . . . . . . . . . 10
11  + + + + + + + +  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Olivia  (Walls left: 9)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 2
Orientation: 1 = H, 2 = V, 0 = cancel
Choose orientation: 2
Anchor row (0..6): 0
Anchor col (0..7): 5
   01234567890123456
 0 . . . . . .|. . .  0
 1  + + + + + + + +   1
 2 . . . . . A|. . .  2
 3  + + + +-+-+ + +   3
 4 . . . . . . . . .  4
 5  + + + + + + + +   5
 6 . . . . . . . . .  6
 7  + + + + + + + +   7
 8 . . . . . . . . .  8
 9  + + + + + + + +   9
10 . . . . . . . . . 10
11  + + + + + + + +  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Tony  (Walls left: 10)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 1
Legal moves:  1:0,5  2:1,4
Pick a move index (0 to cancel): 1
   01234567890123456
 0 . . . . . A|. . .  0
 1  + + + + + + + +   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+ + +   3
 4 . . . . . . . . .  4
 5  + + + + + + + +   5
 6 . . . . . . . . .  6
 7  + + + + + + + +   7
 8 . . . . . . . . .  8
 9  + + + + + + + +   9
10 . . . . . . . . . 10
11  + + + + + + + +  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Olivia  (Walls left: 8)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 2
Orientation: 1 = H, 2 = V, 0 = cancel
Choose orientation: 2
Anchor row (0..6): 0
Anchor col (0..7): 4
Illegal wall (overlap/crossing/no-path/none left). Try again.
Turn: Olivia  (Walls left: 8)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 2
Orientation: 1 = H, 2 = V, 0 = cancel
Choose orientation: 0
Turn: Olivia  (Walls left: 8)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 2
Orientation: 1 = H, 2 = V, 0 = cancel
Choose orientation: 1
Anchor row (0..7): 0
Anchor col (0..6): 6
   01234567890123456
 0 . . . . . A|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+ + +   3
 4 . . . . . . . . .  4
 5  + + + + + + + +   5
 6 . . . . . . . . .  6
 7  + + + + + + + +   7
 8 . . . . . . . . .  8
 9  + + + + + + + +   9
10 . . . . . . . . . 10
11  + + + + + + + +  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Tony  (Walls left: 10)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 1 
Legal moves:  1:1,5  2:0,4
Pick a move index (0 to cancel): 2
   01234567890123456
 0 . . . . A .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+ + +   3
 4 . . . . . . . . .  4
 5  + + + + + + + +   5
 6 . . . . . . . . .  6
 7  + + + + + + + +   7
 8 . . . . . . . . .  8
 9  + + + + + + + +   9
10 . . . . . . . . . 10
11  + + + + + + + +  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Olivia  (Walls left: 7)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 2
Orientation: 1 = H, 2 = V, 0 = cancel
Choose orientation: 1
Anchor row (0..7): 1
Anchor col (0..6): 6
   01234567890123456
 0 . . . . A .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . . . . . . .  4
 5  + + + + + + + +   5
 6 . . . . . . . . .  6
 7  + + + + + + + +   7
 8 . . . . . . . . .  8
 9  + + + + + + + +   9
10 . . . . . . . . . 10
11  + + + + + + + +  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Tony  (Walls left: 10)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 1
Legal moves:  1:0,5  2:1,4  3:0,3
Pick a move index (0 to cancel): 3
   01234567890123456
 0 . . . A . .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . . . . . . .  4
 5  + + + + + + + +   5
 6 . . . . . . . . .  6
 7  + + + + + + + +   7
 8 . . . . . . . . .  8
 9  + + + + + + + +   9
10 . . . . . . . . . 10
11  + + + + + + + +  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Olivia  (Walls left: 6)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 2
Orientation: 1 = H, 2 = V, 0 = cancel
Choose orientation: 1
Anchor row (0..7): 2
Anchor col (0..6): 6
   01234567890123456
 0 . . . A . .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . . . . . . .  4
 5  + + + + + +-+-+   5
 6 . . . . . . . . .  6
 7  + + + + + + + +   7
 8 . . . . . . . . .  8
 9  + + + + + + + +   9
10 . . . . . . . . . 10
11  + + + + + + + +  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Tony  (Walls left: 10)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 1
Legal moves:  1:0,4  2:1,3  3:0,2
Pick a move index (0 to cancel): 2
   01234567890123456
 0 . . . . . .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . A . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . . . . . . .  4
 5  + + + + + +-+-+   5
 6 . . . . . . . . .  6
 7  + + + + + + + +   7
 8 . . . . . . . . .  8
 9  + + + + + + + +   9
10 . . . . . . . . . 10
11  + + + + + + + +  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Olivia  (Walls left: 5)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 2
Orientation: 1 = H, 2 = V, 0 = cancel
Choose orientation: 1
Anchor row (0..7): 3
Anchor col (0..6): 6
   01234567890123456
 0 . . . . . .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . A . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . . . . . . .  4
 5  + + + + + +-+-+   5
 6 . . . . . . . . .  6
 7  + + + + + +-+-+   7
 8 . . . . . . . . .  8
 9  + + + + + + + +   9
10 . . . . . . . . . 10
11  + + + + + + + +  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Tony  (Walls left: 10)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 1
Legal moves:  1:0,3  2:1,4  3:2,3  4:1,2
Pick a move index (0 to cancel): 3
   01234567890123456
 0 . . . . . .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . A . . . . .  4
 5  + + + + + +-+-+   5
 6 . . . . . . . . .  6
 7  + + + + + +-+-+   7
 8 . . . . . . . . .  8
 9  + + + + + + + +   9
10 . . . . . . . . . 10
11  + + + + + + + +  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Olivia  (Walls left: 4)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 2
Orientation: 1 = H, 2 = V, 0 = cancel
Choose orientation: 1
Anchor row (0..7): 4
Anchor col (0..6): 6
   01234567890123456
 0 . . . . . .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . A . . . . .  4
 5  + + + + + +-+-+   5
 6 . . . . . . . . .  6
 7  + + + + + +-+-+   7
 8 . . . . . . . . .  8
 9  + + + + + +-+-+   9
10 . . . . . . . . . 10
11  + + + + + + + +  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Tony  (Walls left: 10)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 2
Orientation: 1 = H, 2 = V, 0 = cancel
Choose orientation: 0
Turn: Tony  (Walls left: 10)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 1
Legal moves:  1:1,3  2:2,4  3:3,3  4:2,2
Pick a move index (0 to cancel): 3
   01234567890123456
 0 . . . . . .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . . . . . . .  4
 5  + + + + + +-+-+   5
 6 . . . A . . . . .  6
 7  + + + + + +-+-+   7
 8 . . . . . . . . .  8
 9  + + + + + +-+-+   9
10 . . . . . . . . . 10
11  + + + + + + + +  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Olivia  (Walls left: 3)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 2
Orientation: 1 = H, 2 = V, 0 = cancel
Choose orientation: 1
Anchor row (0..7): 4
Anchor col (0..6): 6
Illegal wall (overlap/crossing/no-path/none left). Try again.
Turn: Olivia  (Walls left: 3)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 3
Please enter a value between 0 and 2.
Choose action: 2
Orientation: 1 = H, 2 = V, 0 = cancel
Choose orientation: 1
Anchor row (0..7): 5
Anchor col (0..6): 6
   01234567890123456
 0 . . . . . .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . . . . . . .  4
 5  + + + + + +-+-+   5
 6 . . . A . . . . .  6
 7  + + + + + +-+-+   7
 8 . . . . . . . . .  8
 9  + + + + + +-+-+   9
10 . . . . . . . . . 10
11  + + + + + +-+-+  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Tony  (Walls left: 10)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 1
Legal moves:  1:2,3  2:3,4  3:4,3  4:3,2
Pick a move index (0 to cancel): 3
   01234567890123456
 0 . . . . . .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . . . . . . .  4
 5  + + + + + +-+-+   5
 6 . . . . . . . . .  6
 7  + + + + + +-+-+   7
 8 . . . A . . . . .  8
 9  + + + + + +-+-+   9
10 . . . . . . . . . 10
11  + + + + + +-+-+  11
12 . . . . . . . . . 12
13  + + + + + + + +  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Olivia  (Walls left: 2)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 2
Orientation: 1 = H, 2 = V, 0 = cancel
Choose orientation: 1
Anchor row (0..7): 6
Anchor col (0..6): 6
   01234567890123456
 0 . . . . . .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . . . . . . .  4
 5  + + + + + +-+-+   5
 6 . . . . . . . . .  6
 7  + + + + + +-+-+   7
 8 . . . A . . . . .  8
 9  + + + + + +-+-+   9
10 . . . . . . . . . 10
11  + + + + + +-+-+  11
12 . . . . . . . . . 12
13  + + + + + +-+-+  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Tony  (Walls left: 10)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 1
Legal moves:  1:3,3  2:4,4  3:5,3  4:4,2
Pick a move index (0 to cancel): 3
   01234567890123456
 0 . . . . . .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . . . . . . .  4
 5  + + + + + +-+-+   5
 6 . . . . . . . . .  6
 7  + + + + + +-+-+   7
 8 . . . . . . . . .  8
 9  + + + + + +-+-+   9
10 . . . A . . . . . 10
11  + + + + + +-+-+  11
12 . . . . . . . . . 12
13  + + + + + +-+-+  13
14 . . . . . . . . . 14
15  + + + + + + + +  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Olivia  (Walls left: 1)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 2
Orientation: 1 = H, 2 = V, 0 = cancel
Choose orientation: 1
Anchor row (0..7): 7
Anchor col (0..6): 6
   01234567890123456
 0 . . . . . .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . . . . . . .  4
 5  + + + + + +-+-+   5
 6 . . . . . . . . .  6
 7  + + + + + +-+-+   7
 8 . . . . . . . . .  8
 9  + + + + + +-+-+   9
10 . . . A . . . . . 10
11  + + + + + +-+-+  11
12 . . . . . . . . . 12
13  + + + + + +-+-+  13
14 . . . . . . . . . 14
15  + + + + + +-+-+  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Tony  (Walls left: 10)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 1
Legal moves:  1:4,3  2:5,4  3:6,3  4:5,2
Pick a move index (0 to cancel): 3
   01234567890123456
 0 . . . . . .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . . . . . . .  4
 5  + + + + + +-+-+   5
 6 . . . . . . . . .  6
 7  + + + + + +-+-+   7
 8 . . . . . . . . .  8
 9  + + + + + +-+-+   9
10 . . . . . . . . . 10
11  + + + + + +-+-+  11
12 . . . A . . . . . 12
13  + + + + + +-+-+  13
14 . . . . . . . . . 14
15  + + + + + +-+-+  15
16 . . . . B . . . . 16
   01234567890123456

Turn: Olivia  (Walls left: 0)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 2
Orientation: 1 = H, 2 = V, 0 = cancel
Choose orientation: 2
Anchor row (0..6): 3
Anchor col (0..7): 4
Illegal wall (overlap/crossing/no-path/none left). Try again.
Turn: Olivia  (Walls left: 0)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 1
Legal moves:  1:7,4  2:8,5  3:8,3
Pick a move index (0 to cancel): 2
   01234567890123456
 0 . . . . . .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . . . . . . .  4
 5  + + + + + +-+-+   5
 6 . . . . . . . . .  6
 7  + + + + + +-+-+   7
 8 . . . . . . . . .  8
 9  + + + + + +-+-+   9
10 . . . . . . . . . 10
11  + + + + + +-+-+  11
12 . . . A . . . . . 12
13  + + + + + +-+-+  13
14 . . . . . . . . . 14
15  + + + + + +-+-+  15
16 . . . . . B . . . 16
   01234567890123456

Turn: Tony  (Walls left: 10)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 1
Legal moves:  1:5,3  2:6,4  3:7,3  4:6,2
Pick a move index (0 to cancel): 3
   01234567890123456
 0 . . . . . .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . . . . . . .  4
 5  + + + + + +-+-+   5
 6 . . . . . . . . .  6
 7  + + + + + +-+-+   7
 8 . . . . . . . . .  8
 9  + + + + + +-+-+   9
10 . . . . . . . . . 10
11  + + + + + +-+-+  11
12 . . . . . . . . . 12
13  + + + + + +-+-+  13
14 . . . A . . . . . 14
15  + + + + + +-+-+  15
16 . . . . . B . . . 16
   01234567890123456

Turn: Olivia  (Walls left: 0)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 1
Legal moves:  1:7,5  2:8,6  3:8,4
Pick a move index (0 to cancel): 1
   01234567890123456
 0 . . . . . .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . . . . . . .  4
 5  + + + + + +-+-+   5
 6 . . . . . . . . .  6
 7  + + + + + +-+-+   7
 8 . . . . . . . . .  8
 9  + + + + + +-+-+   9
10 . . . . . . . . . 10
11  + + + + + +-+-+  11
12 . . . . . . . . . 12
13  + + + + + +-+-+  13
14 . . . A . B . . . 14
15  + + + + + +-+-+  15
16 . . . . . . . . . 16
   01234567890123456

Turn: Tony  (Walls left: 10)
[1] Move pawn  [2] Place wall  [0] Quit
Choose action: 1
Legal moves:  1:6,3  2:7,4  3:8,3  4:7,2
Pick a move index (0 to cancel): 3
   01234567890123456
 0 . . . . . .|. . .  0
 1  + + + + + +-+-+   1
 2 . . . . . .|. . .  2
 3  + + + +-+-+-+-+   3
 4 . . . . . . . . .  4
 5  + + + + + +-+-+   5
 6 . . . . . . . . .  6
 7  + + + + + +-+-+   7
 8 . . . . . . . . .  8
 9  + + + + + +-+-+   9
10 . . . . . . . . . 10
11  + + + + + +-+-+  11
12 . . . . . . . . . 12
13  + + + + + +-+-+  13
14 . . . . . B . . . 14
15  + + + + + +-+-+  15
16 . . . A . . . . . 16
   01234567890123456

Game over! Winner: Tony
Would you like to keep playing games? 0 = No, 1 = Yes: 1
Welcome! What game would you like to play?
Enter 1 for sliding game, 2 for dots and boxes, and 3 for quoridor     
Enter 1,2, or 3: 2
How many rows do you want? (must be 2 or more)): 2
How many columns do you want? (must be 2 or more): 2
What is player 1's' name? Tony
What is player 2's' name? Olivia
*   *   *
  1   2
*   *   *
  3   4
*   *   *

Scores: Tony=0 | Olivia=0
Turn: Tony
Tony, it is your turn, which box would you like to select? (press 0 to quit): 0
You chose to quit the game. Returning to main menu...
Would you like to keep playing games? 0 = No, 1 = Yes: 0
(base) PS C:\Users\17815\Desktop\CS611\611-ps3>
```