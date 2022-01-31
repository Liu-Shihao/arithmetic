package com.lsh.day04;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/1/31 1:37 下午
 * @desc ：
 */
public class Code01 {

    public static void main(String[] args) {
        int timetest = 100;
        for (int i = 0; i < timetest; i++) {
            int[] arr = generateRandomArray(10, 20);
            bubbleSort(arr);
            if (find(arr,5)){
                //find方法没有找到
                if (test(arr,5)){
                    printArr(arr);
                    System.out.println("找到了");
                    System.out.println("==========");
                }else{
                    printArr(arr);
                    System.out.println("错误！");
                    System.out.println("==========");
                }
            }
        }

//        int[] arr = generateRandomArray(10, 10);
//        printArr(arr);
//        bubbleSort(arr);
//        printArr(arr);
//        System.out.println(test(arr, 5));
//        System.out.println(find(arr, 5));

        System.out.println("==完成==");

    }

    /**
     * 通过二分法查找数组中的num
     * @param arr
     * @param num
     */
    public static boolean find(int[] arr,int num){
        int L = 0;
        int R = arr.length-1;

        while (L<=R){
            int mid = (L+R)/2;
            if (arr[mid] == num){
                return true;
            }else if(arr[mid] < num){
                L = mid +1;
            }else if (arr[mid] > num){
                R = mid -1;
            }
        }
        return false;
    }

    /**
     * 对数器：测试find 方法是否正确
     * @param arr
     * @param num
     * @return
     */
    public static boolean test(int[] arr,int num ){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num){
                return true;
            }
        }
        return false;
    }


    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+ " ");
        }
        System.out.println();
    }

    /**
     * 冒泡排序
     * 0 ~ n-1
     * 1 ~ n-1
     * 2 ~ n-1
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int N = arr.length;
        for (int end = N-1; end > 0; end--) {
            for (int second = 1; second <= end; second++) {
                if (arr[second] < arr[second-1]){
                    int temp = arr[second-1];
                    arr[second-1] = arr[second];
                    arr[second] = temp;
                }

            }

        }

    }

    public static int[] generateRandomArray(int maxLength,int maxValue){
        int N =(int)(Math.random()*(maxLength+1-2))+2;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int)(Math.random()*(maxValue+1));
        }
        return arr;

    }




}
