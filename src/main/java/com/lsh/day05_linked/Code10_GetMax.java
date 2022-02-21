package com.lsh.day05_linked;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/21 4:22 下午
 * @desc ：递归-获得数组最大值
 */
public class Code10_GetMax {
    public static void main(String[] args) {
        int[] arr = {3,0,-1,2,13,0,8,9};
        System.out.println(getMax(arr));


    }

    public static int getMax(int[] arr){
        return process(arr,0,arr.length-1);
    }

    /**
     * arr数组 L-R范围上求最大值
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int process(int[] arr,int L,int R){
        //arr[L..R]范围上只有一个数
        if (L == R){
            return arr[L];
        }
        int mid = L + ((R-L)>>1);
        int leftMax = process(arr,L,mid);
        int rightMax = process(arr,mid+1,R);
        return Math.max(leftMax,rightMax);
    }
}
