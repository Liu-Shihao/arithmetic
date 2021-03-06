package com.lsh.day08_binarytree;

import java.util.LinkedList;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/10 1:44 下午
 * @desc ：算法题目： 二叉树按层遍历并收集节点
 * BinaryTreeLevelOrderTraversal
 */
public class Code06_BinaryTreeLevelOrderTraversal {
    /**
     *
     * @param root
     * @return
     */
    public LinkedList<LinkedList<Integer>> levelOrderBottom(Node root){
        LinkedList<LinkedList<Integer>> answer = new LinkedList<>();
        if (root == null){
            return answer;
        }
        LinkedList<Node> queue = new LinkedList<>();
        //新建LinkedList 将头节点放入
        queue.add(root);
        //如果queue 链表不为空
        while (!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> curAns = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                Node curNode = queue.poll();
                curAns.add(0,curNode.value);
                if (curNode.left != null){
                    //如果 左节点 有元素，放入queue队列中
                    queue.add(curNode.left);
                }
                if (curNode.right != null){
                    //如果 右节点 有元素，放入queue队列中
                    queue.add(curNode.right);
                }
            }
            //将这一层的节点放入大链表中
            answer.add(0,curAns);
        }
        return answer;
    }


}
