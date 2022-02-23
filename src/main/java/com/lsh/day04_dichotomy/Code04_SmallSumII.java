package com.lsh.day04_dichotomy;

import com.lsh.day02_sort.SortUtil;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/23 2:59 下午
 * @desc ：归并问题：最小和问题2：一个数组里有几个逆序对
 * 前边的元素比后边的元素大，成为逆序对
 * 例如：2，5，4，7，1，6
 * 21、54、51、41、71、76、 存在6个逆序对
 */
public class Code04_SmallSumII {

    public static void main(String[] args) {
        System.out.println("测试开始");
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = SortUtil.generateRandomArray(5, 10);
            int[] copyArray = SortUtil.copyArray(arr);
            int r1 = mergeSortII(copyArray);
            int r2 = text(arr);
            if ( r1!= r2){
                SortUtil.printArr(arr);
                System.out.println("出错了！r1 = "+r1+" , r2 = "+r2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static int mergeSortII(int[] arr){
        if (arr==null || arr.length<2){
            return 0;
        }
        return process(arr,0,arr.length-1);
    }

    public static int process(int[]arr,int L,int R){
        //如果只剩一个元素，即L==R,一个元素即使有序的
        if (L==R){
            return 0;
        }
        //不止一个元素
        int mid = L + ((R - L)>>1);

        return process(arr,L,mid)
                +process(arr,mid+1,R)
                +merge(arr,L,mid,R);
    }

    /**
     *
     * @param arr
     * @param L
     * @param M
     * @param R
     * @return
     */
    public static int merge(int[] arr,int L,int M,int R){
        //借助一个辅助数组，R-L+1 表示元素的个数
        int[] help = new int[R-L+1];
        int index= help.length -1;
        //指针1开始的位置
        int p1 = M;
        //指针2开始的位置
        int p2 = R;
        int res = 0;
        //如果两个指针都不越界
        while (p1 >= L && p2 > M){
            res += arr[p1] > arr[p2] ? (p2 - M) : 0;
            //两个指针标记的元素，哪个小就往辅助数组里排，小的元素的指针和index指针都向后移动，大元素的指针不变
            //注意：此处不能arr[p1]>=arr[p2] ，左组和右组相等的时候，左组指针不动，右组指针向前移动。只能拷贝右组的元素，
            // 因为左组和右组相等的时候，不知道右组是否还存在比左组小的元素，所以只能拷贝右组的元素，移动右组的指针
            help[index--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        //两个指针有一个越界，跳出循环
        while (p1 >= L){
            help[index--] = arr[p1--] ;
        }
        while (p2 > M){
            help[index--] = arr[p2--] ;
        }
        for (int i = 0; i < help.length; i++) {
            arr[L++] = help[i];
        }
        return res;
    }

    public static int text(int[] arr){
        if (arr==null|| arr.length<2){
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i]>arr[j]){
                    ans++;
                }
            }

        }
        return ans;
    }


}
