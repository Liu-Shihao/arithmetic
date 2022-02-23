package com.lsh.day04_dichotomy;

import com.lsh.day02_sort.SortUtil;

import java.util.Arrays;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/23 3:00 下午
 * @desc ：归并算法：一个数右边的数乘2都比这个数小 ，返回这个数组中一共有多少个这样的数？
 * 例如：6，8，1，5，3，2
 * 6>2x1、6>2x2
 * 8>1x2、8>3x2、8>2x2
 * 1没有
 * 5>2x2
 * 3没有
 * 2没有
 * 所以这个数组中存在6个这样的数。
 */
public class Code04_SmallSumIII {

    public static void main(String[] args) {
//        int[] arr = {6,8,1,5,3,2};
        System.out.println("测试开始！");
        int testTime = 10000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = SortUtil.generateRandomArray(20, 10);
            int[] copyArray = SortUtil.copyArray(arr);
            int r1 = mergeSort(copyArray);
            int r2 = test(arr);
            if (r1 != r2){
                System.out.println("出错了！");
                System.out.println("r1 = "+r1);
                System.out.println("r2 = "+r2);
                SortUtil.printArr(arr);
                break;
            }
            Arrays.sort(arr);
            if (!SortUtil.isEqual(arr,copyArray)){
                System.out.println("排序出错了！");
                SortUtil.printArr(arr);
                SortUtil.printArr(copyArray);
                break;
            }
        }
        System.out.println("测试结束！");
    }

    public static int mergeSort(int[] arr){
        if (arr == null || arr.length < 2){
            return 0;
        }
        return process(arr,0,arr.length-1);

    }

    private static int process(int[] arr, int L, int R) {
        if (L == R){
            return 0;
        }
        int mid = L + ((R-L)>>1);
        return process(arr, L, mid)
                + process(arr,mid+1,R)
                + merge(arr,L,mid,R);

    }

    private static int merge(int[] arr, int L, int M, int R) {
        int ans = 0;
        //1.先计算
        // windowsR 表示目前囊括的右组中元素 从右组的第一个元素开始，指针向右移动(指针不回退)，
        int windowR = M+1;
        //遍历左组元素
        for (int i = L; i <= M; i++) {
            while (windowR <=R && arr[i] > (arr[windowR]<<1)){
                windowR++;
            }
            //此时windowR 到 右组第一个位置的元素的距离，就是此时所有右组乘2小于左组的数的个数
            ans += windowR - (M+1);
        }

        //2.再单独合并
        int[] help = new int[R-L+1];
        int p1 = L;
        int p2 = M+1;
        int index = 0;
        while (p1 <= M && p2 <= R){
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M){
            help[index++] = arr[p1++];
        }
        while (p2 <= R){
            help[index++] = arr[p2++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L++] = help[i];
        }
        return ans;
    }


    public static int test(int[] arr){
        if (arr == null || arr.length < 2){
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] > (arr[j]<<1)){
                    ans++;
                }
            }

        }
        return ans;
    }
}
