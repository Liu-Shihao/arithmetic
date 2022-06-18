package com.lsh.day05_linkedlist;

import java.util.Stack;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/25 1:09 上午
 * @desc ：单链表题目：判断链表是否为回文结构
 * 1.借助栈数据结构
 * 2.使用快慢指针方法
 */
public class Code11_VIS_IsPalindrome {

    static class Node<T>{

        private T value;
        private Node next;

        public Node(T t){
            this.value = t;
        }
    }

    public static void main(String[] args) {
        Node<Integer> head = new Node<>(1);
        head.next = new Node<>(2);
        head.next.next = new Node<>(2);
        head.next.next.next = new Node<>(1);
        System.out.println(isPalindrome1(head));
    }

    //借助栈的数据结构
    public static boolean isPalindrome1(Node head){
        if (head == null || head.next == null) return true;
        Node node = head;
        boolean bool = true;
        Stack<Node> stack = new Stack<>();

        while (node != null){
            stack.push(node);
            node = node.next;
        }
        node = head;
        while (node != null){
            Node pop = stack.pop();
            if (node.value != pop.value) {
                bool = false;
                break;
            }
            node = node.next;
        }
        return bool;
    }
    //方式2：不借助数据结构，使用快慢指针方式
    //空间复杂度为O(1)
    public static boolean isPalindrome2(Node head){
        if (head == null || head.next == null) return true;
        boolean bool = true;

        Node n1 = head;
        Node n2 = head;

        while (n2 != null){
            n1 = head.next;//mid
            n2 = head.next.next;//end
        }
        n2 = n1.next;//n2 中点的下一个节点
        n1.next = null;//n1 中点  ，将中间节点下一个节点置空
        Node n3 = null;
        //反转第二段链表 指向mid
        while (n2 != null){
            n3 = n2.next;//n3 记录下一个节点信息，保存n2的next指针
            n2.next = n1;//n2的next指针指向上一个节点
            n1 = n2;//n1 记录上一个节点信息
            n2 = n3;//n2向下移动一位
        }
        //反转完成后，此时n1到达原始链表的末尾
        n3 = n1;  //将n3设置为第二段链表反转后的头节点
        n2 = head;//将头节点赋值给n2
        while (n1 != null && n2 != null){
            //两个指针都不为null，进行比较（如果有一个节点为null，说明到达中点，结束循环比较）
            if (n1.value != n2.value){
                bool = false;
                break;
            }
            n2 = n2.next;
            n1 = n1.next;
        }
        // 1 -> 2  ->  3  <- 2 <- 1
        // n2         mid        n1
        //此时链表反转后，n2、n1到达null位置
        //两个链表比较完成后，需要将第二段链表反转恢复原样
        n1 = n3.next;//n3为第二段链表反转后的头节点 ，使用n1记录下一个节点信息
        n3.next = null;//将节点断开
        //此时n1节点来到第二段链表反转后的第二个节点
        while (n1 != null){
            n2 = n1.next;//使用n2变量记录下一个节点信息
            n1.next = n3;//n3记录为上一个节点信息   3  <-   2   <-   1
            n3 = n1;//                          n2      n1       n3
            n1 = n2;//                        下一节点           上一节点
        }
        return bool;
    }
}
