package com.lsh.leetcode;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/4 5:38 下午
 * @desc ：并查集题目
 * leetcode原题：https://leetcode.com/problems/number-of-provinces/
 * 有n个城市。其中一些是连接的，而另一些则不是。如果a市与b市直接相连，b市与c市直接相连，则a市与c市间接相连。
 * 一个省是由直接或间接相连的城市组成的一组，除省外没有其他城市。
 * 如果第i个城市和第j个城市是直接相连的，则isConnected[i][j] = 1，否则isConnected[i][j] = 0。
 * 返回省的总数。
 * (注意：如果isConnected[i][j] = 1，则isConnected[j][i] = 1)
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 *
 *
 *
 */
public class Code574_NumberOfProvinces {

    public int findCircleNum(int[][] isConnected) {
        int N =  isConnected.length;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (isConnected[i][j] == 1){//i 和 j互相认识
                    unionFind.union(i,j);//合并i 和 j （包括其子集）
                }
            }
        }
        return unionFind.sets;
    }


    public static class UnionFind{
        //parents[i] = K : i节点的父节点是K
        public int[] parents;
        //每个代表节点的size
        public int[] size;
        //
        public int[] help;
        //一共多少个集合
        public int sets;

        public UnionFind(int N){
            parents = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }

        //从i开始一直往上到不能在往上，返回代表节点
        //help 代替堆结构，收集父路径上节点，最后进行路径压缩
        public int find(int i){
            int hi = 0;
            while (i != parents[i]){
                //说明i的父节点不是他自己，往上找
                help[hi++] = i;
                i = parents[i];
            }
            //由于上一步hi++，所以此时需要先进行hi--
            for (hi--; hi >= 0  ; hi--) {
                parents[help[hi]] = i;
            }
            return i;
        }

        public void union(int i,int j){
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2){
                if (size[f1] > size[f2]){
                    size[f1] += size[f2];
                    parents[f2] = f1;
                }else {
                    size[f2] += size[f1];
                    parents[f1] = f2;
                }
                sets-- ;
            }
        }


    }

}
