package com.lsh.day08_binarytree;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/30 2:08 下午
 * @desc ：求二叉树某一节点的后继节点
 * 即求出在二叉树的中序遍历中这个节点下一位的节点
 * 第一种方法:通过parent节点 找到头节点，然后遍历出中序 找到该节点的下一个节点，时间复杂度O(N)
 * 第二种方法：从该节点直接找到它的后继节点（直接距离为K），时间复杂度为O(K)
 * 后继节点：
 */
public class Code17_SuccessorNode {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;//指向父节点

        public Node(int data) {
            this.value = data;
        }
    }

    public static void main(String[] args) {
        Node head= new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.parent = head;
        head.right.parent = head;

        head.left.left = new Node(4);
        head.left.left.parent = head.left;
        head.left.right = new Node(5);
        head.left.right.parent = head.left;

        head.left.right.left = new Node(8);
        head.left.right.right = new Node(9);
        head.left.right.left.parent = head.left.right;
        head.left.right.right.parent = head.left.right;

        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.right.left.parent = head.right;
        head.right.right.parent = head.right;


        System.out.println("二叉树中序遍历：");
        midPrint(head);

        System.out.println();
        System.out.println("2 的后继节点是："+getSuccessorNode(head.left).value);//2 的后继节点是8
        System.out.println("8 的后继节点是："+getSuccessorNode(head.left.right.left).value);//8 的后继节点是5
        System.out.println("9 的后继节点是："+getSuccessorNode(head.left.right.right).value);//9 的后继节点是1
        System.out.println("7 的后继节点是："+getSuccessorNode(head.right.right));//7 的后继节点是null

    }

    //求该节点的后继节点
    public static Node getSuccessorNode(Node node){
        if (node == null){
            return null;
        }
        if (node.right != null){
            //如果该节点存在右树，则找该节点右树上的最左树节点，即为该节点的后继节点
            return getMostLeft(node.right);
        }else {
            //该节点无右树
            Node parent = node.parent;
            while (parent != null && parent.right == node){
                //当前节点是父节点的右孩子
                node = parent;
                parent = node.parent;
            }
            return parent;
        }

    }

    private static Node getMostLeft(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static  void midPrint(Node head){
        if (head == null) {
            return;
        }
        midPrint(head.left);
        System.out.print(head.value + " ");
        midPrint(head.right);
    }

}
