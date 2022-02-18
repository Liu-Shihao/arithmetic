package com.lsh.question;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/18 9:47 上午
 * @desc ：异或 ^
 * 1.如何不用额外变量交换两个数
 * 2.一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
 */
public class Code01 {

    public static void main(String[] args) {
        //如何不用额外变量交换两个数
        int a = 16;
        int b = 6;
        System.out.println(a);
        System.out.println(b);
        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println(a);
        System.out.println(b);


        //在这个数组中只有5出现了奇数次，其他数都出现了偶数次
        int[] arr = {1,1,3,4,3,4,4,4,5,5,5};
        int XOR = 0;
        for (int i = 0; i < arr.length; i++) {
            XOR ^= arr[i];
        }
        System.out.println(XOR);
    }
}
