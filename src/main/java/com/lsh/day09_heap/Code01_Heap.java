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
     * 添加节点，元素值大的向上浮
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

}
