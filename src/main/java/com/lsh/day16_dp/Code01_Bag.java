package com.lsh.day16_dp;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/21 8:14 下午
 * @desc ：动态规划（DynamicProgramming）与暴力破解：经典背包问题
 * 有两个数组表示货物的重量和价值 （两个数组长度一样），往背包里装货物，在背包装满前价值最大。
 */
public class Code01_Bag {

    public static void main(String[] args) {
        int[] weight = {2,4,1,6,8,4,3};
        int[] value = {3,8,5,4,2,10,9};
        System.out.println(maxValue1(weight,value,20));
        System.out.println(maxValue2(weight,value,20));

    }
    /**
     * 暴力破解-递归
     * @param weight
     * @param value
     * @param baglimit
     * @return
     */
    public  static int maxValue1(int[] weight,int[] value,int baglimit){
        return process1(weight,value,0,baglimit);
    }

    /**
     * 0...n-1  所有货物
     * 0...index-1  已经挑选的货物，不能在挑选
     * index ... n-1 可以挑选的剩余货物
     * @param weight
     * @param value
     * @param index 只能选index之后的货物
     * @param reset 背包还有多少容量
     * @return 返回最大价值
     */
    public static int process1(int[] weight,int[] value,int index,int reset){
        //如果背包剩余容量是负数，说明之前的选择是错误的
        if (reset<0){
            //返回 -1，表示无效解
            return -1;
        }
        //reset >= 0
        if (index==weight.length){
            //代表没货物了
            return 0;
        }
        //既有负重（背包剩余容量），又有货物
        //情况1:当前index位置的货物，没要
        int p1 = process1(weight,value,index+1,reset);
        //情况2:当前index位置的货物，要了
        int p2 = -1;
        int next = process1(weight,value,index+1,reset-weight[index]);
        if (next != -1){
            p2 = value[index] + next;
        }
        return Math.max(p1,p2);
    }

    /**
     * 动态规划优化
     * @param weight   重量数组
     * @param value    价值数组
     * @param baglimit 背包剩余容量
     * @return
     */
    public static int maxValue2(int[] weight,int[] value,int baglimit){
        int n = weight.length;
        //构造一个二维数组，用作缓存表
        int[][] dp = new int[n + 1][baglimit+1];
        // -2 表示没算过
        // -1 表示无效解
        // 正数 表示
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= baglimit; j++) {
                dp[i][j] = -2;
            }
        }
        return process2(weight,value,0,baglimit,dp);
    }

    public static int process2(int[] weight,int[] value,int index,int rest,int[][] dp){
        if (rest<0){
            return -1;
        }
        if (dp[index][rest] != -2){
            return dp[index][rest];
        }
        //缓存未命中
        int ans = 0;
        if (index == weight.length){
            ans = 0;
        }else {
            int p1 = process2(weight,value,index+1,rest,dp);
            //情况2:当前index位置的货物，要了
            int p2 = -1;
            int next = process2(weight,value,index+1,rest-weight[index],dp);
            if (next != -1){
                p2 = value[index] + next;
            }
            ans = Math.max(p1,p2);
        }
        dp[index][rest] = ans;
        return ans;
    }



}
