package com.lsh.day14_graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/5 8:56 下午
 * @desc ：用点结构和边结构 来表示图结构
 *
 */
public class Graph {
    public HashMap<Integer,Node> nodes;
    public HashSet<Edge> edges;


    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}
