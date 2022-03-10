package com.lsh.leetcode;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/10 10:40 下午
 * @desc ：14. Longest Common Prefix
 * https://leetcode.com/problems/longest-common-prefix/
 * 编写一个函数，在字符串数组中找出最长的公共前缀字符串。
 * 如果没有公共前缀，则返回空字符串""。
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 *
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 */
public class Code14_LongestCommonPrefix {
    public static void main(String[] args) {
//        String[] strs = {"flower","flow","flight"};
        String[] strs = {"dog","racecar","car"};
        System.out.println(longestCommonPrefix2(strs));

    }

    /**
     * 别人方法
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0)    return "";
        String pre = strs[0];
        int i = 1;
        while(i < strs.length){
            while(strs[i].indexOf(pre) != 0)
                pre = pre.substring(0,pre.length()-1);
            i++;
        }
        return pre;
    }

    /**
     * 自己想出方法
     * @param strs
     * @return
     */
    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0 || "".equals(strs[0])) return "";
        String ans = "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if ((i==strs[j].length())||("".equals(strs[j]))||(c != strs[j].charAt(i))) return ans;
            }
            ans += c;
        }
        return ans;
    }
}
