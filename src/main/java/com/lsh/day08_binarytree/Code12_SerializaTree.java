package com.lsh.day08_binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/28 4:47 下午
 * @desc ：二叉树的序列化和反序列化  数结构是在内存中的，如果关机，则小时，所有需要将树结构序列化成String类型储存到磁盘中，
 * 使用分隔符和占位符保存树结构
 * Serializable
 * UnSerializable
 * 注意：中序不存在序列化和反序列化，因为中序序列化后存在歧义，即多个结构的二叉树中序序列化后的String表示是一样的
 *
 * 1.前序 序列化与反序列化
 * 2.后序 序列化与反序列化
 * 3.层级序 序列化与反序列化
 *
 *
 */
public class Code12_SerializaTree   {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);

        head.left.left = new Node(4);
        head.left.right = new Node(6);
        head.left.left.left = new Node(5);
        head.right = new Node(3);
        head.right.left = new Node(7);
        head.right.left.right = new Node(9);

        head.right.right = new Node(8);
        head.right.right.left = new Node(10);
        head.right.right.right = new Node(11);
        //先序的序列化与反序列化
//        Queue queue = proSerializable(head);
//        prePrint(unserializablePreTree(queue));

        //后序的序列化与反序列化
//        Queue<String> queue1 = afterSerializable(head);
//        while (!queue1.isEmpty()) {
//            String poll = queue1.poll();
//            System.out.print(poll+" ");
//        }
//        afterPrint(unserializableAfterTree(queue1));

        //层级遍历的序列化与反序列化
        Queue<String> levalSerial = levalSerial(head);
//        while (!levalSerial.isEmpty()) {
//            String poll = levalSerial.poll();
//            System.out.print(poll+" ");
//        }
        levalPrint(unLevalSerial(levalSerial));

    }
    //先序打印
    public static void prePrint(Node head){
        if (head == null) return;
        System.out.print(head.value+" ");
        prePrint(head.left);
        prePrint(head.right);
    }
    //后序打印
    public static void afterPrint(Node head){
        if (head == null) return;
        afterPrint(head.left);
        afterPrint(head.right);
        System.out.print(head.value+" ");
    }

    //层级打印
    public static void levalPrint(Node head){
        if (head == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.value+" ");
            if (node.left != null){
                queue.add(node.left);
            }
            if (node.right != null){
                queue.add(node.right);
            }

        }
    }


    /**
     * 先序序列化
     * @param head
     * @return
     */
    public static Queue proSerializable(Node head){
        Queue<String> res = new LinkedList<>();
        pre(head,res);
        return res;
    }
    public static void pre(Node node , Queue<String> queue){
        if (node == null){
            queue.add(null);
        }else {
            queue.add(String.valueOf(node.value));
            pre(node.left,queue);
            pre(node.right,queue);
        }
    }
    /**
     * 后序序列化
     * @param head
     * @return
     */
    public static Queue<String> afterSerializable(Node head){
        Queue<String> res = new LinkedList<>();
        after(head,res);
        return res;
    }

    public static void after(Node node,Queue<String> queue){
        if (node == null){
            queue.add(null);
        }else {
            after(node.left,queue);
            after(node.right,queue);
            queue.add(String.valueOf(node.value));
        }
    }

    /**
     * 反序列化 先序
     */
    public static Node unserializablePreTree(Queue<String> queue){
        if (queue == null || queue.size() == 0) return null;
        return unPre(queue);
    }
    private static Node unPre(Queue<String> queue) {
        String value = queue.poll();
        if (value == null){
            return null;
        }
        Node node = new Node(Integer.parseInt(value));
        node.left = unPre(queue);
        node.right = unPre(queue);
        return node;
    }


    /**
     * 反序列化 后序
     */
    public static Node unserializableAfterTree(Queue<String> queue){
        if (queue == null || queue.size() == 0) {
            return null;
        }
        //null null 5 null 4 null null 6 2 null null null 9 7 null null 10 null null 11 8 3 1
        // 左右头  ->  stack(头右左)
        //因为在进行后序序列化的时候 进入队列的顺序为 左 右 头，
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        return unAfter(stack);

    }

    private static Node unAfter(Stack<String> stack) {

        String value = stack.pop();
        if (value == null){
            return null;
        }else {
            //头 右 左 -> 左 右 头 即后序
            Node node = new Node(Integer.parseInt(value));
            //注意 先右在左
            node.right = unAfter(stack);

            node.left = unAfter(stack);
            return node;
        }
    }

    //按层方式序列化
    private static Queue<String> levalSerial(Node head){
        LinkedList<String> ans = new LinkedList<>();
        if (head == null){
            ans.add(null);
        }else {
            ans.add(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<>();//存储子节点，一遍往下层遍历
            queue.add(head);
            while (!queue.isEmpty()) {
                head = queue.poll();
                if (head.left != null) {
                    ans.add(String.valueOf(head.left.value));
                    queue.add(head.left);
                }else {
                    ans.add(null);
                }
                if (head.right != null) {
                    ans.add(String.valueOf(head.right.value));
                    queue.add(head.right);
                }else {
                    ans.add(null);
                }
            }

        }
        return ans;
    }

    //按层方式按序列化
    private static Node unLevalSerial(Queue<String> list){
        if (list == null || list.size() == 0){
            return null;
        }
        Node head = generateNode(list.poll());
        Queue<Node> queue = new LinkedList<Node>();
        if (head != null){
            queue.add(head);
        }
        Node node = null;
        while (! queue.isEmpty()){
            node = queue.poll();

            node.left = generateNode(list.poll());
            node.right = generateNode(list.poll());

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }

    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.valueOf(val));
    }











    }
