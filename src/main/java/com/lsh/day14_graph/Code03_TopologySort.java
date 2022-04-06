package com.lsh.day14_graph;

import java.util.*;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/5 10:01 下午
 * @desc ：图的拓扑排序：
 * 1）在图中找到所有入度为0的点输出
 * 2）把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
 * 3）图的所有点都被删除后，依次输出的顺序就是拓扑排序
 *
 * 要求：有向图且其中没有环
 * 应用：事件安排、编译顺序
 */
public class Code03_TopologySort {
    public static List<Node> sortedTopology(Graph graph){
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node,node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        ArrayList<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()){
            Node cur = zeroInQueue.poll();
            //弹出入度为0的节点
            result.add(cur);
            //遍历该节点的邻节点，将他们的in入度减一，如果他们的入度为0，则加入到zeroInQueue中
            for (Node next : cur.nexts) {
                //该节点所有的邻节点 的入度-1
                inMap.put(next,inMap.get(next)-1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
