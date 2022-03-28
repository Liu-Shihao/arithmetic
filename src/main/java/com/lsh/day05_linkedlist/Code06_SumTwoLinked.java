package com.lsh.day05_linkedlist;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/6 4:32 下午
 * @desc ：题目2：两个链表相加
 *
 */
public class Code06_SumTwoLinked {
    public static class ListNode{
        public int value;
        public ListNode next;
        public ListNode(int v){
            value = v;
            next = null;
        }
    }

    /**
     * 两个链表相加
     * 分为三种情况：
     * 1.长短链表有元素。
     * 2.长链表有元素，端链表无元素。
     * 3.长短链表无元素，但是进位为1。
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode head1,ListNode head2){
        int len1 = listLength(head1);
        int len2 = listLength(head2);
        ListNode l = len1 >= len2 ? head1:head2;
        ListNode s = l == head1 ? head2 : head1;
        ListNode curL = l;
        ListNode curS = s;
        ListNode last = curL;
        //进位
        int carry = 0;
        int curNum= 0;
        while (curS != null){
            curNum = curL.value + curS.value + carry;
            //余数
            curL.value = (curNum % 10);
            //进位
            carry = curNum / 10;
            last = curL;
            curS = curS.next;
            curL = curL.next;
        }
        while (curL != null){
            curNum = curL.value  + carry;
            //余数
            curL.value = (curNum % 10);
            //进位
            carry = curNum / 10;
            last = curL;
            curL = curL.next;
        }
        if (carry != 0){
            last.next = new ListNode(1);
        }
        return l;
    }



    /**
     * 获得链表长度
     * @param head
     * @return
     */
    public static int listLength(ListNode head){
        int len = 0;
        while (head != null){
            len++;
            head = head.next;
        }
        return len;
    }

    /**
     *  从左到右是数字的低位到高位
     *   999 + 001 = 1000
     *   9->9->9 + 1->0->0 = 0->0->0->1
     * @param args
     */
    public static void main(String[] args) {
        ListNode head1 = new ListNode(9);
        head1.next = new ListNode(9);
        head1.next.next = new ListNode(9);
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(0);
        head2.next.next = new ListNode(0);
        ListNode newHead = addTwoNumbers(head1, head2);

        while (newHead!= null){
            System.out.print(newHead.value + " ");
            newHead = newHead.next;
        }
        System.out.println();
    }


}
