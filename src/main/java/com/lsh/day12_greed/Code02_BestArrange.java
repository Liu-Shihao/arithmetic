package com.lsh.day12_greed;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/1 4:01 下午
 * @desc ：贪心算法问题：会议室问题
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
 * 返回最多的宣讲场次。
 *
 * 贪心策略1：开始时间最早  错误
 * 贪心策略2：持续时间最短  错误
 * 贪心策略3：结束时间最早  正确
 *
 * 暴力解：
 *
 */
public class Code02_BestArrange {
    public static void main(String[] args) {
        Program[] programs = new Program[5];
        programs[0] = new Program(1,3);
        programs[1] = new Program(1,4);
        programs[2] = new Program(3,4);
        programs[3] = new Program(1,10);
        programs[4] = new Program(4,9);

        System.out.println(bestArrange2(programs));

    }

    public static class Program {
        public int start;//开始时间
        public int end;//结束时间

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    //暴力解 所有情况都尝试
    public static int bestArrange1(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        return process(programs, 0, 0);
    }

    /**
     *
     * @param programs  还剩余的会议
     * @param done      已经安排的会议数
     * @param timeLine  会议室会议结束时间
     * @return  返回能安排的最多会议数量
     */
    private static int process(Program[] programs, int done, int timeLine) {
        if (programs == null || programs.length == 0) {
            //没有会议了:
         return done;
        }
        //剩余还有会议:
        int max = done;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine) {
                Program[] next = copyButExcept(programs, i);
                max = Math.max(max, process(next, done + 1, programs[i].end));
            }
        }
        return max;
    }
    public static Program[] copyButExcept(Program[] programs, int index) {
        Program[] ans = new Program[programs.length - 1];
        int j = 0;
        for (int i = 0; i < programs.length; i++) {
            if (i != index){
                ans[j++] = programs[i];
            }
        }
        return ans;
    }

    //贪心算法
    public static int bestArrange2(Program[] programs) {
        if (programs == null|| programs.length == 0 ){
            return 0;
        }
        Arrays.sort(programs,new ProgramsComparator());
        int timeEnd = 0;
        int res = 0;
        for (Program program : programs) {
            if (program.start >= timeEnd ){
                res++;
                timeEnd = program.end;
            }
        }
        return res;
    }

    public static class ProgramsComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            //根据会议的结束时间排序
            return o1.end - o2.end;
        }
    }


}
