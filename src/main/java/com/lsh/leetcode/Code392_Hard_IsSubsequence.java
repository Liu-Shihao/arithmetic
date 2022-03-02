package com.lsh.leetcode;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/2 9:18 上午
 * @desc ：392. Is Subsequence
 * https://leetcode.com/problems/is-subsequence/
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
