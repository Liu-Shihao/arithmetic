package com.lsh.day16_dp;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/12 9:31 上午
 * @desc ：动态规划问题：最长公共子序列问题
 * https://leetcode.com/problems/longest-common-subsequence/
 * 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 *
 * 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 *
 * 样本对照模型
 */
public class Code07_LongestCommonSubsequence {
    //暴力递归（超时）
    public int longestCommonSubsequence1(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0){
            return 0;
        }
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        return process1(str1,str2,str1.length-1,str2.length-1);

    }
    //返回str1[0..i] 和str2[0..j]位置上的最长公共子序列
    //
    private int process1(char[] str1, char[] str2, int i, int j) {
        if (i==0 && j==0){
            //1. 两个数组都只剩下一个字符，判断两个字符是否相等
            return str1[i] == str2[j] ? 1:0;
        } else if (i == 0){
            //2. 其中一个数组只剩下一个字符
            if (str1[i] == str2[j]){
                //如果相等，则直接返回1
                return 1;
            }else {
                //如果不相等，则将str1[i] 和 str2[j-1]比较
                return process1(str1,str2,i,j-1);
            }
        }else if (j ==0){
            //3. 与情况2相同
            if (str1[i] == str2[j]){
                return 1;
            }else {
                return process1(str1,str2,i-1,j);
            }
        }else {
            //4. 两个数组都剩下不止一个字符，i != 0 j != 0
            int p1 =process1(str1,str2,i,j-1);
            int p2 =process1(str1,str2,i-1,j);
            int p3 = str1[i] == str2[j] ? 1+process1(str1,str2,i-1,j-1):0;
            return Math.max(p1,Math.max(p2,p3));
        }
    }
}
