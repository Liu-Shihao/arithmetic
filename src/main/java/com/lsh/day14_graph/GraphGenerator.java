package com.lsh.day14_graph;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/5 9:03 下午
 * @desc ：图结构生成器
 */
public class GraphGenerator {

    /**
     * [3,5,7] 5 ——> 7  距离为3
     * [1,0,3] 0 ——> 3  距离为1
     * [5,3,5] 3 ——> 5  距离为5
     * @param matrix 入参 二维矩阵  一个数组就表示一个边
     * @return  返回整个图结构
     */
    public static Graph createGraph(int[][] matrix){
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int[] edge = matrix[i];
            int weight = edge[0];
            int from = edge[1];
            int to = edge[2];
            if (!graph.nodes.containsKey(from)){
                //如果图结构的点集中没有包含from点，则创建出from节点
                Node node = new Node(from);
                graph.nodes.put(from,node);
            }
            if (!graph.nodes.containsKey(to)){
                //如果图结构的点集中没有包含to点，则创建出to节点
                Node node = new Node(to);
                graph.nodes.put(to,node);
            }
            //获得from节点和to节点
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            //创建从from到to的边结构
            Edge edge1 = new Edge(weight,fromNode,toNode);
            //从from节点出发的边 +1
            fromNode.edges.add(edge1);
            //从from出发到达的点 +1
            fromNode.nexts.add(toNode);
            //fron节点的 out +1
            fromNode.out++;
            //to节点的 in+1
            toNode.in++;

            graph.edges.add(edge1);
        }
        return graph;

    }
}
