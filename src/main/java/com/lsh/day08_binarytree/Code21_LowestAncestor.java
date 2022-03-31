package com.lsh.day08_binarytree;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/31 3:45 下午
 * @desc ：二叉树问题：找到a,b的最低公共祖先
 */
public class Code21_LowestAncestor {

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);

        head.left.left = new Node(1);
        head.left.right = new Node(6);
        head.left.left.right = new Node(2);

        head.right.left = new Node(7);
        head.right.right = new Node(9);

        Node lowestAncestor = findLowestAncestor(head, head.left.left.right, head.left.right);
        System.out.println(lowestAncestor.value);

    }

    static class Info{
        boolean findA;//是否找到A
        boolean findB;//是否找到B
        Node ans;     //最低公共祖先

        public Info(boolean b1,boolean b2,Node node){
            findA = b1;
            findB = b2;
            ans = node;
        }

    }

    public static Node findLowestAncestor(Node root,Node a,Node b){
        return process(root,a,b).ans;
    }

    private static Info process(Node node,Node a, Node b) {
        if (node == null){
            return new Info(false,false,null);
        }
        Info leftInfo = process(node.left, a, b);
        Info rightInfo = process(node.right, a, b);

        //自己本身是a 或者 左树发现a  或者右树发现a
        boolean findA = (node == a) || leftInfo.findA || rightInfo.findA;

        //自己本身是b 或者 左树发现b  或者右树发现b
        boolean findB = (node == b) || leftInfo.findB || rightInfo.findB;
        Node ans = null;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        }else {
            if (findA && findB){
                ans = node;//即发现了A又发现了B 则自己就是最低公共祖先
            }
        }
        return new Info(findA,findB,ans);

    }
}
