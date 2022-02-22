package com.lsh.day10;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/21 9:11 下午
 * @desc ： 纸牌问题  先后手拿牌，最后拿到的牌和最大
 * 暴力破解与动态规划
 */
public class Code02_DynamicProgram_ZhiPai {
    public static void main(String[] args) {
        int[] arr = {2,8,4,3,7,10};

        System.out.println(getWinnerScore(arr));

        System.out.println(getWinnerScore2(arr));
    }

    /**
     * 递归实现-暴力破解
     * @param arr
     * @return
     */
    public static int getWinnerScore(int[] arr){
        int xian = xian(arr,0,arr.length-1);
        int hou = hou(arr,0,arr.length-1);
        return Math.max(xian,hou);
    }

    /**
     * 目前，在arr[L..R]这个返回上玩牌
     * 返回。先手最终的最大得分
     * @param L
     * @param R
     * @return
     */
    public static int xian(int[] arr,int L,int R){
        //首先定义终止条件
        if(L == R){
            //只剩一张牌，先手直接拿走
            return arr[L];
        }
        if (L == R-1){
            //还剩两张牌,返回最大的一张
            return Math.max(arr[L],arr[R]);
        }
        //L..R 上不止两张牌
        //可能性1. 你先手拿走L位置的牌,获得这个牌的值
        int p1 = arr[L] + hou(arr,L+1,R);
        //可能性2：你先手拿走R位置的牌
        int p2 = arr[R] + hou(arr,L , R-1);
        return Math.max(p1,p2);
    }

    /**
     * 后手拿牌
     * 目前是在arr[L..R]上玩牌
     * @param arr
     * @param
     * @return
     */
    private static int hou(int[] arr, int L, int R) {
        if (L==R){
            //只剩一张牌,但是被先手拿走
            return 0;
        }
        if (L == R-1){
            //还剩两张牌，先手拿走最大的，只剩下张小的
            //因为是后手那，所以先手不会留最大的给你，只能拿最小的牌，所以取最小值
            return Math.min(arr[L],arr[R]);
        }
        //L..R不止两张牌
        //后手
        //可能性1：对手先手拿走L位置的牌，你可以在L+1到R上先手拿牌。
        int p1 = xian(arr,L+1,R);

        //可能性2：对手先手拿走R位置的牌，你可以在L到R-1范围上先手拿牌
        int p2 = xian(arr,L,R-1);
        return Math.min(p1,p2);
    }

    /**
     * 优化递归：动态规划
     * 加上一个缓存表（二维数组实现）
     * @param arr
     * @return
     */
    public static int getWinnerScore2(int[] arr){
        int n = arr.length;
        int[][] dpxian = new int[n][n];
        int[][] dphou = new int[n][n];
        //初始化二维数组，-1表示未计算过，如果计算过，则不需要重复计算，直接拿走用即可
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dpxian[i][j] = -1;
                dphou[i][j] = -1;
            }
        }
        int xian = xian2(arr,0,arr.length-1,dpxian,dphou);
        int hou = hou2(arr,0,arr.length-1,dpxian,dphou);
        return Math.max(xian,hou);
    }
    public static int xian2(int[] arr,int L,int R,int[][] dpxian,int[][] dphou){
        if (dpxian[L][R] != -1){
            return dpxian[L][R];
        }
        int ans  = 0;
        if(L == R){
            return arr[L];
        }else if (L == R-1){
            return Math.max(arr[L],arr[R]);
        }else {
            int p1 = arr[L] + hou2(arr,L+1,R,dpxian,dphou);
            int p2 = arr[R] + hou2(arr,L , R-1,dpxian,dphou);
            ans= Math.max(p1,p2);
        }
        dpxian[L][R] = ans;
        return ans;
    }
    private static int hou2(int[] arr, int L, int R,int[][] dpxian,int[][] dphou) {
        if (dphou[L][R] != -1){
            return dphou[L][R];
        }
        int ans = 0;
        if (L==R){
            //只剩一张牌,但是被先手拿走
            return arr[L];
        }else if (L == R-1){
            //还剩两张牌，先手拿走最大的，只剩下张小的
            return Math.min(arr[L],arr[R]);
        }else {
            int p1 = xian2(arr,L+1,R,dpxian,dphou);
            int p2 = xian2(arr,L,R-1,dpxian,dphou);
            ans = Math.min(p1,p2);
        }
        dphou[L][R]=ans;
        return ans;
    }

}
