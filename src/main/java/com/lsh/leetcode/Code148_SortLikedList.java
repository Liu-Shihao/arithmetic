package com.lsh.leetcode;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/24 9:07 下午
 * @desc ： 给定一个单链表节点，将它变有序，返回头节点
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * 148. Sort List：https://leetcode.com/problems/sort-list/
 *
 */
public class Code148_SortLikedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        sortList(head);
    }


    public static class ListNode {
          int val;
          ListNode next;
          ListNode(int val) { this.val = val; }
    }

    /**
     * 主程序
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode midNode = getMidNode(head);
        ListNode left = sortList(head);
        ListNode right = sortList(midNode);
        head = merge(left,right);
        return head;

    }

    /**
     * 进行合并
     * @param head1
     * @param head2
     * @return
     */
    private static ListNode merge(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null){
            return head1 == null ? head2 : head1;
        }
        ListNode head = head1.val < head2.val ? head1:head2;
        ListNode cur1 = head.next;
        ListNode cur2 = (head == head1) ? head2 : head1;
        ListNode pre = head;
        while ( cur1 != null && cur2 != null){
            if (cur1.val < cur2.val){
                pre.next = cur1;
                cur1 = cur1.next;
            }else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }

    /**
     * 获取链表中点，并分两条链表
     * @param head
     * @return
     */
    public static ListNode getMidNode(ListNode head){
        ListNode preMid = null;
        while (head != null && head.next != null){
            preMid = (preMid == null) ? head : preMid.next;
            head = head.next.next;
        }
        ListNode mid = preMid.next;
        preMid.next = null;
        return mid;
    }


//    public ListNode sortList1(ListNode head) {
//        if (head == null){
//              return null;
//        }
//        if (head.next == null){
//          return head;
//        }
//        PriorityQueue<Integer> heap = new PriorityQueue<>();
//        while (head!= null){
//          heap.offer(head.val);
//          head = head.next;
//        }
//        head = new ListNode(heap.poll());
//        ListNode pre = head;
//        ListNode cur ;
//
//        while (!heap.isEmpty()){
//          cur = new ListNode(heap.poll());
//          pre.next = cur;
//          pre = cur;
//        }
//        return head;
//    }

}
