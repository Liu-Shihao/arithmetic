package com.lsh.leetcode;

import java.util.HashMap;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/11 9:20 下午
 * @desc ：691. Stickers to Spell Word
 * https://leetcode.com/problems/stickers-to-spell-word/
 */
public class Code691_Stickers2SpellWord {
    public static int minStickers1(String[] stickers,String target){
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE  ? -1: ans;
    }

    /**
     *
     * @param stickers 字符串数组
     * @param target   目标字符串
     * @return 返回拼成能够拼成目标字符串的最少贴纸数
     */
    private static int process1(String[] stickers, String target) {
        if (target.length() == 0){
            return 0;//如果目标字符串已经没有了，则不需要贴纸了，所以返回0
        }
        int min = Integer.MAX_VALUE;
        for (String first : stickers) {
            String resp = minus(target,first);
            if (resp.length() != target.length()) {//如果这张贴纸有包含目标字符串的字符则继续
                min = Math.min(min,process1(stickers,resp));//
            }
        }
        //如果min为最大值，则+0 如果不为最大值则+1
        return min+(min == Integer.MAX_VALUE ? 0:1);
    }

    //返回从target目标字符串减去first贴纸中包含的，剩余目标字符
    private static String minus(String target, String first) {
        char[] tArr = target.toCharArray();
        char[] fArr = first.toCharArray();
        int[] count = new int[26];
        for (char c : tArr) {
            count[c-'a']++;//统计target目标字符串中的字符出现次数
        }
        for (char c : fArr) {
            count[c-'a']--;//用贴纸中的字符减去
        }
        StringBuilder ans= new StringBuilder();
        for (int i = 0; i < count.length; i++) {//查看剩下的字符
            if (count[i]>0){
                for (int j = 0; j < count[i]; j++) {
                    ans.append((char) (i+'a'));
                }
            }
        }
        return ans.toString();
    }
    //升级优化3：加缓存
    public static int minStickers3(String[] stickers,String target){
        if (stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return -1;
        }
        int N = stickers.length;
        int[][] counts = new int[N][26];//用字母频次表示每张贴纸
        for (int i = 0; i < stickers.length; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char c : chars) {
                counts[i][c-'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = process3(counts,target,dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;


    }

    private static int process3(int[][] stickers, String t, HashMap<String, Integer> dp) {
        if (dp.containsKey(t)) {
            return dp.get(t);
        }
        char[] tArr = t.toCharArray();
        int[] tCount = new int[26];
        for (char c : tArr) {
            tCount[c -'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] sticker = stickers[i];
            if (sticker[tArr[0]-'a'] > 0){
                StringBuffer stringBuffer = new StringBuffer();
                for (int j = 0; j < tCount.length; j++) {
                    int k = tCount[j] - sticker[j];
                    if (k > 0) {
                        for (int l = 0; l < k; l++) {
                            stringBuffer.append((char) (j+'a'));
                        }
                    }
                }
                String rest = stringBuffer.toString();
                min = Math.min(min,process3(stickers,rest,dp));
            }
        }
        int ans = min+(min == Integer.MAX_VALUE ? 0:1 );
        dp.put(t,ans);
        return ans;
    }
}
