package com.lsh.day04;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/1/31 1:37 下午
 * @desc ：二分法：
 * 0.找出有序数组中的num
 * 1.找出数组中大于等于num的最左边的数
 * 2.在有序数组arr中小于等于num最右边的数
 */
public class Code01 {

    public static void main(String[] args) {
        int timetest = 100;
        for (int i = 0; i < timetest; i++) {
            int[] arr = generateRandomArray(10, 20);
            bubbleSort(arr);
//            if (find(arr,5)){
//                //find方法没有找到
//                if (test(arr,5)){
//                    printArr(arr);
//                    System.out.println("找到了");
//                    System.out.println("==========");
//                }else{
//                    printArr(arr);
//                    System.out.println("错误！");
//                    System.out.println("==========");
//                }
//            }
            printArr(arr);

//            System.out.println(mostLeftNoLessIndex(arr,5));
//            System.out.println(test2(arr,5));

            System.out.println(mostRightNoLessThenIndex(arr,5));
            System.out.println(test3(arr,5));

            System.out.println("======================");




        }


        System.out.println("==完成==");

    }

    /**
     * 题目一：通过二分法查找数组中的num
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
     * 升级2：找出数组中大于等于num 的最左边的数
     * @param arr
     * @param num
     * @return
     */
    public static int mostLeftNoLessIndex(int[] arr,int num){
        if (arr == null || arr.length == 0 ){
            return  -1;
        }
        int L = 0 ;
        int R = arr.length-1;
        int ans = -1;
        while (L <= R){
            int mid = (L+R)/2;
            if (arr[mid] >= num){
                ans = mid;
                R = mid - 1;
            }else {
                //arr[mid] < num
                L = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 升级3 ：在有序数组arr中 小于等于num最右边的数
     * @param arr   2，4，6，5，5，8，9
     * @param num
     * @return
     */
    public static int mostRightNoLessThenIndex(int[] arr,int num ){
        if (arr == null || arr.length == 0){
            return  -1;
        }
        int L= 0;
        int R= arr.length-1;
        int ans = -1;
        while (L<= R){
            int mid = (L+R)/2;
            if (arr[mid] <= num){
                ans = mid;
                L = mid + 1;
            }else {
                //arr[mid] > num
                R = mid - 1;
            }
        }
        return ans;
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

    /**
     * 对数器2： 测试mostLeftNoLessIndex方法是否正确
     * @param arr
     * @param num
     * @return
     */
    public static int test2(int[] arr,int num){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= num){
                return i;
            }

        }
        return -1;
    }

    /**
     * 对数器3 测试方法
     * @param arr
     * @param num
     * @return
     */
    public static int test3(int[] arr,int num){
        int ans = -1;
        for (int i = arr.length-1; i >= 0  ; i--) {
            if (arr[i] <= num){
                return i;
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
