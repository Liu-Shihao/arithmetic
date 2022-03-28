package com.lsh.day08_tree;

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
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);

        head.left.left = new Node(4);
        head.left.right = new Node(5);

        head.right.left = new Node(6);
        head.right.right = new Node(7);

        printTree(head);


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
