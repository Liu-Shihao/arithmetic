package com.lsh.day14_graph;

import java.util.*;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/6 7:12 下午
 * @desc ：最小生成树算法 Kruskal K算法
 * 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
 * 2）当前的边要么进入最小生成树的集合，要么丢弃
 * 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
 * 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
 * 5）考察完所有边之后，最小生成树的集合也得到了
 */
public class Code04_Kruskal {
    /**
     * 1.首先将所有的点加入到并查集中
     * 2.将所有的边添加到小根堆中（按照边长（权重）从小到大排序）
     * 3.定义Set，避免重复
     * 4.从堆中按照边长从小到大弹出 ，合并from和to点 ，然后添加到set中，直到所有的边都弹出
     * @param graph 图结构（包括点和边）
     * @return 返回最小生成树
     */
    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());//将图结构所有的节点初始化到并查集中
        //定义小根堆
        PriorityQueue<Edge> heap = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {
            heap.offer(edge);//将所有的边加入小根堆中，排序
        }
        HashSet<Edge> ans = new HashSet<>();
        while (!heap.isEmpty()) {
            Edge edge = heap.poll();
            //从堆中弹出一个边，判断from节点和to节点，是否已经属于一个集合
            if(unionFind.isSameSet(edge.from,edge.to)){
                //如果from节点和to节点不是一个集合
                ans.add(edge);
                unionFind.union(edge.from,edge.to);
            }
        }
        return ans;

    }


    //定义并查集
    public static  class UnionFind{

        public HashMap<Node,Node> fatherMap;//key 某一个节点， value key节点往上的节点
        public HashMap<Node,Integer> size;// key 某一个集合的代表节点, value key所在集合的节点个数

        public UnionFind(){
            fatherMap = new HashMap();
            size = new HashMap();
        }
        //将并查集进行初始化
        public void makeSets(Collection<Node> nodes){
            fatherMap.clear();
            size.clear();
            for (Node node : nodes) {
                fatherMap.put(node,node);
                size.put(node,1);
            }

        }
        //向上找到代表元素并压缩路径
        public Node find(Node node){
            Stack<Node> stack = new Stack<>();
            while (node != fatherMap.get(node)){
                stack.push(node);
                node = fatherMap.get(node);
            }
            while (!stack.isEmpty()){
                Node pop = stack.pop();
                fatherMap.put(pop,node);
            }
            return node;
        }

        //判断 节点a和节点b是否在同一个集合中
        public boolean isSameSet(Node a,Node b){
            return find(a) == find(b);
        }

        //节点合并
        public void union(Node a,Node b){
            Node f1 = find(a);
            Node f2 = find(b);
            if (f1 != f2) {
                if (size.get(f1)>size.get(f2)){
                    fatherMap.put(f2,f1);
                    size.put(f1,size.get(f1)+size.get(f2));
                    size.remove(f2);
                }else {
                    fatherMap.put(f1,f2);
                    size.put(f2,size.get(f1)+size.get(f2));
                    size.remove(f1);
                }
            }
        }
    }
    //定义比较器
    public static class EdgeComparator implements Comparator<Edge>{
        //按照边长 从小到大排序
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }





}
