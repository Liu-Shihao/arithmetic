package com.lsh.day13_union;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/4 11:12 下午
 * @desc ：
 * leetcode付费题目：https://leetcode.com/problems/number-of-islands-ii/
 * 参数 int r,int c,int[][] grid
 * 给出一个rxc的矩阵
 * 0 0 0 0
 * 0 0 0 0
 * 0 0 0 0
 * 然后根据给出的二维数组进行动态标记
 * [[0,1],[1,1],[,0,2],[3,2]]
 * 0 1 0 0            0 1 0 0            0 1 1 0             0 1 1 0
 * 0 0 0 0            0 1 0 0            0 1 0 0             0 1 0 0
 * 0 0 0 0 (1)        0 0 0 0  (1)       0 0 0 0 (1)         0 0 0 1 (2)
 * 依次返回岛的个数
 * 所以最后返回[1,1,1,2]
 *
 */
public class Code04_NumberOfIslandsII {

    /**
     *
     * @param r 行数
     * @param c 列数
     * @param positions 二维数组 :包含坐标位置
     * @return
     */
    public static List<Integer> numIslands2(int r, int c, int[][] positions){
        UnionFind unionFind = new UnionFind(r, c);
        ArrayList<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(unionFind.connect(position[0],position[1]));
        }
        return ans;
    }

    public  static class UnionFind{
        public int[] parents;
        public int[] size;
        public int[] help;
        public int sets;
        public int col;//列数
        public int row;//列数

        //初始化
        private UnionFind(int m,int n){
            row = m;
            col = n;
            sets = 0;
            int len = row * col;
            parents = new int[len];
            size = new int[len];
            help = new int[len];
        }

        private int index(int r,int c){
            return r * col + c;
        }

        private int find(int i){
            int hi = 0;
            while (parents[i] != i){
                help[hi++] = i;
                i = parents[i];
            }
            for ( hi--; hi >= 0; hi--) {
                parents[help[hi]] = i;
            }
            return i;
        }

        //合并方法  如果两个节点相邻就合并为一个集合
        private void union(int r1,int c1,int r2,int c2){
            //越界判断
            if (c1 < 0 || c1 >= row || c2 < 0 || c2 >= col || r1 < 0 || r1 >= row || r2 < 0 || r2 >= row) {
                return;
            }
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            if (size[i1] == 0 || size[i2] == 0) {
                //如果该节点的范围大小为0 说明并没有坐标插入，直接返回
                return;
            }
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1!=f2){
                if (size[f1]> size[f2]){
                    size[f1] += size[f2];
                    parents[f2] = f1;
                }else {
                    size[f2] += size[f1];
                    parents[f1] = f2;
                }
                sets--;
            }
        }

        //此方法是为了进行动态初始化，插入新的坐标节点
        private int connect(int r,int c){
            int index = index(r,c);
            //表示此坐标节点为第一次插入（如果此节点为重复插入则直接返回）
            if (size[index] == 0){
                //将该节点进行初始化
                parents[index] = index;
                size[index] = 1;
                sets++;
                //将该节点与周围节点进行合并
                union(r,c,r-1,c);//上
                union(r,c,r+1,c);//下
                union(r,c,r,c-1);//左
                union(r,c,r,c+1);//右
            }
            return sets;
        }
    }



    //并查集第二种解法:如果m*n比较大，会经历很重的初始化，而k比较小，怎么优化的方法
    public static List<Integer> numIslands22(int r, int c, int[][] positions){
        ArrayList<Integer> ans = new ArrayList<>();
        UnionFind2 unionFind2 = new UnionFind2();
        for (int[] position : positions) {
            ans.add(unionFind2.connect(position[0],position[1]));
        }
        return ans;
    }

    //如果m*n比较大，会经历很重的初始化，而k比较小，怎么优化的方法
    public static class UnionFind2{
        private HashMap<String,String> parents;
        private HashMap<String,Integer> size;
        private ArrayList<String> help;
        private int sets;


        public UnionFind2(){
            parents = new HashMap<>();
            size = new HashMap<>();
            help = new ArrayList<>();
            sets = 0;
        }

        private String find(String cur){
            while (!cur.equals(parents.get(cur))){
                help.add(cur);
                cur = parents.get(cur);
            }
            for (String str : help) {
                parents.put(str,cur);
            }
            help.clear();
            return cur;
        }

        private void union(String s1, String s2) {
            //注意：如果parents中没有包含则说明没有坐标插入过，则直接返回，（不肯能相邻）
            if (parents.containsKey(s1) && parents.containsKey(s2)) {
                String f1 = find(s1);
                String f2 = find(s2);
                if (!f1.equals(f2)) {
                    int size1 = size.get(f1);
                    int size2 = size.get(f2);
                    String big = size1 >= size2 ? f1 : f2;
                    String small = big == f1 ? f2 : f1;
                    parents.put(small, big);
                    size.put(big, size1 + size2);
                    sets--;
                }
            }
        }
        public int connect(int r, int c) {
            String key = r + "_" + c;
            if (!parents.containsKey(key)) {
                parents.put(key, key);
                size.put(key, 1);
                sets++;
                String up = (r - 1) + "_" + c;
                String down = (r + 1) + "_" + c;
                String left = r + "_" + (c - 1);
                String right = r + "_" + (c + 1);
                union(up, key);
                union(down, key);
                union(left, key);
                union(right, key);
            }
            return sets;
        }


    }




}
