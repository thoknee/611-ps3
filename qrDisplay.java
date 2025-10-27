public class qrDisplay implements Display<qrGame> {

    private final Player p1;
    private final Player p2;
    
    // ANSI colors
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";

    public qrDisplay(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public String display(qrGame game) {
        Board cells = game.getPlayerBoard();
        qrWallBoard walls = game.getWallBoard();

        final int N = cells.getRows();
        final int G = 2 * N - 1;
        final char[][] grid = new char[G][G];

        // fill blanks
        for (int R = 0; R < G; R++) {
            for (int C = 0; C < G; C++) grid[R][C] = ' ';
        }

        // baseline cell dots at even even
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                grid[2*r][2*c] = '.';
            }
        }

        // draw '+' at odd-odd junctions
        for (int R = 1; R < G; R += 2) {
            for (int C = 1; C < G; C += 2) {
                grid[R][C] = '+';
            }
        }

        // pawns 
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                Piece piece = cells.getPiece(r, c);
                if (piece instanceof qrPlayerPiece) {
                    // qrPlayerPiece qp = (qrPlayerPiece) piece;
                    // char ch = (qp.player() == qrPlayerPiece.Player.P1) ? p1Char : p2Char;
                    qrPlayerPiece qp = (qrPlayerPiece) piece;
                    String name = qp.getName(); 
                    char ch = Character.toUpperCase(name.charAt(0));
                    grid[2 * r][2 * c] = ch;
                }
            }
        }

        // walls from anchors
        final int A = walls.getRows(); // anchors per side = N-1
        for (int ar = 0; ar < A; ar++) {
            for (int ac = 0; ac < A; ac++) {
                Piece p = walls.getPiece(ar, ac);
                if (!(p instanceof qrWallPiece)) continue;
                qrWallPiece wp = (qrWallPiece) p;

                if (wp.orientation() == qrWallPiece.Orientation.H) {
    
                    int R = 2*ar + 1;
                    int C1 = 2*ac;
                    int C2 = 2*ac + 2;
                    if (inGrid(G, R, C1)) grid[R][C1] = '-';
                    if (inGrid(G, R, C2)) grid[R][C2] = '-';
                } else {

                    int C = 2*ac + 1;
                    int R1 = 2*ar;
                    int R2 = 2*ar + 2;
                    if (inGrid(G, R1, C)) grid[R1][C] = '|';
                    if (inGrid(G, R2, C)) grid[R2][C] = '|';
                }
            }
        }


        StringBuilder sb = new StringBuilder((G + 6) * (G + 6));


        sb.append("    ");
        for (int c = 0; c < N; c++) sb.append(RED).append(c).append(" ").append(RESET);
        sb.append("\n");
        sb.append("    ");
        for (int c = 0; c < N - 1; c++) sb.append(BLUE).append(" ").append(c).append(RESET);
        sb.append("\n");

        // draw rows
        char p1Initial = Character.toUpperCase(p1.getName().charAt(0));
        char p2Initial = Character.toUpperCase(p2.getName().charAt(0));

        for (int R = 0; R < G; R++) {
            // row labels for player/wall lines
            if (R % 2 == 0)
                sb.append(RED).append(String.format("%2d ", R / 2)).append(RESET);
            else
                sb.append(BLUE).append(String.format("%2d ", R / 2)).append(RESET);

            sb.append(" ");
            for (int C = 0; C < G; C++) {
                char ch = grid[R][C];
                // colorize selectively
                if (ch == '|')
                    sb.append(BLUE).append(ch).append(RESET);
                else if (ch == '-')
                    sb.append(BLUE).append(ch).append(RESET);
                else if (ch == p1Initial || ch == p2Initial)
                    sb.append(RED).append(ch).append(RESET);
                else
                    sb.append(ch);
            }

            // row label mirrored at right side
            if (R % 2 == 0)
                sb.append(" ").append(RED).append(String.format("%2d", R / 2)).append(RESET);
            else
                sb.append(" ").append(BLUE).append(String.format("%2d", R / 2)).append(RESET);
            sb.append("\n");
        }

        // footer labels (same as header)
        sb.append("    ");
        for (int c = 0; c < N; c++) sb.append(RED).append(c).append(" ").append(RESET);
        sb.append("\n");
        sb.append("    ");
        for (int c = 0; c < N - 1; c++) sb.append(BLUE).append(" ").append(c).append(RESET);
        sb.append("\n");

        return sb.toString();
    }


    public String display(Board ignored) { return ""; }

    private static boolean inGrid(int G, int R, int C) {
        return R >= 0 && R < G && C >= 0 && C < G;
    }
}