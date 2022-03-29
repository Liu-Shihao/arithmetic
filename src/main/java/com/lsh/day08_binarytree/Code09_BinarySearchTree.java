package com.lsh.day08_binarytree;

import java.util.LinkedList;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/10 5:01 下午
 * @desc ：判断是否为搜索二叉树：左树节点都比头节点小，右树节点都比头节点大
 * 一棵二叉树的中序遍历结果是递增的就，那就是搜索二叉树
 * 方法1：中序遍历 是否是递增的
 * 方法2：递归，
 *
 */
public class Code09_BinarySearchTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void in(TreeNode root,LinkedList<Integer> linkedList){
        if (root == null){
            return ;
        }
        in(root.left,linkedList);
        linkedList.add(root.val);
        in(root.right,linkedList);
    }

    /**
     * 方法1 ：获得中序排列链表，然后判断此链表是否为递增链表，如果是递增 的则为搜索二叉树，否则不是
     * @param root
     * @return
     */
    public static boolean isSearchTree1(TreeNode root){
        LinkedList<Integer> linkedList = new LinkedList<>();
        in(root,linkedList);
        Integer curNode = linkedList.pollFirst();
        while (!linkedList.isEmpty()){
            Integer next = linkedList.pollFirst();
            if (curNode > next){
                return false;
            }
            curNode = next;
        }
        return true;
    }

    /**
     * 定义Info
     */
    public static class Info{
        public boolean IsBST;
        public int max;
        public int min;

        public Info(boolean is,int max,int min){
            this.IsBST = is;
            this.max = max;
            this.min = min;

        }

    }

    /**
     * 方法2 ：通过递归实现
     * @param x
     * @return
     */
    public static Info process(TreeNode x){
        if (x == null){
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int min = x.val;
        int max = x.val;

        if (leftInfo != null){
            max = Math.max(leftInfo.max,max);
            min = Math.min(leftInfo.min,min);
        }
        if (rightInfo != null){
            max = Math.max(rightInfo.max,max);
            min = Math.min(rightInfo.min,min);
        }
        boolean isBST = true ;
        // 如果左树不为空，并且左树不是搜索树
        if(leftInfo != null && !leftInfo.IsBST){
            isBST = false;
        }
        //如果右树不为空，并且右树不是搜索树
        if(rightInfo != null && !rightInfo.IsBST){
            isBST = false;
        }
        //左树最大值是否小于头节点，如果左树为空则返回true
        //右树最小值是否大于头节点，如果右树为空则也返回true
        boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.val);
        boolean rightMinMoreX = rightInfo == null ? true : (rightInfo.min > x.val);
        //如果以上两个条件有一个不成立，则isBST = false
        // !(leftMaxLessX && rightMinMoreX)    <=>   (!leftMaxLessX || !rightMinMoreX)
        if (!(leftMaxLessX && rightMinMoreX)){
            isBST = false;
        }
//        if (!leftMaxLessX || !rightMinMoreX){
//            isBST = false;
//        }

        return new Info(isBST,max,min);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);

        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);

        System.out.println(isSearchTree1(root));

        System.out.println(process(root).IsBST);
    }
}
