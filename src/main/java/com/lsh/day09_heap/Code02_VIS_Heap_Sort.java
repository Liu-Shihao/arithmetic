package com.lsh.day09_heap;

import com.lsh.day02_sort.SortUtil;

import java.util.PriorityQueue;

/**
 * 重点
 * @author ：LiuShihao
 * @date ：Created in 2022/2/28 4:21 下午
 * @desc ：Heap 堆 （优先级队列）
 * 重点： heapify方法（下沉节点）和heapInsert方法（上浮节点）
 * 小根堆 ：每个子树的最小值就是子头节点的值
 * 大根堆 ：每个子树的最大值就是子头节点的值
 * 完全二叉树就是堆结构。堆结构就是大跟堆或者小根堆
 *       0
 *    1      2
 *  3   4  5   6
 *  第i位置的子左节点：i*2+1  ；第i位置的子右节点：i*2+2  ；第i位置的父节点：(i-1)/2
 *  heapInsert 和 heapify
 *  堆排序
 *
 *  叶节点：向下没有节点了。如果有N个元素的完全二叉树，则它的叶节点有N/2个
 *
 *  算法题：已知一个无序数组，如果有序则每个元素移动的距离不超过K（K小于数组长度），选择一个合适的排序策略进行排序
 *  假设 k = 5
 *VeryImportant
 *
 */
public class Code02_VIS_Heap_Sort {

    //已知一个无序数组，如果有序则每个元素移动的距离不超过K（K小于数组长度），选择一个合适的排序策略进行排序
    public static void main(String[] args) {
        int K = 5;
        int[] arr = {5,3,0,7,2,1,10,9,4,6,8};
//        int K = 2;
//        int[] arr = {3,3,1,2,7,8,5,6,10,9};
        SortUtil.printArr(arr);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int N = arr.length;
        for (int i = 0; i <= K; i++) {
            heap.add(arr[i]);
        }
        //弹出一个 新增一个
        int i = 0;
        for (; i < N && K < N-1; i++) {
            arr[i] = heap.poll();
            heap.add(arr[++K]);
        }
        while (!heap.isEmpty()){
            arr[i++] = heap.poll();
        }
        SortUtil.printArr(arr);
    }

    /**
     * 堆排序
     * @param arr
     */
    public static void heapSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        //从上往下建立大跟堆 O(N*logN)
//        for (int i = 0; i < arr.length; i++) {//O(N)
//            //遍历元素，元素上浮，遍历完成后 数组形成大跟堆
//            heapInsert(arr,i);//O(logN)
//        }
        //从下往上建立大跟堆 O(N)
        for (int i = arr.length -1 ; i >= 0; i--) {
            heapify(arr,i,arr.length);
        }
        int heapSize = arr.length;
        //此时数组为大跟堆，将第一位元素（最大值）和最后一位元素交换位置，然后将末尾元素从堆里断开
        SortUtil.swap(arr,0,--heapSize);
        //O(N*logN)
        while (heapSize > 0){
            //下沉第一位元素
            heapify(arr,0,heapSize);//O(logN)
            //重新形成大跟堆，再将第一位元素（目前最大值）交换到最后一位，并断开
            SortUtil.swap(arr,0,--heapSize);//O(1)
        }

    }

    /**
     * 上浮节点
     * 添加节点，元素值大的向上浮
     * 不断和父节点比较，只到到达头节点或者比父节点小为止。
     * @param arr 表示堆结构
     * @param index
     */
    public static void heapInsert(int[] arr,int index){
        //当index来到0位置，或者index比自己的父节点大，则进入循环，交换该位置与父节点的位置
        while (arr[index] > arr[(index-1)/2]){
            SortUtil.swap(arr,index,(index-1)/2);
            index = (index-1)/2;
        }
    }

    /**
     * 下沉节点
     * 不断和自己的左右孩子比较，知道没有孩子或者没有左右孩子比自己大。
     * @param arr
     * @param index
     * @param heapSize
     */
    public static void heapify(int[] arr,int index,int heapSize){
        //求出左孩子 index * 2 + 1   ； 右孩子 ： index * 2 + 2
        int left = index * 2 +1;
        //如果左节点 在 堆的范围内
        while (left < heapSize){
            //larger 表示 左右孩子中最大的一个
            //如果右孩子存在，并且比左孩子大，则最大的是右孩子；否则是最大的是左孩子
            int larger = left +1 < heapSize && arr[left] < arr[left+1] ? left+1 : left;
            //比较左右孩子和父节点谁更大
            larger = arr[index] > arr[larger] ? index:larger;
            // 如果最大的节点就是父节点自己，找到合适的位置，则结束循环。
            if (larger == index){
                break;
            }
            SortUtil.swap(arr,index,larger);
            index = larger;
            left = index * 2 + 1;
        }
    }

}
