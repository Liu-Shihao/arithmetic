package day01;

/**
 * Author: LiuShihao
 * Date: 2022/7/17 14:02
 * Desc: 感染问题
 * https://leetcode.com/problems/number-of-islands/
 */
public class LeetCode200_Infect {

    public  int numIslands(char[][] grid) {
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
    //将该节点上下左右的点全部感染
    private  void infect(char[][] grid, int i, int j) {
        if (i < 0 || i == grid.length || j <0 || j== grid[0].length || grid[i][j] != '1'){
            return;
        }
        grid[i][j] = '2';
        infect(grid,i-1,j);
        infect(grid,i+1,j);
        infect(grid,i,j-1);
        infect(grid,i,j+1);
    }






}
