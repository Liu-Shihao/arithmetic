package com.lsh.day14_graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/5 9:27 下午
 * @desc ：图的宽度优先遍历
 */
public class Code01_BFS {


    public static void bfs(Node start){
        if (start == null){
            return;
        }
        //准备队列
        Queue<Node> queue = new LinkedList<>();
        //准备Set 每次入队时，先查询set中是否包含，如果包含了说明节点之前已经如果队列了，不能重复入队（因为图结构节点可能重复指向）
        HashSet<Node> hashSet = new HashSet<>();
       queue.add(start);
       hashSet.add(start);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!hashSet.contains(next)){
                    queue.add(next);
                    hashSet.add(next);
                }
            }
        }




    }
}
