package com.lsh.day07;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/9 1:42 下午
 * @desc ：合并K个升序列表：可以直接使用小根堆实现 优先队列
 *
 */
public class Code03_Q1 {
    public static class ListNode{
        public int value;
        public ListNode next;

        public ListNode(int v){
            this.value = v;
            next = null;
        }
    }
    //定义 ListNode 的比较器
    public static class MyComparator implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.value-o2.value;
        }
    }

    /**
     * heap 意思是堆
     * @param lists 单链表集合 表示有K个链表
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists){
        if (lists == null){
            return null;
        }

        PriorityQueue<ListNode> heap = new PriorityQueue<>(new MyComparator());

        for (int i = 0; i < lists.length; i++) {
            //把每个链表的第一个元素（头节点）都加进 堆heap 去
            heap.add(lists[i]);
        }
        if (heap.isEmpty()){
            return null;
        }
        //弹出堆中最小的元素 作为新链表的头节点
        ListNode head = heap.poll();
        ListNode pre = head;
        if (pre.next != null){
            heap.add(pre.next);
        }
        while (!heap.isEmpty()){
            ListNode cur = heap.poll();
            pre.next = cur;
            pre = cur;
            if (pre.next != null){
                heap.add(pre.next);
            }
        }
        return head;
    }

    /**
     * 链表1：0 -> 4 -> 6 -> 7
     * 链表2：3 -> 5 -> 8 -> 8
     * 链表3：1 -> 3 -> 5 -> 10
     *
     * 合并后：0 -> 1 -> 3 -> 3 -> 4 -> 5 -> 5 -> 6 -> 7 -> 8 -> 8 -> 10 ->
     * @param args
     */
    public static void main(String[] args) {
        ListNode head1 = new ListNode(0);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(6);
        head1.next.next.next = new ListNode(7);

        ListNode head2 = new ListNode(3);
        head2.next = new ListNode(5);
        head2.next.next = new ListNode(8);
        head2.next.next.next = new ListNode(8);

        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(3);
        head3.next.next = new ListNode(5);
        head3.next.next.next = new ListNode(10);

        ListNode[] lists = new ListNode[]{head1, head2, head3};

        ListNode head = mergeKLists(lists);
        printLinkedList(head);



    }

    public static void printLinkedList(ListNode head){
        while (head != null){
            System.out.print(head.value + " -> ");
            head = head.next;
        }
        System.out.println();
    }
}
