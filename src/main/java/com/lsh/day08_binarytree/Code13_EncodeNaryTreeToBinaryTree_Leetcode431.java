package com.lsh.day08_binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/29 6:29 下午
 * @desc ：
 * https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/
 * 将一个多叉数 用二叉树的结构来表示 并且能够反序列化回去
 */
public class Code13_EncodeNaryTreeToBinaryTree_Leetcode431 {

    public  static class Node{
        public int val;
        public List<Node> children;

        public Node(){}

        public Node(int _val){
            this.val = _val;
        }

        public Node(int _val,List<Node> _children){
            val = _val;
            children = _children;

        }
    }
    public  static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val){
            this.val = val;
            left = null;
            right = null;
        }
    }

    class Codec{
        //将多叉树 转换为 二叉树结构
        public TreeNode encode(Node root){
            if (root == null){
                return null;
            }
            TreeNode head = new TreeNode(root.val);
            //把多叉树头节点所有的子节点 放到 二叉树的左子节点右节点
            head.left = en(root.children);

            return head;
        }

        private TreeNode en(List<Node> children) {
            TreeNode head = null;
            TreeNode cur = null;
            for (Node child : children) {
                TreeNode treeNode = new TreeNode(child.val);
                if (head == null) {
                    head = treeNode;
                }else {
                    cur.right = treeNode;
                }
                cur = treeNode;
                cur.left = en(child.children);

            }
            return head;
        }

        //将二叉树 恢复为 多叉树结构
        public Node decode(TreeNode root){
            if (root == null) {
                return null;
            }
            return new Node(root.val,de(root.left));
        }

        //将二叉树的左树 转化为多叉树的子节点
        private List<Node> de(TreeNode root) {
            ArrayList<Node> children = new ArrayList<>();
            while (root != null) {
                Node cur = new Node(root.val,de(root.left));
                children.add(cur);
                root = root.right;
            }
            return children;
        }
    }


}
