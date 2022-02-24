package com.lsh.day04_merge;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/24 10:32 上午
 * @desc ：归并问题： 求出一个数组中所有子串的累加和在[low,up]范围的个数
 * leetcode地址: https://leetcode.com/problems/count-of-range-sum/
 */
public class Code05_Merge_Hard_CountRangeSum {

    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null || nums.length ==0){
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
//            sum[i] += arr[i];
            sum[i] = sum[i-1] + nums[i];
        }
        return process(sum,0,nums.length-1,lower,upper);
    }

    /**
     *
     * @param sum  数组前缀和
     * @param L
     * @param R
     * @param lower
     * @param upper
     * @return
     */
    private int process(long[] sum, int L, int R, int lower, int upper) {
        if (L == R){
            if (sum[L] >= lower && sum[L] <= upper){
                return 1;//找到一个符合条件的子串 0..L
            }else {
                return 0;
            }
        }
        // L != R  范围不止一个位置
        int mid = L +((R-L)>>1);
        int left = process(sum, L, mid, lower, upper);
        int right = process(sum, mid + 1, R, lower, upper);
        int merge = merge(sum, L, mid,R, lower, upper);
        return left+right+merge;

    }

    private int merge(long[] sum, int L, int M, int R, int lower, int upper) {
        int ans = 0;
        //窗口大小 [windowL,windowR)
        int windowL = L;
        int windowR = L;
        //此时 L..M  M+1..R
        //遍历右组元素
        for (int i = M+1; i <= R ; i++) {
            long min =  sum[i] - upper;
            long max =  sum[i] - lower;
            while (windowR <= M && sum[windowR] <= max){
                windowR++;
            }
            while (windowL <= M && sum[windowL] < min){
                windowL++;
            }
            ans += windowR - windowL;
        }
        long[] help = new long[R-L+1];
        int index = 0;
        int p1 = L;
        int p2 = M+1;
        while (p1<=M && p2<=R){
            help[index++] = sum[p1] <= sum[p2] ? sum[p1++] :sum[p2++];
        }
        while (p1 <= M){
            help[index++] = sum[p1++] ;
        }
        while (p2 <= R){
            help[index++] = sum[p2++];
        }
        for (int i = 0; i < help.length; i++) {
            sum[L++] = help[i];
        }
        return ans;
    }
}
