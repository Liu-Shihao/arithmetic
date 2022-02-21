package com.lsh.day05_linked;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/5 6:38 下午
 * @desc ：题目：K个节点的组内逆序调整
 */
public class Code05_ReverseK {

    /**
     * 单链表结构
     * @param <V>
     */
    public static class ListNode<V>{
        private V value;
        private ListNode<V> next;

        public ListNode(V date){
            value = date;
            next = null;
        }
    }

    /**
     * K个节点的组内逆序调整
     * @param head
     * @param K
     * @return
     */
    public static ListNode reverseKGroup(ListNode head,int K){
        ListNode start = head;
        //是否凑齐第一组
        ListNode end = getKGroupEndNode(start, K);
        if (end == null){
            //队列中不到K个元素，不做处理，返回原head
            return head;
        }
        //此时第一组的end逆序排列后成为整个队列的新头部
        head = end;
        reverse(start,end);
        //上一组的start经过逆序后成为最后节点
        ListNode lastStart = start;
        while (lastStart.next != null){
            //如果上一组的结尾节点的next节点不为空
            start = lastStart.next;
            end = getKGroupEndNode(start, K);
            if (end == null){
                //剩余的元素不够凑成一组，则不进行逆序处理，返回head。
                return head;
            }
            reverse(start,end);
            //注意：需要将上一组的结尾节点的next指针指向这一组的end节点 ：才能将上一组和这一组连接起来。
            lastStart.next = end;
            //并且需要更新lastStart为这一组的start
            lastStart = start;
        }
        //所有的元素都正好成组，所有组逆序处理后返回head。
        return head;
    }

    /**
     * 获得起始点后的第K个元素
     *  1   2   3   4   null
     *  start 1  k = 5
     * @param start
     * @param K
     * @return
     */
    public static ListNode getKGroupEndNode(ListNode start,int K){
        while ( --K != 0 && start != null){
            start = start.next;
        }
        return start;
    }

    /**
     * 组内逆序
     * start        end
     * 1  ->  2 ->  3
     * ---------------------------------
     * start
     * cur               end = end.next
     * 1  ->  2 ->  3 -> 4
     * ---------------------------------
     * 1  <-  2 <-  3 <- 4
     * ↓                 ↑
     * |_________________|
     * @param start  起始位置
     * @param end    结束位置
     * @return
     */
    public static void reverse(ListNode start,ListNode end){
        //先将end向后移动一位。
        end = end.next;
        ListNode pre = null;
        ListNode cur= start;
        ListNode next= null;
        while (cur != end){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // cur 到达end位置结束循环，并且将start的next指针指向end（即当时end的next）
        start.next = end;
    }

    /**
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
     * 组内K个节点逆序
     * 3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 7
     * @param args
     */
    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(1);
        head.next = new ListNode<>(2);
        head.next.next = new ListNode<>(3);
        head.next.next.next = new ListNode<>(4);
        head.next.next.next.next = new ListNode<>(5);
        head.next.next.next.next.next = new ListNode<>(6);
        head.next.next.next.next.next.next = new ListNode<>(7);
        ListNode newHead = reverseKGroup(head, 3);
        while (newHead!= null){
            System.out.print(newHead.value + " ");
            newHead = newHead.next;
        }
        System.out.println();
    }


}
