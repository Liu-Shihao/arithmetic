package com.lsh.day03_random;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/1/29 4:18 下午
 * @desc ：
 * 题目1： 给定一个函数f，可以等概率返回1-5 ，返回1-7等概率。
 * 给定函数 f 定概率返回1~5
 * f2处理： 等概率返回 0和1
 * f3处理：等概率返回 0~7
 * f4处理：等概率返回 0~6  (7重做)
 * g函数处理：f4（） +1 即可达到要求
 *
 *
 * 发散： 给一个函数 a到b上 等概率返回 求返回17到56上等概率返回
 * 思路：
 * 1.首先获得 1和0 的等概率发生器
 * 2.17~56  =》 0~39上等概率
 * 3.通过1和0等概率 生成2进制 获得000000 ~ 111111 即（0~64）   可以获得0~64上等概率
 * 4.将大于39的数重做，即可返回0~39等概率
 * 5.随后在此基础上+17即可得到 17~56上等概率
 *
 * 以上是0和1等概率情况，还有0和1不等概率的问题：
 * 给一个函数f（） 返回0的概率是p ,返回1的概率是1-p
 * 我们需要借助两次f()函数:
 * （0，1） p(1-p) 返回 0 ,
 * （1，0） (1-p)p 返回 1。这样返回1和0就是等概率的。
 *
 *
 */
public class Code02 {

    public static void main(String[] args) {
        int count = 0 ;
        int total = 10000 ;
        int[] arr = new int[8];
        for (int i = 0; i < total; i++) {
            int n = g();
            arr[n]++;
        }
        for (int i = 0; i < 8; i++) {
            System.out.println(i+" 这个数字出现了 "+ arr[i] + " 次");
        }

    }

    /**
     * f 函数 ：等概率返回1-5
     * @return等概率返回1-5
     */
    public static int f(){
        return (int)(Math.random()*5)+1;
    }

    /**
     * 根据f函数，返回等概率返回 0和1
     * @return 等概率返回 0和1
     */
    public static int f2(){
        int ans = 0;
        do {
           ans =  f();
        }while (ans == 3);
        //如果是1和2 返回0   如果是4和5 返回1；如果是3 则重新循环。
        return ans < 3 ? 0 :1;
    }

    /**
     * 由于已知 f2 可以实现等概率返回 0和1 所以
     * 所以通过 2进制 获得 000 ~ 111 也是等概率的 即0~7 是等概率的 为： 0.5x0.5x0.5     即 1/8
     * @return 等概率返回0~7
     * 0 这个数字出现了 1265 次
     * 1 这个数字出现了 1285 次
     * 2 这个数字出现了 1256 次
     * 3 这个数字出现了 1242 次
     * 4 这个数字出现了 1260 次
     * 5 这个数字出现了 1191 次
     * 6 这个数字出现了 1255 次
     * 7 这个数字出现了 1246 次
     */
    public static  int f3(){
        int ans = (f2()<<2) + (f2()<<1) + (f2()<<0);
       return ans;
    }

    /**
     * 根据f3 函数，等概率返回 0~6,
     * @return等概率返回 0~6
     *
     * 0 这个数字出现了 1423 次
     * 1 这个数字出现了 1350 次
     * 2 这个数字出现了 1528 次
     * 3 这个数字出现了 1457 次
     * 4 这个数字出现了 1444 次
     * 5 这个数字出现了 1381 次
     * 6 这个数字出现了 1417 次
     * 7 这个数字出现了 0 次
     */
    public static int f4(){
        int ans = 0 ;
        do {
            ans = f3();
            //如果返回值为7 则重新进入循环。
        }while (ans == 7);
        return  ans ;
    }

    /**
     * 由于已知f4函数等概率返回0~6，所以+1即可等概率返回1~7
     * @return等概率返回1~7
     * 0 这个数字出现了 0 次
     * 1 这个数字出现了 1483 次
     * 2 这个数字出现了 1399 次
     * 3 这个数字出现了 1461 次
     * 4 这个数字出现了 1408 次
     * 5 这个数字出现了 1438 次
     * 6 这个数字出现了 1448 次
     * 7 这个数字出现了 1363 次
     */
    public static int g(){
        return f4()+1;
    }

    /**
     * 给定x（）函数，不等概率返回1和0
     * 不等概率返回0和1
     */
    public static int x(){
        return Math.random()>0.84 ? 0:1;
    }

    /**
     * 由已知函数x()不等概率返回 1和0
     * do中由x函数返回一次 0或1
     * while中再有x函数返回一次 1或0
     * 如果两次的值相同则重新进入循环
     * 只有 0 1  或者 1 0 即两次调用x函数值不相同时，返回ans值。实现等概率返回0和1
     * @return实现等概率返回0和1
     */
    public static int y(){
        int ans = 0 ;
        do {
            ans = x();
        }while (ans == x());
        return ans;
    }

}
