/*
 *  The simple Board method that can be built upon for any game
 *  
 *  Allows for simple board manuevers:
 *  - Creates a 2d array of tiles
 *  - Gets specific piece from a tile
 *  - Allows to set pieces to a certain tile
 *  
 *  Written by Tony Ponomarev and Olivia Ma
 * 
 */


public class Board{

    private final Tile[][] board;
    private int rows;
    private int columns;

    public Board(Config config){

        // Creates a board based on the rows and columns given by user
        int rows= config.getRows();
        int columns = config.getColumns();

        this.rows = rows;
        this.columns = columns;

        //Creates empty board
        this.board = new Tile[rows][columns];

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                board[i][j] = new Tile();
            }
        }
        
    }

    //Getters
    public Tile[][] getBoard(){
        return this.board;
    }
    public Piece getPiece(int r, int c){ 
        return board[r][c].getPiece(); 
    }
    public int getRows(){
        return this.rows;
    }
    public int getColumns(){
        return this.columns;
    }

    //Setters
    void setPiece(int r,int c, Piece p){ 
        board[r][c].setPiece(p); 
    }

    public void clear(int r, int c){
        board[r][c] = null;
    }

    public void swapPiece(int r1, int c1, int r2, int c2){
        Piece tmp = board[r1][c1].getPiece();
        board[r1][c1].setPiece(board[r2][c2].getPiece());
        board[r2][c2].setPiece(tmp);
    }
}
