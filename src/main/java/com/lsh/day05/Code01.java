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
     *
     * @param head
     * @return
     */
    public static Node reverseLinkedList(Node head){
        Node pre = null;
        Node next = null;
        /**
         * 反转链表
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
