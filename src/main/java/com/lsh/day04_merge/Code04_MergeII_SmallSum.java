package com.lsh.day04_merge;

import com.lsh.day02_sort.SortUtil;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/22 4:38 下午
 * @desc ：小和问题 归并算法
 * 一个数组 ，将每个数右边比他小的所有元素累加和 相加。求出最小和
 * 例如：2，6，4，1，8
 * 在2右边比它小的元素有1
 * 在6右边比它小的元素有4，1
 * 在4右边比它小的元素有1
 * 在1右边比它小的元素没有
 * 在8右边比它小的元素没有
 * 所以最小和就是 1+4+1+1=7
 *
 */
public class Code04_MergeII_SmallSum {

    public static void main(String[] args) {
        int testTime = 100;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            int[] arr = SortUtil.generateRandomArray(10, 100);
            int[] copyArray = SortUtil.copyArray(arr);
            if (test(arr) != smallSum(copyArray) ){
                System.out.println("发生错误！");
                SortUtil.printArr(arr);
                break;
            }
        }
        System.out.println("测试结束！");

    }

    public static int smallSum(int[] arr){
        if (arr==null || arr.length < 2 ){
            //如果只有一个元素，则左边比他小的没有，所以和为0
            return 0;
        }
        return process(arr,0,arr.length-1);
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R){
            return 0;
        }
        //L < R
        int mid = L +((R-L)>>1);
        return process(arr,L,mid)
                + process(arr,mid+1,R)
                + marge(arr,L,mid,R);
    }

    /**
     * 合并
     * @return
     */
    private static int marge(int[] arr, int L, int M, int R) {
        int[] help = new int[R-L+1];
        int i = 0;
        int p1 = L;
        int p2 = M+1;
        // res 表示小和
        int res = 0;
        while (p1 <= M && p2 <= R){
            // 左组小于右组，才有小和产生
            // 右组中大于arr[p1]的元素有(R -p2 +1)个
            res += arr[p1] < arr[p2] ? (R -p2 +1)*arr[p1] : 0;
            //此处不能用<=判断，左组等于右组的时候，只能移动右组的指针，
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M){
            help[i++] = arr[p1++];
        }
        while (p2 <= R){
            help[i++] =arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L+j] = help[j];
        }
        return res;
    }

    public static int test(int[] arr){
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (arr[j] < arr[i]){
                    res += arr[j];
                }
            }
        }
        return res;
    }

}
