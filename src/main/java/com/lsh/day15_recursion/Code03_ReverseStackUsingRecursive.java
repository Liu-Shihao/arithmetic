package com.lsh.day15_recursion;

import java.util.Stack;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/8 5:50 下午
 * @desc ：递归练习3
 * 给你一个栈，请你逆序这个栈，
 * 不能申请额外的数据结构，
 * 只能使用递归函数。 如何实现?
 */
public class Code03_ReverseStackUsingRecursive {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);//头 3 2 1 尾
        reverse(stack);
        while (!stack.isEmpty()){
            System.out.println(stack.pop());// 头 1 2 3 尾
        }
    }


    public static void reverse(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        int last = f(stack);//弹出栈底元素
        reverse(stack);
        stack.push(last);//最后再将栈底元素放入栈中，此时即处于栈顶位置，完成栈逆序
    }


    // 取出栈底元素
    private static int f(Stack<Integer> stack){
        Integer result = stack.pop();//弹出栈顶元素
        if (stack.isEmpty()) {
            return result;//如果栈空则返回
        }else {
            int last = f(stack);//弹出下一个栈顶元素 ，此递归过程最终会把栈底元素返回
            stack.push(result);//将弹出的栈顶元素重新放回栈中
            return last;//将最终栈底元素返回
        }
    }


}
