/*
 *  Implementation of Piece where it is blank
 *  
 *  Current Uses:
 *  - Blank tile in Sliding game
 *  - Non claimed edge in dots/boxes
 * 
 *  Written by Tony Ponomarev and Olivia Ma
 * 
 * 
 */

public class BlankPiece implements Piece{
    public boolean isBlank() { 
        return true; 
    }
    @Override
    public boolean isBox() {
        return true;
    }
}
