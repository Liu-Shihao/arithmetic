package com.lsh.leetcode;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/25 1:43 下午
 * @desc ：2. Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/
 * 两链表相加
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 */
public class Code02_AddTwoNumbers {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null){
            return head1 == null ? head2 : head1;
        }
        int carry = 0;
        ListNode head;
        if ((head1.val+head2.val)>=10 ){
            carry = 1;
            head = new ListNode((head1.val+head2.val)%10);
        }else {
            head = new ListNode(head1.val+head2.val);
        }
        ListNode cur1 = head1.next;
        ListNode cur2 = head2.next;
        ListNode pre = head;
        while (cur1 != null || cur2 != null){
            cur1 = (cur1 == null)?new ListNode(0):cur1;
            cur2 = (cur2 == null)?new ListNode(0):cur2;
            //从低位开始相加
            int num = cur1.val + cur2.val + carry;
            if (num>=10){
                num %= 10;
                carry = 1;
            }else {
                carry = 0;
            }
            pre.next = new ListNode(num);
            pre = pre.next;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        if (carry != 0){
            pre.next = new ListNode(1);
        }
        return head;
    }


    //
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode p, dummy = new ListNode(0);
        p = dummy;
        while (l1 != null || l2 != null || carry != 0) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }
            p.next = new ListNode(carry%10);
            carry /= 10;
            p = p.next;
        }
        return dummy.next;
    }

    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
