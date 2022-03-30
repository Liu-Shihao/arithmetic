package com.lsh.day08_binarytree;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/30 1:01 下午
 * @desc ：二叉树结构
 */
public class Node {
        public Integer value;
        public Node right;
        public Node left;

        public Node(int v){
            value = v;
            right = null;
            left = null;
        }
}
