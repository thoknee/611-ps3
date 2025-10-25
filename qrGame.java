/*
 * 
 *  Will hold the quoridor game logic 
 * 
 *  written by Tony Ponomarev and Olivia Ma
 * 
 */
public class qrGame extends Game{
    
   private Board boardPlayer;
   private qrWallBoard boardWalls;
   private int N;


   public qrGame(Board boardPlayer, qrWallBoard boardWalls){
      
      super(boardPlayer);
        this.boardPlayer = boardPlayer;
        this.boardWalls  = boardWalls;

      this.N = getPlayerBoard().getRows();

     }

   public static final class Cell {
      public final int r, c;
      public Cell(int r, int c) { 
         this.r = r; 
         this.c = c;
       }
      }


    private static final int[][] DIRS = {
         {-1, 0},
         { 0, 1},
         { 1, 0},
         { 0,-1} 
      };


   private boolean inBounds(int r, int c) {
         return 0 <= r && r < N && 0 <= c && c < N;
      }
   private boolean isOccupied(int r, int c, qrPlayerPiece a, qrPlayerPiece b) {
         return (a.row() == r && a.col() == c) || (b.row() == r && b.col() == c);
      }
   private boolean isOpponentAt(int r, int c, qrPlayerPiece me, qrPlayerPiece opp) {
         return (opp.row() == r && opp.col() == c);
      }

   private boolean openNorth(int r, int c) {
         if (r - 1 < 0) return false;
         return !getWallBoard().has(r - 1, c, qrWallPiece.Orientation.H);
      }
   private boolean openSouth(int r, int c) {
         if (r + 1 >= N) return false;
         return !getWallBoard().has(r, c, qrWallPiece.Orientation.H);
      }
   private boolean openWest(int r, int c) {
         if (c - 1 < 0) return false;
         return !getWallBoard().has(r, c - 1, qrWallPiece.Orientation.V);
      }
   private boolean openEast(int r, int c) {
         if (c + 1 >= N) return false;
         return !getWallBoard().has(r, c, qrWallPiece.Orientation.V);
      }
   private boolean edgeOpen(int r, int c, int dir) {
         switch (dir) {
            case 0: return openNorth(r, c);
            case 1: return openEast(r, c);
            case 2: return openSouth(r, c);
            default:return openWest(r, c);
         }
      }

   public java.util.List<Cell> legalPawnMoves(qrPlayerPiece me, qrPlayerPiece opp) {
         java.util.ArrayList<Cell> out = new java.util.ArrayList<Cell>(8);
         int r = me.row(), c = me.col();

         // Normal steps
         for (int dir = 0; dir < 4; dir++) {
            if (!edgeOpen(r, c, dir)) continue;
            int nr = r + DIRS[dir][0], nc = c + DIRS[dir][1];
            if (!inBounds(nr, nc)) continue;

            if (!isOpponentAt(nr, nc, me, opp)) {
                  out.add(new Cell(nr, nc));
            } else {
                  // jump/diagonal logic
                  if (edgeOpen(nr, nc, dir)) {
                     int fr = nr + DIRS[dir][0], fc = nc + DIRS[dir][1];
                     if (inBounds(fr, fc)) {
                        out.add(new Cell(fr, fc));   // straight jump
                     }
                  } else {
                     // diagonal alternatives around the opponent
                     int left  = (dir + 3) % 4;
                     int right = (dir + 1) % 4;

                    
                     if (edgeOpen(nr, nc, left)) {
                        int dlr = nr + DIRS[left][0], dlc = nc + DIRS[left][1];
                        if (inBounds(dlr, dlc) && !isOccupied(dlr, dlc, me, opp)) {
                              out.add(new Cell(dlr, dlc));
                        }
                     }
                     if (edgeOpen(nr, nc, right)) {
                        int drr = nr + DIRS[right][0], drc = nc + DIRS[right][1];
                        if (inBounds(drr, drc) && !isOccupied(drr, drc, me, opp)) {
                              out.add(new Cell(drr, drc));
                        }
                     }
                  }
            }
         }
         return out;
      }


   public boolean movePawn(qrPlayerPiece me, qrPlayerPiece opp, int r, int c) {

         // Makes a list of legal moves
         java.util.List<Cell> legal = legalPawnMoves(me, opp);

         //Checks if a move is legal
         boolean ok = false;
         for (Cell dst : legal) {
            if (dst.r == r && dst.c == c) {
               ok = true; break;
               }
         }
         // If not then we can't move
         if (!ok) {
            return false; 
         }
         // Makes the move
         getPlayerBoard().setPiece(me.row(), me.col(), null);
         getPlayerBoard().setPiece(r, c, me);
         me.moveTo(r, c);

         return true;
}


// Makes sure we can place the wall and places. 
     public boolean placeWall(int r, int c, qrWallPiece.Orientation o,qrPlayerPiece p1, qrPlayerPiece p2,qrPlayerPiece currentPlayer) {
         
      if (currentPlayer.wallsLeft() <= 0) {
            return false;
         }
         if (!boardWalls.canPlace(r, c, o)){
            return false;
         }


         boardWalls.place(r, c, o);

          boolean ok = bfsHasPathToGoal(p1) && bfsHasPathToGoal(p2);

         if (!ok) {
               boardWalls.remove(r, c);

               return false;
         }

         currentPlayer.useWall();
         return true;
      }

      // Getters

      public Board getPlayerBoard() { 
         return boardPlayer; 
      }
      public qrWallBoard getWallBoard()   { 
         return boardWalls; 
      }

   // Run BFS to make sure placing a wall won't block a player from reaching the end.
   private boolean bfsHasPathToGoal(qrPlayerPiece p) {
      int n = N;
      boolean[][] vis = new boolean[n][n];
      java.util.ArrayDeque<int[]> q = new java.util.ArrayDeque<int[]>();

      q.add(new int[]{p.row(), p.col()});
      vis[p.row()][p.col()] = true;

      while (!q.isEmpty()) {
         int[] cur = q.removeFirst();
         int r = cur[0], c = cur[1];


         if (r == p.goalRow()) return true;


         if (openNorth(r,c) && !vis[r-1][c]) { 
            vis[r-1][c] = true; q.add(new int[]{r-1,c});
         }
         if (openEast(r,c)  && !vis[r][c+1]) { 
            vis[r][c+1] = true; q.add(new int[]{r,c+1}); 
         }
         if (openSouth(r,c) && !vis[r+1][c]) { 
            vis[r+1][c] = true; q.add(new int[]{r+1,c}); 
         }
         if (openWest(r,c)  && !vis[r][c-1]) { 
            vis[r][c-1] = true; q.add(new int[]{r,c-1}); 
         }
      }
      return false;
   }
   }
