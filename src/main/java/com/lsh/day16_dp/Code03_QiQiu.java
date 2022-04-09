package com.lsh.day16_dp;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/21 9:47 下午
 * @desc ：打爆气球获得最大得分
 * 3，2，5，1，2  先有一组气球
 * 0，1，2，3，4   索引
 * 左边没有气球 系数为1，右边没有气球系数为1
 * 否则系数为该气球的分值
 * 例如：如果打爆第一个气球：1(系数为0)x3x2(系数为2)
 *
 */
public class Code03_QiQiu {

    public static void main(String[] args) {
        int[] arr = {3,1,6,3,7,5,9,12,4,10,56,20,19,45};
        Long start = System.currentTimeMillis();
        System.out.println(maxScore1(arr));
        System.out.println("暴力破解方法耗时："+(System.currentTimeMillis() - start));
        Long start2 = System.currentTimeMillis();
        System.out.println("------");
        System.out.println(maxScore2(arr));
        System.out.println("动态规划方法耗时："+(System.currentTimeMillis() - start2));

    }

    /**
     * 递归所有情况：暴力破解
     * @param arr
     * @return
     */
    public static int maxScore1(int[] arr){
        int n = arr.length;
        //借助一个新的数组 ，在原数组的前后位置各加上一个1
        int[] help = new int[n+2];
        help[0] = 1;
        help[n+1] = 1;
        for (int i = 1; i <= n; i++) {
            help[i] = arr[i-1];
        }
        //原始数组前后各补充 1
        //[2,5,1,6,2] -->[1,2,5,1,6,2,1]

        return  process1(help,1,n);
    }

    /**
     * arr[L..R]上打爆气球返回最大得分
     * 潜台词：
     * 设定arr[L-1]上的气球一定没爆， arr[R+1]上的气球一定没爆
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int process1(int[]arr,int L,int R){
        if (L==R){
            //只剩一个气球
            return arr[L-1] * arr[L] * arr[R+1];
        }
        //不止一个气球
        //分析可能性：
        //可能性1：最后打爆L位置的气球
        int p1 = process1(arr,L+1,R)+arr[L-1] * arr[L]*arr[R+1];
        //可能性2：最后打爆R位置的气球
        int p2 = process1(arr,L,R-1)+arr[L-1] * arr[R]*arr[R+1];
        int ans = Math.max(p1,p2);
        //尝试中间每一个气球都最后打爆：
        for (int mid = L+1; mid < R; mid++) {
            //arr[mid] 一定要最后打爆
            int cur = process1(arr,L,mid-1) + process1(arr,mid+1,R) + arr[L-1]* arr[mid]*arr[R+1];
            ans = Math.max(ans,cur);
        }
        return ans;
    }

    /**
     * 动态规划优化改进
     * 加上缓存表查询（二维数组实现）
     * @param arr
     * @return
     */
    public static int maxScore2(int[] arr){
        int n = arr.length;
        int[] help = new int[n+2];
        help[0] = 1;
        help[n+1] = 1;
        for (int i = 1; i <= n; i++) {
            help[i] = arr[i-1];
        }
        //初始化缓存表  -1表示未计算过。
        int [][] dp = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }

        }
        return  process2(help,1,n,dp);
    }
    /**
     * arr[L..R]上打爆气球返回最大得分
     * 潜台词：
     * 设定arr[L-1]上的气球一定没爆， arr[R+1]上的气球一定没爆
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int process2(int[]arr,int L,int R,int[][] dp){
        if (dp[L][R] != -1){
            return dp[L][R];
        }
        int ans = 0;
        if (L==R){
            //只剩一个气球
            ans =  arr[L-1] * arr[L] * arr[R+1];
        }else {
            //不止一个气球
            //分析可能性：
            //可能性1：最后打爆L位置的气球
            int p1 = process2(arr,L+1,R,dp)+arr[L-1] * arr[L]*arr[R+1];
            //可能性2：最后打爆R位置的气球
            int p2 = process2(arr,L,R-1,dp)+arr[L-1] * arr[R]*arr[R+1];
             ans = Math.max(p1,p2);
            //尝试中间每一个气球都最后打爆：
            for (int mid = L+1; mid < R; mid++) {
                //arr[mid] 一定要最后打爆
                int cur = process2(arr,L,mid-1,dp) + process2(arr,mid+1,R,dp) + arr[L-1]* arr[mid]*arr[R+1];
                ans = Math.max(ans,cur);
            }
        }
        dp[L][R] = ans;
        return ans;
    }

}
