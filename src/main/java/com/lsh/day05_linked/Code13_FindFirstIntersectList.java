package com.lsh.day05_linked;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/26 5:57 下午
 * @desc ：链表相交问题
 * 如果一个链表没有交点则返回null，如果有交点返回第一个交点
 */
public class Code13_FindFirstIntersectList {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = head.next.next.next;
        System.out.println(getFirstIntersectNode(head).value);


    }

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 使用快慢指针方式判断链表是否相交并找到第一个交点
     * @param head
     * @return
     */
    public static Node getFirstIntersectNode(Node head){
        if (head == null || head.next == null || head.next.next == null) return null;
        // 准备两个指针
        Node fast = head.next.next;//一次两步
        Node slow = head.next;//一次一步
        while (fast != slow){
            if (fast.next == null || fast.next.next == null){
                return null;
            }
            System.out.println("fast :"+fast.value);
            System.out.println("slow :"+slow.value);
            fast = fast.next.next;
            slow = slow.next;

        }
        System.out.println("第一次相遇点："+slow.value);
        System.out.println("============");
        //跳出循环则说明快慢指针相遇
        fast = head;//相遇后将fast指针重置到头节点
        while (fast != slow){
            fast = fast.next;//fast指针调整为一次一步
            slow = slow.next;
            System.out.println("fast :"+fast.value);
            System.out.println("slow :"+slow.value);
        }
        System.out.println("再次相遇点："+slow.value);
        //快慢指针再次相遇即为第一个交点
        return fast;





    }


}
