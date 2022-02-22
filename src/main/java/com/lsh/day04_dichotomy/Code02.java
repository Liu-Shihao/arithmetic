package com.lsh.day04_dichotomy;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/1 3:55 下午
 * @desc ： 局部最小值问题
 */
public class Code02 {

    /**
     * 局部最小值问题
     * @param arr
     * @return
     */
    public static int oneMixIndex(int[] arr){
        //边界处理
        if (arr == null || arr.length == 0){
            return -1;
        }
        int N = arr.length;
        if (N == 1){
            return 0;
        }
//        if (arr.length == 2){
//            return  arr[0]< arr[1] ? 0:1;
//        }
        //如果 0 1 递增 则直接返回0
        if (arr[0] < arr[1]){
            return 0;
        }
        //如果最后两位是递减，则直接返回最后位置
        if (arr[N-2] > arr[N-1]){
            return N-1 ;
        }
        int L = 0;
        int R = N-1;
        int ans = -1;
        //写法一：
//        while (L <= R){
//            int mid = (L+R)/2;
//            // I.I      刚好中值 比相邻两个数都小
//            if ( arr[mid] < arr[mid-1] && arr[mid] < arr[mid+1]){
//                ans = mid;
//                break;
//            }
//            // .I       中值 大于左边的
//            if (arr[mid] > arr[mid-1]){
//                R = mid -1;
//                continue;
//            }
//            //I.   中值大于右边的
//            if (arr[mid] > arr[mid+1]){
//                L = mid + 1;
//                continue;
//            }
//        }
//        return ans;

        while (L <= R){
            int mid = (L+R)/2;
            //此处需要判断mid是否为0 ，如果为0 ，mid-1则越界
            if (mid == 0){
                return  arr[0] < arr[1] ? 0:1;
            }
            if (arr[mid] < arr[mid-1] && arr[mid] < arr[mid+1]){
                //同时小 ，即返回
                ans = mid;
                break;
            }else {
                //不同时小，分三种情况
                // 1 左 < 我 ， 我 > 右
                // 2 左 < 我 ， 我 < 右
                // 3 左 > 我 ， 我 > 右

                if (arr[mid -1] < arr[mid]){
                    //即 1、2 两种情况
                    R = mid - 1;
                }else {
                    //即第三种情况  我大于右边
                    L = mid + 1;
                }
            }
        }
        return ans;
    }

    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+ " ");
        }
        System.out.println();
    }

    /**
     * 生成相邻不等的随机数组
     * @param maxLength
     * @param value
     * @return
     */
    public static  int[] generateRandomArray(int maxLength,int value){
        int len = (int) (Math.random() * (maxLength - 2 + 1)) + 2;
        int[] arr = new int[len];
        arr[0] = (int) (Math.random() * (value + 1));
        for (int i = 1; i < arr.length; i++) {
            do {
                arr[i] = (int) (Math.random() * (value + 1));
            }while (arr[i] == arr[i-1]);
        }
        return arr;
    }

    public static boolean check(int[] arr,int minIndex){
        if (arr == null || arr.length == 0){
            return  minIndex==-1;
        }
        int L = minIndex - 1;
        int R = minIndex + 1;
        Boolean left = L >= 0 ? arr[L] > arr[minIndex] : true;
        Boolean right = R < arr.length ? arr[R] > arr[minIndex] : true;
        return left&&right;
    }

    public static void main(String[] args) {

        int timetest = 100;
        for (int i = 0; i < timetest; i++) {
            int[] arr = generateRandomArray(10, 10);
            printArr(arr);
            int minIndex = oneMixIndex(arr);
            System.out.println(check(arr,minIndex)+"====="+minIndex);
            System.out.println("=============");
        }

    }
}
