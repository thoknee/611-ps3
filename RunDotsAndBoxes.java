/*
 * 
 *  NEEDS IMPLEMENTATION 
 *  
 *  Will run dots and boxes similar to RunSliding.java
 * 
 *  Written by Tony Ponomarev and Olivia Ma
 * 
 */

public class RunDotsAndBoxes {
    public void runGame(Config cfg, ConsoleInput p) {
        
        // Sets everything up
        Board board = new Board(cfg);
        dbGame game = new dbGame(board);

        Display<Board> view = new dbDisplay(cfg.getPlayers()[0], cfg.getPlayers()[1]);
        System.out.println(view.display(board));
        
        int rows = board.getRows();
        int cols = board.getColumns();
        
        // Keeps track of turn
        int currentTurn = 0;


        // Actual game loop
        while(!game.isGameOver()){
            
            // gets the current player
            Player curPlayer = cfg.getPlayers()[currentTurn];

            //Prints out helpful information about scores
            System.out.println("Scores: " + cfg.getPlayers()[0].getName() + "=" + cfg.getPlayers()[0].getScore()
                     + " | " + cfg.getPlayers()[1].getName() + "=" + cfg.getPlayers()[1].getScore());
            System.out.println("Turn: " + curPlayer.getName());

            boolean moveMade = false;

            // Turn loop
            while(!moveMade){
                
                // Prompts for the box to select.
                String boxPrompt = curPlayer.getName() + ", it is your turn, which box would you like to select? (press 0 to quit): ";
                int box = p.intInRange(boxPrompt, 0, (rows)*(cols));
                
                // exit back to Play.run()
                if (box == 0) {
                    System.out.println("You chose to quit the game. Returning to main menu...");
                    return; 
                }

                int idx = box - 1;
                int r = idx / cols;
                int c = idx % cols;
                
                // All unclaimed edges for a box
                java.util.List <Integer> open = game.unclaimedEdgesForBox(box);

                // Makes sure there is a valid move, if not we try again
                if(open.isEmpty()){
                    System.out.println("That box has no available edges. Pick another box.");
                    continue;
                }


                // Makes sure we get a valid edge.
                while(true){
                    
                    // Shows valid edges
                    System.out.println("Open edges for box " + box + ": " + open + "  (1=TOP,2=RIGHT,3=BOTTOM,4=LEFT)");
                    //Gets edge number
                    int edgeNum = p.intInRange("Pick an edge, 0 to choose a different box, or -1 to quit the game: ", -1, 4);

                    if (edgeNum == -1) {
                        System.out.println("You chose to quit. Returning to main menu...");
                        return;
                    }

                    // If 0, we prompt for box
                    if (edgeNum == 0) {
                        break;
                    }

                    if (!open.contains(edgeNum)) {
                        System.out.println("That edge isn't available on this box. Try again.");
                        continue;
                    }


                    Edge e = Edge.fromInt(edgeNum);
                    boolean edgeClaimed = game.claimEdge(box, e, curPlayer);

                    // Makes sure it isn't claimed
                    if (!edgeClaimed) {
                        System.out.println("Edge already taken. Try another edge.");
                        open = game.unclaimedEdgesForBox(box);
                        if (open.isEmpty()) {
                            System.out.println("No edges left on this box. Choose a different box.");
                            break;
                        }
                        continue;
                    }

                    // Check to see if box is completed
                    int gained = game.resolveCompletedBoxes(r, c, curPlayer);
                    System.out.println(view.display(board));

                    // Checks for current turn. If scored, then we add points.
                    if (gained == 0) {
                        currentTurn = (currentTurn + 1) % cfg.getPlayers().length;
                    } else {
                        System.out.println(curPlayer.getName() + " completed " + gained + " box"
                                        + (gained == 1 ? "" : "es") + ". You get another turn!");
                    }

                    moveMade = true;
                    break;
                }
            }
        }
     }
}
