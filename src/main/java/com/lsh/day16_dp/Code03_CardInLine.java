package com.lsh.day16_dp;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/21 9:11 下午
 * @desc ： 从暴力破解到动态规划：纸牌问题
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数。
 *
 * win1: 暴力递归
 * win2: 加上缓存
 * win3: 动态规划
 */
public class Code03_CardInLine {
    public static void main(String[] args) {
//        int[] arr = {2,8,4,3,7,10};
//        // 先：10 3 8
//        // 后：7 4 2
//        System.out.println(win1(arr));
//        System.out.println(win2(arr));
//        System.out.println(win3(arr));

        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }

    /**
     * 根据规则返回最后获胜者的分数
     * @param arr
     * @return
     */
    public static int win1(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int first = f(arr,0,arr.length-1);
        int second = g(arr,0,arr.length-1);
        return Math.max(first,second);

    }

    /**
     * 先手拿牌
     * @param arr 牌堆
     * @param L 左边位置牌
     * @param R 右边位置牌
     * @return
     */
    private static int f(int[] arr,int L,int R){
        if (L==R){
            //即只剩一张牌，先手直接拿走
            return arr[L];
        }
        //第一种可能性：先手拿左边的牌，然后后手只能拿[L+1,R]位置的牌
        int p1 = arr[L] + g(arr,L+1,R);
        //第二种可能性：先手拿右边的牌，然后后手只能拿[L,R-1]位置的牌
        int p2 = arr[R] + g(arr,L,R-1);
        return Math.max(p1,p2);

    }

    /**
     * 后手拿牌
     * @return
     */
    private static int g(int[] arr, int L, int R) {
        if (L==R){
            // 只剩一张牌，则被先手拿走，后手没有牌拿返回0
            return 0;
        }
        //可能性一：先手拿走左边的牌，  后手从剩下的（L+1,R）位置先手
        int p1 = f(arr,L+1,R);
        //可能性二：先手拿走右边的牌，  后手从剩下的（L,R-1）位置先手
        int p2 = f(arr,L,R-1);
        return Math.min(p1,p2);
    }

    /**
     * 优化升级2：经过分析发现存在重复计算数据，所以加入缓存
     * 一共7张牌
     * 例如先手肯能拿走第一张或者第7张
     * 则依赖 arr[0]+g[1,6]  和 arr[6] + g[0,5]
     * g[1,6]依赖  f[1,5] 和 f[2,6]
     * g[0,5]依赖  f[0,5] 和 f[1,5]
     * ……
     * @param arr
     * @return
     */
    public static int win2(int[] arr){
        if (arr == null || arr.length ==0){
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];//
        int[][] gmap = new int[N][N];
        //初始化缓存
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }
        return Math.max(f2(arr,0,N-1,fmap,gmap),g2(arr,0,N-1,fmap,gmap));
    }
    //先手方法 缓存优化
    private static int f2(int[] arr, int L, int R, int[][] fmap,int[][] gmap) {
        if (fmap[L][R] != -1){
            return fmap[L][R];
        }
        int ans ;
        if (L==R){
            ans =  arr[L];
        }else{
            int p1 = arr[R]+g2(arr,L,R-1,fmap,gmap);
            int p2 = arr[L]+g2(arr,L+1,R,fmap,gmap);
            ans = Math.max(p1,p2);
        }
        fmap[L][R] = ans;
        return ans;
    }

    //后手方法 缓存优化
    private static int g2(int[] arr, int L, int R,int[][] fmap, int[][] gmap) {
        if (gmap[L][R] != -1){
            return gmap[L][R];
        }
        int ans = 0;
        if (L!=R){
            int p1 = f2(arr,L+1,R,fmap,gmap);
            int p2 = f2(arr,L,R-1,fmap,gmap);
            ans = Math.min(p1,p2);
        }
        gmap[L][R] = ans;
        return ans;
    }


    //升级三方法：动态规划
    public  static int win3(int[] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        //初始化famp 和 gmap
        //当L == R时 fmap[L,R] = arr[L];
        //当L == R时 gmap[L,R] = 0; 默认值即为0
        for (int i = 0; i < N; i++) {
            fmap[i][i] = arr[i];
        }
        //通过暴力递归分析可知：
        // fmap[L,R]位置的值 是由arr[L]+g[L+1,R] 和 arr[R]+g[l,R-1] MAX决定的
        // gmap[L,R]位置的值 是由f[L+1,R] 和f[L,R-1] 的MIN决定的
        //已经初始化了fmap 和 gmap 对角线的值
        //补充剩下表格 按照对角线填充
        // [0,1] 、[1,2]、[2,3]、[3,4]
        // [0,2] 、[1,3] 、[2,4]
        // [0,3]、[1,4]
        // [0,4]
        for (int startCol = 1; startCol < N; startCol++) {
            int L = 0;
            int R = startCol;
            while (R<N){
                fmap[L][R] = Math.max(arr[L]+gmap[L+1][R],arr[R]+gmap[L][R-1]);
                gmap[L][R] = Math.min(fmap[L+1][R],fmap[L][R-1]);
                R++;
                L++;
            }
        }
        return Math.max(fmap[0][N-1],gmap[0][N-1]);

    }




}
