/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/2 2:45 下午
 * @desc ：冒泡排序
 */
public class Review20220202 {

    public static void main(String[] args) {
        int[] arr = {7, 4, 2, 9, 6, 4, 1};
        printArr(arr);
//        bubbleSort(arr);
        bubbleSort2(arr);
        printArr(arr);

    }

    /**
     * TODO 易错点：second <= end;
     * end > 0 或 end >= 0 无所谓，因为最后一次最后一个数无所谓。
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        int N = arr.length;
        for (int end = N-1; end > 0 ; end--) {
            for (int second = 1; second <= end; second++) {
                if (arr[second-1] > arr[second]){
                    int temp = arr[second-1];
                    arr[second-1] = arr[second];
                    arr[second] = temp;
                }
                
            }
            
        }
    }

    /**
     * TODO 易错点：j = 0; j < N-1;  j从0开始和后一位的比较，所以j+1 要小于 N-1
     * @param arr
     */
    public static void bubbleSort2(int[] arr){
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N-1; j++) {
                if (arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }

            }

        }


    }

    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    
}
