package com.lsh.day02;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/1/27 9:52 上午
 * @desc ：冒泡排序：两两比较交换
 *
 */
public class BubbleSort {

    /**
     * 交换数组中 i和j的位置
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr,int i , int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 打印数组
     * @param arr
     */
    public static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void BubbleSort(int[] arr){
        if (arr == null || arr.length<2){
            return;
        }
        
//虽然可以排序，但是不是冒泡算法：因为并不是两两比较交换，可以看出是把第一位和每位元素比较，然后把最大值放在第一位，类似原则排序
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = i+1; j < arr.length; j++) {
//                if (arr[i] > arr[j]){
//                    //交换位置
//                    swap(arr,i,j);
//                }
//            }
//        }


//冒泡算法 简单写法：
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr.length-1-i; j++) {
//                if (arr[j] > arr[j+1]){
//                    swap(arr,j+1,j);
//                }
//            }
//        }



        //冒泡算法：两两比较交换
        // 0 ~ n-1   找出最大的 放在最后位置n-1
        // 0 ~ n-2   找出最大的 放在最后位置n-2
        // 0 ~ n-3   找出最大的 放在最后位置n-3
        // ...
        // 0 ~ end-1   找出最大的 放在最后位置end-1
        // 0 ~ end   找出最大的 放在最后位置end

        //冒泡排序 左老师写法：
        int N = arr.length;
        //end 为数组未排序元素的最后一位
        for (int end = N-1; end >= 0 ; end--) {
            // 1 2   2 3   3 4
            //第一个数和第二个数比较 second = 1  即：arr[1] 和 arr[1-1]
            //second ++   第二次比较：arr[2] 和 arr[2-1]
            //如果second比second-1 大，则交换位置。
            for (int second = 1; second <= end; second++) {
                if (arr[second -1] > arr[second]){
                    swap(arr,second,second-1);
                }
            }
        }


    }

    public static void main(String[] args) {
        int[] arr = {4, 6, 2, 7, 1, 9, 5, 5, 2, 3};
        printArray(arr);
        BubbleSort(arr);
        printArray(arr);
    }





}
