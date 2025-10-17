/*
 * 
 *  This will hold the wall piece info for the quoridor game
 * 
 *  Written by Tony Ponomarev and Olivia Ma
 *  
 */

public class qrWallPiece implements Piece{
    
    
    public qrWallPiece() {

    }


    public boolean isBlank(){
        return true;
    }





    // We don't really need this I'm not sure why I put isBox in the Piece interface - something to look at
    public boolean isBox(){
        return true;
    }


}
