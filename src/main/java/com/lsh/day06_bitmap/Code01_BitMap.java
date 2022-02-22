package com.lsh.day06_bitmap;

import java.util.HashSet;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/7 4:36 下午
 * @desc ：位图的实现
 * int  32位
 * long 64位
 * 1    2   3   4   5   6   7   8   9   10
 * 2    4   8   16  32  64  128 256 512 1024
 */
public class Code01_BitMap {

    public static class BitMap{
        private long[] bits;

        public BitMap(int max){
            // >> 6表示 ➗64
            //(max + 64) >> 6   等同于  (max + 64)/64
            bits = new long[(max + 64) >> 6];
        }

        /**
         * 要用1L左移 表示64位
         * 用1 则为int类型 ，只有32位
         * @param num
         */
        public void add(int num){
            bits[num >> 6] |= (1L << (num & 63));
        }
        public void delete(int num){
            bits[num >> 6] &= ~(1L << (num & 63));
        }
        public boolean contains(int num){
            return (bits[num >> 6] & (1L << (num & 63))) != 0;
        }



    }

    /**
     * >> 6 等同于 /64（除以64）
     * %64（除以64后的余数） 等同于 &63（与63） ,因为63的二进制表示为111111
     * @param args
     */
    public static void main(String[] args) {
//        System.out.println(8363>>6);
//        System.out.println(8363/64);
//        System.out.println(8363%64);
//        System.out.println(8363&63);
        System.out.println("测试开始！");
        int max = 10000;
        BitMap bitMap = new BitMap(max);
        HashSet<Integer> set = new HashSet<>();
        int testTime = 10000000;
        for (int i = 0; i < testTime; i++) {
            int num = (int) (Math.random() * (max + 1));
            double decide = Math.random();
            if (decide < 0.333) {
                bitMap.add(num);
                set.add(num);
            } else if (decide < 0.666) {
                bitMap.delete(num);
                set.remove(num);
            } else {
                if (bitMap.contains(num) != set.contains(num)) {
                    System.out.println("Oops!");
                    break;
                }
            }
        }
        for (int num = 0; num <= max; num++) {
            if (bitMap.contains(num) != set.contains(num)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");

    }

}
