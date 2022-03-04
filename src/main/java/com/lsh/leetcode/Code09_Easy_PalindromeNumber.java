package com.lsh.leetcode;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/4 2:12 下午
 * @desc ：9. Palindrome Number
 * https://leetcode.com/problems/palindrome-number/
 * 给定一个整数x，如果x是回文整数则返回true。
 *
 * 当一个整数向后读和向前读相同时，它就是回文。
 *
 * 例如，121是回文，而123不是。
 * 反转
 */
public class Code09_Easy_PalindromeNumber {

    public boolean isPalindrome(int x) {
        if (x < 0 ) return false;//负数一定不是回文数，直接返回false
        if (x >= 0 && x < 10) return true;//10以内的个位数一定是回文数，返回true
        //如果反转后的数字与原数字相同返回true，否则返回false；并且需要考虑边界情况 1234567899 反转 后越界
        int ans = x;
        int res = 0;
        while (x != 0){
            //对原数字依次取出末尾数 ： 原数字对10取模
            int tail = x % 10;
            x /= 10;
            if (res > Integer.MAX_VALUE/10){
                return false;
            }
            res = res * 10 + tail;
        }
        return ans == res;
    }
}
