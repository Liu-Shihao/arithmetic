package com.lsh.day01;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/1/26 4:29 下午
 * @desc ：给一个N 返回 1！+2！+……N！的结果
 * 算法一:算出每个数的阶乘，然后在相加
 *
 * 算法二：算出一个数的阶乘，然后累乘相加
 */
public class Code02 {

    public static void main(String[] args) {
        System.out.println(f1(10));
        System.out.println(f2(10));
    }

    /**
     * 将每个数的阶乘累加
     * @param n
     * @return
     */
    public static long f1(long  n){
        long sum = 0L;
        for (int i = 1; i < n+1; i++) {
            sum +=factorial(i);
        }
        return sum;

    }

    /**
     * 求这个数的阶乘
     * @param n
     * @return
     */
    public static long factorial(long n){
        long ans = 1L;
        for (int i = 1; i < n+1 ; i++) {
            //ans = ans * i <==> ans *= i
            ans *= i;
        }
        return ans;
    }

    /**
     * 复用前一个数的阶乘 累加
     * @param n
     * @return
     */
    public static long f2(long  n){
        long sum = 0L;
        long temp = 1L;
        for (int i = 1; i < n+1; i++) {
            temp *= i;
            sum += temp;
        }
        return sum;
    }
}
