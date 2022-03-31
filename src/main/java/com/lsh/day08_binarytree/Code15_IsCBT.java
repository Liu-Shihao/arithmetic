package com.lsh.day08_binarytree;

import java.util.LinkedList;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/30 4:00 下午
 * @desc ：完全二叉树：从上到下、从左到右 变满
 * 判断二叉树是否是完全二叉树
 * 1.非递归
 * 2.递归套路解
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

//        head.right.right.right = new Node(8);

        System.out.println(isCBT1(head));
        System.out.println(isCBT2(head));
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

    public static class Info{
        public boolean isFull;//是否是满的
        public boolean isCBT;//是否是完全二叉树
        public int hight;//高度

        public Info(Boolean b1,Boolean b2, int h){
            isFull = b1;
            isCBT = b2;
            hight = h;
        }

    }

    public static boolean isCBT2(Node head){
        return process(head).isCBT;
    }

    private static Info process(Node node) {
        if (node == null) {
            return new Info(true,true,0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int hifht = Math.max(leftInfo.hight,rightInfo.hight)+1;
        //是否满？ 左树和右树都是满的，并且高度一样
        boolean isFull = leftInfo.isFull&&rightInfo.isFull && leftInfo.hight == rightInfo.hight;
        //讨论是否是完全二叉树
        boolean isCBT = false;

        //可能性1：左树是和右树都是满的 并且高度一样
        if (isFull == true){
            isCBT = true;//如果整个数是满的，则为完全二叉树
        }
        //可能性2：左树是完全树、右树是满的，左右树高度差为1
        if (leftInfo.isCBT && rightInfo.isFull && Math.abs(leftInfo.hight-rightInfo.hight) == 1){
            isCBT = true;
        }
        //可能性3：左树是满的，右树是满的，左右树高度差为1
        if (leftInfo.isFull && rightInfo.isFull && Math.abs(leftInfo.hight-rightInfo.hight) == 1){
            isCBT = true;
        }
        //可能性4：左树是满的，右树是完全的，但是左右高度相等
        if (leftInfo.isFull && rightInfo.isCBT && leftInfo.hight==rightInfo.hight){
            isCBT = true;
        }
        return new Info(isFull,isCBT,hifht);
    }

}
