/*
 * 
 *  This will hold the player piece info for the quoridor game
 * 
 * 
 *  Written by Tony Ponomarev and Olivia Ma
 */



public class qrPlayerPiece implements Piece{
    
     public enum Player { P1, P2 }
    
    private final Player player;
    private final int goalRow;
    private int row, col;
    private int wallsLeft;

    public qrPlayerPiece(Player player, int startRow, int startCol, int goalRow, int wallsPerPlayer) {
        this.player = player;
        this.row = startRow;
        this.col = startCol;
        this.goalRow = goalRow;
        this.wallsLeft = wallsPerPlayer;

    }
    public Player player(){ 
        return player; 
    }
    public int row(){ 
        return row; 
    }
    public int col(){ 
        return col; 
    }
    public int wallsLeft(){ 
        return wallsLeft; 
    }
    public int goalRow(){ 
        return goalRow; 
    }

    public void moveTo(int r, int c) {
        this.row = r;
        this.col = c;
    }

     public void useWall() {
        if (wallsLeft <= 0) {
            throw new IllegalStateException("No walls left");
        }
        wallsLeft--;
    }
    public boolean reachedGoal() {
        return row == goalRow;
    }

    public boolean isBlank(){
        return true;
    }



    // We don't really need this I'm not sure why I put isBox in the Piece interface - something to look at
    public boolean isBox(){
        return true;
    }
}
