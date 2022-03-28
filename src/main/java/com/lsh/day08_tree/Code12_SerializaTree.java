package com.lsh.day08_tree;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/28 4:47 下午
 * @desc ：二叉树的序列化和反序列化  数结构是在内存中的，如果关机，则小时，所有需要将树结构序列化成String类型储存到磁盘中，
 * 使用分隔符和占位符保存树结构
 * Serializable
 * UnSerializable
 *
 * 序列化，将数结构转成String
 */
public class Code12_SerializaTree   {
    static class Node{
        public Integer value;
        public Node right;
        public Node left;

        public Node(int v){
            value = v;
            right = null;
            left = null;
        }
    }

    /**
     *
     * @param head
     * @return
     */
    public static String serializableTree(Node head){
        String str = "";


        return str;
    }

    /**
     *
     * @param str
     * @return
     */
    public static Node unserializableTree(String str){
        String[] split = str.split(",");
        Node head = new Node(Integer.parseInt(split[0]));


        return head;
    }




}
