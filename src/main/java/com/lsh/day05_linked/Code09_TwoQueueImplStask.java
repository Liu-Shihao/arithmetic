package com.lsh.day05_linked;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/21 3:54 下午
 * @desc ：使用两个队列结构实现栈结构
 * 使用LinkedList做队列结构
 */
public class Code09_TwoQueueImplStask<T> {

    public class TwoQueueImplStack{
        private Queue<T> queue;
        private Queue<T> help;

        public TwoQueueImplStack(){
            queue = new LinkedList<T>();
            help = new LinkedList<T>();
        }

        public void push(T t){
            queue.offer(t);
        }

        public T poll(){
            while (queue.size()>1){
                help.offer(queue.poll());
            }
            T poll = queue.poll();
            Queue<T> temp = queue;
            queue = help;
            help = temp;
            return poll;
        }

        public T peek(){
            while (queue.size()>1){
                help.offer(queue.poll());
            }
            T poll = queue.poll();
            help.offer(poll);
            Queue<T> temp = queue;
            queue = help;
            help = temp;
            return poll;
        }
    }
}
