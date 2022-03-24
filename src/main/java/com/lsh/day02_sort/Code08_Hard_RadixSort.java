package com.lsh.day02_sort;

import java.util.Arrays;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/24 4:49 下午
 * @desc ：基数排序
 * 例如： 103、43、14、1、90
 * 首先用0将低位按照对高位数的位数补齐：103、043、014、001、090
 * 然后分别按个位数、十位数、百位数...进行排序
 *
 */
public class Code08_Hard_RadixSort {

    public static void main(String[] args) {
        int testtime = 10000;
        int maxLength = 10;
        int maxValue = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testtime; i++) {
            int[] array = SortUtil.generateRandomArray(maxLength, maxValue,true);
            int[] copyArray = SortUtil.copyArray(array);
            Arrays.sort(array);
            radixSort(copyArray);
            if (!SortUtil.isEqual(array, copyArray)){
                System.out.println("基数排序失败");
                SortUtil.printArr(array);
                SortUtil.printArr(copyArray);
                break;
            }
        }
        System.out.println("测试结束");
    }

    //基数排序
    public static void radixSort(int[] arr){
        if (arr == null || arr.length < 2){return;}
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    //arr[L..R]排序  ,  最大值的十进制位数digit
    private static void radixSort(int[] arr, int L, int R, int digit) {
        //radix 基数
        final int radix = 10;
        int i = 0, j = 0;
        //准备辅助数组  有多少个数准备多少个辅助空间
        int[] help = new int[R-L+1];
        for (int d = 1; d <= digit ; d++) {//有多少为就进出几次
            //10个空间
            //count[0] 表示d当前为是0的有多少个
            //count[1] 表示d当前为是0~1的有多少个
            //count[2] 表示d当前为是0~2的有多少个
            //count[i] 表示d当前为是0~3的有多少个
            int[] count = new int[radix];//0-9

            //统计count数组每个位置的数出现次数
            for ( i = L; i <= R; i++) {
                //获取该位置上的数值
                 j = getDigit(arr[i],d);
                 count[j]++;
            }
            //将count数组进行累加
            for ( i = 1; i < radix; i++) {
                count[i] = count[i] + count[i-1];
            }
            //将arr原数组从右往左遍历，开始将数倒到help数组中

            for (i = R; i >= L ; i--) {
                j = getDigit(arr[i],d);//获得该位置上的数字
                //将该数放到辅助数组 最右的位置（count[j] - 1），并且将计数数组count 该位数字的值减一
                help[count[j] - 1] = arr[i];
                count[j]--;
            }
            //最后将排好序的辅助数组复制到原数组中
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = help[j];
            }
        }

    }

    //获取该位上的数值
    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

    //获得最大值的位数
    private static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max,arr[i]);
        }
        int res = 0;
        while (max != 0){
            //最大值➗10
            max /=10;
            res ++;
        }
        return res;
    }

}
