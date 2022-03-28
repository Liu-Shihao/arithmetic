package com.lsh.day05_linkedlist;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/26 5:57 下午
 * @desc ：链表相交问题
 * 如果一个链表没有交点则返回null，如果有交点返回第一个交点
 */
public class Code13_VIS_FindFirstIntersectList {

    public static void main(String[] args) {
        System.out.println("==START==");
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);
        System.out.println("------");

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4


        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);
        System.out.println("------");
        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);
        System.out.println("==END==");


    }

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    //判断两个链表是否相交，并返回交点
    //1.分别判断两个链表是否是有环链表
    // 2.1 两个链表都是无环链表
    //  .  .(无交点)     .    .(有交点)
    //  .  .            .    .
    //  .  .             . .
    //  .  .               .
    // 2.2 一个有环、一个无环(此种情况两个链表一定不会相交，因为一个节点只有一个next指针，不会指向两个节点)
    // 2.3 两个链表都是有环链表（这种情况又分为三种情况）：
    //  2.3.1 两个有环链表没有相交
    //  2.3.2 两个有环链表在入环前相交
    //  2.3.3 两个链表再环上相交
    /**
     *   2.3.1:        2.3.2:  .        2.3.3:
     *   .    .          .   .          .     .
     *   .    .            .            .     .
     *   ...  ...          .            .     .
     *   ...  ...          .            .      .
     *                    . .       .   .   .   .
     *                    . .       .   .   .   .
     */
    public static Node getIntersectNode(Node head1,Node head2){
        if (head1 == null || head2 == null)return null;
        //1.判断两个链表是否有环
        Node loopNode1 = getLoopNode(head1);
        Node loopNode2 = getLoopNode(head2);
        if (loopNode1 == null && loopNode2 == null){
            //两个链表都是无环链表
            return noLoop(head1,head2);
        }
        if (loopNode1 != null && loopNode2 != null){
            System.out.println("第一个链表的入环节点："+loopNode1.value);
            System.out.println("第二个链表的入环节点："+loopNode2.value);
            //两个链表都有环
            return bothLoop(head1,loopNode1,head2,loopNode2);
        }
        //如果一个有环一个无环，则一定没有交点
        return null;

    }

    //两个链表都有环
    private static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        //1.如果两个有环两表的入环节点不相等，则不想交
        if (loop1 == loop2){
            System.out.println("两个入环接节点一样");
            //两个链表的入环节点是一个 如2.3.2情况
            //找一个交点，代码可以复用两个无环链表相交找交点的方式，原理一样
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            //注意：此处的判断与两个无环链表找交点不同，因为无环链表有null值，而有环链表是没有null的，所以是以交点为end节点
            while (cur1.next != loop1){
                n++;
                cur1 = cur1.next;
            }
            while (cur2.next != loop2){
                n--;
                cur2 = cur2.next;
            }
            if (cur1 != cur2 ){
                return null;
            }
            cur1 = n > 0 ? head1 : head2;//curl1 表示长的链表
            cur2 = cur1 == head1 ? head2 : head1;//curl2表示短的链表
            n = Math.abs(n);
            while (n != 0){
                cur1 = cur1.next;
                n--;
            }
            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else {
            //两个链表的入环节点不是同一个：情况如2.3.1 和 2.3.3
            //如果loop1和loop2在一个环上，则遍历其中一个链表，一定能找到另一个链表的入环节点
            cur1 = loop1.next;
            while (cur1 != loop1){
                if (cur1 == loop2){
                    return loop2;
                }
                cur1 = cur1.next;
            }
            return null;
        }

    }

    //判断两条无环链表是否有交点
    private static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        //如果两个链表相交，则最后的节点一定是相同的
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null){
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null){
            n--;
            cur2 = cur2.next;
        }
        //此时curl1 和curl2 都到达链表的最后节点
        if (cur1 != cur2 ){
            //如果不相同，则说明两个链表没有相交
            return null;
        }
        //如果curl1和curl2 是相同的，则说明两个链表一定相交
        //此时n的值即为两个链表长度的差值,如果n是大于0 则说明curl1的链表长，否则说明curl2的链表长
        cur1 = n > 0 ? head1 : head2;//curl1 表示长的链表
        cur2 = cur1 == head1 ? head2 : head1;//curl2表示短的链表
        n = Math.abs(n);
        while (n != 0){
            cur1 = cur1.next;
            n--;
        }
        //此时长链表已经跳过了差值
        while (cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        //当curl1和curl2相等的时候，即为交点
        return cur1;
    }

    //判断一个单链表是否是有环链表，如果是则返回第一个交点
    public static Node getLoopNode(Node head){
        if (head == null || head.next == null || head.next.next == null) return null;
        // 准备两个指针
        Node fast = head.next.next;//一次两步
        Node slow = head.next;//一次一步
        while (fast != slow){
            if (fast.next == null || fast.next.next == null){
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        //跳出循环则说明快慢指针相遇
        fast = head;//相遇后将fast指针重置到头节点
        while (fast != slow){
            fast = fast.next;//fast指针调整为一次一步
            slow = slow.next;
        }
        //快慢指针再次相遇即为第一个交点
        return fast;
    }


}
