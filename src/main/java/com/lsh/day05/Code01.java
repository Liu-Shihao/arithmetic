package com.lsh.day05;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/3 7:42 下午
 * @desc ：单链表和双链表
 * 1.单链表的反转
 */
public class Code01 {

    /**
     * 单节点：值、next指针
     */
    public static class Node{
        public int value;
        public Node next;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 双节点：值、last指针、next指针
     */
    public static class DoubleNode{
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data){
            this.value = data;
        }
    }

    /**
     * 反转单链表
     * head
     *  |
     *  1 -> 2 -> 3 -> null
     *
     *  null <- 1 <- 2 <- 3
     * @param head
     * @return
     */
    public static Node reverseLinkedList(Node head){
        Node pre = null;
        Node next = null;
        /**
         * 反转单链表： 1 -> 2 -> 3 -> null
         * head为1时：
         * 1 的 next指针为 2
         * 反转时，先将next指针保留为next
         * 然后将 1 的next指针指向 null
         * 将当前head（1） 记录为 pre（上一个）
         * 将next （2） 记录为 head
         * -------------------
         * head为2时： pre 为 1
         * 将head（2）的next指针保留为next
         * 将head（2）的next指针指向pre
         * 将head（2）记录为pre
         * 将head（2）记录为next（3）
         * -------------------
         * head为3时：pre为2 同上，但是head（3）记录为next（null）
         * -------------------
         * head 为 null 结束循环，同时返回pre：此时head为3
         *
         */
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        // 123 -> 321
        n1 = reverseLinkedList(n1);
        while (n1 != null){
            System.out.print(n1.value+" ");
            n1 = n1.next;
        }

    }
}
