/**
 * @author ：LiuShihao
 * @date ：Created in 2022/6/13 3:42 下午
 * @desc ：
 */
public class Code01_Sort {

    public static void main(String[] args) {
        int testTime = 10;
        for (int i = 0; i < testTime; i++) {
            int[] generate = ArrUtil.generate(20, 10);
//            selectSort(generate);
//            bubbleSort(generate);
//            insertSort(generate);
//            merger(generate);
            quickSort(generate);
            System.out.println("\n--------------");
            ArrUtil.print(generate);
        }

    }

    /**
     * 选择排序：
     * 时间复杂度O(n^2)
     * 空间复杂度O(1)
     * 不稳定
     * @param arr
     */
    public static void selectSort(int[] arr){
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            int minValueIndex = i;
            for (int j = i+1; j < length; j++) {
                minValueIndex = arr[minValueIndex] < arr[j] ? minValueIndex : j;
            }
            ArrUtil.swap(arr,minValueIndex,i);
        }
    }

    /**
     * 冒泡排序
     * 时间复杂度O(n^2)
     * 空间复杂度O(1)
     * 稳定
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        int N = arr.length;
        for (int end = N-1; end >= 0; end--) {
            for (int second = 1; second <= end ; second++) {
                if (arr[second] < arr[second-1]){
                    ArrUtil.swap(arr,second,second-1);
                }
            }
        }
    }

    /**
     * 插入排序
     * 时间复杂度O(n^2)
     * 空间复杂度O(1)
     * 稳定
     * @param arr
     */
    public static void insertSort(int[] arr){
        int N = arr.length;
        for (int end = 1; end < N ; end++) {
            for (int pre = end -1; (pre>=0)&& arr[pre] > arr[pre+1];pre--){
                ArrUtil.swap(arr,pre,pre+1);
            }
        }
    }

    /**
     * 归并排序
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(n) 借助辅助数组
     * 稳定
     * @param arr
     */
    public static void merger(int[] arr){
        process(arr,0,arr.length-1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L==R) return;
        int mid = L + (R-L)/2;
        process(arr,L,mid);
        process(arr,mid+1,R);
        merge(arr,L,mid,R);
    }

    private static void merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int index = 0,p1 = L,p2 = mid+1;
        while (p1<=mid && p2 <= R){
            help[index++] = arr[p1] < arr[p2] ? arr[p1++]:arr[p2++];
        }
        while (p1<=mid){
            help[index++] = arr[p1++];
        }
        while (p2 <= R){
            help[index++] = arr[p2++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L++] = help[i];
        }

    }

    /**
     * 快速排序
     * 不稳定
     * 时间复杂度O(nlogn)
     * 空间复杂度O(logn)
     * @param arr
     */
    public static void quickSort(int[] arr){
        process2(arr,0,arr.length-1);
    }

    private static void process2(int[] arr, int L, int R) {
        if (L>=R) return;
        int[] equals = partition(arr,L,R);
        process2(arr,L,equals[0]-1);
        process2(arr,equals[1]+1,R);
    }

    //在L..R上分割
    private static int[] partition(int[] arr, int L, int R) {
        int lessR =L-1,moreL = R,index = L;
       while (index<moreL){
           if (arr[index] < arr[R]){
               ArrUtil.swap(arr,++lessR,index++);
           }else if(arr[index] > arr[R]){
               ArrUtil.swap(arr,index,--moreL);
           }else {
               index++;
           }
       }
        ArrUtil.swap(arr,index,R);
        return new int[]{lessR+1,moreL};
    }

    /**
     * 堆排序
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     * 稳定
     * @param arr
     */
    public static void  heapSort(int[] arr){


    }


    public static void heapify(int[] arr,int index,int heapSize){

    }





}
