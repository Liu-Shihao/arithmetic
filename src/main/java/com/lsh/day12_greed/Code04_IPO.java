package com.lsh.day12_greed;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/2 5:15 下午
 * @desc ：贪心算法：收益最大问题
 *输入: 正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数。
 */
public class Code04_IPO {

    public static void main(String[] args) {

    }

    public static class Program{
        public int c;//花费
        public int p;//利润

        public Program(int c,int p){
            this.c = c;
            this.p = p;
        }
    }

    /**
     * 贪心解法
     * @param Capital 项目的花费
     * @param Profits (利润)
     * @param K 你只能串行的最多做k个项目
     * @param W 初始的资金
     * @return
     */
    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
        //按照花费从小到大排序
        PriorityQueue<Program> minCostQ = new PriorityQueue<>();
        //按照利润从大到小排序
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>();

        for (int i = 0; i < Profits.length; i++) {
            minCostQ.add(new Program(Capital[i],Profits[i]));
        }
        //按照初始资金可以购买的项目 按照花费从小到大取出 K 个
        //然后在按照利润从大到小
        for (int i = 0; i < K; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c < W){
                //只有当minCostQ不为空，并且初始资金大于项目花费时才弹出
                //将所有可以做的项目弹出到maxProfitQ中
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                //如果maxProfitQ为空
                return W;
            }
            //弹出目前 花费最小，利润最大的项目
            W += maxProfitQ.poll().p;
        }
        return W;

    }

    //按照花费来从小到大排序  正序
    public static class CostComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }
    }
    //按照利润来从大到小排序  倒序
    public static class ProfitsComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }
    }
}
