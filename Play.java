/*
 * 
 *  Handles the logic of the game actually playing. 
 *  
 * 
 * 
 * 
 */
import java.util.Scanner;

public class Play {
    
    private final Scanner sc;
    private final ConsoleInput p; 

    public Play() {
        this.sc = new Scanner(System.in);
        this.p = new ConsoleInput(sc);
    }

    public void run(){
        
        Setup setup = new Setup();
        boolean again;

        do {
            Config cfg = new Config();
            cfg = setup.intro(p, cfg);

            RunGame runSlide = new RunSliding();
            RunDotsAndBoxes rundb = new RunDotsAndBoxes();

            switch (cfg.getGameType()) {
                case SLIDING:
                    runSlide.runGame(cfg, p);
                    break;
                case DOTS_AND_BOXES:
                    rundb.runGame(cfg, p);
                    break;
                case QUORIDOR:
                    break;
            }

            again = p.intInRange("Would you like to keep playing games? 0 = No, 1 = Yes: ", 0, 1) == 1;
        } while (again);
    }
}



