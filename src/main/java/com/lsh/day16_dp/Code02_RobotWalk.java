package com.lsh.day16_dp;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/9 5:07 下午
 * @desc ：从暴力递归到动态规划
 * 机器人走路问题
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M（start）、K、P（aim），返回方法数。
 */
public class Code02_RobotWalk {

    public static void main(String[] args) {
        System.out.println(ways1(2,4,5,6));
        System.out.println(ways2(2,4,5,6));
        System.out.println(ways3(2,4,5,6));
    }

    /**
     * 方法1 ：暴力递归方式
     * @param start 起点位置
     * @param aim 目的地
     * @param N 一共N个节点
     * @param K 要求K步完成
     * @return
     */
    public static int ways1(int start,int aim,int N, int K){
        return process1(start,aim,K,N);
    }

    /**
     *
     * @param cur 当前位置
     * @param aim 目标节点
     * @param rest 剩余步数
     * @param N 几个节点
     */
    private static int process1(int cur,int aim,int rest,int N){
        if (rest == 0){
            //剩余步数为0 则结束，此时如果当前位置处于目标位置上 则是一种可能性 否则不是
            return cur == aim ? 1 : 0;
        }
        if (cur == 1){
            return process1(2,aim,rest-1,N);
        }
        if (cur == N){
            return process1(N-1,aim,rest-1,N);
        }
        return process1(cur +1,aim,rest-1,N) + process1(cur - 1,aim,rest-1,N);

    }

    /**
     * 升级方法：加上缓存
     * @param start 开始位置
     * @param aim   目标位置
     * @param N     节点个数
     * @param K     目标步数
     * @return
     */
    public static int ways2(int start,int aim,int N, int K){
        //根据剩余位置和剩余步数 创建二维数组 dp[cur][rest]
        //cur(当前位置) 范围为 1-N
        //rest(剩余步数)范围为 0-K
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;//初始化缓存
            }
        }
        return process2(start,aim,K,N,dp);
    }


    /**
     * 优化process1 ：加上缓存
     * @param cur 当前位置 范围：1~N
     * @param aim 目标位置
     * @param rest 剩余步数 范围 0~K
     * @param N   节点个数
     * @return
     */
    private static int process2(int cur,int aim,int rest,int N,int[][] dp){
        if (dp[cur][rest] != -1){
            return dp[cur][rest];//缓存命中
        }
        //没有缓存
        int ans = 0;
        if (rest == 0){
            //剩余步数为0 则结束，此时如果当前位置处于目标位置上 则是一种可能性 否则不是
            ans =  cur == aim ? 1 : 0;

        }else if (cur == 1){
            ans = process2(2,aim,rest-1,N,dp);
        }else if (cur == N){
            ans = process2(N-1,aim,rest-1,N,dp);
        }else {
            ans =process2(cur + 1,aim,rest-1,N,dp) + process2(cur - 1,aim,rest-1,N,dp);
        }
        dp[cur][rest] = ans;//添加缓存
        return ans;
    }

    /**
     * 代码升级 最终版本
     * @param start
     * @param aim
     * @param N
     * @param K
     * @return
     */
    public static int ways3(int start,int aim,int N, int K){
        //边界过滤
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        //[cur][rest] 建立 rest(剩余步数)为行  cur（当前位置）为列 的矩阵表
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;//即第一列（rest=0）剩余步数为0，在此列上，只有aim目标位置为1 ，其他位置为0（补全第一列数据）
        //在第一行的数据，依赖(2,rest-1)的数据(左下)
        //在最后一行的数据，依赖(N-1,rest-1)的数据（左上）
        //在中间行的数据依赖(cur+1,rest-1)+(cur-1,rest-1)
        //从左往右，一列一列的填充数据

        for (int rest = 1; rest < K+1; rest++) {
            dp[1][rest] = dp[2][rest-1];//设置第一行数据
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur+1][rest-1]+dp[cur-1][rest-1];//通过for循环设置中间行数据
            }
            dp[N][rest] = dp[N-1][rest-1];//设置最后一行数据
        }
        return dp[start][K];
    }





}