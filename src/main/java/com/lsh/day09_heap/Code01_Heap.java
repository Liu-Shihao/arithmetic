package com.lsh.day09_heap;

import com.lsh.day02_sort.SortUtil;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/28 4:21 下午
 * @desc ：Heap 堆 （优先级队列）
 * 小根堆 ：每个子树的最小值就是子头节点的值
 * 大根堆 ：每个子树的最大值就是子头节点的值
 * 完全二叉树就是堆结构。堆结构就是大跟堆或者小根堆
 *       0
 *    1      2
 *  3   4  5   6
 *  第i位置的子左节点：i*2+1  ；第i位置的子右节点：i*2+2  ；第i位置的父节点：(i-1)/2
 *
 */
public class Code01_Heap {
    public static void main(String[] args) {
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
