package com.lsh.day13_union;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/5 5:57 下午
 * @desc  TODO 并查集岛问题升级3  2022年04月05日18:29:34
 * 在岛问题1的基础上升级：如何设计并行方法
 *
 * 首先将矩阵一分为二
 * 1.将感染的首个点标记（A、B、C、D...）
 * 2.如果一半区域岛的个数是2（A、B）；另一半区域岛的个数是2（C、D）
 * 3.最后在将A、B、C、D合并，（如果A和C相邻；A和D相邻；B和C、D相邻，则最后都会合并为一个集合）所以最后岛的个数为1
 *
 */
public class Code05_NumberOfIslandsIII {

    public static int numIslands1(char[][] board){
        int isLands = 0;
        int[][] ans = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '1'){
                    isLands++;
                    ans[i] = new int[]{i,j};
                    infect(board,i,j);
                }
            }
        }
        return 0;




    }

    private static void infect(char[][] board, int i, int j) {
        if (i<0 || i == board.length|| j<0 || j== board[0].length || board[i][j] != '1'){
            //如果i、j越界 或者该处不是1 返回
            return;
        }
        //该节点是1
        board[i][j] = '2';
        infect(board,i-1,j);//上
        infect(board,i+1,j);//下
        infect(board,i,j-1);//左
        infect(board,i,j+1);//右

    }

}
