/*
 *  NEEDS IMPLEMENTATION 
 *  
 * This will have the logic for the Dots and boxes game 
 * 
 * Methods to claim an edge, check completion, determine scores, and check game end.
 * 
 * Written by Tony Ponomarev and Olivia Ma
 */

public class dbGame extends Game{

    public dbGame(Board board){
        super(board);
        // every tile has a dbPiece (box)
        int rows = board.getRows();
        int cols = board.getColumns();
        int id = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Piece p = new dbPiece();

                board.setPiece(r, c, p);
                ((dbPiece) p).setBoxId(id++);
            }
        }
    }



    public boolean claimEdge(int boxId, Edge e, Player player) {
        // validate indices
        
        //Turn id into valid rows/columns so we can access array
        int rows = board.getRows();
        int cols = board.getColumns();
        if (boxId < 1 || boxId > rows * cols) {
            return false;
        }

        int idx = boxId - 1;
        int r = idx / cols;
        int c = idx % cols;


        if (r < 0 || r >= board.getRows() || c < 0 || c >= board.getColumns()) {
            throw new IndexOutOfBoundsException("Box coordinates are out of range.");
        }

        dbPiece piece = (dbPiece) board.getPiece(r, c);
        
        // edge is already claimed
        if (piece.isEdgeClaimed(e)){
            return false; 
        }

        // claim edge for this box
        piece.setEdgeOwner(e, player);

        // also claim shared edge on existent adjacent edges
        int ar = r, ac = c;
        Edge opposite = null;
        switch (e) {
            case TOP: ar = r - 1; ac = c; opposite = Edge.BOTTOM; break;
            case RIGHT:  ar = r;     ac = c + 1; opposite = Edge.LEFT;  break;
            case BOTTOM: ar = r + 1; ac = c;     opposite = Edge.TOP; break;
            case LEFT:  ar = r;     ac = c - 1; opposite = Edge.RIGHT;  break;
        }

        // validate indices
        if (ar >= 0 && ar < board.getRows() && ac >= 0 && ac < board.getColumns()) {
            dbPiece adj = (dbPiece) board.getPiece(ar, ac);
            // if not already claimed by adjacent,  which it shouldn't be, set the opposite edge to same player
            if (!adj.isEdgeClaimed(opposite)) {
                adj.setEdgeOwner(opposite, player);
            }
        }
        return true;
    }

    private int checkAndAssignBox(int r, int c, Player player) {
        // validate indices
        if (r < 0 || r >= board.getRows() || c < 0 || c >= board.getColumns()){
            return 0;
        }

        dbPiece p = (dbPiece) board.getPiece(r, c);
        
        // claimed earlier
        if (p.isBoxClaimed()){
            return 0;
        }

        // claim now
        if (p.claimedEdgeCount() == 4) {
            p.setBoxOwner(player);
            player.incrementScore();
            return 1;
        }

        return 0;
    }

    // after edge is claimed...
    // check if any boxes were completed
    // assign owner for completed boxes, return number of boxes claimed


    // NEEDS TESTING
    public int resolveCompletedBoxes(int r, int c, Player player) {
        int claimed = 0;
        int rows = board.getRows();
        int cols = board.getColumns();
        
        claimed += checkAndAssignBox(r, c, player);

        // check neighbors, a shared edge could have completed them
        if (r - 1 >= 0){
            claimed += checkAndAssignBox(r - 1, c, player);
        }
        if (c + 1 < cols){
            claimed += checkAndAssignBox(r, c + 1, player);
        }
        if (r + 1 < rows){
            claimed += checkAndAssignBox(r + 1, c, player);
        }
        if (c - 1 >= 0){
            claimed += checkAndAssignBox(r, c - 1, player);
        }

        return claimed;
    }

    public boolean isGameOver() {
        int rows = board.getRows();
        int cols = board.getColumns();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dbPiece p = (dbPiece) board.getPiece(r, c);
                if (!p.isBoxClaimed()){
                    return false;
                }
            }
        }

        // all boxes are claimed!!!
        return true;
        
    }

    // NEEDS IMPLENTATION
    public int[] getScores(Config cfg) {
        

        int [] scores = new int[cfg.getPlayers().length];
        
        for(int i = 0; i < cfg.getPlayers().length; i++){
            scores[i] = cfg.getPlayers()[i].getScore();
        }
        return scores;
    }

    public java.util.List<Integer> unclaimedEdgesForBox(int boxId) {
        int rows = board.getRows();
        int cols = board.getColumns();
        java.util.List<Integer> out = new java.util.ArrayList<>(4);
        
        if (boxId < 1 || boxId > rows * cols) {
            return out;
        }
        int idx = boxId - 1;
        int r = idx / cols;
        int c = idx % cols;
        dbPiece p = (dbPiece) board.getPiece(r, c);
        
        if (!p.isEdgeClaimed(Edge.TOP)){
            out.add(1);
        }
        if (!p.isEdgeClaimed(Edge.RIGHT)){
            out.add(2);
        }
        if (!p.isEdgeClaimed(Edge.BOTTOM)){
            out.add(3);
        }
        if (!p.isEdgeClaimed(Edge.LEFT)){
            out.add(4);
        }
        return out;
}
}

    
