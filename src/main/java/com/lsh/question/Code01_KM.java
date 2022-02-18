package com.lsh.question;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/18 9:47 上午
 * @desc ：异或 ^
 * 1.如何不用额外变量交换两个数
 * 2.一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
 * 3.怎么把一个int类型的数，提取出最右侧的1来 a&(-a)
 * 4.一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
 * 5.一个数组中有一种数出现K次，其他数都出现了M次， M > 1,  K < M,找到出现了K次的数。要求，额外空间复杂度O(1)，时间复杂度O(N)
 */
public class Code01_KM {

    /**
     * 如何不用额外变量交换两个数
     * @param a
     * @param b
     */
    public static void swapTwoNum(int a,int b){
        System.out.println("a:"+a);
        System.out.println("b:"+b);
        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println("a:"+a);
        System.out.println("b:"+b);
    }

    /**
     * 在这个数组中只有一个数出现了奇数次，其他数都出现了偶数次，返回这个数。
     * @param arr
     * @return
     */
    public static int onlyOneNum(int[] arr){
        int XOR = 0;
        for (int i = 0; i < arr.length; i++) {
            XOR ^= arr[i];
        }
        return XOR;
    }

    /**
     * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
     * @param arr
     */
    public static void onlyTwoNum(int[] arr){
        int eor = 0;
        for (int i = 0; i < arr.length ; i++) {
            eor ^= arr[i];
        }
        //异或数组中所有元素，出现偶数次的全部抵消，剩下的只有两个出现奇数次的数，并且结果肯定不为 0 ；eor= a^b;
        //找出eor（a^b）结果中任意一位1（32位上为1的），假设取最右侧的1 即 ：&上相反数
        int eor2 = eor&(-eor);
        //eor2 表示（a^b） 最右侧的1   ，a和b异或结果上其中一位为1则说明a和b这位置上一定不相同！
        //我们通过eor2和所有元素相与，区分出 其他所有元素上这一位为1 和0 的，这样就能区分出a 和 b，（包括出现偶数次的元素，但是出现偶数次的元素异或后抵消）
        //
        int onlyOne = 0;
        for (int i = 0; i < arr.length; i++) {
            //如果这个数和最右侧的1 与完不为0 ，则说明这个数这一位上一定是1
            if ((eor2 & arr[i]) != 0){
                //数组中所有元素中，这一位上与1相与，结果为1的说明这位是1；为0的说明这位上为0，而a和b一定被区分开来
                //重点：
                //找出数组中所有这一位上为1的元素，（里面一定与a或者b），进行异或，则偶数次的全部抵消，最后只剩下 a或者b
                onlyOne^=arr[i];
            }
        }
        //得出a、b其中一个值后，在和（a^b）进行异或，求得另外一个值。
        System.out.println("出现奇数次的两个数分别是："+onlyOne+" ，"+(eor^onlyOne));
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //如何不用额外变量交换两个数
        int a = 16;
        int b = 6;
        swapTwoNum(a,b);

        System.out.println("-----------");
        //------------------------------------------
        int[] arr = {4,1,2,6,4,2};
        swap(arr,1,1);
        printArr(arr);
        //使用异或交换数组同位置的数，会把此位置的数变为0：   4,1,2,6,4,2 --swap(arr,1,1)——>  4 0 2 6 4 2
        //因为数组中的同一位置的内存区域地址是一样的，N^N结果为0。
        //------------------------------------------
        int[] arr1 = {1,1,3,4,3,4,4,4,5,5,5};
        onlyOneNum(arr1);


        //------------------------------------------
        // 获得这个数最右侧的1
        // int c = 7;
        // System.out.println((c&(-c)));
        //------------------------------------------
        //一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
        //6 和  10出现了奇数次，其他数都出现了偶数次
        int[] arr2 = {6,1,2,1,2,10,10,10,8,8,12,12,12,12};
        onlyTwoNum(arr2);

        //------------------------------------------
        //对数器测试
        System.out.println("-----------------------------");
        int testTime = 100;
        int numKinds = 3;
        int range = 10;
        int max = 9;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int aa = (int) (Math.random() * max)+1;
            int bb = (int) (Math.random() * max)+1;
            int k = Math.min(aa,bb);
            int m = Math.max(aa,bb);
            if (k == m){
                m++;
            }
//            System.out.println("k="+k+",m="+m);
            int[] randomArr = randomArr(numKinds, range, k, m);
//            printArr(randomArr);
            if ( km(randomArr,k,m) != test(randomArr,k,m)){
                System.out.println("错误！"+km(randomArr,k,m) +" - "+test(randomArr,k,m));
                break;
            }
        }
        System.out.println("测试结束");

    }

    /**
     * 一个数组中有一种数出现K次，其他数都出现了M次， M > 1,  K < M,找到出现了K次的数。要求，额外空间复杂度O(1)，时间复杂度O(N)
     * 把一位整型用长度为32的数组表示
     * 3出现了3次，其他数字出现了5次。  k = 3 ; M = 5
     * int[] arr3 = {3,3,3,1,1,1,1,1,2,2,2,2,2,4,4,4,4,4,5,5,5,5,5};
     * int m = 5;
     * int k = 3;
     * @return
     */
    public static int km(int[] arr, int k,int m){
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32 ; i++) {
                //num右移0位、右移1位、右移2位、
                t[i] += (num>>i & 1);
            }
        }
//        printArr(t);
        //把一位整型用长度为32的数组表示，所有元素32位上分别对应位置相加，得出t数组
        //如第0位置上为13，说明所有元素的32位二进制上的第0位上为1的一共有13个
        //分析可得，如果13是M的倍数的话，说明出现K次的那个数的第0位上一定不是1；相反如果不是M的倍数的话则说明出现K次的这个数的第0位上为1
        int ans = 0;
        for (int i = 0; i < 32 ; i++) {
            if (((t[i]%m) != 0)){
                //说明出现k次的数这位上为1
//                ans += Math.pow(2,i);
                //已经知道了这个数哪些位置上是1，直接和1或运算将该位置或上1就行
                ans |= (1<<i);
            }
        }
        return ans;
    }

    /**
     * 对数器测试方法
     * @param arr
     * @param m
     * @param k
     * @return
     */
    public static int test(int[] arr, int k,int m){
        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        for (int num : arr) {
            if (integerIntegerHashMap.get(num) == null){
                integerIntegerHashMap.put(num,1);
            }else {
                integerIntegerHashMap.put(num,integerIntegerHashMap.get(num)+1);
            }
        }
        for (int key : integerIntegerHashMap.keySet()){
            if (integerIntegerHashMap.get(key) == k){
                return key;
            }
        }
        return -1;
    }

    /**
     * 随机生成 一个数出现K次 其他数出现M次的数组
     * @param numKinds  几种数
     * @param maxValue  最大值
     * @param m
     * @param k
     * @return
     */
    public static int[] randomArr(int numKinds,int maxValue, int k, int m){
        numKinds = (int)(Math.random()*numKinds)+2;
        int length = k+(numKinds-1)*m;
        int[] arr = new int[length];
        HashSet<Integer> numSet = new HashSet<>();
        int index = 0;
        int kValue = (int)(Math.random()*maxValue)+1;
        numSet.add(kValue);
        for (; index < k; index++) {
            arr[index] = kValue;
        }
        numKinds --;
        while (numKinds != 0){
            int curNum = 0;
            do {
                curNum = (int)(Math.random()*maxValue)+1;
            }while (numSet.contains(curNum));
            numSet.add(curNum);
            numKinds --;
            for (int i = 0; i < m; i++) {
                arr[index] = curNum;
                index++;
            }

        }
        return arr;

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
