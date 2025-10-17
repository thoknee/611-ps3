/*
 * 
 *  This holds the came configuration aka things about the game 
 *  Includes:
 *  type of game, list of players, rows/columns of board, and difficulty if applicable
 * 
 *  Written by Tony Ponomarev and Olivia Ma
 */

public class Config {
    private GameType gameType;
    private Player[] players;
    private int rows;
    private int columns;
    private int difficulty;

    public Config(){
    }

    // stores important information given to us by the player.

    public Config(GameType gt, String... names) {
        setGameType(gt);
        for (int i = 0; i < Math.min(names.length, players.length); i++) {
            players[i] = new Player(names[i]);
        }
    }

    // Getters and setters
    public GameType getGameType() {
         return gameType; 
    }
    public Player[] getPlayers() {
        return players.clone();
    }
    public Player getPlayer(int idx) { 
        return players[idx]; 
    }
    public int getRows(){
        return this.rows;
    }
    public int getColumns(){
        return this.columns;
    }
    public int getDifficulty(){
        return this.difficulty;
    }
     public int playerCount() { 
        return players.length;
     }

    public void setGameType(GameType gt) {
        this.gameType = gt;
        this.players = new Player[gt.players];
    }
    public void setPlayer(int idx, Player p) {
        if (idx < 0 || idx >= players.length) throw new IndexOutOfBoundsException();
        players[idx] = p;
    }
    
    public void setRows(int rows){
        this.rows = rows;
    }
    public void setColumns(int columns){
        this.columns = columns;
    }
    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
}
