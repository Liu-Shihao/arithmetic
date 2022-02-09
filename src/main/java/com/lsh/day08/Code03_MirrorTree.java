package com.lsh.day08;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/9 3:49 下午
 * @desc ：算法题目：判断两颗数是否是镜像数
 * 镜像：左节点结构和右节点结构是对称的
 *      1
 *    2   2
 *   3 1 1 3
 */
public class Code03_MirrorTree {

    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int v){
            val = v;
        }
    }

    /**
     * 判断是否是镜像数
     * @param head1
     * @param head2
     * @return
     */
    public static Boolean isMirrorTree(TreeNode head1,TreeNode head2){

        if (head1 == null ^ head2 == null){
            return false;
        }
        if (head1 == null && head2 == null){
            return true;
        }
        // 头节点的左节点和头节点的右节点 相等
        return head1.val == head2.val && isMirrorTree(head1.left,head2.right) && isMirrorTree(head1.right,head2.left);


    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        System.out.println(isMirrorTree(root,root));

    }

}
