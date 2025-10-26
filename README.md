CS611: Assignment 2 Dots and Boxes

Teammates: Antony Ponomarev and Olivia Ma

This is a terminal based simple game engine made completely in java. The high level idea is to create a game engine that is easily extendable to add other games - especially those based on a grid system like the two games currently implemented.

Currently, this game engine has 3 games.
- Sliding puzzle
- Dots and boxes
- Quoridor

Sliding puzzle is an old school puzzle you have probably played at least once in your life. It is a one player game where the objective is to fix the numbers in chronological order being able to move them one at a time through an empty space.

Dots and boxes is a 2 player game where you face off against an opponent. The game starts with an array of dots of which every turn you can connect with a line. Upon completing an entire box with 4 lines you get 1 point and the player with more points wins.

Quoridor is a bit more indepth 2 player game. Each player is put on one end of a 9x9 board and are tasked with getting to the other side. Each player can have either move one square or place a wall to block the opponent. The first person to the other end wins. A game with various strategies, it a lot of fun and has a ton of nuance to it.

To use this game engine it is quite simple. Open a terminal and navigate to the directory in which the files exist. From there, you need to compile the Main.java as so:

```javac Main.java```

Upon finishing compilation, you can then simply run the code using the following command:

```java Main```

Upon running it with this command you should be prompted with an introduction and all the information you need to be able to play either game. Have fun playing!


Prominent Files: 

- Main.java – Entry point to the application. Initializes the configuration, players, and starts the RunDotsAndBoxes game.

- RunDotsAndBoxes.java – Manages the overall game flow and turn sequence, prompting users for moves, handling scoring, and determining when the game ends. Allows players to quit mid-game or play again after finishing.

- dbGame.java – Core logic for Dots and Boxes gameplay. Handles edge claiming, box completion, scoring, and determining when the game is over.

- dbDisplay.java – Responsible for displaying the board in the console, including box edges, player colors, and ownership.

- dbPiece.java – Represents an individual box in the grid. Tracks which edges are claimed and which player owns the box (if completed).

- Board.java – Stores the state of the entire grid, including all boxes and edges. Provides helper methods for accessing and updating board data.

- Player.java – Holds player information, including name, color, and score.

- Config.java – Stores configuration details such as player setup and board dimensions.

- ConsoleInput.java – Handles validated user input from the console, ensuring numeric ranges and providing prompts.

- Edge.java – Enum for edge positions (TOP, RIGHT, BOTTOM, LEFT) with helper conversion methods.


Note: Example below does not include color, but colors are printed on the terminal via ANSI escape sequences. 


Input/Output Example (Summary)

Everything ending with a ‘?’ or ‘:’ is an input prompt, and everything else is printed output.

```
(base) PS C:\Users\17815\Desktop\CS611\611-ps2> javac Main.java        
(base) PS C:\Users\17815\Desktop\CS611\611-ps2> java Main              
Welcome! What game would you like to play?
Enter 1 for sliding game and 2 for dots and boxes.
Enter 1 or 2: 2
What is player 1's' name? Olivia
What is player 2's' name? Antony
How many rows do you want? (must be 2 or more)): 2
How many columns do you want? (must be 2 or more): 2
*   *   *
  1   2
*   *   *
  3   4
*   *   *

Scores: Olivia=0 | Antony=0
Turn: Olivia
Olivia, it is your turn, which box would you like to select? (press 0 to quit): 1
Open edges for box 1: [1, 2, 3, 4]  (1=TOP,2=RIGHT,3=BOTTOM,4=LEFT)
Pick an edge, 0 to choose a different box, or -1 to quit the game: 1
*---*   *
  1   2
*   *   *
  3   4
*   *   *

Scores: Olivia=0 | Antony=0
Turn: Antony
Antony, it is your turn, which box would you like to select? (press 0 to quit): 1
Open edges for box 1: [2, 3, 4]  (1=TOP,2=RIGHT,3=BOTTOM,4=LEFT)
Pick an edge, 0 to choose a different box, or -1 to quit the game: 2
*---*   *
  1 | 2
*   *   *
  3   4
*   *   *

Scores: Olivia=0 | Antony=0
Turn: Olivia
Olivia, it is your turn, which box would you like to select? (press 0 to quit): 1
Open edges for box 1: [3, 4]  (1=TOP,2=RIGHT,3=BOTTOM,4=LEFT)
Pick an edge, 0 to choose a different box, or -1 to quit the game: 3
*---*   *
  1 | 2
*---*   *
  3   4
*   *   *

Scores: Olivia=0 | Antony=0
Turn: Antony
Antony, it is your turn, which box would you like to select? (press 0 to quit): 1
Open edges for box 1: [4]  (1=TOP,2=RIGHT,3=BOTTOM,4=LEFT)
Pick an edge, 0 to choose a different box, or -1 to quit the game: 4
*---*   *
| A | 2
*---*   *
  3   4
*   *   *

Antony completed 1 box. You get another turn!
Scores: Olivia=0 | Antony=1
Turn: Antony
Antony, it is your turn, which box would you like to select? (press 0 to quit): 2
Open edges for box 2: [1, 2, 3]  (1=TOP,2=RIGHT,3=BOTTOM,4=LEFT)
Pick an edge, 0 to choose a different box, or -1 to quit the game: 1
*---*---*
| A | 2
*---*   *
  3   4
*   *   *

Scores: Olivia=0 | Antony=1
Turn: Olivia
Olivia, it is your turn, which box would you like to select? (press 0 to quit): 2
Open edges for box 2: [2, 3]  (1=TOP,2=RIGHT,3=BOTTOM,4=LEFT)
Pick an edge, 0 to choose a different box, or -1 to quit the game: 2
*---*---*
| A | 2 |
*---*   *
  3   4
*   *   *

Scores: Olivia=0 | Antony=1
Turn: Antony
Antony, it is your turn, which box would you like to select? (press 0 to quit): 2
Open edges for box 2: [3]  (1=TOP,2=RIGHT,3=BOTTOM,4=LEFT)
Pick an edge, 0 to choose a different box, or -1 to quit the game: 3
*---*---*
| A | A |
*---*---*
  3   4
*   *   *

Antony completed 1 box. You get another turn!
Scores: Olivia=0 | Antony=2
Turn: Antony
Antony, it is your turn, which box would you like to select? (press 0 to quit): 3
Open edges for box 3: [2, 3, 4]  (1=TOP,2=RIGHT,3=BOTTOM,4=LEFT)
Pick an edge, 0 to choose a different box, or -1 to quit the game: 2
*---*---*
| A | A |
*---*---*
  3 | 4
*   *   *

Scores: Olivia=0 | Antony=2
Turn: Olivia
Olivia, it is your turn, which box would you like to select? (press 0 to quit): 4
Open edges for box 4: [2, 3]  (1=TOP,2=RIGHT,3=BOTTOM,4=LEFT)
Pick an edge, 0 to choose a different box, or -1 to quit the game: 2
*---*---*
| A | A |
*---*---*
  3 | 4 |
*   *   *

Scores: Olivia=0 | Antony=2
Turn: Antony
Antony, it is your turn, which box would you like to select? (press 0 to quit): 3
Open edges for box 3: [3, 4]  (1=TOP,2=RIGHT,3=BOTTOM,4=LEFT)
Pick an edge, 0 to choose a different box, or -1 to quit the game: 3
*---*---*
| A | A |
*---*---*
  3 | 4 |
*---*   *

Scores: Olivia=0 | Antony=2
Turn: Olivia
Olivia, it is your turn, which box would you like to select? (press 0 to quit): 3
Open edges for box 3: [4]  (1=TOP,2=RIGHT,3=BOTTOM,4=LEFT)
Pick an edge, 0 to choose a different box, or -1 to quit the game: 4
*---*---*
| A | A |
*---*---*
| O | 4 |
*---*   *

Olivia completed 1 box. You get another turn!
Scores: Olivia=1 | Antony=2
Turn: Olivia
Olivia, it is your turn, which box would you like to select? (press 0 to quit): 4
Open edges for box 4: [3]  (1=TOP,2=RIGHT,3=BOTTOM,4=LEFT)
Pick an edge, 0 to choose a different box, or -1 to quit the game: 3
*---*---*
| A | A |
*---*---*
| O | O |
*---*---*

Olivia completed 1 box. You get another turn!
Would you like to keep playing games? 0 = No, 1 = Yes: 1
Welcome! What game would you like to play?
Enter 1 for sliding game and 2 for dots and boxes.
Enter 1 or 2: 2
What is player 1's' name? Olivia
What is player 2's' name? Antony
How many rows do you want? (must be 2 or more)): 2
How many columns do you want? (must be 2 or more): 2
*   *   *
  1   2
*   *   *
  3   4
*   *   *

Scores: Olivia=0 | Antony=0
Turn: Olivia
Olivia, it is your turn, which box would you like to select? (press 0 to quit): 1
Open edges for box 1: [1, 2, 3, 4]  (1=TOP,2=RIGHT,3=BOTTOM,4=LEFT)
Pick an edge, 0 to choose a different box, or -1 to quit the game: 0
Olivia, it is your turn, which box would you like to select? (press 0 to quit): 1
Open edges for box 1: [1, 2, 3, 4]  (1=TOP,2=RIGHT,3=BOTTOM,4=LEFT)
Pick an edge, 0 to choose a different box, or -1 to quit the game: -1
You chose to quit. Returning to main menu...
Would you like to keep playing games? 0 = No, 1 = Yes: 0
(base) PS C:\Users\17815\Desktop\CS611\611-ps2> 
```

Some info and online resources we used in creating this:

adding color to console source: https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println