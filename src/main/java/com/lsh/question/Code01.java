package com.lsh.question;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/18 9:47 上午
 * @desc ：异或 ^
 * 1.如何不用额外变量交换两个数
 * 2.一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
 * 3.怎么把一个int类型的数，提取出最右侧的1来 a&(-a)
 * 4.一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
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
        //------------------------------------------
        int[] arr = {4,1,2,6,4,2};
        swap(arr,1,1);
        printArr(arr);
        //使用异或交换数组同位置的数，会把此位置的数变为0：   4,1,2,6,4,2 --swap(arr,1,1)——>  4 0 2 6 4 2
        //因为数组中的同一位置的内存区域地址是一样的，N^N结果为0。
        //------------------------------------------
        //在这个数组中只有5出现了奇数次，其他数都出现了偶数次
        int[] arr1 = {1,1,3,4,3,4,4,4,5,5,5};
        int XOR = 0;
        for (int i = 0; i < arr1.length; i++) {
            XOR ^= arr1[i];
        }
        System.out.println(XOR);
        //------------------------------------------
        int c = 7;
        System.out.println((c&(-c)));
        //------------------------------------------




    }

    /**
     * 异或操作 不使用额外变量 交换值
     * 异或，相同为0 不同为1 ；就是无进位相加
     * 两个规律： 0 ^ N = N ；N ^ N = 0
     * a = a 异或 b;          此时 a 的值为  ：a 异或 b
     * b = a 异或 b 异或 b ;   (b 异或 b)结果为0,    0 ^ a = a;  所以 b = a; 此时 b = a;
     * a = a 异或 b 异或 a ;    a异或a结果为0, 0再异或b 则结果为b；所以 a = b;
     * 前提：i和j 不能是同一位置；
     * @param i
     * @param j
     */
    public static void swap(int[] arr,int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * 遍历打印数组元素
     * @param arr
     */
    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print( arr[i] +" ");
        }
        System.out.println();
    }
}
