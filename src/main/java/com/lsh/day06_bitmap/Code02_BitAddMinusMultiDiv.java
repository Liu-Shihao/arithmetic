package com.lsh.day06_bitmap;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/8 9:51 上午
 * @desc ： 加减乘除
 */
public class Code02_BitAddMinusMultiDiv {

    /**
     * 相加
     * @param a
     * @param b
     * 亦或 表示无进位相加；与表示进位信息，左移一位表示进位表示的值，两者相加即为和
     * 需要相加到没有进位信息为止。
     * @return
     */
    public static int add(int a ,int b){
        int sum = a;
        while (b != 0){
            //表示无进位相加（相同为0，不同为1）
            sum = a ^ b;
            //表示进位信息
            b = (a & b)<<1;
            a = sum;
        }
        return sum;
    }

    /**
     * n的相反数 = 取反加一 ,即~n+1
     * @param n
     * @return
     */
    public static int negNum(int n){
        return add((~n),1);
    }

    /**
     * 相减
     * a-b 即 a+(b的相反数)  a+（-b）
     * @param a
     * @param b
     * @return
     */
    public static int minus(int a ,int b){
        return add(a,negNum(b));
    }

    /**
     * 相乘
     * @param a
     * @param b
     * @return
     */
    public static int multi(int a,int b){
        int res = 0;
        while (b != 0){
            if ((b&1) != 0){
                res = add(res,a);
                a <<= 1;
                b >>>= 1;
            }
        }
        return res;
    }

    /**
     * 是否是负数
     * @param n
     * @return
     */
    public static boolean isNeg(int n) {
        return n < 0;
    }

    /**
     * 除法
     * @param a
     * @param b
     * @return
     */
    public static int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    /**
     * 系统最小值没有绝对值。
     * 涉及系统最小值 除法
     * @param a
     * @param b
     * @return
     */
    public static int divide(int a, int b) {
        //如果a和b都是系统最小值，则返回1
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            //如果b为系统最小值，则返回0
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            //如果a为系统最小值
            if (b == negNum(1)) {
                //如果b为-1，则返回系统最大值。
                return Integer.MAX_VALUE;
            } else {
                int c = div(add(a, 1), b);
                return add(c, div(minus(a, multi(c, b)), b));
            }
        } else {
            //a和b都不为系统最小值，则正常计算
            return div(a, b);
        }
    }

    public static void main(String[] args) {
        System.out.println(add(394,294));
        System.out.println(minus(394,294));
        System.out.println(negNum(294));
    }


}
