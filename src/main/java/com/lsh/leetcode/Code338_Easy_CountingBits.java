package com.lsh.leetcode;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/1 3:27 下午
 * @desc ：338. Counting Bits
 * @link ：https://leetcode.com/problems/counting-bits/
 * 给定一个整数n，返回一个长度为n + 1的数组ans，使得对于每个i (0 <= i <= n)， ans[i]是i的二进制表示形式中1的个数。
 * 索引的二进制形式有几个1
 * input: n = 5
 * output: [0,1,1,2,1,2]
 *
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 *
 * i = 5    101 >>1   010  >>1  001
 *           &1        &1        &1
 *            1         0         1
 *
 */
public class Code338_Easy_CountingBits {

    public int[] countBits(int n) {
        int[] arr = new int[n + 1];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i >> 1] + (i & 1);
        }
        return arr;
    }


    public int[] countBits2(int n) {
        int[] arr = new int[n + 1];
        //ans[i]是i的二进制表示形式中1的个数
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 32; j++) {
                if ((i & (1<<j)) != 0){
                    arr[i] += 1;
                }
            }
        }
        return arr;
    }
}
