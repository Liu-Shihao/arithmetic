package com.lsh.day14_graph;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/5 9:40 下午
 * @desc ：图的深度优先遍历
 */
public class Code02_DFS {

    //从一条路到底，然后依次向上返回走节点的其他分支
    //已经打印过的节点不在重复打印
    public static void dfs(Node start){
        if (start == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        set.add(start);
        stack.push(start);
        System.out.println(start.value);//入栈打印
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)){
                    //如果
                    set.add(next);
                    stack.push(cur);
                    stack.push(next);
                    System.out.println(next.value);//入栈打印
                    break;
                }
            }





        }

    }
}
