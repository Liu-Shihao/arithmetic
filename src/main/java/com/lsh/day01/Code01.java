package com.lsh.day01;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/1/26 1:37 下午
 * @desc ：算法第一天 ：  位进制运算
 * 1.打印二进制
 */
public class Code01 {

    /**
     * int 32位，所以 int可以表示的范围为：0 ~ 2^32-1，
     * 但是在Java中int最大值为2147483647 转换为2进制：01111111111111111111111111111111
     * 无符号范围  0 ~ 2^32-1
     * 在Java中是有符号的，即： - 2^31  ~ 2^31-1
     *
     */
    public static void main(String[] args) {
        int n = 63;
        num(n);
        //一个数字左移，右边拿0来补; 一个数字左移一位就表示x2, 右移一位则表示➗2；
//        System.out.println(Integer.MAX_VALUE);
//        num(Integer.MAX_VALUE);
//        num(n);
//        ~ 取反符号  取反后二进制 0变1，1变0  ，十进制-1
        // 正数的带符号右移和不带符号右移是一样的，
        // 负数的带符号右移和不带符号右移是不一样的：
        // 负数带符号右移：用符号位补位
        // 负数的不带符号右移：用0补位
        // >> 带符号右移   ；>>> 不带符号右移
        //相反数 a = 5  相反数可以写：-a  ；还可以写：(~a +1)即：  N = ~N +1
        // 最小数和0 取反后还是他自己
        // 因为最小数的二进制为10000000000000000000000000000000，取反后为：01111111111111111111111111111111 ，然后+1，每位进1，最后又变成了10000000000000000000000000000000
        //包括0取反后还是他自己：00000000000000000000000000000000 取反后为：11111111111111111111111111111111，然后+1 溢出，最后为00000000000000000000000000000000
//        int a = 0;
//        num(a);
//        num(a);
//        num(~a);
//        num(-a);
//        System.out.println(a);
//        System.out.println(-a);
    }

    /**
     * 输入整数打印32位二进制形式
     * @param num
     */
    public static void num(int num){
        for (int i = 31; i >= 0; i--) {
            //Java中 & 叫做 按位与 ，&&叫做短路与，
            // & 既是位运算符又是逻辑运算符，&的两侧可以是int，也可以是boolean表达式，当&两侧是int时，要先把运算符两侧的数转化为二进制数再进行运算，而短路与（&&）的两侧要求必须是布尔表达式。
            // & 两位都为1才是1，否则为0 即：num的32位每一位和1比，同为1 则打印1，不同则为0；
            System.out.print((num &(1 << i)) == 0 ? "0":"1");
        }
        System.out.println();

    }



}
