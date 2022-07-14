/**
 * Author: LiuShihao
 * Date: 2022/7/13 22:49
 * Desc: 感染问题
 * leetcode200题目：https://leetcode.com/problems/number-of-islands/
 * https://leetcode.com/problems/number-of-islands/
 */
public class Test_Infect {

    public static void main(String[] args) {

    }

    public static int numIslands(char[][] grid){
        if (grid == null || grid.length == 0) return 0;
        int isLands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1'){
                    isLands++;
                    infect(grid,i,j);
                }
            }
        }
        return isLands;
    }

    private static void infect(char[][] grid, int i, int j) {
        if (i<0 || i==grid.length || j<0 || j==grid[0].length || grid[i][j] != '1'){
            return;
        }
        grid[i][j] = '2';
        infect(grid,i-1,j);
        infect(grid,i+1,j);
        infect(grid,i,j-1);
        infect(grid,i,j+1);
    }

}
