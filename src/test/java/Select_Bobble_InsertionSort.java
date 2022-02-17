import java.util.Arrays;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/16 11:02 上午
 * @desc ：选择、冒泡、插入 排序
 */
public class Select_Bobble_InsertionSort {

    /**
     * 遍历打印数组元素
     * @param arr
     */
    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print( arr[i] +" ");
        }
        System.out.println();
    }

    /**
     * 随机生成一个数组
     * @param maxLength 数组最大长度
     * @param maxValue 数组中元素最大值
     * @return
     */
    public static int[] generateRandomArr(int maxLength,int maxValue){
        maxLength = (int)(Math.random() * maxLength+1);
        int[] arr = new int[maxLength];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * maxValue+1);
        }
        return arr;
    }

    /**
     * 复制数组
     * @param arr
     * @return 返回一个新的相同的数组
     */
    public static int[] copy(int[] arr){
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    /**
     * 交换数组中两个位置的数
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr,int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 0 ~ n-1 、1 ~ n-1 、2 ~ n-1 、3 ~ n-1   由外层循环控制  i 从 0 开始    i++
     * 内层循环，从i之后的位置开始比较，找到最小值的位置。
     * @param arr
     */
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

    /**
     * 冒泡排序
     * 0 ~ n-1  范围
     * 0 ~ n-2  范围
     * 0 ~ n-3  范围
     * 0 ~ n-4  范围
     *
     * 外层循环控制end范围，注意 end可以为0，但没必要，因为当只剩下0 和 1 两个元素后 （end = 1）比较完成后只剩下一个数（end=0），没有意义再进行一排序了
     * 内层循环 second 表示比较的第二位元素 和second -1 前一位元素进行比较
     * 外层循环从0开始，内层循环second表示第二位元素，所以second = 1
     * 注意 second 可以到达最后一位 end 所以 second <= end
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        if (arr == null|| arr.length < 2){
            return;
        }
        int N = arr.length;
        for (int end = N-1; end > 0 ; end--) {
            for (int second = 1; second <= end; second++) {
                if (arr[second] < arr[second-1] ){
                    swap(arr,second,second-1);
                }
            }
        }
    }

    /**
     * 插入排序
     * 0 ~ n-1 排序
     * 1 ~ n-1 排序
     * 2 ~ n-1 排序
     * 3 ~ n-1 排序
     * 外层循环 i 从 0 开始(或者直接从1开始比较)，i ++  到n-1为止
     * @param arr
     */
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
            bubbleSort(copy);
//            insertionSort(copy);
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
