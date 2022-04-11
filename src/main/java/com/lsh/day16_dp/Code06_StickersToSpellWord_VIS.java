package com.lsh.day16_dp;

import java.util.HashMap;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/11 4:48 下午
 * @desc ：从暴力递归到动态规划问题：
 * https://leetcode.com/problems/stickers-to-spell-word
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2
 */
public class Code06_StickersToSpellWord_VIS {

    public static void main(String[] args) {
        String[] stickers = {"abc", "c", "abcd"};
        String target = "ababc";
        System.out.println(minStickers1(stickers,target));
        System.out.println(minStickers2(stickers,target));
        System.out.println(minStickers3(stickers,target));
    }

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


    //优化升级2: 直接使用频次表
    public static int minStickers2(String[] stickers,String target){
        if (stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return -1;
        }
        int N = stickers.length;
        int[][] sticker = new int[N][26];//用字母频次表示每张贴纸
        for (int i = 0; i < stickers.length; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char c : chars) {
                sticker[i][c-'a']++;
            }
        }
        int ans =process2(sticker,target);
        return ans == Integer.MAX_VALUE  ? -1: ans;

    }

    private static int process2(int[][] sticker, String target) {
        if(target.length() == 0){
            return 0;
        }
        char[] t = target.toCharArray();

        int[] tCount = new int[26];
        for (char c : t) {
            tCount[c-'a']++;
        }
        int N = sticker.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] first = sticker[i];
            //最关键的优化(重要的剪枝!这一步也是贪心!)
            if (first[t[0]-'a']>0){//如果贴纸中包含目标字符的子一个字符，才开始(剪枝)
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {//直接用目标频次 减去 贴纸频次（剩余大于0的即为剩下字符）
                    if (tCount[j] > 0) {
                        int i1 = tCount[j] - first[j];
                        if (i1>0){
                            for (int k = 0; k <i1 ; k++) {
                                builder.append((char)(j+'a'));
                            }
                        }
                    }

                }
                min = Math.min(min,process2(sticker, builder.toString()));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0:1);
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
