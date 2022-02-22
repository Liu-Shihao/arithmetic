package com.lsh.day02_sort;

import java.util.Stack;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/11 4:29 下午
 * @desc ：快速排序 两种方式实现
 * 1.递归实现
 * 2.非递归实现
 * 3.对数器测试
 */
public class Code05_QuickSort {

    /**
     *  小于num的放左边，大于num的放右边
     * @param arr
     */
    public static void splitNum(int[] arr){
        int lessEquseR = -1;
        int index = 0;
        int mostR = arr.length -1;

        while (index < arr.length){
            if (arr[index] <= arr[mostR]){
//                swap(arr,lessEquseR+1,index);
//                lessEquseR ++;
//                index ++;
                //合并上面三行
                SortUtil.swap(arr,++lessEquseR,index++);

            }else {
                index ++;
            }
        }
    }

    /**
     * 小于num的放左边，大于num的放右边，等于num的放中间
     * @param arr
     */
    public static void splitNum2(int[] arr){
        int N = arr.length;
        int lessR = -1;
        int moreL = N - 1;
        int index = 0;
        //
        while (index < moreL){
            if (arr[index] < arr[N - 1]){
                SortUtil.swap(arr,index++,++lessR);
            }else if (arr[index] > arr[N - 1]){
                SortUtil.swap(arr,index,--moreL);
            }else {
                index++;
            }
        }
        SortUtil.swap(arr,moreL,N-1);
    }



    /**
     * 数组arr[L...R]范围上用arr[R]划分，返回等于arr[R]的范围
     * partition  划分；分层
     * @param arr
     * @param L
     * @param R
     */
    public static int[] partition(int[] arr,int L ,int R){
        //lessR：小于划分值arr[R]的做右边位置
        int lessR = L - 1;
        //moreL：大于划分值arr[R]的最左边位置
        int moreL = R;
        //index：当前位置
        int index = L;
        while (index < moreL){
            if (arr[index] < arr[R]){
                //小于arr[R] 交换lessR 和 index 的位置，index向下一位(即index++)，lessR自增一位（先自增在使用即++lessR）
                SortUtil.swap(arr,index++,++lessR);
            }else if (arr[index] > arr[R]){
                //大于arr[R] ，交换moreL和index 的位置，index位置不变，moreL向前一位（先减一再使用即--moreL）
                SortUtil.swap(arr,index,--moreL);
            }else {
                //等于arr[R],不交换，index向下一位
                index++;
            }
            //当index指针和moreL重合，跳出循环;所以最后index和moreL的值是一样的。
        }
        SortUtil.swap(arr,index,R);
        return new int[] {lessR+1,moreL};
    }

    /**
     * 快速排序1 递归实现
     * @param arr
     */
    public static void quickSort1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process(arr,0,arr.length-1);
    }
    /**
     * 快速排序-递归过程
     * @param arr
     * @param L
     * @param R
     */
    public static void process(int[]arr,int L,int R){
        if (L >= R){
            //如果 L>R 无效范围，如果L=R只有一个数，都直接返回
            return;
        }
        //L<R
        //将arr[L...R]范围上划分
        int [] equal = partition(arr,L,R);
        //equal[0] 等于区域的第一个数
        //equal[1] 等于区域的最后一个数
        //将等于区域左边的重新排序
        process(arr,L,equal[0]-1);
        //将等于区域右边的重新排序
        process(arr,equal[1]+1,R);
    }

    /**
     * 快速排序-非递归实现
     * @param arr
     */
    public static void quickSort2(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        Stack<Job> stack = new Stack<>();
        Job job = new Job(0, arr.length - 1);
        stack.push(job);
        while (!stack.isEmpty()){
            Job cur = stack.pop();
            //从栈去除任务，进行划分
            int[] queals = partition(arr, cur.L, cur.R);
            if (queals[0] > cur.L){
                //等于区域的最小值 大于L 说明存在小于区域
                stack.push(new Job(cur.L,queals[0]-1));
            }
            if (queals[1] < cur.R){
                //等于区域的最大值 小于R 说明存在大于区域
                stack.push(new Job(queals[1]+1,cur.R));
            }
        }

    }

    public static class Job{
        public int L;
        public int R;

        public Job(int L,int R){
            this.L = L;
            this.R = R;
        }
    }

    /**
     * 通过对数器测试
     * @param args
     */
    public static void main(String[] args) {

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("=====测试开始=====");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = SortUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = SortUtil.copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            if (!SortUtil.isEqual(arr1, arr2) ) {
                System.out.println("排序错误！");
                succeed = false;
                break;
            }
        }
        System.out.println("=====测试结束=====");
        System.out.println(succeed ? "成功!" : "失败!");
    }
}
