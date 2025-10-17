/*
 * 
 *  Player class that can hold how many wins they have and name (might need playerId?)
 * 
 *  Written by Tony Ponomarev and Olivia Ma
 * 
 */

public class Player {
    

    private final String name;
    private int score;
    private int wins;


    public Player(String name){
        this.name = name;
    }

    public String getName() { 
        return name;
     }

    public int getWins() { 
        return wins; 
    }
    public int getScore(){
        return this.score;
    }

    public void incrementWins(){
        this.wins++;
    }
    public void incrementScore(){
        this.score++;
    }
    

}
