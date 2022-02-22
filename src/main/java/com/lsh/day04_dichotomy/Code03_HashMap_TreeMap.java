package com.lsh.day04_dichotomy;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/21 5:19 下午
 * @desc ：HashMap哈希表  TreeMap 有序表
 *
 */
public class Code03_HashMap_TreeMap {

    public static class A{
        Integer a;

        public A(int a){
            this.a=a;
        }
    }

    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        Integer a = 1;
        Integer b = 1;
        hashMap.put(a,"a");
        // 哈希表中 基础数据类型 是按 值传递的
        System.out.println(hashMap.get(b));

        HashMap<A, Integer> hashMapA = new HashMap<>();
        A a1 = new A(1);
        A b1 = new A(1);
        hashMapA.put(a1,a1.a);
        // 哈希表中 引用类型 是按 引用传递的
        System.out.println(hashMapA.get(b1));








        System.out.println("=============");

        TreeMap<Integer, String> treeMap = new TreeMap<>();

        treeMap.put(1,"1");
        treeMap.put(3,"3");
        treeMap.put(5,"5");
        treeMap.put(2,"2");
        treeMap.put(8,"8");

        //值最小的Key
        System.out.println(treeMap.firstKey());
        //值最大的Key
        System.out.println(treeMap.lastKey());
        //大于等于5最大的Key
        System.out.println(treeMap.ceilingKey(5));
        //小于等于5最大的Key
        System.out.println(treeMap.floorKey(5));






    }
}
