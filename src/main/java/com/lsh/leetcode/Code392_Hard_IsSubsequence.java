package com.lsh.leetcode;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/2 9:18 上午
 * @desc ：392. Is Subsequence
 * https://leetcode.com/problems/is-subsequence/
 * 给定两个字符串s和t，如果s是t的子序列则返回true，否则返回false。
 * 一个字符串的子序列是一个新字符串，它是通过删除一些字符(可以不删除)而形成的，而不影响其余字符的相对位置。(例如，“ace”是“abcde”的子序列，而“aec”不是)。
 */
public class Code392_Hard_IsSubsequence {

    //判断s是否是t的子字符
    public static boolean isSubsequence(String s, String t) {
        if (s.length() > t.length())return false;
        //定义标记位
        int pre = 0;
        for (int i = 0; i < s.length(); i++) {
            //indexOf(int ch, int fromIndex)
            //返回从pre位置之后的字符中的此字符位置，并更新标记位
            pre = t.indexOf(s.charAt(i),pre);
            if (pre == -1) return false;
            //存在次字符，标记位下移一位
            pre++;
        }
        return true;
    }
}
