package com.lsh.day08_binarytree;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/9 3:49 下午
 * @desc ：算法题目：判断一颗二叉树是否是镜像树
 * 镜像：左节点结构和右节点结构是对称的
 *      1
 *    2   2
 *   3 1 1 3
 */

public class Code03_MirrorTree {

    /**
     * 判断是否是镜像数
     * @param head1
     * @param head2
     * @return
     */
    public static Boolean isMirrorTree(Node head1,Node head2){

        if (head1 == null ^ head2 == null){
            return false;
        }
        if (head1 == null && head2 == null){
            return true;
        }
        // 头节点的左节点和头节点的右节点 相等
        return head1.value == head2.value && isMirrorTree(head1.left,head2.right) && isMirrorTree(head1.right,head2.left);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(2);
        System.out.println(isMirrorTree(root,root));
        System.out.println(isMirrorTree(root,null));
        System.out.println(isMirrorTree(null,null));
    }

}
