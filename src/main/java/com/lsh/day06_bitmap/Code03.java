package com.lsh.day06_bitmap;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/14 11:08 上午
 * @desc ：位图 BitMap
 * 一个int整数 是32位
 * & 与操作  同1为1 ，有0为0
 * | 或操作  有1为1 ，同0为0
 *
 * 可以通过|或操作将该位置设置为1 ，以此来表示标记数字
 * 可以通过&与操作将该位置设置为0 ，取消数字
 */
public class Code03 {

    /**
     * 一个数可以表示  0-31
     * 两个数可以表示  32-63
     * 三个数可以表示  64-96
     * ...
     *
     * 位图类
     */
    public static class BitMap{

        public int[] bits;

        /**
         * 初始化数组的长度，最大值（补上一位） 除以32 即表示数组最大多少长度可以表示这个最大值。
         * @param max 表示此位图结构中存储的最大的值
         */
        public BitMap(int max){
            //(max + 32) >> 5   等同于  (max + 32)/32
            bits = new int[(max + 32)>>5];
        }

        /**
         * 通过或操作
         * 向位图里添加一个数
         * 00000000000000000000000000000001
         * 00000000000000000000000000010000
         * 与操作
         * 00000000000000000000000000010001 将该位置标记为1
         * @param num
         */
        public void add(int num){
            //首先判断是在数组的哪一位上表示  除以32
            //再判断这位整数上的第几位 模上32 取余数即表示第几位
            //把这个位标记为1 即 与上1<<第几位
            //bits[num >> 5] = bits[num >> 5] | 1<<(num % 32 ) 就等同于bits[num >> 5]  |=   1<<(num % 32 )
            //bits[num >> 5] = bits[num >> 5] | 1<<(num % 32 );
            //注意：
            //%64（除以64后的余数） 等同于 &63（与63） ,因为63的二进制表示为111111
            //所以num % 32 等同于  num & 31
            bits[num >> 5]  |=   1<<(num & 31 );
        }

        /**
         * 从位图里删除一个数
         * 和（余数取反）与操作
         *
         * 该位置数：00000000000000000000000000100001
         * 余数：   00000000000000000000000000000001
         * 余数取反：11111111111111111111111111111110
         * 与操作： 00000000000000000000000000000001
         * 即该位置上的1消除，表示此数值已删除
         * @param num
         */
        public void del(int num){
            //通过&与操作将数组上第几位的数字上的该位置清空
            bits[num >> 5]  &=   ~(1<<(num & 31 ));
        }

        /**
         * 查看位图中是否包含这个数
         * @param targetNum 目标数
         * @return  是否包含
         */
        public boolean contains(int targetNum){
            //该位置上的数 和32的余数 与操作如果不为0 则存在 否则不存在
            return (bits[targetNum >> 5] & (1 << (targetNum & 31))) != 0;
        }
    }

    /**
     * 打印二进制
     * 从0位开始到31位
     * @param num
     */
    public static void printNum(int num){
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1<<i)) == 0 ? "0": "1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //新建一个最大值为1000的位图
        BitMap bitMap = new BitMap(1000);
        System.out.println("数组长度："+bitMap.bits.length);
        System.out.println("是否包含100："+bitMap.contains(100));
        //添加操作 ：100/32 = 3...4 ；即把数组第4个元素的第4位标记为1
        bitMap.add(100);
        printNum(bitMap.bits[3]);
        System.out.println("是否包含100："+bitMap.contains(100));
        //删除操作
        bitMap.del(100);
        System.out.println("是否包含100："+bitMap.contains(100));

//        int num = 0;
//        // false  第7位上没有1, 表示 7 不存在
//        System.out.println(!((num & (1 << 7)) == 0) );
//        // (num | 1 << 7 ) 将第7位上标记为1 ： 00000000000000000000000010000000 表示7
//        printNum(num | 1 << 7 );
//        num = num | 1 << 7 ;
//        //true  第7位上有1, 表示 7 存在
//        System.out.println(!((num & (1 << 7)) == 0) );




//        //将num第0位上标记上1 表示1
//        System.out.println(num | 1);
//        //把1左移一位（<<1），将第二位上设置为1  表示2
//        System.out.println(num | 1<<1);
//        //把1第三位设置为1     表示3
//        System.out.println(num | 1<<3);
//        //通过将一个int整数的32位 标记上1这种方式，则可以通过一个整数表示32个整数有没有出现过，出现过该位为1，没有出现过该位为0。如：
//        System.out.println("----------------");
//        //表示7出现过  所以 | 或 操作可以表示添加操作
//        printNum(num | (1 << 7));
//        //表示7没有出现过   所以 & 与 操作可以表示添加操作
//        printNum(num & (1<< 7));



    }
}
