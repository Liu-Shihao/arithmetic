package com.lsh.day05_linked;

import java.util.Stack;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/21 3:42 下午
 * @desc ：使用两个栈结构实现队列结构
 */
public class Code08_TwoStaskImplQueue {

    public static class TwoStackQueue{
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public TwoStackQueue(){
            pushStack = new Stack<Integer>();
            popStack = new Stack<Integer>();
        }

        /**
         * 向popStack倒数据
         * 1.必须保证popStack为空才能倒数据
         * 2.必须保证pushStack数据一次倒完
         */
        private void pushToPopStack(){
            if (popStack.isEmpty()){
                while (!pushStack.isEmpty()){
                    popStack.push(pushStack.pop());
                }
            }
        }

        public void add (int num){
            pushStack.push(num);
            pushToPopStack();
        }

        public int poll(){
            if (pushStack.isEmpty() && popStack.isEmpty()){
                throw new RuntimeException("Queue is empty!");
            }
            pushToPopStack();
            return popStack.pop();
        }

        public int peek(){
            if (pushStack.isEmpty() && popStack.isEmpty()){
                throw new RuntimeException("Queue is empty!");
            }
            pushToPopStack();
            return popStack.peek();
        }


    }



}
