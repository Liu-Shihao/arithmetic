package com.lsh.day14_graph;

import java.util.*;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/6 10:45 上午
 * @desc ：拓扑排序方式：点次
 *
 * https://www.lintcode.com/problem/127/
 *
 * 给定一个有向图，图节点的拓扑排序定义如下:
 * 对于图中的每一条有向边 A -> B , 在拓扑排序中A一定在B之前.
 * 拓扑排序中的第一个节点可以是图中的任何一个没有其他节点指向它的节点.
 * 针对给定的有向图找到任意一种拓扑排序的顺序.
 *
 * graph = {0,1,2,3#1,4#2,4,5#3,4,5#4#5}
 * [0, 1, 2, 3, 4, 5]
 */
public class Code04_TopologySorti {

    class DirectedGraphNode {
       int label;//自己
       List<DirectedGraphNode> neighbors;//由它出发的相邻点

       DirectedGraphNode(int x) {
         label = x;
         neighbors = new ArrayList<DirectedGraphNode>();
       }
    }



    //节点的点次记录
    public static class Record {
        public DirectedGraphNode node; //当前节点
        public long nodes;//点次

        public Record(DirectedGraphNode n, long o) {
            node = n;
            nodes = o;
        }
    }

    public static class MyRecordComparator implements Comparator<Record>{

        @Override
        public int compare(Record o1, Record o2) {
            //根据nodes排序，nodes大的排在前
            return o1.nodes == o2.nodes ? 0 : o1.nodes > o2.nodes ? -1 : 1;
        }
    }

    /**
     * @param graph: 有向图节点列表
     * @return: 给定图的任何拓扑顺序。
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode directedGraphNode : graph) {
            f(directedGraphNode,order);
        }
        ArrayList<Record> records = new ArrayList<>();
        for (Record value : order.values()) {
            records.add(value);
        }
        records.sort(new MyRecordComparator());
        ArrayList<DirectedGraphNode> ans = new ArrayList<DirectedGraphNode>();
        for (Record record : records) {
            ans.add(record.node);
        }

        return ans;
    }

    /**
     * 当前来到cur点，请返回cur点所到之处，所有的点次！
     * @param cur
     * @param order 缓存
     * @return
     */
    public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        long nodes = 0;
        for (DirectedGraphNode neighbor : cur.neighbors) {
            nodes += f(neighbor, order).nodes;
        }
        Record ans = new Record(cur, nodes + 1);
        order.put(cur,ans);
        return ans;
    }










    }
