package com.lsh.day14_graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/6 10:14 下午
 * @desc ：迪瑞克斯拉算法
 * 1）Dijkstra算法必须指定一个源点
 * 2）生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，源点到其他所有点的最小距离都为正无穷大
 * 3）从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源点到各个点的最小距离表，不断重复这一步
 * 4）源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了
 */
public class Code06_Dijkstra {

    /**
     * 迪瑞克斯拉算法一
     * @param from 给出一个出发节点
     * @return 返回出发节点可以到达的所有节点的最短距离的哈希表
     */
    public static HashMap<Node,Integer> dijkstra1(Node from){
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from,0);
        //selectedNodes 已经确认的点
        HashSet<Node> selectedNodes = new HashSet<>();
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);//获得在distanceMap中，不在selectedNodes，距离最近的点
        //此时获得minNode 为原始点
        while (minNode != from){
            Integer distance = distanceMap.get(minNode);//获得该节点的距离
            for (Edge edge : minNode.edges) {
                //遍历这个最小距离节点的 所有的边
                Node toNode = edge.to;//获得to
                if (!selectedNodes.contains(toNode)) {
                    //如果这个节点 不包含在selectedNodes
                    distanceMap.put(toNode,distance+edge.weight);
                }else {
                    //如果包含在selectedNodes中，则更新最小值
                    distanceMap.put(toNode,Math.min(distanceMap.get(toNode),distance+edge.weight));
                }

            }
            selectedNodes.add(minNode);//遍历完这个节点到其他节点的所有距离后，将此节点锁定
            //然后从distanceMap获得下一个距离最近的节点
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }
    //获得distanceMap中距离最小的点，但是不包含在selectedNodes中
    private static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
        Node ans = new Node();
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            Integer distance = entry.getValue();
            if (!selectedNodes.contains(node) && distance < min){
                ans = node;
                min = distance;
            }
        }
        return ans;
    }

    /**
     * 使用加强堆优化后的迪瑞克斯拉算法
     * @return
     */
    public static HashMap<Node,Integer> dijkstra2(Node head,int size){

        NodeHeap nodeHeap = new NodeHeap(size);

        HashMap<Node, Integer> result = new HashMap<>();


        return result;

    }

    public static class Record{
        private Node node;
        private int distance;

        public Record(Node node,int distance){
            this.node = node;
            this.distance = distance;
        }
    }

    public static  class NodeHeap{
        private Node[] nodes;//实际的堆结构
        private HashMap<Node,Integer> heapIndexMap;//反向索引： key 某一个node， value 上面堆中的位置(索引)
        private HashMap<Node,Integer> distanceMap;// 距离表：key 某一个节点， value 从源节点出发到该节点的目前最小距离（距离）
        private int size;//堆上有多少节点

        public NodeHeap(int size){
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        /**
         * 有一个节点node，从源点触发到达该点的距离为distance，判断是否在堆中加入/更新/忽略
         * 如果堆中不存在新节点 则添加
         * 如果堆中存在新节点，但是距离比堆中的小，则更新节点值
         * 如果距离比堆中的大，则忽略节点值
         * @param node
         * @param distance
         */
        public void addOrUpdateOrIgnore(Node node,int distance){
            //1.首先判断该节点是否在堆中
            if (inHeap(node)){
                //堆中包含该节点 则更新
                distanceMap.put(node,Math.min(distance,distanceMap.get(node)));
                // 因为 距离小才更新，索引堆内位置调整一定是上浮调整
                insertHeapify(node,heapIndexMap.get(node));//更新反向索引表和堆位置
            }
            if (!isEntered(node)){
                //节点不在堆中，并且没有被弹出（即从未入过堆）
                nodes[size] = node;//将节点接入到堆的最后一位
                heapIndexMap.put(node,size);
                distanceMap.put(node,distance);
                //然后进行堆内调整
                insertHeapify(node,size++);
            }
            //之后说明节点加入过，但是已经被弹出 则忽略
        }

        /**
         * 堆内调整  上浮节点
         * @param node 节点
         * @param index 索引
         */
        private void insertHeapify(Node node, int index) {
            while(distanceMap.get(nodes[index])< distanceMap.get(nodes[(index-1)/2])){
                //根据node的距离，判断堆内节点是否需要调整
                swap(index,(index-1) /2);
                index = (index-1) /2;
            }
        }

        //调整堆内两个元素的位置
        private void swap(int index1, int index2) {
            //堆内节点调整，记得更新反向索引表
            heapIndexMap.put(nodes[index1],index1);
            heapIndexMap.put(nodes[index2],index2);
            Node temp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = temp;
        }

        //返回该节点是否入过堆  如果加入过堆，但是已经被弹出，则在heapIndexMap中更新value为-1
        private boolean isEntered(Node node){
            return heapIndexMap.containsKey(node);
        }

        //判断节点是否在堆中，不包括已经弹出的
        private boolean inHeap(Node node){
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        //弹出堆顶节点
        public Record pop(){
            //此时堆顶的节点为nodes[0] ，将第一个节点与最后一个节点交换位置
            Record record = new Record(nodes[0], distanceMap.get(nodes[0]));
            swap(0,size-1);
            heapIndexMap.put(nodes[size-1],-1);//将堆顶节点弹出后，将反向索引表的value更新为-1
            distanceMap.remove(nodes[size-1]);//将节点从distanceMap移除
            nodes[size-1] = null;
            heapify(0,size--);
            return record;
        }

        /**
         * 调整堆内节点-下沉节点
         * @param index
         * @param size
         */
        private void heapify(int index, int size) {
            int left = index * 2 + 1;//找到左子节点
            //没有右节点没有超出边界
            while (left < size) {
                //left + 1 为右子节点
                //left + 1 < size 如果右节点没有超出边界 并且 右节点的距离 < 左节点的距离
                //为了找到 左右节点中更小的一个节点（因为小根堆结构，只有最小的在顶部，左右节点没有顺序）
                int smallest = left + 1 < size && distanceMap.get(nodes[left+1]) < distanceMap.get(nodes[left])
                        ? left + 1
                        : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                ////然后将新节点和子节点中更小的节点进行比较
                //如果新节点的distance距离 比 更小节点的距离还小 则交换位置，否则则停止，找到正确的位置
                if (smallest == index) {
                    break;
                }
                swap(smallest,index);
                index = smallest;
                left = left * 2 + 1;
            }
        }

    }
}
