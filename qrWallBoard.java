public class qrWallBoard extends Board{
     private final int N;

    public qrWallBoard(Config cfg) {
        super(cfg);
        
        this.N = cfg.getRows();
        // if (N < 5 || (N % 2) == 0) throw new IllegalArgumentException("odd N >= 5");
        
    }

    @Override
    public void setPiece(int r, int c, Piece p) {
        if (p != null && !(p instanceof qrWallPiece))
            throw new IllegalArgumentException("WallBoard only accepts WallAnchorPiece or null");
        super.setPiece(r, c, p);
    }

    @Override
    public Piece getPiece(int r, int c) {
        return super.getPiece(r, c);
    }

    @Override
    public void swapPiece(int r1, int c1, int r2, int c2) {
        throw new UnsupportedOperationException("Swap not supported on WallBoard");
    }
    @Override
    public void clear(int r, int c) {
        super.setPiece(r, c, null);
    }


    public boolean has(int r, int c, qrWallPiece.Orientation o) {
        if (r < 0 || r >= getRows() || c < 0 || c >= getColumns()){
            return false;
        }
        Piece p = super.getPiece(r, c);
        return p != null && ((qrWallPiece) p).orientation() == o;
    }

    public boolean canPlace(int r, int c, qrWallPiece.Orientation o) {
        // // 0..N-2 anchors
        // if (r < 0 || r >= N-1 || c < 0 || c >= N-1) return false;
        // orientation-dependent bounds
        if (o == qrWallPiece.Orientation.H) {
            // horizontal: valid anchors are 0..(N-1) for rows, 0..(N-2) for cols
            if (r < 0 || r >= N || c < 0 || c >= N - 1) return false;
        } else {
            // vertical: valid anchors are 0..(N-2) for rows, 0..(N-1) for cols
            if (r < 0 || r >= N - 1 || c < 0 || c >= N) return false;
        }

        // overlap: already something here?
        Piece existing = getPiece(r, c);
        if (existing != null) return false;

        // crossing: the "+" at same anchor is forbidden; if perpendicular exists, existing!=null above covers it.
        // (Because we store exactly one piece per anchor: H or V)

        // OPTIONAL: if you want to disallow extending beyond board margins, bounds already ensure legality.
            return true;
        }

    public void place(int r, int c, qrWallPiece.Orientation o) {
        setPiece(r, c, new qrWallPiece(o));
    }

    public void remove(int r, int c) {
        setPiece(r, c, null);
    }

    public int anchorsRows() { 
        return getRows(); 
    }      // = N-1
    public int anchorsCols() { return getColumns(); }   // = N-1
    public int cellsPerSide() { return N; }

    // --- Renderer helper: which TWO segments does this anchor span on the 2N-1 grid? ---
    // Using (R,C) on the 2N-1 grid (odd,odd = cells; even,odd = H segments; odd,even = V segments)
    
    // THINK THERE IS AN ERROR HERE, commented out code you wrote, check if this is correct
    public int[][] segmentsOnRenderGrid(int r, int c, qrWallPiece.Orientation o) {
        if (o == qrWallPiece.Orientation.H) {
            // H wall at anchor (r,c) spans (2r+2, 2c+1) and (2r+2, 2c+3)
            // return new int[][] { {2*r + 2, 2*c + 1}, {2*r + 2, 2*c + 3} };
            return new int[][] { {2*r + 1, 2*c}, {2*r + 1, 2*c + 2} };
        } else {
            // V wall at anchor (r,c) spans (2r+1, 2c+2) and (2r+3, 2c+2)
            // return new int[][] { {2*r + 1, 2*c + 2}, {2*r + 3, 2*c + 2} };
            return new int[][] { {2*r, 2*c + 1}, {2*r + 2, 2*c + 1} };
        }
    }

    // --- Rules helper: which pawn-grid edges does this anchor block? ---
    // Return the four directed (cell->cell) edges to clear in your adjacency/edge-mask.
    public int[][] blockedEdges(int r, int c, qrWallPiece.Orientation o) {
        // Each edge is encoded as (r1, c1, r2, c2) in the N×N pawn grid.
        if (o == qrWallPiece.Orientation.H) {
            // Blocks movement between rows r and r+1 for columns c and c+1
            return new int[][] {
                { r,   c,   r+1, c   }, // south from (r,c)
                { r+1, c,   r,   c   }, // north from (r+1,c)
                { r,   c+1, r+1, c+1 },
                { r+1, c+1, r,   c+1 }
            };
        } else {
            // Vertical: blocks between cols c and c+1 for rows r and r+1
            return new int[][] {
                { r,   c,   r,   c+1 }, // east from (r,c)
                { r,   c+1, r,   c   }, // west from (r,c+1)
                { r+1, c,   r+1, c+1 },
                { r+1, c+1, r+1, c   }
            };
        }
    }
}
