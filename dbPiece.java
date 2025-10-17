/*
 * This will hold the dots/boxes piece. 
 * 
 *  It will have a player associated with it and should check if the piece completes a box
 * 
 * 
 * 
 */

public class dbPiece implements Piece{
    // hold who claimed each of the four edges around a box
    // if edge.SIDE.getIndex() == null -> side isn't claimed
    // edgeOwners = [null, null, null, null];
    // edgeOwners[Edge.TOP.getIndex()] = Olivia;
    // edgeOwners = [Olivia, null, null, null];
    private final Player[] edgeOwners;
    // hold which player owns this dot/boxes piece
    private Player boxOwner;
    // An id field that we use to display.
    private int boxId;

    public dbPiece() {
        this.edgeOwners = new Player[4];
        this.boxOwner = null;
    }
    
    // override isn't necessary, just tells compiler that we are overriding, not making a new method
    @Override
    public boolean isBlank(){
        // dbPiece always represents a box (not a blank slot)
         return false;
    }

    @Override
    public boolean isBox() {
        return true;
    }

    public void setBoxId(int id) { 
        this.boxId = id; 
    }
    public int getBoxId() { 
        return boxId; 
    }

    public boolean isEdgeClaimed(Edge e) {
        return edgeOwners[e.getIndex()] != null;
    }

    public Player getEdgeOwner(Edge e) {
        return edgeOwners[e.getIndex()];
    }

    public void setEdgeOwner(Edge e, Player p) {
        edgeOwners[e.getIndex()] = p;
    }

    public int claimedEdgeCount() {
        int claimedCount = 0;
        for (Player p : edgeOwners){
            if (p != null){
                claimedCount++;
            }
        }
        return claimedCount;
    }

    public boolean isBoxClaimed() {
        return boxOwner != null;
    }

    public Player getBoxOwner() {
        return boxOwner;
    }

    // used when piece.claimEdgeCount() == 4
    public void setBoxOwner(Player p) {
        this.boxOwner = p;
    }
}

// https://stackoverflow.com/questions/44654291/is-it-good-practice-to-use-ordinal-of-enum
enum Edge {
    TOP(0),
    RIGHT(1),
    BOTTOM(2),
    LEFT(3);

    private final int index;

    private Edge(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static Edge fromInt(int v) {
        switch (v) {
            case 1: return TOP;
            case 2: return RIGHT;
            case 3: return BOTTOM;
            case 4: return LEFT;
            default: throw new IllegalArgumentException("Edge must be 1 - 4.");
        }
    }
}
