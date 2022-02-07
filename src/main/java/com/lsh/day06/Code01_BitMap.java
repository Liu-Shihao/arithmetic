package com.lsh.day06;

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

    public static class BigMap{
        private long[] bits;

        public BigMap(int max){
            // >> 6表示 ➗64
            //(max + 64) >> 6   等同于  (max + 64)/64
            bits = new long[(max + 64) >> 6];
        }
    }

    /**
     * >> 6 等同于 /64（除以64）
     * %64（除以64后的余数） 等同于 &63（与63） ,因为63的二进制表示为111111
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(8363>>6);
        System.out.println(8363/64);
        System.out.println(8363%64);
        System.out.println(8363&63);

    }

}
