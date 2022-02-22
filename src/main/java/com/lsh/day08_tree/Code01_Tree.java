package com.lsh.day08_tree;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/9 2:19 下午
 * @desc ：二叉树基本概念、题目：判断两个二叉树是否相等
 * 一个二叉树不能有闭环
 * 先序：头左右   1 2 4 5 3 6 7 先打印先头 然后左树 最后右树
 * 中序：左头右   4 2 5 1 6 3 7
 * 后序：左右头   4 5 2 6 7 3 1
 */
public class Code01_Tree {

    public static class Node{
        public int val;
        public Node left;
        public Node right;

        public Node(int v){
            val = v;
        }
    }

    /**
     * 先序打印：头 左  右
     * @param head
     */
    public static void pre(Node head){
        if(head == null){
            return;
        }
        System.out.print(head.val+" ");
        pre(head.left);
        pre(head.right);

    }

    /**
     * 中序打印：左 头 右
     * @param head
     */
    public static void in(Node head){
        if(head == null){
            return;
        }
        in(head.left);
        System.out.print(head.val+" ");
        in(head.right);
    }
    /**
     * 后打印：左  右  头
     * @param head
     */
    public static void pos(Node head){
        if(head == null){
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.print(head.val+" ");
    }


    public static void main(String[] args) {
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);

        node.left.left = new Node(4);
        node.left.right = new Node(5);

        node.right.left = new Node(6);
        node.right.right = new Node(7);
        pre(node);
        System.out.println();
        System.out.println("===============");
        in(node);
        System.out.println();
        System.out.println("===============");
        pos(node);


    }
}
