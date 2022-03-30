package com.lsh.day08_binarytree;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/30 6:32 下午
 * @desc ：判断二叉树是否是满的
 */
public class Code19_IsFull {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right = new Node(3);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.right.right.left = new Node(9);

        System.out.println(isFull(head));
    }

    public static boolean isFull(Node head){
        Info info = process(head);
//        return info.nodes == Math.pow(2,info.hight-1);
        System.out.println(info);
        return (1 << info.hight) -1 == info.nodes;
    }

    static class Info{
        int hight;//高度
        int nodes;//节点数

        public Info(int hight,int nodes){
            this.hight = hight;
            this.nodes = nodes;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "hight=" + hight +
                    ", nodes=" + nodes +
                    '}';
        }
    }

    public static Info  process(Node head){
        if (head == null){
            return new Info(0,0);//如果遇到空节点，则返回高度为0 节点数为0
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int hight = Math.max(leftInfo.hight,rightInfo.hight)+1;//最高高度加上自己
        int nodes = leftInfo.nodes + rightInfo.nodes + 1 ;
        return new Info(hight,nodes);
    }
}
