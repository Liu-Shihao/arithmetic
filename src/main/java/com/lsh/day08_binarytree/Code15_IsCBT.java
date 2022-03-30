package com.lsh.day08_binarytree;

import java.util.LinkedList;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/30 4:00 下午
 * @desc ：完全二叉树：从上到下、从左到右 变满
 * 判断二叉树是否是完全二叉树
 */
public class Code15_IsCBT {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);

        head.left.left = new Node(4);
        head.left.right = new Node(5);

        head.right.left = new Node(6);
        head.right.right = new Node(7);

        head.right.right.right = new Node(8);

        System.out.println(isCBT1(head));
    }


    public static boolean isCBT1(Node head){
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            l = node.left;
            r = node.right;
            //两个条件：
            //1.存在右节点，但是不存在左节点，则一定不是完全二叉树
            //2.已经存在不双全的节点后，又遇到了当前节点不是叶节点。则一定不是完全二叉树
            if ((l == null && r != null) || (leaf && (l != null || r != null))) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                //如果遇到节点的左右子节点不全，则将leaf标记为true
                leaf = true;
            }
        }
        return true;
    }

}
