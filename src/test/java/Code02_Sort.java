/**
 * @author ：LiuShihao
 * @date ：Created in 2022/6/17 9:34 下午
 * @desc ：
 */
public class Code02_Sort {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int[] array = ArrUtil.generate(20, 20);
            mergeSort(array);
            ArrUtil.print(array);
        }

    }
    //选择排序  不稳定
    public static void selectSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            int minValueIndex = i;
            for (int j = i+1; j < length; j++) {
                minValueIndex = arr[minValueIndex] < arr[j] ? minValueIndex:j;
            }
            int temp = arr[i];
            arr[i] = arr[minValueIndex];
            arr[minValueIndex] = temp;
        }
    }
    //冒牌排序 不稳定
    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        int length = arr.length;
        for (int end = length; end >= 0 ; end--) {
            for (int second = 1; second < end; second++) {
                if (arr[second] < arr[second-1]){
                    int temp = arr[second];
                    arr[second] = arr[second-1];
                    arr[second-1] = temp;
                }
            }
        }

    }
    //插入排序
    public static void  insertSort(int[] arr){
        if(arr == null|| arr.length < 2) return;
        int length = arr.length;
        for (int end = 1; end < length; end++) {
            //pre 表示已排序的最后一位元素 pre+1 表示待排序元素
            for (int pre = end-1; pre >=0 && (arr[pre] > arr[pre+1]); pre--) {
                int temp = arr[pre+1];
                arr[pre+1] = arr[pre];
                arr[pre] = temp;
            }
        }
    }


    public static void mergeSort(int[] arr){
        if (arr == null || arr.length <2) return;
        process(arr,0,arr.length-1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L==R) return;
        int M = L + (R-L)/2;
        process(arr,L,M);
        process(arr,M+1,R);
        merge(arr,L,M,R);

    }

    private static void merge(int[] arr,int L,int M,int R) {
        int[] help = new int[R - L + 1];
        int index = 0 , p1 = L,p2=M+1;
        while (p1<=M && p2 <=R){
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1<=M){
            help[index++] = arr[p1++];
        }
        while (p2 <= R){
            help[index++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L++] = help[i];
        }
    }

}
