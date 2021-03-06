package com.lsh.day02_sort;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/1/26 4:56 下午
 * @desc ： 选择排序：找到最小值直接交换
 * 遍历找到最小值 放在索引为0的位置
 * 遍历找到最小值 放在索引为1的位置
 * 遍历找到最小值 放在索引为2的位置
 * 遍历找到最小值 放在索引为3的位置....
 *
 */
public class Code01_SelectSort {

    public static void main(String[] args) {
        int[] arr = {2, 5, 7, 2, 3, 7, 1, 4, 9};
        SortUtil.printArr(arr);
        selectSort(arr);
        SortUtil.printArr(arr);
    }

    public static void selectSort(int[] arr){
        //首先定义边界条件
        //如果为空不排序，如果不为空但是只有一个元素也不需要排序
        if (arr == null || arr.length<2){
            return;
        }
        int N = arr.length;
        // 0 ~ n-1
        // 1 ~ n-1
        // 2 ~ n-1
        for (int i = 0; i < N ; i++) {
            //最小值的索引位置  定义为当前位置
            int minValueIndex = i;
            for (int j = i+1; j < N; j++) {
                //如果 当前位置的值小于后一位的值，则minValueIndex不变，否则为后一位的索引。
                minValueIndex = arr[minValueIndex] > arr[j]? j : minValueIndex;
            }
            //已经找出最小值位置的数，交换位置
            int temp = arr[i] ;
            arr[i] = arr[minValueIndex];
            arr[minValueIndex] = temp;
        }

    }

}
