package com.lsh.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/28 9:50 上午
 * @desc ：3. Longest Substring Without Repeating Characters
 * @link ：https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Given a string s, find the length of the longest substring without repeating characters.
 * 给定一个字符串s，找出不重复字符的最长子字符串的长度。
 */
public class Code03_Hard_LengthOfLongestSubstring {

    public static void main(String[] args) {
        String s = "abcabcbb";
        int i = lengthOfLongestSubstring(s);
    }

    /**
     * 滑动窗口
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        //chars字符数组：用来标识每个字符出现的索引位置
        Integer[] chars = new Integer[128];
        //滑动窗口 左边界
        int left = 0;
        //滑动窗口 右边界
        int right = 0;
        //包含子串的长度
        int res = 0;
        while (right < s.length()) {
            //遍历字符串的每一位字符
            char r = s.charAt(right);
            Integer index = chars[r];
            System.out.println(r+":"+index);
            //判断该字符是否重复出现过
            if (index != null && index >= left && index < right) {
                //当前的元素已经是重复出现了，并且当前元素上一次出现的位置在滑动窗口的内部
                //将重置滑动窗口的起点
                left = index + 1;
            }
            res = Math.max(res, right - left + 1);
            //更新该字符最新的索引位置
            chars[r] = right;
            //向下跳一位
            right++;
        }

        return res;
    }

    /**
     * 借助哈希表
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int ans = 0;
        //新建哈希表：字符为Key，索引位置为Value
        Map<Character, Integer> map = new HashMap<>();
        //遍历每一位字符
        for (int j = 0, i = 0; j < n; j++) {
            //判断该字符是否重复出现
            if (map.containsKey(s.charAt(j))) {
                //i 表示该字符上一次出现的位置
                i = Math.max(map.get(s.charAt(j)), i);
            }
            //计算没有重复子串的长度 j为当前该位置
            ans = Math.max(ans, j - i + 1);
            //更新该字符的最新位置
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }




}
