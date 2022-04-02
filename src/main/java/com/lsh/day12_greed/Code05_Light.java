package com.lsh.day12_greed;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/2 6:31 下午
 * @desc ：贪心算法：灯照问题     （此题贪心、动态规划、暴力都可以解）
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class Code05_Light {

    public static void main(String[] args) {
        String road = ".XX...X..X..X";//最优解为4
        System.out.println(minLight2(road));
    }

    //TODO 暴力解  2022年04月02日19:03:22

    //贪心解
    public static int minLight2(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        char[] str = road.toCharArray();
        int i = 0;
        int light = 0;
        while (i<str.length){
            if (str[i] == 'X') {
                i = i+1;
            }else {
                light++;
                // 当前位置 不是X
                if (i + 1 == str.length) {
                    //当前位置为最后一个
                    break;
                }
                if (str[i+1] == 'X'){
                    //下一个位置 为 X
                    //.X
                    i = i+2;//i跳到了i+2位置
                }else {
                    //下一个位置不是X
                    // ...  或者是 ..X
                    i = i+3;//i跳到了i+3位置
                }
            }
        }
        return light;
    }
}
