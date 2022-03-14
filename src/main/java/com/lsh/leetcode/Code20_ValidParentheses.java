package com.lsh.leetcode;

import java.util.HashMap;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/14 9:10 上午
 * @desc ：TODO 20. Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/
 * Example 1:
 *
 * Input: s = "()"
 * Output: true
 * Example 2:
 *
 * Input: s = "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: s = "(]"
 * Output: false
 */
public class Code20_ValidParentheses {
    public static void main(String[] args) {

    }

    public boolean isValid(String s) {
        boolean bool = false;
        HashMap<Character, Character> hashMap = new HashMap<>();
        hashMap.put('(',')');
        hashMap.put('[',']');
        hashMap.put('{','}');

        int p1 = 0;
        int p2 = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);


        }
        return false;
    }
}
