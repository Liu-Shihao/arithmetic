package com.lsh.day05_linked;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/7 1:51 下午
 * @desc ：两个有序链表的合并
 */
public class Code07MergeLinked {

    public static class ListNode{
        public int value;
        public ListNode next;

        public ListNode(int v){
            value = v;
            next = null;
        }
    }

    /**
     * 合并两个有序两表
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode head1,ListNode head2){
        //边界条件 如果head1为null则返回head2；head2位null返回head1
        if (head1 == null || head2 == null){
            return head1 == null ? head2 : head1;
        }
        //确定头节点，值小的为头节点
        ListNode head = head1.value < head2.value ? head1 : head2;
        // cur1 为头节点的下一位
        ListNode cur1 = head.next;
        // cur2 为另一个链表的头节点
        ListNode cur2 = head == head1 ? head2 : head1;
        // 定义pre为当前节点的上一个节点
        ListNode pre = head;
        //cur1和cur2都不为空
        while (cur1 != null && cur2 != null){
            if (cur1.value <= cur2.value){
                pre.next = cur1;
                //此时pre来到 cur1
                //cur1 来到下一位
                cur1 = cur1.next;
            }else {
                //head1.value > head2.value
                pre.next = cur2;
                cur2= cur2.next;
            }
            //pre 向下移动一位
            pre = pre.next;
        }
        //cur1和cur2有一个为空 或者两个都为空
        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }

    /**
     * 1 -> 3 -> 5 -> 7 -> 9
     * 2 -> 4 -> 6 -> 8 -> 10 -> 12 -> 14
     * @param args
     */
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(3);
        head1.next.next = new ListNode(5);
        head1.next.next.next = new ListNode(7);
        head1.next.next.next.next = new ListNode(9);

        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(4);
        head2.next.next = new ListNode(6);
        head2.next.next.next = new ListNode(8);
        head2.next.next.next.next = new ListNode(10);
        head2.next.next.next.next.next = new ListNode(12);
        head2.next.next.next.next.next.next = new ListNode(14);
        ListNode head = mergeTwoLists(head1, head2);
        while (head != null){
            System.out.print(head.value + " -> ");
            head = head.next;
        }
        System.out.println();
    }
}
