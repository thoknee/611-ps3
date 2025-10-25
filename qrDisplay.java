public class qrDisplay implements Display<qrGame> {

    private final char p1Char;
    private final char p2Char;

    public qrDisplay(char p1Char, char p2Char) {
        this.p1Char = p1Char;
        this.p2Char = p2Char;
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
                    qrPlayerPiece qp = (qrPlayerPiece) piece;
                    char ch = (qp.player() == qrPlayerPiece.Player.P1) ? p1Char : p2Char;
                    grid[2*r][2*c] = ch;
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


        StringBuilder sb = new StringBuilder((G + 4) * (G + 4));


        sb.append("   ");
        for (int C = 0; C < G; C++) sb.append(C % 10);
        sb.append('\n');

        for (int R = 0; R < G; R++) {
            sb.append(String.format("%2d ", R));
            for (int C = 0; C < G; C++) {
                char ch = grid[R][C];

                if (ch == ' ' && (R % 2 == 0) && (C % 2 == 0)) ch = '.';
                sb.append(ch);
            }
            sb.append(' ').append(String.format("%2d", R)).append('\n'); 
        }


        sb.append("   ");
        for (int C = 0; C < G; C++) sb.append(C % 10);
        sb.append('\n');

        return sb.toString();
    }


    public String display(Board ignored) { return ""; }

    private static boolean inGrid(int G, int R, int C) {
        return R >= 0 && R < G && C >= 0 && C < G;
    }
}