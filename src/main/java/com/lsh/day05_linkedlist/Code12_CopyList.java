package com.lsh.day05_linkedlist;

import java.util.HashMap;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/26 5:57 下午
 * @desc ：复制链表
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class Code12_CopyList {

    public static class Node {
        public int value;
        public Node next;
        public Node random;//随机指针

        public Node(int data) {
            this.value = data;
        }
    }

    //方法1：使用容器
    public static Node copyRandomList1(Node head) {
        // Key 老节点
        // Value 新节点
        HashMap<Node, Node> hashMap = new HashMap<>();
        Node cur = head;
        while (cur != null){
            hashMap.put(cur,new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null){
            Node node = hashMap.get(cur);
            node.next = hashMap.get(cur.next);
            node.random = hashMap.get(cur.random);
            cur = cur.next;
        }
        return hashMap.get(head);

    }
    //方法2 ：不使用容器
    public static Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        // 1 -> 2 -> 3 -> null
        // 1 -> 1' -> 2 -> 2' -> 3 -> 3'
        while (cur != null){
            next =cur.next;
            cur.next = null;
            cur.next = new Node(cur.value);
            cur.next.next = next;//此步骤很重要，容易被忽略
            cur = next;
        }
        cur = head;
        Node copy = null;
        //复制random指针
        while (cur != null){
            next = cur.next.next;
            copy = cur.next;
            copy.random =  cur.random != null ? cur.random.next : null;//易错点
            cur = next;
        }
        //复制random指针后，将链表断开
        Node res = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next == null ? null :next.next ;
            cur = next;
        }
        return res;
    }


}
