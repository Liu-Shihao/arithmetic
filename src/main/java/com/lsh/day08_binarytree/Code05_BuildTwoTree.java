package com.lsh.day08_binarytree;

import java.util.HashMap;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/9 5:02 下午
 * @desc ：用先序数组和中序数组建出二叉树数并返回头节点
 * 先序： 1 2 4 5 3 6 7
 * 中序： 4 2 5 1 6 3 7
 *
 * 因为需要先序和中序两个数组才能确定左树结构和右树结构
 *
 */
public class Code05_BuildTwoTree {

    /**
     * 有一棵树，先序数组是pre[L1..R1] 中序数组是 in[L2..R2]
     * 请建出二叉树并返回头节点
     * @param pre   先序数组
     * @param L1    先序数组左边界
     * @param R1    先序数组右边界
     * @param in    中序数组
     * @param L2    中序数组左边界
     * @param R2    中序数组右边界
     * @return
     */
    public static Node f(int[] pre,int L1,int R1,int[] in,int L2,int R2){
        if (L1>R1){
            return null;
        }
        Node head = new Node(pre[L1]);
        if (L1 == R1){
            return head;
        }
        //pre[L1]为头节点，找到中序数组中头节点的位置 find
        int find = L2;
        while (in[find] != pre[L1]){
            find ++;
        }
        // 则 中序的左树结构为  [L2...find-1] 右树结构为[find+1...R2]
        // 则 先序的左树结构为  [L1+1...]
        head.left = f(pre,L1+1,L1+find-L2,in,L2,find-1);
        head.right = f(pre,L1+find-L2+1,R1,in,find+1,R2);

        return head;
    }

    /**
     * 优化f方法
     * @param pre
     * @param L1
     * @param R1
     * @param in
     * @param L2
     * @param R2
     * @param valueIndexMap
     * @return
     */
    public static Node g(int[] pre,int L1,int R1,int[] in,int L2,int R2,HashMap<Integer,Integer> valueIndexMap){
        if (L1>R1){
            return null;
        }
        Node head = new Node(pre[L1]);
        if (L1 == R1){
            return head;
        }
        //pre[L1]为头节点，找到中序数组中头节点的位置 find
//        int find = L2;
//        while (in[find] != pre[L1]){
//            find ++;
//        }
        //在f方法通过while遍历来确定头节点的位置，改进后通过Hash表直接确定头节点的位置。
        int find = valueIndexMap.get(pre[L1]);
        // 则 中序的左树结构为  [L2...find-1] 右树结构为[find+1...R2]
        // 则 先序的左树结构为  [L1+1...]
        head.left = g(pre,L1+1,L1+find-L2,in,L2,find-1,valueIndexMap);
        head.right = g(pre,L1+find-L2+1,R1,in,find+1,R2,valueIndexMap);

        return head;
    }

    /**
     * 生成哈希表 记录中序数组所有元素的位置
     * @param in
     * @return
     */
    public static HashMap<Integer,Integer> valueIndexMap(int[] in){
        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            integerIntegerHashMap.put(in[i],i);
        }
        return integerIntegerHashMap;
    }


    public static void pre(Node head){
        if (head == null){
            return;
        }
        System.out.print(head.value +" ");
        pre(head.left);
        pre(head.right);
    }
    public static void in(Node head){
        if (head == null){
            return;
        }
        in(head.left);
        System.out.print(head.value +" ");
        in(head.right);
    }

    public static void main(String[] args) {
//        int[] pre = {1, 2 ,4, 5, 3, 6, 7};
//        int[] in = {4, 2, 5, 1, 6, 3, 7};
        //一个不完整的二叉树
        int[] pre = {1, 2 ,3, 4, 5,6};
        int[] in = {2, 1, 5, 4, 6, 3};
//        TreeNode head = f(pre, 0, 6, in, 0, 6);
        Node head = g(pre, 0, 5, in, 0, 5, valueIndexMap(in));
        pre(head);
        System.out.println();
        in(head);
        System.out.println();
    }


}
