package com.lsh.leetcode;

import java.util.HashMap;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/25 9:06 上午
 * @desc ：给定一个target数，返回数组中任意两个元素相加等于target的位置，不能重复使用
 * 1. Two Sum :https://leetcode.com/problems/two-sum/
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 */
public class Code01_TwoSum {

    /**
     * 借助哈希表
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap hashMap = new HashMap<Integer,Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i] )){
                return new int[]{i,(int)hashMap.get(target - nums[i])};
            }
            hashMap.put(nums[i],i);
        }
        return null;
    }

    /**
     * 双层循环
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
}
