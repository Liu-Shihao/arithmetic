package com.lsh.day08_binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/28 4:27 下午
 * @desc ：二叉树 层级打印
 */
public class Code01_PrintTreeIII {

    static class Node{
        public Integer value;
        public Node right;
        public Node left;

        public Node(int v){
            value = v;
            right = null;
            left = null;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.left = new Node(2);

        node.left.left = new Node(4);
        node.left.right = new Node(6);
        node.left.left.left = new Node(5);
        node.right = new Node(3);
        node.right.left = new Node(7);
        node.right.left.right = new Node(9);

        node.right.right = new Node(8);
        node.right.right.left = new Node(10);
        node.right.right.right = new Node(11);

        printTree(node);


    }

    //层级打印二叉树
    public static void printTree(Node head){
        if (head == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            System.out.print(node.value+" ");
            if (node.left!= null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }

    }




}
