package com.lsh.day05_linkedlist;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/10 3:27 下午
 * @desc ：  Stack 栈 先进后出   弹夹结构
 * 1.使用链表实现栈功能
 * 2.使用数组实现栈功能   速度最快
 *
 */
public class Code15_Stack {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 1000000 ; i++) {
            stack.push(i);
        }
        while (!stack.isEmpty()){
            Integer pop = stack.pop();
//            System.out.print(pop+" ");
        }
        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println("---------stack 耗时："+(end-start));


        long start2 = System.currentTimeMillis();

        LinkedList<Integer> linkedListStack = new LinkedList<>();
        for (int i = 0; i <1000000 ; i++) {
            linkedListStack.addLast(i);
        }
        while (!linkedListStack.isEmpty()){
            Integer last = linkedListStack.pollLast();
//            System.out.print(last+" ");
        }
        long end2 = System.currentTimeMillis();
        System.out.println();
        System.out.println("---------linkedListStack 耗时："+(end2-start2));


        long start3 = System.currentTimeMillis();
        int[] arrStack = new int[1000000];
        int index = 0;
        for (int i = 0; i < 1000000; i++) {
            arrStack[index++] = i;
        }
        for (int i = 0; i < 1000000; i++) {
//            System.out.print(arrStack[--index]+" ");
        }

        long end3 = System.currentTimeMillis();
        System.out.println();
        System.out.println("---------arrStack 耗时："+(end3-start3));


    }
}
