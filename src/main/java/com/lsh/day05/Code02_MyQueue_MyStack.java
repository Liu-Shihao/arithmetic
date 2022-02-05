package com.lsh.day05;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/3 11:56 下午
 * @desc ：单链表和双链表结构
 * 1.单链表反转
 * 2.双链表反转
 * 3.单链表实现队列（先进先出）
 * 4.单链表实现栈（先进后出）
 */
public class Code02_MyQueue_MyStack {


    public static class Node<V>{
        public V value;
        public Node<V> next;

        public Node(V v){
            this.value = v;
            this.next = null;
        }
    }

    /**
     * 1. 单链表实现队列结构
     * @param <V>
     */
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
                //如果尾部没有元浸塑，即队列为空，则，头尾都指向cur当前元素
                head = cur;
                tail = cur;
            }else {
                //队列不为空，则当前的尾部的next指针指向cur，tail来到cur（最后一位）
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
            //如果head不为空，则返回head的值，并将head记录为head的next，size-1；
            if (head != null){
                ans = head.value;
                head = head.next;
                size--;
            }
            //如果此时head为空，即队列为空，则将头尾一致head=null tail= null
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
     * 2. 单链表实现栈结构
     * @param <V>
     */
    public static class MyStack<V>{
        private Node<V>  head;
        private int size;

        public MyStack(){
            this.head = null;
            this.size = 0;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public int size(){
            return size;
        }

        /**
         * 入栈
         * @param value
         */
        public void push(V value){
            Node cur = new Node(value);
            if (head == null){
                //如果栈为空， 则head = cur
                head = cur;
            }else {
                //如果栈不为空，则cur的next指向当前head，并且head记录为cur
                cur.next = head;
                head = cur;
            }
            size++;
        }

        /**
         * 弹出栈元素
         * @return
         */
        public V pop(){
            V ans = null;
            if (head != null){
                //如果栈不为空，则直接返回head的value，并且head记录为head的next指针。
                ans = head.value;
                head = head.next;
                size--;
            }
            return ans;
        }

        public V peek(){
            return head.value == null ? null:head.value;
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

    /**
     * 栈 对数器
     */
     public static void testStack() {
        MyStack<Integer> myStack = new MyStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myStack.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * maxValue);
                myStack.push(num);
                test.push(num);
            } else if (decide < 0.66) {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.pop();
                    int num2 = test.pop();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myStack.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myStack.isEmpty()) {
            int num1 = myStack.pop();
            int num2 = test.pop();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
     }

    public static void main(String[] args) {
//        testQueue();
        testStack();
    }
}
