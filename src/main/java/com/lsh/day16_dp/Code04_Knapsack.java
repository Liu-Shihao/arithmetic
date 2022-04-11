package com.lsh.day16_dp;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/21 8:14 下午
 * @desc ：从暴力破解到动态规划：经典背包问题
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 */
public class Code04_Knapsack {

    public static void main(String[] args) {
        int[] weight = {2,4,1,6,8,4,3};
        int[] value = {3,8,5,4,2,10,9};
        System.out.println(maxValue1(weight,value,20));
        System.out.println(maxValue2(weight,value,20));
        System.out.println(maxValue3(weight,value,20));

    }

    /**
     *
     * @param weight 货物重量
     * @param value  对应货物价值
     * @param bag    背包容量
     * @return
     */
    public static int maxValue1(int[] weight,int[] value,int bag){
        if (weight == null || value == null || weight.length != value.length) {
            //边界处理 无效参数
            return 0;
        }
        //尝试函数
        return process1(weight,value,0,bag);
    }
    //当前到了index号货物
    //当前所做选择不能超过背包容量，返回最大价值
    //该递归只有两个可变参数 index 和 bag
    private static  int process1(int[] weight,int[] value,int index ,int bag){
        //定义base case
        if (bag < 0){
            //背包没有容量了
            return -1;//表示上一步递归无效，因为已经超出背包容量了
        }
        if (weight.length == index){
            //没有货物了
            return 0;
        }
        //还有货物 并且背包还有空间
        //可能性1： 不要当前的货物
        int p1 = process1(weight,value,index+1,bag);
        //可能性2： 要当前的货物 ，此出可能性需要做判断：因为如果选择了当前货物，但是已经超出了背包容量，则需要将货物吐出来
        int p2 = 0;
        if (-1 != process1(weight,value,index+1,bag-weight[index])){
            p2 = value[index] + process1(weight,value,index+1,bag-weight[index]);
        }
        return Math.max(p1,p2);//返回两种可能性的最大值
    }




    /**
     * 优化升级2：傻缓存
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

    //升级三：动态规划
    public static int maxValue3(int[] w,int[] v,int bag){
        if (w == null || v == null || w.length != v.length) {
            return 0;
        }
        int N = w.length;
        //在递归中只有两个可变参数 index当前货物位置 和 rest剩余背包容量
        //index 范围是0 - N
        //rest 范围是 负 - bag，暂时为0 - N，负数情况从代码中过滤
        int[][] dp = new int[N + 1][bag + 1];
        // 通过base case初始化表：即index=N 的时候 值为0 （默认值即为0）
        //dp[index,rest] 的值 依赖dp[index+1,rest] 和dp[index+1,rest-w[index]]
        //即上一行 依赖下一行的值
        //已经初始化了最后一行，所以从下往上填充表格
        //最终返回dp[0,bag]
        //(index 从n-1行开始，因为第N行初始化为0)
        for (int index = N-1; index >= 0 ; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index+1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1:dp[index+1][rest-w[index]];
                if (next != -1){
                    p2 = v[index]+next;
                }
                dp[index][rest] = Math.max(p1,p2);
            }
        }
        return dp[0][bag];
    }






}
