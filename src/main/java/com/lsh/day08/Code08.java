package com.lsh.day08;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/10 3:41 下午
 * @desc ：判断一棵树是否是平衡树
 * 什么是平衡数？ 每一棵子树左树和右树的高度差不超过1
 */
public class Code08 {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 定义Info
     * height为 树的高度是什么？
     * isBalanced 是否是平衡数
     */
    public static  class Info{
        public int height;
        public boolean isBalanced;

        public Info(boolean isBalanced,int height){
            this.height = height;
            this.isBalanced = isBalanced;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "高度=" + height +
                    ", 是否平衡=" + isBalanced +
                    '}';
        }
    }

    public static Info process(TreeNode x){
        if (x == null){
            return new Info(true,0);
        }
        // x != null
        //左树Info
        Info leftInfo = process(x.left);
        //右树Info
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height - rightInfo.height) >= 1;

        return new Info(isBalanced,height);

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);

        root.left.left.left = new TreeNode(1);
        root.left.right.right = new TreeNode(1);

        Info process = process(root);
        System.out.println(process);

    }




}
