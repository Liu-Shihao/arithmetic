package day01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Author: LiuShihao
 * Date: 2022/7/17 14:36
 * Desc: 并查集问题
 */
public class LeetCode200_UnionFind{

    public int numIslands(char[][] grid) {
        int rowNum = grid.length;
        int colNum = grid[0].length;
        Point[][] points = new Point[rowNum][colNum];
        ArrayList<Point> list = new ArrayList<>();
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (grid[i][j] == '1'){
                    points[i][j] = new Point();
                    list.add(points[i][j]);
                }
            }
        }

        UnionFind<Point> unionFind = new UnionFind<>(list);
        //只合并左上
        //从第一行开始合并 （0,1）（0,2）（0,rowNum）
        for (int i = 1; i < colNum; i++) {
            if (grid[0][i-1] == '1' &&  grid[0][i] == '1' ){
                unionFind.union(points[0][i-1],points[0][i]);
            }
        }
        //从第一例开始合并
        for (int i = 1; i < rowNum; i++) {
            if (grid[i-1][0] == '1' &&  grid[i][0] == '1' ){
                unionFind.union(points[i-1][0],points[i][0]);
            }
        }
        //从（1，1）开始合并
        for (int i = 1; i < rowNum; i++) {
            for (int j = 1; j < colNum; j++) {
                if (grid[i][j] == '1'){
                    if (grid[i-1][j] == '1'){
                        unionFind.union(points[i-1][j],points[i][j]);
                    }
                    if (grid[i][j-1] == '1'){
                        unionFind.union(points[i][j-1],points[i][j]);
                    }
                }
            }
        }

        return unionFind.setNum();

    }
    class Point{

    }


    class Node<V>{
        V v;
        public Node(V v) {
            this.v = v;
        }
    }
    class UnionFind<V>{
        HashMap<V,Node<V>> nodes;
        HashMap<Node<V>,Node<V>> parents;
        HashMap<Node<V>,Integer> sizeMap;

        public UnionFind(List<V> list) {
            this.nodes = new HashMap<>();
            this.parents = new HashMap<>();
            this.sizeMap = new HashMap<>();
            for (V cur : list) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur,node);
                parents.put(node,node);
                sizeMap.put(node,1);
            }
        }

        public void union(V a,V b){
           Node<V> father1 =  findFather(nodes.get(a));
           Node<V> father2 =  findFather(nodes.get(b));
           if (father1 != father2){
               Integer size1 = sizeMap.get(father1);
               Integer size2 = sizeMap.get(father2);
               Node big = size1 > size2 ? father1 : father2;
               Node small = big == father1 ? father2 : father1;
               parents.put(small,big);
               sizeMap.put(big, size1+size2);
               sizeMap.remove(small);
           }
        }

        public  int setNum(){
            return sizeMap.size();
        }

        private Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> stack = new Stack<>();
            while (parents.get(cur) != cur){
                stack.push(cur);
                cur = parents.get(cur);
            }
            while (!stack.isEmpty()){
                parents.put(stack.pop(),cur);
            }
            return cur;

        }
    }


}
