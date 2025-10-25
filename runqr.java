/*
 * 
 *  This implements the runGame interface and has the actual gameloop of the quoridor games
 * 
 *  Written by Tony Ponomarev and Olivia Ma
 * 
 * 
 */



public class runqr implements RunGame{
    
    
    
    public void runGame(Config cfg, ConsoleInput in) {

        // Builds our boards
        final int N = cfg.getRows();
        Board cellBoard = new Board(new ConfigBuilder().rows(N).columns(N).build());
        qrWallBoard wallBoard = new qrWallBoard(new ConfigBuilder().rows(N-1).columns(N-1).build());

        // Creates player pieces
        qrGame game = new qrGame(cellBoard, wallBoard);

        int wallsPerPlayer = 10;
        int mid = N / 2;

        qrPlayerPiece p1Pawn = new qrPlayerPiece(qrPlayerPiece.Player.P1, 0,     mid, N-1, wallsPerPlayer);
        qrPlayerPiece p2Pawn = new qrPlayerPiece(qrPlayerPiece.Player.P2, N-1,   mid, 0,   wallsPerPlayer);

        // Place pawns on the cell board
        cellBoard.setPiece(0,   mid, p1Pawn);
        cellBoard.setPiece(N-1, mid, p2Pawn);

        // Sets up display
        qrDisplay view = new qrDisplay('A', 'B'); // or whatever chars you like
        System.out.println(view.display(game));


        int cur = 0;
        while (true) {

            // Win check
            if (p1Pawn.reachedGoal() || p2Pawn.reachedGoal()) {
                String winner = p1Pawn.reachedGoal() ? cfg.getPlayer(0).getName()
                                                      : cfg.getPlayer(1).getName();
                System.out.println("Game over! Winner: " + winner);
                break;
            }

            Player curMeta = cfg.getPlayer(cur);
            qrPlayerPiece me   = (cur == 0) ? p1Pawn : p2Pawn;
            qrPlayerPiece opp  = (cur == 0) ? p2Pawn : p1Pawn;

            System.out.println("Turn: " + curMeta.getName()
                    + "  (Walls left: " + me.wallsLeft() + ")");
            System.out.println("[1] Move pawn  [2] Place wall  [0] Quit");

            int action = in.intInRange("Choose action: ", 0, 2);
            if (action == 0) {
                System.out.println("Exiting to main menu...");
                return;
            }

            boolean madeMove = false;

            if (action == 1) {
                java.util.List<qrGame.Cell> legal = game.legalPawnMoves(me, opp);
                if (legal.isEmpty()) {
                    System.out.println("No legal pawn moves. Choose another action.");
                } else {
                    System.out.print("Legal moves:");
                    for (int i = 0; i < legal.size(); i++) {
                        qrGame.Cell c = legal.get(i);
                        System.out.print("  " + (i+1) + ":" + c.r + "," + c.c);
                    }
                    System.out.println();
                    int idx = in.intInRange("Pick a move index (0 to cancel): ", 0, legal.size());
                    if (idx != 0) {
                        qrGame.Cell dst = legal.get(idx-1);
                        cellBoard.setPiece(me.row(), me.col(), null);
                        boolean ok = game.movePawn(me, opp, dst.r, dst.c);
                        if (!ok) {
                            System.out.println("Illegal move (unexpected). Try again.");
                            cellBoard.setPiece(me.row(), me.col(), me);
                        } else {
                            cellBoard.setPiece(dst.r, dst.c, me);
                            madeMove = true;
                        }
                    }
                }

            } else {
                // --- Place wall ---
                System.out.println("Orientation: 1 = H, 2 = V, 0 = cancel");
                int o = in.intInRange("Choose orientation: ", 0, 2);
                if (o == 0) {
                } else {
                    qrWallPiece.Orientation orient = (o == 1) ? qrWallPiece.Orientation.H
                                                             : qrWallPiece.Orientation.V;
                    int maxAnchor = N - 1; 
                    int r = in.intInRange("Anchor row (0.." + (maxAnchor-1) + "): ", 0, maxAnchor-1);
                    int c = in.intInRange("Anchor col (0.." + (maxAnchor-1) + "): ", 0, maxAnchor-1);

                    boolean ok = game.placeWall(r, c, orient, p1Pawn, p2Pawn, me);
                    if (!ok) {
                        System.out.println("Illegal wall (overlap/crossing/no-path/none left). Try again.");
                    } else {
                        madeMove = true;
                    }
                }
            }

            // Show board if anything changed
            if (madeMove) {
                System.out.println(view.display(game));
                cur = 1 - cur;
            }
        }
    }
    private static class ConfigBuilder {
        private final Config cfg = new Config();
        ConfigBuilder rows(int r) { cfg.setRows(r); return this; }
        ConfigBuilder columns(int c) { cfg.setColumns(c); return this; }
        Config build() { return cfg; }
    }
}
