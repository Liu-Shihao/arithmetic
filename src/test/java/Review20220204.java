/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/4 12:55 上午
 * @desc ：
 */
public class Review20220204 {
    public static void main(String[] args) {
        int[] arr = {6, 4, 2, 7, 4, 8, 1, 9, 0, 3};
        printArray(arr);
//        insertionSort1(arr);
        insertionSort2(arr);
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
     * TODO 易错点：i = 1; i从1开始；int nextValueIndex = i;新值的位置从i开始，开始和前一位（nextValueIndex-1）比较
     * 条件(nextValueIndex-1 >= 0)&& arr[nextValueIndex-1] > arr[nextValueIndex]
     * 前一位要有值！新值要比前一位元素小 才交换位置！
     * @param arr
     */
    public static void insertionSort1(int[] arr){
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            int nextValueIndex = i;
            while ((nextValueIndex-1 >= 0)&& arr[nextValueIndex-1] > arr[nextValueIndex]){
                int temp = arr[nextValueIndex];
                arr[nextValueIndex] = arr[nextValueIndex-1];
                arr[nextValueIndex-1] = temp;
                nextValueIndex --;
            }
        }
    }

    /**
     * TODO 易错点： int end = 1;  int pre = end -1; (pre>= 0)&&(arr[pre]>arr[pre+1]); pre--
     * @param arr
     */
    public static void insertionSort2(int[] arr){
        for (int end = 1; end < arr.length ; end++) {
            for (int pre = end -1; (pre>= 0)&&(arr[pre]>arr[pre+1]); pre--) {
                int temp = arr[pre];
                arr[pre] = arr[pre+1];
                arr[pre+1] = temp;
            }

        }
    }
}
