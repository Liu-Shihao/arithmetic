package com.lsh.day03_random;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/1/29 6:01 下午
 * @desc ：对数器
 * 1.随机生成样本
 * 2.自动比对结果
 */
public class Code03 {

    /**
     * 生成随机长度，值随机大小的数组
     * @param maxLength
     * @param maxValue
     * @return 数组
     */
    public static int[] randomLengthRandomValue(int maxLength,int maxValue){
        int length = (int) (Math.random()*(maxLength-2))+2;
        int[] arr = new int[length];
        for (int i = 0; i < length ; i++) {
            arr[i]  = (int) (Math.random() * maxValue);
        }
        return arr;

    }

    /**
     * 复制数组，返回新数组。
     * @param arr1
     * @return
     */
    public static int[] copyArr(int[] arr1){
        int[] arr2 = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }
        return arr2;
    }

    /**
     * 交换位置
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr,int i ,int j){
        int temp = arr[i];
        arr[i]  = arr[j];
        arr[j] = temp;
    }


    /**
     * 冒泡排序：
     * 0 ~ n-1
     * 0 ~ n-2
     * 0 ~ n-3
     * 0 ~ 1
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        if (arr  == null || arr.length < 2){
            return;
        }
        int N = arr.length;
        for (int end = N-1; end > 0 ; end--) {
            // 01 12 23 34
            for (int second = 1 ; second <= end; second++) {
                if (arr[second-1] > arr[second]){
                    swap(arr,second,second-1);
                }
            }
        }
    }

    /**
     * 打印数组
     * @param arr
     */
    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 排序后校验，
     * @param arr
     * @return
     */
    public static boolean checkSort(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max > arr[i]){
                return false;
            }
            max = Math.max(arr[i],max);
        }
        return true;
    }


    public static void main(String[] args) {
        int maxLength= 10;
        int maxValue= 20;
        for (int i = 0; i < 1000; i++) {
            int[] arr1 = randomLengthRandomValue(maxLength, maxValue);
            int[] temp = copyArr(arr1);
            bubbleSort(arr1);
            if (!checkSort(arr1)){
                printArr(temp);
                printArr(arr1);
                System.out.println("冒泡排序错了！");
                break;
            }
        }
    }
}
