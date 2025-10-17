/*
 * 
 *  A type of piece that implements the Piece interface 
 *  
 *  This one is specifically for the number game (just holds an integer)
 * 
 * 
 * 
 */


public class NumberPiece implements Piece{
    private final int value;

    public NumberPiece(int value) { 
        this.value = value; 
    }

    public int getValue() { 
        return value; 
    }
    public boolean isBlank() { 
        return false; 
    }

    @Override
    public boolean isBox() {
        return false;
    }
}
