package com.lsh.day15_recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/8 3:18 下午
 * @desc ：递归练习2
 * 打印一个字符串的全部子序列
 * 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
 * 打印一个字符串的全部排列
 * 打印一个字符串的全部排列，要求不要出现重复的排列
 */
public class Code02_PrintAllSubsquences {

    public static void main(String[] args) {
        String str = "abcc";
        System.out.println("打印全部子序列：");
        ArrayList<String> subs = subs(str);
        subs.forEach(System.out::println);
        System.out.println("=======");

        System.out.println("打印全部子序列（不重复）：");
        HashSet<String> subs2 = subs2(str);
        subs2.forEach(System.out::println);
        System.out.println("=======");

        System.out.println("打印字符串全排列1：");
        ArrayList<String> strings = permutation1(str);
        strings.forEach(System.out::println);
        System.out.println("=======");

        System.out.println("打印字符串全排列2：");
        List<String> permutation2 = permutation2(str);
        permutation2.forEach(System.out::println);

    }

    public static ArrayList<String> subs(String str){
        char[] chars = str.toCharArray();
        String path = "";
        ArrayList<String> ans = new ArrayList<>();
        process1(chars, 0, ans, path);
        return ans;

    }
    public static HashSet<String> subs2(String str){
        char[] chars = str.toCharArray();
        String path = "";
        HashSet<String> ans = new HashSet<>();
        process2(chars, 0, ans, path);
        return ans;

    }
    /**
     *
     * @param str 原始字符串
     * @param index 目前到达位置
     * @param ans
     * @param path 目前选择
     * @return
     */
    private static void process1(char[] str, int index, ArrayList<String> ans, String path) {
        if (index == str.length) {
            ans.add(path);
            return ;
        }
        //没有要index位置的字符
        process1(str,index+1,ans,path);
        //要了index位置的字符
        process1(str,index+1,ans,path+str[index]);
    }
    private static void process2(char[] str, int index, HashSet<String> ans, String path) {
        if (index == str.length) {
            ans.add(path);
            return ;
        }
        //没有要index位置的字符
        process2(str,index+1,ans,path);
        //要了index位置的字符
        process2(str,index+1,ans,path+str[index]);
    }

    // 递归字符全排列
    public static ArrayList<String> permutation1(String s){
        ArrayList<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0){
            return ans;
        }
        char[] str = s.toCharArray();
        ArrayList<Character> rest = new ArrayList<>();
        for (char c : str) {
            rest.add(c);
        }
        String path = "";
        f(rest,ans,path);
        return ans;
    }

    /**
     *
     * @param rest 表示未使用的字符
     * @param ans 结果集合
     * @param path
     */
    private static void f(ArrayList<Character> rest, ArrayList<String> ans, String path) {
        if (rest.isEmpty()){
            ans.add(path);
        }else {
            //如果还有未使用的字符
            int N = rest.size();
            for (int i = 0; i < N; i++) {
                Character cur = rest.get(i);
                rest.remove(i);//将已使用的的字符从结合中删除
                f(rest,ans,path+cur);//递归排列剩下元素
                rest.add(i,cur);//恢复现场。将删除的字符重新添加回rest集合中(注意原位置)，因为当以第二个字符进行排列的时候，需要这个字符
            }
        }
    }
    //字符串全排列  递归2
    public static List<String> permutation2(String s){
        ArrayList<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0){
            return ans;
        }
        char[] str = s.toCharArray();
        g(str,0,ans);
        return ans;
    }

    /**
     * 在原数组中互相交换位置实现全排列
     * @param str
     * @param index
     * @param ans
     */
    private static void g(char[] str, int index, ArrayList<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        }else {
            boolean[] visited = new boolean[256];//用于去重（减支，如果遇到重复直接跳过，比走完所有分支后再使用Set过滤效率高）
            for (int i = index; i < str.length; i++) {
                if (!visited[str[i]]){
                    visited[str[i]] = true;
                    swap(str,index,i);//用index的值和i交换
                    g(str,index+1,ans);//
                    swap(str,index,i);//恢复现场
                }

            }
        }
    }

    private static void swap(char[] str ,int i ,int j){
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

}
