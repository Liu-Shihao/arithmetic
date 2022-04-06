package com.lsh.day14_graph;

import java.util.*;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/6 9:21 下午
 * @desc ：最小生成树算法 Prim
 * 1）可以从任意节点出发来寻找最小生成树
 * 2）某个点加入到被选取的点中后，解锁这个点出发的所有新的边
 * 3）在所有解锁的边中选最小的边，然后看看这个边会不会形成环
 * 4）如果会，不要当前边，继续考察剩下解锁的边中最小的边，重复3）
 * 5）如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2）
 * 6）当所有点都被选取，最小生成树就得到了
 */
public class Code05_Prim {

    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }
    public static Set<Edge> primMST(Graph graph) {
        //1.解锁的边进入小根堆
        PriorityQueue<Edge> edges = new PriorityQueue<>();

        //2.解锁的点
        HashSet<Node> nodeSet = new HashSet<>();
        Set<Edge> result = new HashSet<>(); // 依次挑选的的边在result里

        for (Node node : graph.nodes.values()) {
            if (!nodeSet.contains(node)){
                //解锁第一个点，随即解锁以这个点的出发的边
                // 然后将边添加到小根堆中，（按权重从小到大），弹出权重最小的边  解锁这个边
                //  随即解锁这条边的to节点，（如果这个to节点不包含在之前的set集合中，说明该点未解锁）
                //   然后继续点 解锁边 边解锁点 （如果to节点是已经包含在之前的set中，说明形成闭环，则跳过该点）
                //   最最终由解锁的一个点，解锁了所有的边和点
                nodeSet.add(node);
                for (Edge edge : node.edges) {
                    edges.offer(edge);//解锁点后将所有的边解锁，添加到小根堆中
                }
                while (!edges.isEmpty()){
                    Edge edge = edges.poll();
                    Node toNode = edge.to;//下一个解锁的点
                    if (!nodeSet.contains(toNode)){
                        //set中不包含就是未解锁的点
                        nodeSet.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            edges.offer(nextEdge);//将新解锁的边添加到小根堆中
                        }
                    }
                }
            }
            break;//如果整个图结构所有节点形成最小生成树只有1棵，则可以直接break，如果存在多颗最小生成树（即森林情况）则不能break
        }
        return result;

    }


}
