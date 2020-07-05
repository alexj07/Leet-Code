import java.util.*;


/*
200. Number of Islands:
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and
is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all
surrounded by water.
 */

/*
After finding the first island spot, use BFS to mark all connected island parts as visited. Only increment island counter
when a new island that has not been found with BFS is encountered.
 */

public class NumberOfIslands {

        public int numIslandsBFS(char[][] grid){

            if(grid.length == 0 || grid[0].length == 0) return 0;

            int islandCount = 0;
            boolean[][] status = new boolean[grid.length][grid[0].length];

            for(int i = 0; i < grid.length; i ++){
                for(int j = 0; j < grid[0].length; j ++){
                    if(status[i][j] == false && grid[i][j] == '1'){
                        islandCount ++;
                        bfs(grid,status,i,j);
                    }
                }
            }
            return islandCount;
        }

        public void bfs(char[][] grid, boolean[][] status, int row, int col){
            Queue<int[]> qu = new LinkedList<>();
            qu.add(new int[]{row,col});
            status[row][col] = true;

            while(!qu.isEmpty()){

                int[] current = qu.remove();
                int rowCur = current[0];
                int colCur = current[1];
                status[rowCur][colCur] = true;


                int[][] adjacentOffset = new int[][]{{rowCur + 1,colCur},{rowCur - 1, colCur},{rowCur,colCur + 1},{rowCur, colCur -1}};

                for(int[] offset: adjacentOffset){
                    if(offset[0] >= grid.length || offset[0] < 0 || offset[1] >= grid[0].length || offset[1] < 0) continue;
                    if(grid[offset[0]][offset[1]] == '1' && status[offset[0]][offset[1]] == false){
                        qu.add(offset);
                    }
                    status[offset[0]][offset[1]] = true;
                }

            }
        }



        /*
         */
        public int numIslandsDFS(char[][] grid){
            int islandCount = 0;
            if(grid.length == 0 || grid[0].length == 0) return 0;
            boolean[][] status = new boolean[grid.length][grid[0].length];
            for(int i = 0; i < grid.length; i ++){
                for(int j = 0; j < grid[0].length; j ++){
                    if(grid[i][j] == '1' && status[i][j] == false){
                        islandCount += 1;
                        dfs(grid,status,i,j);
                    }
                }
            }
            return islandCount;
        }

        public void dfs(char[][] grid, boolean[][] status, int row, int col){
            Stack<int[]> stack = new Stack<>();
            stack.push(new int[]{row,col});

            while(!stack.isEmpty()){
                int[] current = stack.pop();
                int rowCurr = current[0];
                int colCurr = current[1];
                status[rowCurr][colCurr] = true;
                int[][] adjacent = {{rowCurr,colCurr + 1},{rowCurr + 1, col},{rowCurr,colCurr - 1},{rowCurr - 1, colCurr}};
                for(int[] curr: adjacent){
                    if(curr[0] >= grid.length || curr[0] < 0 || curr[1] >= grid[0].length || curr[1] < 0) continue;
                    if(grid[curr[0]][curr[1]] == '1' && status[curr[0]][curr[1]] == false){
                        stack.push(curr);
                    }
                    status[curr[0]][curr[1]] = true;
                }
            }
        }

        public static void main(String[] args) {
            char[][] input = new char[][]{{'1','1','1','1','0'},
                                          {'1','1','0','1','0'},
                                          {'1','1','0','0','0'},
                                          {'1','0','1','0','1'}};
            NumberOfIslands test = new NumberOfIslands();
            System.out.println("BFS: " + test.numIslandsBFS(input));
            System.out.println("DFS: " + test.numIslandsDFS(input));
        }


}
