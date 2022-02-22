package com.lsh.day03_random;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/1/29 3:18 下午
 * @desc ：Java 中的Math.random函数
 */
public class Code01 {

    public static void main(String[] args) {
        int count = 0 ;
        int totalCount = 10000;
        for (int i = 0; i < totalCount; i++) {
            if (Math.random() < 0.3){
                count++;
            }

        }
        System.out.println((double)count/(double)totalCount);
        System.out.println("=================");
        int n = 9 ;
        int[] arr = new int[9];
        for (int i = 0; i < totalCount ; i++) {
           int x  =  (int)(Math.random()*n);
            arr[x] ++;
        }
        for (int i = 0; i < n; i++) {
            System.out.println(i + " 这个数出现了"+arr[i]+" 次");

        }
        /**
         * 0 这个数出现了1056 次
         * 1 这个数出现了1081 次
         * 2 这个数出现了1155 次
         * 3 这个数出现了1105 次
         * 4 这个数出现了1113 次
         * 5 这个数出现了1126 次
         * 6 这个数出现了1133 次
         * 7 这个数出现了1151 次
         * 8 这个数出现了1080 次
         * 即 Java 中的Math.random函数 是等概率的
         */
        System.out.println("=================");
        count = 0 ;
        double x = 0.7;
        for (int i = 0; i < totalCount; i++) {
            if (xToXPower2()< x){
                count++;
            }
        }
        System.out.println((double)count/(double)totalCount);
        System.out.println(Math.pow(x,2));
        System.out.println("=================");

        count = 0 ;
        x = 0.7;
        for (int i = 0; i < totalCount; i++) {
            if (xToXPower3()< x){
                count++;
            }
        }
        System.out.println((double)count/(double)totalCount);
        System.out.println(1 - Math.pow((1 - x),3));
        System.out.println("=================");
    }
    /**
     * Math.random() 函数 返回的是 [0,1)区间的数，并且是等概率的
     * 返回0~0.1 范围的数是0.1
     * 返回0~0.2 范围的数是0.2
     * 返回0~0.3 范围的数是0.3    即 返回0-x范围的数的概率是x
     * 现在改为返回0-x范围的数的概率是x的平方
     * 即 使用Math.max函数，
     * 原理：两次随机数中的最大值都在0~x范围里，则两个随机数都在0~x范围里，所以 0~x范围的概率为 x的平方
     * @return
     */
    public static double xToXPower2(){
        return Math.max(Math.random(),Math.random());
    }

    /**
     * 现调整为Math.min函数 实现 返回0-x范围的数的概率是x的立方
     * 原理：
     * 随机数落在0~x范围内的概率为x,则不在0~x范围内的概率为 1-x
     * 两次随机数中的最小值不在0~x范围内，则两个随机数都不在0~x范围内，
     * 所以不在0~x范围内的概率为 （1-x）^3，所以落在0~x范围内的概率为 1 - (1-x）^3
     * @return
     */
    public static double xToXPower3(){
        return Math.min(Math.random(),Math.min(Math.random(),Math.random()));
    }
}
