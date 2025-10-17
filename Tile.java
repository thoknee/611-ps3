/*
 *  A tile of the overarching board
 *  For now, tile only holds a single piece - could be grown and changed later.
 * 
 * 
 *  Written by Tony Ponomarev and Olivia MA
 * 
 * 
 */

public final class Tile {

    private Piece piece;


     public Tile() { 
        this.piece = null;
     }

     public Tile(Piece p){
        this.piece = p;
     }

    public boolean isEmpty() { 
        return piece == null; 
    }

    public Piece getPiece() {
         return piece; 
    }
    public void setPiece(Piece p) {
         this.piece = p;
     }
}
