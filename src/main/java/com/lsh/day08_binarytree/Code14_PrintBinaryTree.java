package com.lsh.day08_binarytree;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/29 8:24 下午
 * @desc ：二叉树打印函数
 */
public class Code14_PrintBinaryTree {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);

        head.left.left = new Node(4);
        head.left.right = new Node(6);
        head.left.left.left = new Node(5);
        head.right = new Node(3);
        head.right.left = new Node(7);
        head.right.left.right = new Node(9);

        head.right.right = new Node(8);
        head.right.right.left = new Node(10);
        head.right.right.right = new Node(11);
        printTree(head);
    }
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }
    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }
    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

}
