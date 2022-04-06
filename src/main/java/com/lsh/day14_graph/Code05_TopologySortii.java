package com.lsh.day14_graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/6 4:56 下午
 * @desc ：另一种拓扑排序方式：深度
 */
public class Code05_TopologySortii {

    class DirectedGraphNode {
        int label;//自己
        List<DirectedGraphNode> neighbors;//由它出发的相邻点

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static class Record {
        public DirectedGraphNode node;
        public int deep;//深度

        public Record(DirectedGraphNode n, int o) {
            node = n;
            deep = o;
        }
    }
    public static class MyComparator implements Comparator<Record> {
        //根据深度从大到小排序
        @Override
        public int compare(Record o1, Record o2) {
            return o2.deep - o1.deep;
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            f(node,order);
        }
        ArrayList<Record> arrayList = new ArrayList<>();
        for (Record value : order.values()) {
            arrayList.add(value);
        }
        arrayList.sort(new MyComparator());
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record record : arrayList) {
            ans.add(record.node);
        }
        return ans;

    }

    public static Record f(DirectedGraphNode node , HashMap<DirectedGraphNode,Record> order){
        if (order.containsKey(node)){
            return order.get(node);
        }
        int follow = 0;
        for (DirectedGraphNode neighbor : node.neighbors) {
            //注意此处与Code04_TopologySorti不一样
            follow = Math.max(follow, f(neighbor, order).deep);

        }
        Record record = new Record(node, follow+1);//最大深度+1（自己）
        order.put(node,record);
        return record;

    }










}
