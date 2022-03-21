package com.lsh.day09_heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/1 11:18 上午
 * @desc ：加强堆
 * 最大线段重合问题
 * 一个数轴上，有很多的线段：[1,4] 、 [3,5]、 [7,9]、[6,10]...
 *MaximumLineSegmentCoincidence
 * 给定很多的线段，每个线段都有两个数[start,end],表示线段开始和结束的时间，左右都是闭区间
 * 规定：
 *      线段开始和结束的位置都是整数值
 *      线段重合长度必须>=1
 * 返回最大线段重合区域中，包含了几条线段
 *
 *
 */
public class Code03_MaximumLineSegmentCoincidence {

    public static class Line{
        public int start;
        public int end;

        public Line(int start,int end){
            this.start = start;
            this.end = end;
        }
    }

    public class EndComporator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.end - o2.end;
        }
    }
    public class StrtComporator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    /**
     * 入参为二维数组
     * m[0][0] 表示第一段线段的起点  ；m[0][1] 表示第一段线段的终点
     * @param m
     * @return
     * 此算法复杂度：O(N*logN)
     */
    public int findMaxLineNum(int[][] m){
        int max = 0;
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0],m[i][1]);
        }
        Arrays.sort(lines,new StrtComporator());//对线段以开始位置进行排序

        // 小根堆，每一条线段的结尾数值，使用默认的
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < lines.length; i++) {
            //当堆不为空，或者堆内的最小值小于等于当前线段的起始值 ，则弹出堆内元素
            while (!heap.isEmpty() && heap.peek()<=lines[i].start){
                heap.poll();
            }
            //将当前线段的终点值加入到堆中
            heap.add(lines[i].end);
            max = Math.max(max,heap.size());
        }
        return max;

    }


}
