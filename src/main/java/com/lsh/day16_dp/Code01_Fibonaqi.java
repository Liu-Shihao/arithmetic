package com.lsh.day16_dp;

import java.util.HashMap;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/9 1:38 下午
 * @desc ：斐波那契数列问题
 *
 * f(n) = f(n-1)+f(n-2)
 */
public class Code01_Fibonaqi {

    public static void main(String[] args) {

        System.out.println(f1(9));
        System.out.println(f2(9));

    }
    //递归方法
    public static int f1(int n){
        if (n == 1 || n == 2){
            return 1;
        }
        return  f1(n-1)+ f1(n-2);
    }

    public static int f2(int n){
        HashMap<Integer, Integer> map = new HashMap<>();
        return dp(n,map);
    }

    //动态规划
    public static int dp(int n, HashMap<Integer,Integer> map){
        if (map.containsKey(n)){
            return map.get(n);
        }else {
            if (n == 1|| n == 2){
                return 1;
            }
            int ans = f1(n-1)+ f1(n-2);
            map.put(n,ans);
            return ans;
        }

    }

}
