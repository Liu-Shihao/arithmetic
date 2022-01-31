/**
 * @author ：LiuShihao
 * @date ：Created in 2022/1/31 1:05 下午
 * @desc ： 廿九
 */
public class Review20220131 {
    public static void main(String[] args) {

        int[] arr = {7, 1, 4, 8, 9, 4, 6, 2};
        printArr(arr);
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int minValueindex = i;
            //TODO : 易错点 j=1； 正确的是j从 i+1处开始，即i的下一位
            for (int j = 1+i; j < N; j++) {
                minValueindex = arr[minValueindex] > arr[j] ? j : minValueindex;
            }
            int temp = arr[i];
            arr[i] = arr[minValueindex];
            arr[minValueindex] = temp;
        }
        printArr(arr);

    }

    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length ; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }



}
