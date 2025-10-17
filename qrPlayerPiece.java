/*
 * 
 *  This will hold the player piece info for the quoridor game
 * 
 * 
 *  Written by Tony Ponomarev and Olivia Ma
 */



public class qrPlayerPiece implements Piece{
    
    public qrPlayerPiece() {

    }


    public boolean isBlank(){
        return true;
    }





    // We don't really need this I'm not sure why I put isBox in the Piece interface - something to look at
    public boolean isBox(){
        return true;
    }
}
