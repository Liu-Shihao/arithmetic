package com.lsh.day08_tree;

import java.util.Stack;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/9 2:19 下午
 * @desc ：非递归方式先序、中序、后序 打印二叉树
 *          1
 *       2      3
 *    4   5   6    7
 * 先序：头左右   1 2 4 5 3 6 7 先打印先头 然后左树 最后右树
 * 中序：左头右   4 2 5 1 6 3 7
 * 后序：左右头   4 5 2 6 7 3 1
 *
 */
public class Code01_PrintTreeII {

    public static class Node{
        public int val;
        public Node left;
        public Node right;

        public Node(int v){
            val = v;
        }
    }

    public static void main(String[] args) {
        /**
         *      1
         *    2    3
         *  4  5  6  7
         *
         *  先序：1 2 4 5 3 6 7
         *  中序：4 2 5 1 6 3 7
         *  后序：4 5 2 6 7 3 1
         */

        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);

        head.left.left = new Node(4);
        head.left.right = new Node(5);

        head.right.left = new Node(6);
        head.right.right = new Node(7);


        pre(head); //1 2 4 5 3 6 7
        System.out.println("===============");
        mid(head);// 4 2 5 1 6 3 7
        System.out.println("===============");
        after(head);//4 5 2 6 7 3 1
    }

    //非递归 先序打印 : 头 左 右
    public static void pre(Node head){
        System.out.println("pro order：");
        if (head == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()){
            Node node = stack.pop();
            System.out.print(node.val+" ");
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }
        System.out.println();

    }
    //非递归 中序打印 左 头 右
    public static void mid(Node head){
        System.out.println("mid order：");
        if (head == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null){
            if (head != null) {
                stack.push(head);
                head = head.left;//一直找到最后的左子节点
            } else {
                head = stack.pop();
                System.out.print(head.val + " ");
                head = head.right;
            }
        }
        System.out.println();
    }

    //非递归 后序打印： 左 右 头
    public static void after(Node head){
        System.out.println("after order：");
        if (head == null){
            return;
        }
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(head);
        //先放头
        while (!stack1.isEmpty()){
            Node node = stack1.pop();
            stack2.push(node);
            //先放  左 在放右 ，栈弹出的顺序就是 右 左
            if (node.left != null){
                stack1.push(node.left);
            }
            if (node.right != null){
                stack1.push(node.right);
            }
        }
        //这样第一个栈弹出的顺序就是 头 右 左  放到第二个栈中
        //这样 第二个栈弹出的顺序就是倒序 ： 左 右 头
        while (!stack2.isEmpty()){
            Node node = stack2.pop();
            System.out.print(node.val+" ");
        }
        System.out.println();
    }
}
