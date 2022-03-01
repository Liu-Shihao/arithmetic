package com.lsh.leetcode;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/1 1:48 下午
 * @desc ：5. Longest Palindromic Substring
 * @link ：https://leetcode.com/problems/longest-palindromic-substring/
 * 给定一个字符串s，返回s中最长的回文子字符串。
 * "babad"  -> bab
 * "a"      -> a
 * "ac"     -> a
 * "ccc"    -> ccc
 * "aacabdkacaa" -> "aca"   aacabdkacaa
 *      i i+1
 *  0 1 2 3 4 5  偶数
 *  0 1 2 3 4    奇数
 *      i
 */
public class Code05_Hard_LongestPalindromicSubstring {
    public static void main(String[] args) {

    }
    //标记起止位置，最大长度
    int index, maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2){
            return s;
        }

        for (int i = 0; i < len-1; i++) {
            //假设奇数长度，尽量延长回文
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            //假设长度均匀。
            extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(index, index + maxLen);
    }

    /**
     * 扩展回文
     * @param s 目标字符串
     * @param L 回文左边界
     * @param R 回文右边界
     */
    private void extendPalindrome(String s, int L, int R) { 
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        if (maxLen < R - L - 1) {
            index = L + 1;
            maxLen = R - L - 1;
        }
    }

    }
