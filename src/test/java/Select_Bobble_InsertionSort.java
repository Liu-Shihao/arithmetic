import java.util.Arrays;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/16 11:02 上午
 * @desc ：选择、冒泡、插入 排序
 */
public class Select_Bobble_InsertionSort {

    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print( arr[i] +" ");
        }
        System.out.println();
    }

    public static int[] generateRandomArr(int maxLength,int maxValue){
        maxLength = (int)(Math.random() * maxLength+1);
        int[] arr = new int[maxLength];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * maxValue+1);
        }
        return arr;
    }
    public static int[] copy(int[] arr){
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    public static void swap(int[] arr,int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void selectionSort(int[] arr){
        if (arr == null|| arr.length < 2){
            return;
        }
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int minValueIndex = i;
            for (int j = i+1; j < N ; j++) {
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr,minValueIndex,i);
        }
    }

    public static void bubbleSort(int[] arr){
        if (arr == null|| arr.length < 2){
            return;
        }
        int N = arr.length;
        for (int end = N-1; end > 0 ; end--) {
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i+1] ){
                    swap(arr,i,i+1);
                }
            }
        }
    }


    public static void insertionSort(int[] arr){
        if (arr == null|| arr.length < 2){
            return;
        }
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            //pre >= 0 && arr[pre] > arr[pre+1] ；   先判断pre >= 0，才不会导致数组越界
            for (int pre = i-1;   pre >= 0 && arr[pre] > arr[pre+1] ; pre--) {
                swap(arr,pre,pre+1);
            }
        }
    }

    /**
     *
     * @param arr   原始数组
     * @param arr2  排序数组
     * @return
     */
    public static boolean checkSort(int[] arr,int[] arr2){
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int timetest = 10000;
        System.out.println("开始测试");
        retry:
        for (int i = 0; i < timetest; i++) {
            int[] arr = generateRandomArr(20, 10);
            int[] copy = copy(arr);
//            selectionSort(copy);
//            bubbleSort(copy);
            insertionSort(copy);
            if(!checkSort(arr,copy)){
                System.out.println("排序失败！");
                printArr(arr);
                printArr(copy);
                break retry;
            }
        }
        System.out.println("测试结束");


    }


}
