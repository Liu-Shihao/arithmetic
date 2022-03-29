package com.lsh.day08_binarytree;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/9 2:19 下午
 * @desc ：递归方式：先序、中序、后序 打印二叉树
 * 递归序：每个节点到达三次
 *          1
 *       2      3
 *    4   5   6    7
 * 先序：头左右   1 2 4 5 3 6 7 先打印先头 然后左树 最后右树
 * 中序：左头右   4 2 5 1 6 3 7
 * 后序：左右头   4 5 2 6 7 3 1
 *
 * 递归序：到每个节点都是三次
 *
 * 结论：一个二叉树的先序数组和后序数组，在先序数组中此节点之前的节点 和 在后序数组中此节点之后的节点  的共同节点是此节点的祖先节点
 * 因为先序排列，头节点排在前面，后序排列，头节点在后面，所以交集就是X节点的祖先节点
 *
 *
 *
 *
 *
 */
public class Code01_PrintTreeI {

    public static class Node{
        public int val;
        public Node left;
        public Node right;

        public Node(int v){
            val = v;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.left = new Node(2);

        node.left.left = new Node(4);
        node.left.right = new Node(6);
        node.left.left.left = new Node(5);

        node.right = new Node(3);
        node.right.left = new Node(7);
        node.right.left.right = new Node(9);

        node.right.right = new Node(8);
        node.right.right.left = new Node(10);
        node.right.right.right = new Node(11);
        pre(node);
        System.out.println();
        System.out.println("===============");
        in(node);
        System.out.println();
        System.out.println("===============");
        pos(node);
    }
    //递归序：到每个节点都是三次
    public static void f(Node node){
        if (node == null){
            return;
        }
        //1
        f(node.left);
        //2
        f(node.right);
        //3
    }

    /**
     * 先序打印：头 左  右
     * @param head
     */
    public static void pre(Node head){
        if(head == null){
            return;
        }
        System.out.print(head.val+" ");
        pre(head.left);
        pre(head.right);
    }

    /**
     * 中序打印：左 头 右
     * @param head
     */
    public static void in(Node head){
        if(head == null){
            return;
        }
        in(head.left);
        System.out.print(head.val+" ");
        in(head.right);
    }
    /**
     * 后打印：左  右  头
     * @param head
     */
    public static void pos(Node head){
        if(head == null){
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.print(head.val+" ");
    }
}
