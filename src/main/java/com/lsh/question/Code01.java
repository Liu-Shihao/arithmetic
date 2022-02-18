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

        //一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
        //6 和  10出现了奇数次，其他数都出现了偶数次
        int[] arr2 = {6,1,2,1,2,10,10,10,8,8,12,12,12,12};
        int eor = 0;
        for (int i = 0; i < arr2.length ; i++) {
            eor ^= arr2[i];
        }
        //异或数组中所有元素，出现偶数次的全部抵消，剩下的只有两个出现奇数次的数，并且结果肯定不为 0 ；eor= a^b;
        //找出eor（a^b）结果中任意一位1（32位上为1的），假设取最右侧的1 即 ：&上相反数
        int eor2 = eor&(-eor);
        //eor2 表示（a^b） 最右侧的1   ，a和b异或结果上其中一位为1则说明a和b这位置上一定不相同！
        //我们通过eor2和所有元素相与，区分出 其他所有元素上这一位为1 和0 的，这样就能区分出a 和 b，（包括出现偶数次的元素，但是出现偶数次的元素异或后抵消）
        //
        int onlyOne = 0;
        for (int i = 0; i < arr2.length; i++) {
            //如果这个数和最右侧的1 与完不为0 ，则说明这个数这一位上一定是1
            if ((eor2 & arr2[i]) != 0){
                //数组中所有元素中，这一位上与1相与，结果为1的说明这位是1；为0的说明这位上为0，而a和b一定被区分开来
                //重点：
                //找出数组中所有这一位上为1的元素，（里面一定与a或者b），进行异或，则偶数次的全部抵消，最后只剩下 a或者b
                onlyOne^=arr2[i];
            }
        }
        //得出a、b其中一个值后，在和（a^b）进行异或，求得另外一个值。
        System.out.println("出现奇数次的两个数分别是："+onlyOne+" ，"+(eor^onlyOne));







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
