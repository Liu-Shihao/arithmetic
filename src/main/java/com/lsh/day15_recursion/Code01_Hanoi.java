package com.lsh.day15_recursion;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/4/8 10:47 上午
 * @desc ：递归练习1
 * 打印n层汉诺塔从最左边移动到最右边的全部过程 只能小的压在大的上面
 * Move 1 from left to right
 * Move 2 from left to mid
 * Move 1 from right to mid
 * Move 3 from left to right
 * Move 1 from mid to left
 * Move 2 from left to right
 * Move 1 from left to right
 */
public class Code01_Hanoi {
    public static void main(String[] args) {
        hanoi1(3);
        System.out.println("==========================");
        hanoi2(3);
    }

    public static void hanoi1(int n){
        leftToRight(n);
    }

    private  static void leftToRight(int n) {
        if (n == 1){
            System.out.println("Move 1 from left to right");
            return;
        }
        //首先 左边  移到 中间
        leftToMid(n-1);//第一大步
        // 从 左 到 右
        System.out.println("Move "+n+" from left to right");//第二步
        //最后中间 移动 到右边
        midToRight(n-1);//第三大步

    }


    //第一大步 从左到中
    private static  void leftToMid(int n) {
        if (n == 1){
            System.out.println("Move 1 from left to mid");
            return;
        }
        //第一大步再细分为
        // 1.从左到右，
        // 2.然后从左到中，
        // 3.最后在从右到中
        leftToRight(n-1);
        System.out.println("Move "+n+" from left to mid");
        rightToMid(n-1);
    }



    //从中往右   第三大步
    private static  void midToRight(int n) {
        if (n ==1){
            System.out.println("Move 1 from mid to right");
            return;
        }
        //从中往右在细分为3小步：
        // 1.从中往左
        // 2.从中往右
        // 3.从左往右
        midToLeft(n-1);
        System.out.println("Move "+n+" from mid to right");
        leftToRight(n-1);

    }



    // 从右往中
    private static  void rightToMid(int n) {
        if (n == 1){
            System.out.println("Move 1 from right to mid");
            return;
        }
        rightToLeft(n-1);
        System.out.println("Move "+n+" from right to mid");
        leftToMid(n-1);
    }

    //从右到左
    private static void rightToLeft(int n) {
        if (n == 1){
            System.out.println("Move 1 from right to left");
            return;
        }
        rightToMid(n-1);
        System.out.println("Move "+n+" from right to left");
        midToLeft(n-1);
    }

    //从中到左
    private static void midToLeft(int n) {
        if (n==1){
            System.out.println("Move 1 from mid to left");
            return;
        }
        midToRight(n-1);
        System.out.println("Move "+n+" from mid to left");
        rightToLeft(n-1);
    }



    //6合1
    //将左中右的概念转换为from、to、other
    public static void hanoi2(int n){
        if (n>2){
            func(n,"left","right","mid");
        }
    }

    private static void func(int n,String from,String to,String other){
        if (n == 1){
            System.out.println("Move 1 from "+from + " to "+to);
        }else {
            // 先将n-1 从from移动到other
            func(n-1,from,other,to);
            // 然后将n从from移动到to
            System.out.println("Move "+n+" from "+from + " to "+to);
            // 最后将n-1从other移动到to
            func(n-1,other,to,from);
        }
    }

    //非递归版本
    public static void hanoi3(int n){

    }
}
