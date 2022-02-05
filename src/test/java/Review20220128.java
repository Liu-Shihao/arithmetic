/**
 * @author ：LiuShihao
 * @date ：Created in 2022/1/28 5:15 下午
 * @desc ：复习 选择排序、冒泡排序、插入排序
 */
public class Review20220128 {

    public static void main(String[] args) {
        int[] arr = {9, 4, 1, 6, 2, 5, 4,1};
        printArray(arr);
//        selectSort(arr);
//        bubbleSort1(arr);
//        bubbleSort2(arr);
        insertionSort1(arr);
//        insertionSort2(arr);
        printArray(arr);

    }

    /**
     * 打印数组
     * @param arr
     */
    public static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();

    }

    /**
     * 交换位置
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 选择排序：每次找出最小值
     * 0~n-1 中找出最小的  0-1  1-2  2-3
     * 1~n-1 中找出最小的
     * 2~n-1 中找出最小的
     * 3~n-1 中找出最小的
     * @param arr
     */
    public static void selectSort(int[] arr){
        if (arr == null || arr.length < 2  ){
            return;
        }
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int minValueIndex = i;
            //注意：内层循环的初始值是当前值的下一位，即i+1，因为内层循环为当前值和后面的值比较，并记录最小值的位置
            for (int j = i+1; j < N; j++) {
                // 2,1,6,8,1,5
                // 0 1 2 3 4 5
                minValueIndex = arr[minValueIndex] > arr[j] ? j:minValueIndex;
            }
            swap(arr,minValueIndex,i);

        }

    }

    /**
     * 冒泡排序：两两相比  交换位置 每次找出最大值。
     * @param arr
     */
    public static void  bubbleSort1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int N = arr.length;
        // 0 ~ n-1
        // 0 ~ n-2
        // 0 ~ n-3
        //外层循环控制次数
        for (int i = 0; i < N; i++) {
            //内层循环进行两两比较，从索引0位置开始两两比较  即：j和j+1进行比较，j++，此处 限制条件：j < N-1 和j < N-1-i 效果是一样的。
            for (int j = 0; j < N-1-i; j++) {
                if (arr[j] > arr[j+1]){
                    swap(arr,j+1,j);
                }
            }
        }
    }

    /**
     * 冒泡排序第二种写法：
     * @param arr
     */
    public static void bubbleSort2(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int N = arr.length;
        for (int end = N-1; end >= 0; end--) {
            for (int second = 1; second <= end ; second++) {
                if (arr[second-1] > arr[second]){
                    swap(arr,second-1,second);
                }
            }
        }
    }

    /**
     * 插入排序第一种写法：
     * 0 ~ n-1   待排序元素位置 1
     * 1 ~ n-1   待排序元素位置 2
     * 2 ~ n-1   待排序元素位置 3
     * 3 ~ n-1   待排序元素位置 4
     * @param arr
     */
    public static void insertionSort1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int N = arr.length;
        //TODO i可以从1开始，因为0位置的元素不需要排序，
        for (int i = 1; i < N; i++) {
            int nextValueIndex = i ;
            while ((nextValueIndex -1)>=0 && (arr[nextValueIndex] < arr[nextValueIndex-1])){
                swap(arr,nextValueIndex,nextValueIndex-1);
                nextValueIndex--;
            }
        }

    }

    /**
     * 插入排序第二种写法：
     * @param arr
     */
    public static void insertionSort2(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int N = arr.length;
        for (int end = 1; end < N; end++) {
            for (int pre = end -1 ; pre >= 0 &&(arr[pre] > arr[pre+1]); pre--) {
                swap(arr,pre,pre+1);
            }
        }
    }



}
