package com.lsh.day05;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/6 4:32 下午
 * @desc ：题目2：两个链表相加
 */
public class Code06 {
    public static class ListNode{
        public int value;
        public ListNode next;
        public ListNode(int v){
            value = v;
            next = null;
        }
    }

    /**
     *
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
            curNum = curL.value + curS.value + carry;
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


}
