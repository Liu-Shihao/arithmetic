package com.lsh.day13_union;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/4 1:34 下午
 * @desc ：并查集（重要）
 *
 * 1.有若干个样本a、b、c、d…类型假设是V
 * 2.在并查集中一开始认为每个样本都在单独的集合里
 * 3.用户可以在任何时候调用如下两个方法：
 *        boolean isSameSet(V x, V y) : 查询样本x和样本y是否属于一个集合
 *        void union(V x, V y) : 把x和y各自所在集合的所有样本合并成一个集合
 * 4.isSameSet和union方法的代价越低越好
 *
 * 1）每个节点都有一条往上指的指针
 * 2）节点a往上找到的头节点，叫做a所在集合的代表节点
 * 3）查询x和y是否属于同一个集合，就是看看找到的代表节点是不是一个
 * 4）把x和y各自所在集合的所有点合并成一个集合，只需要小集合的代表点挂在大集合的代表点的下方即可
 *
 * 优化：
 *  1）节点往上找代表点的过程，把沿途的链变成扁平的
 *  2）小集合挂在大集合的下面
 *  3）如果方法调用很频繁，那么单次调用的代价为O(1)，两个方法都如此
 * 应用：
 *  解决两大块区域的合并问题
 *  常用在图等领域中
 */
public class Code01_UnionFind {

    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind<V> {
        public HashMap<V,Node<V>> nodes;//所有节点
        public HashMap<Node<V>,Node<V>> parents;//节点的祖先节点（代表节点）  K自己 ： V 代表节点
        public HashMap<Node<V>, Integer> sizeMap;//代表节点的 size

        public UnionFind(List<V> lists){
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : lists) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur,node);
                parents.put(node,node);//初始化时，自己节点指向自己
                sizeMap.put(node,1);//自己为代表节点，大小是1
            }
        }

        //找到当前节点的代表节点
        public Node<V> findFather(Node<V> cur){
            Stack<Node<V>> stack = new Stack<>();
            while (parents.get(cur) != cur){
                //如果当前节点的父节点不是自己，则说明不是代表节点
                stack.push(cur);
                cur = parents.get(cur);
            }
            //结束循环，说明cur目前就是代表节点
            while (!stack.isEmpty()){
                //重新为这个父节点链上的节点直接设置父节点为  代表节点
                parents.put(stack.pop(),cur);
            }
            return cur;
        }

        //判断 元素a  和 元素b 是否在一个集合中
        public boolean isSameSet(V a, V b) {
            //判断a的代表节点和b的代表节点是否是同一个
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        //将元素a和元素b合并为一个集合
        public void union(V a,V b){
            Node<V> father1 = findFather(nodes.get(a));
            Node<V> father2 = findFather(nodes.get(b));
            if (father1 != father2) {
                //如果a和b不在同一个集合
                Integer size1 = sizeMap.get(father1);
                Integer size2 = sizeMap.get(father2);
                Node big = size1>size2 ? father1 : father2;
                Node small = big == father1 ? father2 : father1;
                //判断两个集合的大小，小挂大
                parents.put(small,big);//将小的代表元素 指向 大的代表元素
                sizeMap.put(big,size1 + size2);//更新新的集合的大小
                sizeMap.remove(small);//移除small节点，（只保留代表节点的大小）
            }
        }

        //返回有几个集合（即代表元素有多少）
        public int sets(){
            return sizeMap.size();
        }

    }

}
