/*
 * 
 *  Handles the way that we take inputs for the sliding game specifically.
 * 
 * 
 * 
 * 
 */

public class RunSliding implements RunGame {
     
    public void runGame(Config cfg, ConsoleInput p) {
        Board board = new Board(cfg);
        SlidingGame game = new SlidingGame(board);
        game.shuffleBoard(cfg.getDifficulty());

        Display<Board> view = new SlideDisplay();
        System.out.println(view.display(board));

        while (true) {
            int move = p.intAtLeast("What number would you like to move to the empty square? (Press 0 to quit): ", 0);

            // if player wants to quit mid-game
            // goes back to Play.run()
            if (move == 0) {
                System.out.println("You chose to quit. Returning to main menu...");
                return; 
            }
            
            int direction = game.findMove(move);
            if (direction == -1 || !game.validMove(direction)) {
                System.out.println("That is not a possible move right now.");
                continue;
            }

            boolean moved = game.makeMove(direction);
            if (moved) {
                System.out.println(view.display(board));
            }

            if (game.isSolved()) {
                Player p0 = cfg.getPlayer(0);
                if (p0 != null) p0.incrementWins();
                System.out.println("Well done " + (p0 != null ? p0.getName() : "Player")
                                + "! You won in " + game.getMoves() + " move(s).");
                if (p0 != null) System.out.println("Total wins: " + p0.getWins());
                break;
            }
        }
    }
}
