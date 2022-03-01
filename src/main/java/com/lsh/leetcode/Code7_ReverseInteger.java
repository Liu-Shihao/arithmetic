package com.lsh.leetcode;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/1 3:52 下午
 * @desc ：7. Reverse Integer
 * https://leetcode.com/problems/reverse-integer/
 * 给定一个带符号的32位整数x，返回x并反转其数字。如果反转x值超出有符号32位整数范围[-231,231 - 1]，则返回0。
 * 假设环境不允许存储64位整数(有符号的或无符号的)。
 * Input: x = 123
 * Output: 321
 *
 * Input: x = -123
 * Output: -321
 *
 * Input: x = 120
 * Output: 21
 */
public class Code7_ReverseInteger {

    public int reverse(int x) {
        int result = 0;
        while (x != 0){
            //通过取模得到最后一位数
            int tail = x % 10;
            // newResult乘10 表示向左移动一位，此时将最后一位拼接上
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result){
                return 0;
            }
            result = newResult;
            //X 除以 10  整体向左移一位  ： 8372 --> 837
            x /= 10;
        }
        return result;
    }
}
