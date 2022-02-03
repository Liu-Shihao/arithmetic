package com.lsh.day05;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/3 11:56 下午
 * @desc ：使用单链表结构实现队列
 */
public class Code02_MyQueue {



//    public static void testStack() {
//        MyStack<Integer> myStack = new MyStack<>();
//        Stack<Integer> test = new Stack<>();
//        int testTime = 5000000;
//        int maxValue = 200000000;
//        System.out.println("测试开始！");
//        for (int i = 0; i < testTime; i++) {
//            if (myStack.isEmpty() != test.isEmpty()) {
//                System.out.println("Oops!");
//            }
//            if (myStack.size() != test.size()) {
//                System.out.println("Oops!");
//            }
//            double decide = Math.random();
//            if (decide < 0.33) {
//                int num = (int) (Math.random() * maxValue);
//                myStack.push(num);
//                test.push(num);
//            } else if (decide < 0.66) {
//                if (!myStack.isEmpty()) {
//                    int num1 = myStack.pop();
//                    int num2 = test.pop();
//                    if (num1 != num2) {
//                        System.out.println("Oops!");
//                    }
//                }
//            } else {
//                if (!myStack.isEmpty()) {
//                    int num1 = myStack.peek();
//                    int num2 = test.peek();
//                    if (num1 != num2) {
//                        System.out.println("Oops!");
//                    }
//                }
//            }
//        }
//        if (myStack.size() != test.size()) {
//            System.out.println("Oops!");
//        }
//        while (!myStack.isEmpty()) {
//            int num1 = myStack.pop();
//            int num2 = test.pop();
//            if (num1 != num2) {
//                System.out.println("Oops!");
//            }
//        }
//        System.out.println("测试结束！");
//    }

    public static class Node<V>{
        public V value;
        public Node<V> next;

        public Node(V v){
            this.value = v;
            this.next = null;
        }
    }

    public static class MyQueue<V>{
        //三个属性
        //头
        private Node<V> head;
        //尾
        private Node<V> tail;
        //大小
        private int size;

        //无参构造
        public MyQueue(){
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        public boolean isEmpty(){
            return  size == 0;
        }

        public int size(){
            return size;
        }

        /**
         * 入队
         * @param value
         */
        public void offer(V value){
            Node<V> cur = new Node<>(value);
            if (tail == null){
                head = cur;
                tail = cur;
            }else {
                tail.next = cur;
                tail = cur;
            }
            size++;

        }

        /**
         * 弹出元素
         * @return
         */
        public V  poll(){
            V ans = null;
            if (head != null){
                ans = head.value;
                head = head.next;
                size--;
            }
            if (head == null){
                tail = null;
            }
            return ans;

        }

        /**
         * 查看队列尾部的元素
         * @return
         */
        public V peek(){
            V ans = null;
            if (head !=  null){
                ans = head.value;
            }
            return ans;
        }

    }

    /**
     * 队列 对数器
     */
    public static void testQueue() {
        MyQueue<Integer> myQueue = new MyQueue<>();
        Queue<Integer> test = new LinkedList<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myQueue.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myQueue.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * maxValue);
                myQueue.offer(num);
                test.offer(num);
            } else if (decide < 0.66) {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.poll();
                    int num2 = test.poll();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myQueue.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myQueue.isEmpty()) {
            int num1 = myQueue.poll();
            int num2 = test.poll();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

    public static void main(String[] args) {
        testQueue();
    }
}
