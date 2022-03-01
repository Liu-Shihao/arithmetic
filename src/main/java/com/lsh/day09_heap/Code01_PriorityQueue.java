package com.lsh.day09_heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/28 4:20 下午
 * @desc ：堆 就是 优先队列 PriorityQueue
 * 优先队列 PriorityQueue
 * PriorityQueue 小根堆 小的优先:PriorityQueue<Integer> head = new PriorityQueue<>();
 */
public class Code01_PriorityQueue {

    public static class  MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
    public static void main(String[] args) {
        //优先级队列 就是堆结构
        //如果不是基础数据类型，不指定比较器会报错，因为不知道怎么排序
        //默认小根堆
        PriorityQueue<Integer> smallHeap = new PriorityQueue<>();
        smallHeap.add(1);
        smallHeap.add(3);
        smallHeap.add(3);
        smallHeap.add(3);
        System.out.println(smallHeap.peek());
        smallHeap.add(0);
        smallHeap.add(7);
        System.out.println(smallHeap.peek());
        while (!smallHeap.isEmpty()){
            //小根堆 从小到大 依次弹出
            System.out.println(smallHeap.poll());
        }
        System.out.println("============");
        //Lambda表达式写法
//        PriorityQueue<Integer> bigHeap = new PriorityQueue<Integer>((o1,o2)-> o2-o1);
        PriorityQueue<Integer> bigHeap = new PriorityQueue<>(new Code01_PriorityQueue.MyComparator());
        bigHeap.add(4);
        bigHeap.add(10);
        bigHeap.add(7);
        System.out.println(bigHeap.peek());
    }
}
