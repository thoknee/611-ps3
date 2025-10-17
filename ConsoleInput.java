/*
 * 
 *  This provides methods to make sure inputs from the user are valid
 *  We can check the following:
 *  - Make sure they input an actua line
 *  - Integer
 *  - Integer greater than some value
 *  - Integer in a range 
 * 
 * 
 * 
 *  Written by Tony Ponomarev and Olivia Ma
 */



import java.util.Scanner;

public class ConsoleInput{
    private final Scanner sc;

    public ConsoleInput(Scanner sc) { 
        this.sc = sc; 
    }

    public String isLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            if (!s.isEmpty()) return s;
            System.out.println("Please enter a non-empty value.");
        }
    }

    public int isInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            try { 
                return Integer.parseInt(s); 
            }
            catch (NumberFormatException e) { 
                System.out.println("It must be an integer."); 
            }
        }
    }

    public int intInRange(String prompt, int lo, int hi) {
        while (true) {
            int v = isInt(prompt);
            if (v >= lo && v <= hi) {
                return v;
            }
                System.out.println("Please enter a value between " + lo + " and " + hi + ".");
        }
    }

    public int intAtLeast(String prompt, int lo) {
        while (true) {
            int v = isInt(prompt);
            if (v >= lo) {
                return v;
            }
            System.out.println("Please enter a value >= " + lo + ".");
        }
    }

    public Edge edgeClaimed(String prompt,dbPiece p ){
        while(true){
            int d = (intInRange(prompt, 0, 4));
            Edge e = Edge.fromInt(d);
            if(!p.isEdgeClaimed(e)){
                return e;
            }
            else{
                System.out.println("Please enter a valid edge number");
            }
        }
    }
}
