/*
 * 
 *  Handles all logic (initizlization, moves, and win) for sliding game 
 * 
 * 
 *  Written by Tony Ponomarev and Olivia ma
 *  
 */

import java.util.Random;

public class SlidingGame extends Game{

    // Needed for this specific game
    private int blankRow;
    private int blankColumn;
    private int moves = 0;
    
    
    public SlidingGame(Board board){
        super(board);
        int rows = board.getRows();
        int cols  = board.getColumns();

        int v = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board.setPiece(r, c, new NumberPiece(v++));
            }
        }

        board.setPiece(rows - 1, cols - 1, null);
        this.blankRow = rows - 1;
        this.blankColumn = cols - 1;
        this.moves = 0;
    }


    public int getMoves(){
        return this.moves;
    }

    // Gets the winning state we compare with/
    public void initializeWin(){
        int rows = board.getRows();
        int cols = board.getColumns();
        int v = 1;
        for (int r=0;r<rows;r++)
            for (int c=0;c<cols;c++)
                board.setPiece(r, c, new NumberPiece(v++));
        board.clear(rows-1, cols-1);
        
        blankRow = rows-1; 
        blankColumn = cols-1;
    }



public boolean isSolved() {
     int rows = board.getRows();
    int cols  = board.getColumns();

    for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
            Piece p = board.getPiece(r, c);
            boolean isLastCell = (r == rows - 1) && (c == cols - 1);

            if (isLastCell) {
                if (p != null) {
                return false;
                }
            } else {
                if (!(p instanceof NumberPiece)) {
                    return false;
                }
                int expect = r * cols + c + 1;
                if (((NumberPiece) p).getValue() != expect) {
                    return false;
                }
            }
        }
    }
    return true;
}

private boolean isValueAt(int r, int c, int value) {
    Piece p = board.getPiece(r, c);
    return (p instanceof NumberPiece) && ((NumberPiece) p).getValue() == value;
}

    // Translates the value moved into a cardinal direction that works easier with the move function
    public int findMove(int value){
        int r = blankRow, c = blankColumn;
        int rows = board.getRows(), cols = board.getColumns();


        if (r > 0 && isValueAt(r - 1, c, value)) {
            return 1;
        }

        if (c < cols - 1 && isValueAt(r, c + 1, value)){
            return 2;
        }

        if (r < rows - 1 && isValueAt(r + 1, c, value)) {
            return 3;
        }

        if (c > 0 && isValueAt(r, c - 1, value)) {
            return 4;
        }

        return -1;
        
    }

    // Checks to make sure the move is valid
    public boolean validMove(int direction){
        switch (direction) {
            // Up
            case 1: 
                return blankRow > 0;
            // Right
            case 2: 
                return blankColumn < board.getColumns() - 1;
            // Down
            case 3: 
                return blankRow < board.getRows() - 1;
            // left
            case 4: 
                return blankColumn > 0;
            default: 
                return false;
    }
    }

    // Makes the move (switches spaces on the baord.)
    public boolean makeMove(int direction){
        this.moves ++;
        
        if (validMove(direction)){

            switch(direction){
            case 1:
                board.swapPiece(blankRow, blankColumn, blankRow-1, blankColumn);
                this.blankRow--;
                return true;
            case 2:
                board.swapPiece(blankRow, blankColumn, blankRow, blankColumn + 1);
                this.blankColumn++;
                return true;
            case 3:
                board.swapPiece(blankRow, blankColumn, blankRow + 1, blankColumn);
                this.blankRow++;
                return true;
            case 4:
                board.swapPiece(blankRow, blankColumn, blankRow, blankColumn - 1);
                this.blankColumn--;
                return true;
            }
        }
        return false;

    }

    // Shuffles the board by making a speicifc amount of moves.
    public void shuffleBoard(int difficulty){
        Random random = new Random();
        int [] moves = {75, 100, 150, 175, 200};

        for(int i = 0; i < moves[difficulty - 1]; i ++){
            int randomNumber = random.nextInt(4) + 1;

            if (validMove(randomNumber)){
                makeMove(randomNumber);
            }
        }

    }

}
