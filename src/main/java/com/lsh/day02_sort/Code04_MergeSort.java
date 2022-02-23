package com.lsh.day02_sort;

import java.util.Arrays;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/11 2:51 下午
 * @desc ：归并排序
 * 将数组分成左右两组，将左边部分有序，右边部分有序
 * 用两个指针 分别指向左右最小的元素，拿出最小的，指针向后移动
 *
 * 1. arr[L..R]范围上有序
 * 2. arr[L..M]范围上有序
 * 3. arr[M+1..R]范围上有序
 * 4. merge 合并(借助两个指针，一个数组)
 *
 * T(N) = 2 * T(N/2) + O(N)  ; a = 2 b = 2 d = 1
 * O(N * logN)
 *
 */
public class Code04_MergeSort {

    public static void main(String[] args) {
        System.out.println("测试开始");
        int testTime = 10000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = SortUtil.generateRandomArray(10, 20);
            int[] copyArray = SortUtil.copyArray(arr);
            Arrays.sort(arr);
//            mergeSort1(copyArray);
            mergeSort2(copyArray);
            if (!SortUtil.isEqual(arr,copyArray)){
                System.out.println("出错了！");
                SortUtil.printArr(arr);
                SortUtil.printArr(copyArray);
                break;
            }
        }
        System.out.println("测试结束");
    }
    /**
     * 递归方法实现归并排序
     * @param arr
     */
    public static void mergeSort1(int[] arr){
        //边界处理
        if (arr == null || arr.length < 2){
            return ;
        }
        //让arr[0...N-1]范围上有序
        process(arr,0,arr.length-1);
    }

    /**
     * arr[L...R]范围上，请让这个范围上有序
     * @param arr
     * @param L
     * @param R
     */
    public static void process(int[] arr,int L,int R){
        //base case 如果L == R表示：只有一个元素，一个元素即是有序的
        if (L == R){
            return;
        }
        //不止一个元素，取中点，二分，
        //mid = (L + R)/2;  使用L + ((L - R) >> 1);更安全，如果L+R下表位置很大，则会越界
        int mid = L + ((R - L) >> 1);
        //再分别将左右两部分的数组进行排序 arr[L..mid]、arr[mid+1...R]
        process(arr,L,mid);
        process(arr,mid+1,R);
        //左边右边有序后，进行合并，使用两个指针，那个小排谁，其指针后移
        merge(arr,L,mid,R);
    }

    /**
     * 合并
     * @param arr
     * @param L
     * @param M
     * @param R
     */
    public static void merge(int[] arr,int L ,int M,int R){
        int[] help = new int[R-L+1];
        int index = 0 ;
        int p1 = L;
        int p2 = M + 1;
        //p1 和 p2 都没有越界
        while (p1 <= M && p2 <= R){
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //要么p1越界，要么p2越界 跳出循环
        while (p1 <= M){
            help[index++] = arr[p1++];
        }
        while (p2 <= R){
            help[index++] = arr[p2++];
        }
        //将辅助数组中已经有序的元素 覆盖到原来数组中
        for (int i = 0; i < help.length; i++) {
            arr[L++] = help[i];
        }
    }



    /**
     * TODO 非递归方法
     */
    public static void mergeSort2(int[] arr){
        if (arr == null || arr.length < 2){
            return ;
        }
        int N = arr.length;
        // 步长 mergeSize  当前有序的，左组长度
        int step = 1;
        //步长小于数组长度
        while (step < N){
            // L 当前左组的第一个位置
            int L = 0;
            //当左组开始位置大于数组长度时，停止
            while (L < N){
                // L ..步长.. M  左组  ；M 表示完整左组的最后一个位置
                int M = L + step - 1;
                //如果左组的最后一个位置 超过了 数组的长度：说明没有凑够一个左组，
                if (M >= N){
                    break;
                }
                //定义右边界，正常的R位置应该是M+step，但是不能超过数组长度，所以两者取最小值
                int R = Math.min(M+step,N-1);
                //进行合并
                merge(arr,L,M,R);
                //下一组左组的第一个位置
                L = R + 1;
            }
            //防止溢出
            if (step > N/2){
                break;
            }
            //步长x2
            step <<= 1;
        }
    }







}
