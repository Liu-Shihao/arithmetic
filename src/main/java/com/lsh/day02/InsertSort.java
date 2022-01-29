package com.lsh.day02;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/1/27 1:51 下午
 * @desc ：插入排序：就想打牌一样，新来一张牌，往左边比，在合适的地方插入
 */
public class InsertSort {
    /**
     * 交换数组中 i和j的位置
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr,int i , int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
     * 插入排序：
     * 对0 ~ 0范围排序
     * 对0 ~ 1范围排序 对arr[1]排序 和arr[0]比较， 如果arr[1]小于arr[0]，交换位置。
     * 对0 ~ 2范围排序 对arr[2]排序 和arr[1]比较， 如果arr[2]小于arr[1]，交换位置，然后在对arr[1]排序 和arr[0]比较， 如果arr[1]小于arr[0]，交换位置;如果arr[2]大于于arr[1]则不交换。
     * 对0 ~ 3范围排序
     * .....
     * 对0 ~ n-1范围排序
     *
     *
     * @param arr
     */
    public static void insertSort1(int[] arr){
        if (arr == null || arr.length<2){
            return;
        }
        // 0-0范围上有序
        // 0-1范围上有序
        // 0-2范围上有序
        // 0-3范围上有序
        // 0-n-1 范围上有序
        //变量从1开始
        int N = arr.length;
        for (int end = 1; end < N; end++) {
            //end 表示待排序位置的元素
            int nextValueIndex = end;
            //当前位置的前一位不为空，并且前一位的值大于当前位置，交换元素
            //当前位置的前一位没有元素了，或者 当前位置的值 大于 前一位的值了（因为前面已经是有序的了） 停止循环
            while ((nextValueIndex-1 >= 0) && (arr[nextValueIndex-1] > arr[nextValueIndex])){
                swap(arr,nextValueIndex,nextValueIndex-1);
                nextValueIndex-- ;
            }
        }
    }

    /**
     * 优化插入排序：第二种写法
     * @param arr
     */
    public static void insertSort2(int[] arr){
        if (arr == null || arr.length<2){
            return;
        }
        int N = arr.length;
        for (int end = 1; end < N; end++) {
            //end 表示当前值的位置 ，即新数的位置。  end-1 end
            //pre 表示新数的前一个数 ，所以 pre+1 表示当前比较到的位置     pre   pre+1
            // 如果当前位置的前面还有元素，并且当前位置的值 大于 前一个位置的值 则交换位置。 pre--
            for (int pre =  end -1 ; (pre >= 0) && (arr[pre] > arr[pre+1]); pre--) {
                swap(arr,pre,pre+1);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 6, 2, 7, 1, 9, 5, 5, 2, 3};
        printArray(arr);
//        insertSort1(arr);
        insertSort2(arr);
        printArray(arr);
    }

}
