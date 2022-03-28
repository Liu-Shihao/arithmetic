package com.lsh.day05_linkedlist;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/27 5:38 下午
 * @desc ：链表划分问题
 * 给定一个Value，将一个链表根据Value值划分为小于区域、等于区域、大于区域
 */
public class Code14_PartitionList {

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
//         head1 = listPartition1(head1, 5);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }


    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }
    //借助数组：把链表放在数组中，在数组中-进行划分
    public static Node listPartition1(Node head, int pivot) {
        if (head == null) return null;
        Node cur = head;
        int i = 0;
        //获得数组长度
        while (cur != null){
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        cur = head;
        //填充数组
        for ( i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        //在数组中进行划分
        arrPartition(nodeArr, pivot);
        //划分完成后，重新连接链表结构
        for ( i = 1; i < nodeArr.length; i++) {
            nodeArr[i-1].next = nodeArr[i];
        }
        nodeArr[i-1].next = null;//需要将新连接的链表的最后节点的next指针置空
        return nodeArr[0];
    }

    private static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;//小于区域最右边界
        int index = 0;//当前遍历元素
        int big = nodeArr.length;//大于区域最左边界
        while (index != big){
            if (nodeArr[index].value < pivot){
                //小于区域
                swap(nodeArr,index++,++small);
            }else if(nodeArr[index].value == pivot){
                //等于区域
                index++;//向下移动
            }else {
                //大于区域
                swap(nodeArr,index,--big);
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    //方法2：使用六个变量
    // 小于区头  sH
    // 小于区尾  sT
    // 等于区头  eH
    // 等于区尾  eT
    // 大于区头  bH
    // 大于区尾  bT
    public static Node listPartition2(Node head,int v){
        if (head == null)return null;
        Node sH = null; // small head
        Node sT = null; // small tail
        Node eH = null; // equal head
        Node eT = null; // equal tail
        Node bH = null; // big head
        Node bT = null; // big tail
        Node next = null; // save next node
        //将原链表进行划分
        //划分后会形成三个链表： 小于区域的链表 等于区域的链表 大于区域的链表
        while (head != null){
            next = head.next;//保留next指针
            head.next = null;//将该节点断开
            if (head.value < v){
                //小于区域
                if (sH == null){
                    sH = head;
                    sT = head;
                }else {
                    sT.next = head;
                    sT = head;
                }
            }else if (head.value == v){
                //等于区域
                if (eH == null){
                    eH = head;
                    eT = head;
                }else{
                    eT.next = head;
                    eT = head;
                }
            }else {
                //大于区域
                if (bH ==null){
                    bH = head;
                    bT = head;
                }else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next; //注意要忘记将节点向下移动一位
        }
        //链表划分完成，但是此时需要分析链表为空的情况：小于/等于/大于 三个子链表都有可能为空
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        if (sT != null){
            //存在小于区域
            sT.next = eH;
            //判断是否存在等于区域：如果存在等于区域，则eT=eT，如果不存在等于区域，则eT=sT
            eT = eT == null ? sT : eT;
        }
        if (eT != null){
            //如果上一步中，eT不为空，则指向大于区域的头节点
            eT.next = bH;
        }
        return  sH != null ? sH : (eH != null ? eH : bH);
    }

}
