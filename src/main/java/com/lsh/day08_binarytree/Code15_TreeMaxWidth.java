package com.lsh.day08_binarytree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/29 8:45 下午
 * @desc ：二叉树的最大宽度
 */
public class Code15_TreeMaxWidth {
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
    //返回二叉树最大宽度 借助HashMap容器
    //使用HashMap标记每个节点为第几层
    public static int maxWidthUseMap(Node head) {
        if(head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1; // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0; // 当前层curLevel层，宽度目前是多少
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            //如果左节点不为空，标记左节点节点为下一层
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            //如果右节点不为空，标记右节点节点为下一层
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    //返回二叉树最大宽度 不使用HashMap容器
    //借助 curEnd当前层最后节点 ，nextEnd下一层最后节点 几个变量
    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head; // 当前层，最右节点是谁
        Node nextEnd = null; // 下一层，最右节点是谁
        int max = 0;
        int curLevelNodes = 0; // 当前层的节点数
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (cur == curEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

}
