package com.lsh.day08;

import java.util.LinkedList;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/10 5:01 下午
 * @desc ：判断是否为搜索二叉树：左树节点都比头节点小，右树节点都比头节点大
 * 一棵二叉树的中序遍历结果是递增的就，那就是搜索二叉树
 * 方法1：中序遍历
 * 方法2：递归
 *
 */
public class Code09_SearchTree {

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
     * 获得中序排列链表，然后判断此链表是否为递增链表，如果是递增 的则为搜索二叉树，否则不是
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
        }
        return true;
    }

    /**
     * 定义一个二叉树，中序遍历为 1 2 3 4 5 6 7
     * 定义一个二叉树，中序遍历为 8 2 3 4 5 6 7
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println(isSearchTree1(root));



    }
}
