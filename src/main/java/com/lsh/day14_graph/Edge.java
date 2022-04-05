package com.lsh.day14_graph;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/5 8:56 下午
 * @desc ：线结构
 */
public class Edge {

    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight,Node from,Node to){
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    public Edge() {
    }
}
