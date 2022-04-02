package com.lsh.day12_greed;

import java.util.PriorityQueue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/2 4:02 下午
 * @desc ：贪心算法：分割黄金问题
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。
 * 比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
 *
 * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
 *
 * 如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;一共花费110铜板。
 * 但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30;一共花费90铜板。
 * 输入一个数组，返回分割的最小代价。
 */
public class Code03_LessMoneySplitGold {

    public static void main(String[] args) {
        int[] arr = {10, 30, 20, 50};
        System.out.println(lessMoney1(arr));
        System.out.println(lessMoney2(arr));
    }

    // 纯暴力！
    public static int lessMoney1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }

    /**
     *
     * @param arr  剩余待分割的金条
     * @param pre 目前花费的费用
     * @return
     */
    private static int process(int[] arr, int pre) {
        if (arr.length == 1){
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        //两个递归 排列出所有情况（因为是一分为二，所以两两组合）
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                ans = Math.min(ans,process(copyAndMergeTwo(arr,i,j),pre+arr[i]+arr[j]));

            }
        }
        return ans;
    }

    private static int[] copyAndMergeTwo(int[] arr, int i, int i1) {
        int length = arr.length;
        int[] ans = new int[length-1];
        int index = 0;
        for (int j = 0; j < length; j++) {
            if (j != i && j != i1) {
                ans[index++] = arr[j];
            }
        }
        ans[index] = arr[i]+arr[i1];
        return ans;
    }

    //贪心解：使用小根堆
    public static int lessMoney2(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i : arr) {
            heap.offer(i);
        }
        int sum = 0;
        int cur = 0;
        while (heap.size() != 1) {
            cur = heap.poll() + heap.poll();
            sum +=cur;
            heap.offer(cur);
        }
        return sum;
    }


}
