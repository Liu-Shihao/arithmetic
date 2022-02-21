package com.lsh.day05_linked;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/5 5:27 下午
 * @desc ：双链表实现双端队列（头部尾部都可以入队和出队）
 */
public class Code03_MyDoubleQueue {
    /**
     * 双链表结构
     * @param <V>
     */
    public static class Node<V>{
        private Node<V> last;
        private Node<V> next;
        private V value;

        public Node(V v){
            this.last = null;
            this.next = null;
            value = v ;
        }

    }

    public static class MyDoubleQueue<V>{
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public MyDoubleQueue(){
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty(){
            return size == 0;
        }
        public int size(){
            return size;
        }

        /**
         * 从头部加元素
         * @param v
         */
        public void lpush(V v){
            Node<V> cur = new Node<>(v);
            //队列为空
            if (head == null){
                head = cur;
                tail = cur;
            }else {
                //队列不为空    head
                //     null     1   2   3   null
                //null   4(cur)   1   2   3   null
                //      head
                cur.next = head;
                head.last = cur;
                head = cur;
            }
            size ++;
        }

        /**
         * 从头部弹出元素
         * @return
         */
        public V lpop(){
            V ans = null;
            //队列为空
            if(head == null){
                return ans;
            }
            //队列不为空，弹出一个元素，队列大小-1
            size -- ;
            ans = head.value;
            //队列只有一个元素，弹出元素后，head和tail 置空
            if (head == tail){
                head = null;
                tail = null;
            }else {
                //队列中存在两个及以上元素，弹出头部元素后，head移动到next指针，并将此时的head的last置为null。
                head = head.next;
                head.last = null;
            }
            return ans;
        }

        /**
         * 从尾部加入元素
         * @param v
         */
        public void rpush(V v){
            Node<V> cur = new Node<>(v);
            if (tail == null){
                //如果队列为空
                head = cur;
                tail = cur;
            }else {
                //队列不为空                 tail
                // null     1       2       3       null
                // null     1       2       3       4       null
                //                                  tail
                cur.last = tail;
                tail.next = cur;
                tail = cur;
            }
            size ++;
        }
        public V rpop(){
            V ans = null;
            if (tail == null){
                return ans;
            }
            size --;
            ans = tail.value;
            if (head == tail){
                head = null;
                tail = null;
            }else {
                tail = tail.last;
                tail.next = null;
            }
            return ans;

        }

        public V lpeek(){
            return head == null ? null : head.value;
        }
        public V rpeek(){
            return tail == null ? null : tail.value;
        }

    }




}
