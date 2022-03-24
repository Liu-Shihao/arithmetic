package com.lsh.day02_sort;

import java.util.Arrays;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/24 4:48 下午
 * @desc ：计数排序
 * 例如员工年龄：25、80、119、20、199、30、32、77、38、139、20
 * 借助一个辅助数据从0到200[0,1,2,3,4,5,...,199,200]
 * 将每个年龄进行计数
 * 然后在依次排出
 *
 *
 */
public class Code07_CountSort {

    //不能为负数
    public static void countSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;

        //遍历找出最大值
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i],max);
        }

        //构造辅助数组桶 长度为最大值 例如最大值是200，则桶的长度为201：[0,1,2,3...,200]
        int[] bucket = new int[max+1];

        //进行计数
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]] ++;
        }
        //将桶内数据倒出 ,进行排序
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0){
                arr[i++] = j;
            }
        }
    }

    public static void main(String[] args) {
        int testtime = 10000;
        int maxLength = 10;
        int maxValue = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testtime; i++) {
            int[] array = SortUtil.generateRandomArray(maxLength, maxValue,true);
            int[] copyArray = SortUtil.copyArray(array);
            Arrays.sort(array);
            countSort(copyArray);
            if (!SortUtil.isEqual(array, copyArray)){
                System.out.println("排序失败");
                SortUtil.printArr(array);
                SortUtil.printArr(copyArray);
                break;
            }
        }
        System.out.println("测试结束");

    }





}
