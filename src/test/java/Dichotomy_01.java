/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/17 3:03 下午
 * @desc ：二分法
 *
 * 找到有序数组中大于等于num最左边的数
 */
public class Dichotomy_01 {

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
     * 生成随机数组
     * @param maxLength
     * @param maxValue
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
     * 生成相邻不同的随机数组
     * @param maxLength
     * @param value
     * @return
     */
    public static  int[] generateRandomArray(int maxLength,int value){
        int len = (int) (Math.random() * (maxLength - 2 + 1)) + 2;
        int[] arr = new int[len];
        arr[0] = (int) (Math.random() * (value + 1));
        for (int i = 1; i < arr.length; i++) {
            do {
                arr[i] = (int) (Math.random() * (value + 1));
            }while (arr[i] == arr[i-1]);
        }
        return arr;
    }

    /**
     * 异或操作 不使用额外变量 交换值
     * 异或，相同为0 不同为1 ；就是无进位相加
     * 两个规律： 0 ^ N = N ；N ^ N = 0
     * a = a 异或 b;          此时 a 的值为  ：a 异或 b
     * b = a 异或 b 异或 b ;   (b 异或 b)结果为0,    0 ^ a = a;  所以 b = a; 此时 b = a;
     * a = a 异或 b 异或 a ;    a异或a结果为0, 0再异或b 则结果为b；所以 a = b;
     *
     * @param i
     * @param j
     */
    public static void swap(int[] arr,int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * 找到有序数组中大于等于num最左边的数
     * @param arr
     * @param num
     * @return
     */
    public static int findMoreLeftIndex(int[] arr,int num){
        int moreLeftIndex = -1;
        if (arr == null || arr.length == 0){
            return moreLeftIndex;
        }
        int L = 0;
        int R = arr.length;

        while (L <= R){
            //当R < L 时 结束循环
            int mid = L +((R-L)>>1);
            if (arr[mid]>= num){
                moreLeftIndex = mid;
                R = mid -1;
            }else {
                L = mid +1;
            }
        }
        return moreLeftIndex;
    }

    /**
     * 局部最小值问题
     * @param arr
     * @return
     */
    public static int getLessIndex(int[] arr){
        if (arr == null || arr.length == 0 ){
            return -1;
        }
        if (arr.length < 2 || arr[0] < arr[1]){
            return 0;
        }
        int N = arr.length;
        if (arr[N-1] < arr[N-2]){
            return N-1;
        }
        //左边界从第二位元素开始
        int L = 1;
        //右边界 从倒数第二位元素开始
        int R = N-2;
        while (L < R){
            int mid = L + ((R-L)>>1);
            //中间值 大于 左边
            if ( arr[mid] > arr[mid-1]){
                R = mid-1;
            }else if (arr[mid] > arr[mid+1]){
                //中间值 大于 右边
                L = mid + 1;
            }else {
                return mid;
            }
        }
        return L;
    }

    public static void main(String[] args) {
//        int[] arr = {0,1,1,2,5,5,5,5,5,6,7,7,8};
//        System.out.println(findMoreLeftIndex(arr,1));
        int testtime = 1000;
        for (int i = 0; i  < testtime; i++) {
            int[] arr = generateRandomArray(15, 20);
            printArr(arr);
            System.out.println(getLessIndex(arr));
            System.out.println("---------------------");
        }

    }
}
