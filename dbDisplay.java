/*
 * 
 *  Handles displaying the dots and boxes board
 *  
 *  Written by Tony Ponomarev and Olivia Ma
 * 
 */

public class dbDisplay implements Display<Board>{
    
    private final Player p0, p1;

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    // add color in console for differentiating players' edges
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";

    public dbDisplay(Player p0, Player p1) {
        this.p0 = p0;
        this.p1 = p1;
    }

    // returns colored string if owner == p0 or p1
    // else will return plain white s
    private String colorize(String s, Player owner) {
        if (owner == null) return s;
        if (owner == p0) return RED + s + RESET;
        if (owner == p1) return BLUE + s + RESET;
        return s;
    }

    private static String strRepeat(String s, int n) {
        if (n <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(s.length() * n);
        
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    public String display(Board board) {
        
        StringBuilder sb = new StringBuilder();

        int rows = board.getRows();
        int cols = board.getColumns();

        //Append spaces so each box is the same width
        int maxId = rows * cols;

        int w = Math.max(3, String.valueOf(maxId).length() + 2);

        
        
        for (int r = 0; r < rows; r++) {
            // top edges
            for (int c = 0; c < cols; c++) {
                dbPiece p = (dbPiece) board.getPiece(r, c);
                sb.append("*");
                if (p.isEdgeClaimed(Edge.TOP)) {
                    Player owner = p.getEdgeOwner(Edge.TOP);
                    sb.append(colorize(strRepeat("-", w), owner));
                } else {
                    sb.append(strRepeat(" ", w));
                }
            }
            sb.append("*\n");

            // middle line with vertical edges + center content
            for (int c = 0; c < cols; c++) {
                dbPiece p = (dbPiece) board.getPiece(r, c);

                if (p.isEdgeClaimed(Edge.LEFT)) {
                    sb.append(colorize("|", p.getEdgeOwner(Edge.LEFT)));
                } else {
                    sb.append(" ");
                }

                String cell;
                if (p.isBoxClaimed()) {
                    String nm = p.getBoxOwner().getName();
                    char ch = (nm != null && !nm.isEmpty()) ? Character.toUpperCase(nm.charAt(0)) : '?';
                    cell = colorize(strRepeat(" ", w/2) + (String.valueOf(ch)) + strRepeat(" ", w/2), p.getBoxOwner());
                } else {
                    cell = strRepeat(" ", w/2) + (String.valueOf(p.getBoxId())) + strRepeat(" ", w/2);
                }
                sb.append(cell);
                
                if (c == cols - 1) {
                    if (p.isEdgeClaimed(Edge.RIGHT)) {
                        sb.append(colorize("|", p.getEdgeOwner(Edge.RIGHT)));
                    } else {
                        sb.append(" ");
                    }
                }
            }
            sb.append("\n");
        }

        // bottom edges of last row
        for (int c = 0; c < cols; c++) {
            dbPiece p = (dbPiece) board.getPiece(rows - 1, c);
            sb.append("*");
            if (p.isEdgeClaimed(Edge.BOTTOM)) {
                sb.append(colorize(strRepeat("-", w), p.getEdgeOwner(Edge.BOTTOM)));
            } else {
                sb.append(strRepeat(" ", w));
            }
        }
        sb.append("*\n");

        return sb.toString();
    }
}
    

