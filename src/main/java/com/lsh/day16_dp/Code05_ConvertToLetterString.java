package com.lsh.day16_dp;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/11 2:54 下午
 * @desc ：从暴力递归到动态规划问题：字符串转换问题（数字->字母）
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class Code05_ConvertToLetterString {

    public static void main(String[] args) {
        System.out.println(number("926411738163158120"));
        System.out.println(dp("926411738163158120"));
    }

    public static int number(String str){
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process1(str.toCharArray(),0);

    }

    /**
     * 表示str[0..index] 已经处理过
     * 剩下str[index...] 带转换
     * @param str
     * @param index
     * @return
     */
    private static int process1(char[] str, int index) {
        if (index == str.length) {
            return 1;//到达最后位置，算一种情况
        }
        if (str[index] == '0'){
            return 0;//说明之前的决定有问题，因为1对应A，没有字符对应0，说明0和前一位数字不能才开
        }
        //说明str[index] != '0'
        //可能性一：将该位置分开
        int ways = process1(str,index+1);
        //可能性二：将该位置与下一位置合并在一起
        //下一位置没有超出边界，并且两个字符组成的两位数不能查过27（乘10表示十位数字，后一位表示各位数字）
        if (index+1 < str.length && (str[index] - '0')*10+(str[index+1] - '0')<27){
            ways += process1(str,index+2);
        }
        return ways;
    }


    // 动态规划 升级优化
    //只有一个index变量 所以是个一维数组
    public static int dp(String s){
        if (s == null || s.length() == 0){
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N+1];
        //从右往左 填充数组
        dp[N] = 1;
        for (int index = N-1; index >= 0; index--) {
            if (str[index] != '0'){
                int ways = dp[index+1];
                if (index+1 < str.length && (str[index] - '0')*10+(str[index+1] - '0')<27){
                    ways += dp[index+2];
                }
                dp[index] = ways;
            }
        }
        return dp[0];

    }
}
