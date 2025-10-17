/*
 *  An interface that we can implement to display board for different games.
 * 
 *  Written by Tony Ponomarev and Olivia Ma
 * 
 * 
 */

public interface Display<T>{
    String display(T board);
}
