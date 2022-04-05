package com.lsh.day14_graph;

import java.util.ArrayList;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/5 8:56 下午
 * @desc ：点结构
 */
public class Node {

    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
    }

    public Node() {
    }
}
