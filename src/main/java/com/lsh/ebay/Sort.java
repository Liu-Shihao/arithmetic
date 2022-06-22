package com.lsh.ebay;

import javax.annotation.Resource;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/6/18 10:03 下午
 * @desc ：常见排序算法
 */
public class Sort {

    public static void main(String[] args) {
        int[] arr = {8, 2, 6, 3, 9, 1, 4, 0, 2};
        printArr(arr);
        heapSort(arr);
        printArr(arr);

    }


    public static void selectSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        for (int i = 0; i < arr.length ; i++) {
            int minValueindex = i;
            for (int j = 0; j < arr.length; j++) {
                minValueindex = arr[minValueindex] > arr[j] ? j:minValueindex;
            }
            swap(arr,minValueindex,i);
        }
    }

    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        for (int end = arr.length; end >= 0  ; end--) {
            for (int second = 1; second < end; second++) {
                if (arr[second -1] > arr[second]) {
                    swap(arr,second,second-1);
                }
            }
        }
    }


    public static void insertSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        //end 表示待排序元素
        for (int end = 1; end < arr.length ; end++) {
            //pre 表示前一位已经有序的元素
            for (int pre = end - 1; pre>=0 && arr[pre]>arr[pre+1]; pre--) {
                swap(arr,pre,pre+1);
            }
        }
    }

    public static void megerSort(int[] arr){
        if (arr == null || arr.length < 2)return;
        process(arr,0,arr.length-1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L==R)return;
        int mid = L+(R-L)/2;
        process(arr,L,mid);
        process(arr,mid+1,R);
        meger(arr,L,mid,R);
    }

    private static void meger(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int index = 0, p1 = l,p2 = mid+1;
        while (p1 <= l && p2 <= r){
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1<=l){
            help[index++] = arr[p1++];
        }
        while (p2<=r){
            help[index++] = arr[p2++];
        }
        for (int i = 0; i < help.length ; i++) {
            arr[l++] = help[i];
        }
    }

    public static void quickSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        process2(arr,0,arr.length-1);
    }

    private static void process2(int[] arr, int l, int r) {
        if (l>=r){
            return;
        }
        int temp = arr[r];
        //r-l+1 一般用来表示 当前数组的长度
        int randomIndex = (int)(Math.random() * (r-l+1));
        arr[r] = arr[randomIndex];
        arr[randomIndex] = temp;
        //在从l 到 r 范围上做划分
       int[] equals =  partition(arr,l,r);
       process(arr,l,equals[0]);
       process(arr,equals[1]+1,r);//注意是从equals[1]+1位置开始
    }

    private static int[] partition(int[] arr, int l, int r) {
        int lessR = l-1;//小于区域的右边界 ，刚开始没有小于区域 所以从0 开始
        int moreL = r;//大于区域的左边界，刚开始没有大于区域 所以从最后一位开始
        int index = l;//从l位置开始比较
        while (index<moreL){//当比较位置和大于区域的左边界接触，则停止
            //开始比较：用当前位置的元素和arr[R]位置（最后一位）进行比较
            if (arr[index] < arr[r]){
                //小于区域 :index ++ 小于区域的右边界向右移动一位
                swap(arr,index++,++lessR);
            }else if (arr[index] > arr[r]){
                //大于区域：index 不变，大于区域的左边界向左移动一位
                swap(arr,index,--moreL);
            }else {
                //相等 则index++
                index++;
            }
        }
        //当index指针和大于区域的左边界重合 结束循环 此时arr[index] = arr[moreL]
        //别忘了 交换最后一位元素arr[r] 和 arr[index]的位置
        swap(arr,index,r);
        //将等于区域的第一位和最后一位的索引返回
        return new int[]{lessR+1,moreL};
    }

    public  static void swap(int[] arr ,int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 父节点：(i-1)/2
     * 左孩子：2i+1
     * 右孩子：2i+2
     * @param arr
     */
    public static void heapSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        //从下往上建立大跟堆，通过下沉节点实现排序
        for (int i = arr.length-1; i >= 0; i--) {
            heapify(arr,i,arr.length);
        }
        //此时数组为大跟堆，即父节点都比子节点大
        printArr(arr);
        int heapSize = arr.length;
        //此时数组为大跟堆，将第一位元素（最大值）和最后一位元素交换位置，然后将末尾元素从堆里断开
        swap(arr,0,--heapSize);
        while (heapSize>0){
            //下沉第一位元素
            heapify(arr,0,heapSize);
            //重新形成大跟堆，再将第一位元素（目前最大值）交换到最后一位，并断开
            swap(arr,0,--heapSize);
        }
    }

    /**
     * i = arr.length-1
     * heapify(arr,i,arr.length);
     * 即 节点倒序遍历
     *
     *
     * @param arr
     * @param index  当前节点的位置
     * @param heapSize  堆的大小
     */
    private static void heapify(int[] arr, int index, int heapSize) {
        //左孩子
        int left = index*2+1;
        //求出左孩子位置是否在堆内
        while (left < heapSize){
            //比较出左右孩子中最大的一个:如果没有右孩子则左孩子最大，如果存在右孩子，并且比左孩子大，则去右孩子
            int bigger = left + 1<heapSize && arr[left] < arr[left+1] ? left+1 : left;
            bigger = arr[index] > arr[bigger] ? index:bigger;
            if (bigger == index) break;//如果最大就是i当前位置，则找到合适位置，结束循环
            //否则交换父节点和子节点中最大一个的位置
            swap(arr,index,bigger);
            index = bigger;
            left = index*2 +1;
        }

    }


    public static void printArr(int[] arr){
        for (int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println();
    }

}
