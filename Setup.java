/*
 * 
 *  Runs thte setup of the game. 
 *  
 *  Figures out what game, prompts for player names and saves all to the game config
 * 
 *  
 *  Written by tony Ponomarev and Olivia Ma
 * 
 */
public class Setup {

    public Config intro(ConsoleInput p, Config cfg) {
            
        System.out.println("Welcome! What game would you like to play?\nEnter 1 for sliding game, 2 for dots and boxes, and 3 for quoridor");
        int choice = p.intInRange("Enter 1,2, or 3: ", 1, 3);
        

        if (choice == 1){
            cfg.setGameType(GameType.SLIDING);
            cfg.setRows(p.intAtLeast("How many rows do you want? (must be 2 or more)): ", 2));
        cfg.setColumns(p.intAtLeast("How many columns do you want? (must be 2 or more): ", 2));
            
        }
        else  if (choice == 2){
            cfg.setGameType(GameType.DOTS_AND_BOXES);
            cfg.setRows(p.intAtLeast("How many rows do you want? (must be 2 or more)): ", 2));
            cfg.setColumns(p.intAtLeast("How many columns do you want? (must be 2 or more): ", 2));
        }
        else  if (choice == 3){
            cfg.setGameType(GameType.QUORIDOR);
            // int dim = p.intAtLeast("What dimensions would you like?", 3);
            cfg.setRows(9);
            cfg.setColumns(9);

        }

        for (int i = 0; i < cfg.playerCount(); i++) {
            while (true) {
                String name = p.isLine("What is player " + (i + 1) + "'s' name? ").trim();
                
                // reject empty names or just spaces
                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty or just spaces.");
                    continue;
                }
                
                // check for duplicate names, not allowed
                boolean duplicate = false;
                for (int j = 0; j < i; j++) {
                    Player existing = cfg.getPlayer(j);
                    if (existing != null && existing.getName().equalsIgnoreCase(name)) {
                        System.out.println("That name is already taken.");
                        duplicate = true;
                        break;
                    }
                }
                if (duplicate) continue;

                cfg.setPlayer(i, new Player(name));
                break;
            }           
        }

        

        if (cfg.getGameType() == GameType.SLIDING) {
            cfg.setDifficulty(p.intInRange("What difficulty do you want from 1-5: ", 1, 5));
        }

        return cfg;

    }
    
}
