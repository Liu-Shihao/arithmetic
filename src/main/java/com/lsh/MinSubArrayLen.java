package com.lsh;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/6/22 4:50 下午
 * @desc ：最小子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, …, numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * 示例 1
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 */
public class MinSubArrayLen {

    public static void main(String[] args) {
        int target = 7;
        int[] nums = new int[]{2,3,1,2,4,3};
        System.out.println(minSubArrayLen(target,nums));

    }

    /**
     * 滑动窗口算法
     * 定义 left 左边界
     * 定义 i 一直累加，如果>= target 则停止，累加和减去 left的值， left下移一位，
     * i 继续向下
     * @param s
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int s,int[] nums){
        int left=0;
        int sum=0;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            while(sum>=s){
                min=Math.min(min, i-left+1);
                sum-=nums[left];
                left++;
            }
        }


        return min==Integer.MAX_VALUE?0:min;
    }
}
