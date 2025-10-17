/*
 * 
 *  Abstract game class that we can extend for any game.
 * 
 *  Written by Tony Ponomarev and Olivia Ma
 * 
 * 
 */

public abstract class Game {
    public Board board;


    public Game(Board board){
        this.board = board;
    }

    public Board getBoard(){
        return this.board;
    }

}
