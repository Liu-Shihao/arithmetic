package com.lsh.day12_greed;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/1 1:46 下午
 * @desc ：贪心算法 解字符串字典序问题
 * 给定一个由字符串组成的数组strs，
 * 必须把所有的字符串拼接起来，
 * 返回所有可能的拼接结果中，字典序最小的结果
 *
 */
public class Code01_LowestLexicography {

    public static void main(String[] args) {
        String[] strs = new String[]{"abc","cks","bct"};//aabaabb
//        System.out.println(lowestString2(strs));
        System.out.println(lowestString1(strs));
    }

    //使用暴力方法解
    public static String lowestString1(String[] strs){
        if (strs == null || strs.length == 0) {
            return null;
        }
        TreeSet<String> treeSet = process(strs);
//        Iterator<String> iterator = treeSet.iterator();
//        while (iterator.hasNext()) {
//            System.out.println("next:"+iterator.next());
//        }
        return treeSet.first();
    }

    /**
     * 递归全排列出所有情况 ["abc","cks","bct"]
     * 第一次循环： abc
     * 第一次循环递归内的循环产生两个结果：cks + bct  /  bct + cks
     * 最后第一次循环查产生两个结果 abcbctcks 和 abccksbct
     * 同样 第二次循环 cks 产生两个结果 cksabcbct 和 cksbctabc
     * 最后 第三次循环 bct 产生两个结果 bctabccks 和 bctcksabc
     * TreeSet按照字典序进行排列最后返回字符串全排列情况
     *
     * @param strs
     * @return
     */
    public static TreeSet<String> process(String[] strs){
        TreeSet<String> ans = new TreeSet<>();
        if (strs.length == 0) {
            ans.add("");
            return ans;
        }
        for (int i = 0; i < strs.length; i++) {
            String first = strs[i];
            String[] nexts = removeIndexString(strs, i);
            TreeSet<String> next = process(nexts);
            for (String cur : next) {
                ans.add(first + cur);
            }
        }
        return ans;
    }

    // {"abc", "cks", "bct"}
    // 0 1 2
    // removeIndexString(arr , 1) -> {"abc", "bct"}
    public static String[] removeIndexString(String[] arr, int index) {
        int N = arr.length;
        String[] ans = new String[N - 1];
        int ansIndex = 0;
        for (int i = 0; i < N; i++) {
            if (i != index) {
                ans[ansIndex++] = arr[i];
            }
        }
        return ans;
    }


    public static class MyComparator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return (o1+o2).compareTo(o2+o1);
        }
    }
    //使用贪心算法解
    public static String lowestString2(String[] strs){
        if (strs == null || strs.length == 0) {
            return null;
        }
        Arrays.sort(strs,new MyComparator());
        String res = "";
        for (String str : strs) {
            res +=str;
        }
        return res;
    }

}
