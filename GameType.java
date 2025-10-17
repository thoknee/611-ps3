/*
 *  List of the different game types that we want to add
 * 
 *  Holds the amount of players needed for each game
 * 
 *  Written by Olivia Ma and Tony Ponomarev
 * 
 */


public enum GameType {
    SLIDING(1),
    DOTS_AND_BOXES(2),
    QUORIDOR(2);

    public final int players;
    
    GameType(int players) { 
        this.players = players; 
    }
}
