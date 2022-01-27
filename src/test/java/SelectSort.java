/**
 * @author ：LiuShihao
 * @date ：Created in 2022/1/27 9:21 上午
 * @desc ：选择排序
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {4, 6, 2, 7, 1, 9, 5, 5, 2, 3};
        printArray(arr);
        selectSort(arr);
        printArray(arr);
    }

    /**
     * 选择排序
     * @param arr
     */
    public static void selectSort(int[] arr){
        //定义边界条件
        if (arr == null || arr.length <2){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minValueIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                minValueIndex = arr[minValueIndex] > arr[j] ? j:minValueIndex;
            }
            int temp = arr[i];
            arr[i] = arr[minValueIndex];
            arr[minValueIndex] = temp;
        }
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
}
