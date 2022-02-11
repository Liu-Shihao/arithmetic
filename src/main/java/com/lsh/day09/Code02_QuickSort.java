package com.lsh.day09;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/11 4:29 下午
 * @desc ：
 */
public class Code02_QuickSort {

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
                swap(arr,++lessEquseR,index++);

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
                swap(arr,index++,++lessR);
            }else if (arr[index] > arr[N - 1]){
                swap(arr,index,--moreL);
            }else {
                index++;
            }
        }
        swap(arr,moreL,N-1);
    }

    public static void swap(int[]arr, int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();

    }


    public static void main(String[] args) {
        int[] arr = {3,7,1,4,6,8,2,1,0,4,5,3};
        splitNum(arr);
        printArr(arr);
        int[] arr2 = {3,7,1,4,6,8,2,1,0,4,5,3};
        splitNum2(arr2);
        printArr(arr2);

    }

}
