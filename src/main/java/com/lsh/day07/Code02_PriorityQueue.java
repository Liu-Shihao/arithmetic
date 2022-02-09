package com.lsh.day07;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/9 11:59 上午
 * @desc ：优先队列 PriorityQueue
 * 1. PriorityQueue 小根堆 小的优先:PriorityQueue<Integer> head = new PriorityQueue<>();
 * 2. 通过比较器改造小跟堆，成为大跟堆：大的优先.PriorityQueue<Integer> head = new PriorityQueue<>(new MyComparator());
 */
public class Code02_PriorityQueue {

    public static class MyComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1<o2){
                return 1;
            }else if (o1 > o2){
                return -1;
            }else {
                return 0;
            }
        }
    }
    public static void main(String[] args) {
        PriorityQueue<Integer> head = new PriorityQueue<>(new MyComparator());
        head.add(1);
        head.add(29);
        head.add(50);
        head.add(0);
        head.add(-2);
        System.out.println(head.peek());
        System.out.println("=======");
        while (!head.isEmpty()){
            System.out.println(head.poll());
        }

    }
}
