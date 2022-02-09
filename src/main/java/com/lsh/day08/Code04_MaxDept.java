package com.lsh.day08;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/9 4:43 下午
 * @desc ：算法题目：返回一个二叉树的最大深度
 * 测试链接：https://leetcode.com/problems/maximum-depth-of-binary-tree
 */
public class Code04_MaxDept {

    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int v){
            val = v;
        }
    }
    // 以root为头的树，最大高度是多少返回！
    public static int maxDept(TreeNode root){
        if (root == null){
            return 0;
        }
        return Math.max(maxDept(root.left),maxDept(root.right))+1;
    }

    /**
     *       0
     *     1   1
     *   1  1
     *  1 1
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);

        root.left.left.left = new TreeNode(1);
        root.left.right.right = new TreeNode(1);

        System.out.println(maxDept(root));
    }
}
