package com.lsh.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/4 7:10 下午
 * @desc ：岛问题
 * leetcode200题目：https://leetcode.com/problems/number-of-islands/
 *
 * 给定一个m x n个二维二进制网格，它表示“1”(土地)和“0”(水)的地图，返回岛屿的数量。
 * 岛屿被水包围，通过水平或垂直连接相邻的陆地而形成。你可以假设网格的四边都被水包围着。
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 */
public class Code200_NumberOfLands {
    //一、递归解  复杂度 矩阵 m * n   时间复杂度O(m*n)
    public static int numIslands1(char[][] grid){
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
    //感染方法：将所有连成一片的都变为2
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

    //二、并查集解：可以只判断左上，（但是注意，上一种方法必须上下左右都递归）
    public static int numIslands2(char[][] board){
        int row = board.length;//表示行
        int col = board[0].length;//表示列
        //建立一个和入参一样的Dot矩阵
        Dot[][] dots = new Dot[row][col];
        List<Dot> dotList = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '1'){
                    dots[i][j] = new Dot();
                    dotList.add(dots[i][j]);
                }
            }
        }
        //使用了Node 包裹了Dot对象
        UnionFind1<Dot> unionFind1 = new UnionFind1<>(dotList);
        //因为(0,0)没有左也没有上，所以跳过
        //先判断第一行节点（0,1） （0,2） （0,3）。。。（0,col）
        for (int j = 1; j < col; j++) {
            if (board[0][j-1] == '1' && board[0][j] == '1'){
                unionFind1.union(dots[0][j-1],dots[0][j]);
            }
        }
        //判断第一列 (1,0) (2,0) (3,0) (4,0)...
        for (int i = 1; i < row; i++) {
            if (board[i-1][0] == '1' && board[i][0] == '1'){
                unionFind1.union(dots[i-1][0],dots[i][0]);
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                //当前节点 和 左边节点
                if (board[i][j] == '1'){
                    if (board[i][j-1] == '1' ){
                        unionFind1.union(dots[i][j],dots[i][j-1]);
                    }
                    //当前节点 和 上边节点
                    if (board[i-1][j] == '1' ){
                        unionFind1.union(dots[i][j],dots[i-1][j]);
                    }
                }
            }
        }
        return unionFind1.sets();
    }

    public static class Dot {
        //定义引用数据类型 区分1字符

    }

    public static class Node<V> {
        //节点本身
        V value;
        //指向上级父节点
        public Node(V v) {
            value = v;
        }

    }
    //并查集数据结构
    public static class UnionFind1<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;
        //初始化并查集
        public UnionFind1(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }
        //找到代表节点 并压缩路径
        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }
        //合并两个节点，将小集合指向大集合
        public void union(V a, V b) {
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }

        public int sets() {
            return sizeMap.size();
        }

    }

    //三、并查集第二中数据结构解法：
    public static int numIslands3(char[][] board){
        int row = board.length;
        int col = board[0].length;
        UnionFind2 unionFind2 = new UnionFind2(board);
        //判断第一行
        for (int i = 1; i < col; i++) {
            if (board[0][i-1] == '1' && board[0][i] == '1'){
                unionFind2.union(0,i-1,0,i);
            }
        }
        //判断第一列
        for (int i = 1; i < row; i++) {
            if (board[i-1][0] == '1'&& board[i][0] == '1'){
                unionFind2.union(i-1,0,i,0);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == '1'){
                    if (board[i][j-1] == '1'){
                        unionFind2.union(i,j,i,j-1);
                    }
                    if (board[i-1][j] == '1'){
                        unionFind2.union(i,j,i-1,j);
                    }
                }
            }
        }
        return unionFind2.sets;

    }

    public static class UnionFind2 {
        public int[] parent;//父节点
        public int[] size;//代表节点大小
        public int[] help;//辅助数组 压缩路径
        public int col;//列数
        public int sets;//集合数

        //使用一维数组 表示位置

        /**
         *      0 1 2（row = 3）
         * 0    1 2 3
         * 1    4 5 6
         * 2    7 8 9
         * (col = 3)
         * 一个3X4的矩阵 用一维数组表示行和列
         * 1： 就用0（0x3+0）表示（0，0）
         * 2： 就用1（0x3+1）表示（0，1）
         * 3： 就用2（0x3+2）表示（0，2）
         * 4： 就用3（1x3+0）表示（1，0）
         * 5： 就用4（1x3+1）表示（1，1）
         * (行，列) -> 对应index = 行号 * 列数 + 列号
         * (r,c) -> i  r * col + c
         *
         */
        public UnionFind2(char[][] board){
            col = board[0].length;
            sets = 0;
            int row = board.length;//表示行数
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (board[r][c] == '1') {
                        int i = index(r, c);
                        parent[i] = i;
                        size[i] = 1;
                        sets++;
                    }
                }
            }
        }
        // (r,c) -> i
        private int index(int r, int c) {
            return r * col + c;
        }

        //找到代表节点 并压缩路径
        public int find(int i){
            int hi = 0;
            while (i != parent[i]){
                help[hi++] = i;
                i = parent[i];
            }
            for ( hi--; hi >= 0 ; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        //合并节点(r1,c1)和(r2,c2)
        public void union(int r1, int c1, int r2, int c2) {
            //转换坐标
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            //找到节点对应的代表节点
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }

    }
}
