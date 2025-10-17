/*
 * 
 *  Implements the display interface
 * 
 *  Deals with displaying the sliding game.
 * 
 */

public final class SlideDisplay implements Display<Board> {
    
    public String display(Board board) {
        int rows = board.getRows(), cols = board.getColumns();
        int maxTile = rows * cols - 1;
        int cellWidth = String.valueOf(maxTile).length();

        String cellBorder = "--" + repeat('-', cellWidth) + "+";

        StringBuilder out = new StringBuilder("+");
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) out.append(cellBorder);
            out.append("\n| ");

            for (int j = 0; j < cols; j++) {
                Piece p = board.getPiece(i, j);
                if (p instanceof NumberPiece) {
                    int v = ((NumberPiece) p).getValue();
                    out.append(String.format("%" + cellWidth + "d | ", v));
                } else {
                    out.append(String.format("%" + cellWidth + "s | ", " "));
                }
            }
            out.append("\n+");
        }

        for (int j = 0; j < cols; j++) out.append(cellBorder);
        return out.toString();
    }

    private static String repeat(char ch, int n) {
        char[] a = new char[n];
        java.util.Arrays.fill(a, ch);
        return new String(a);
    }
}
