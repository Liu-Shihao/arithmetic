package com.lsh.day02_sort;

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
 */
public class Code04_MergeSort {

    public static void main(String[] args) {
        int testTime = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr = SortUtil.generateRandomArray(10, 20);
            SortUtil.printArr(arr);
            mergeSort1(arr);
            SortUtil.printArr(arr);
            System.out.println("====================");
        }
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
        process(arr,0,arr.length-1);
    }

    /**
     * arr[L...R]范围上，请让这个范围上有序
     * @param arr
     * @param L
     * @param R
     */
    public static void process(int[] arr,int L,int R){
        //base case
        if (L == R){
            return;
        }
        //mid = (L + R)/2;  使用L + ((L - R) >> 1);更安全，如果L+R下表位置很大，则会越界
        int mid = L + ((R - L) >> 1);
        process(arr,L,mid);
        process(arr,mid+1,R);
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
        int i = 0 ;
        int p1 = L;
        int p2 = M + 1;
        //p1 和 p2都没有越界
        while (p1 <= M && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //要么p1越界，要么p2越界 跳出循环
        while (p1 <= M){
            help[i++] = arr[p1++];
        }
        while (p2 <= R){
            help[i++] = arr[p2++];
        }
        //将辅助数组中已经有序的元素 覆盖到原来数组中
        for (int j = 0; j < help.length; j++) {
            arr[L++] = help[j];
        }
    }



    /**
     * TODO 非递归方法
     */
    public static void mergeSort2(int[] arr){
        if (arr == null || arr.length < 2){
            return ;
        }
        int step = 1;
        while (step < arr.length){

        }
    }







}
