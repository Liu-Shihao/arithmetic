package com.lsh.day08_binarytree;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/30 1:00 下午
 * @desc ：二叉树折纸问题
 * 把一张纸条第一次对折 ：会出现一个凹向下的折痕
 * 第二次对折：在第一次对折折痕上方出现一个凹向下的折痕，下方出现凸向上的折痕
 * 第三次对折：在第二次对折的凹向下的折痕上方出现凹向下的折痕，下方出现凸向上的折痕；同样第二次对折的凸向上折痕的上下方向也是一样
 *                                   2       1      2
 * 所以：在对折三次后的纸条的折痕顺序是：凹  凹  凸  凹  凹  凸  凸
 * 所以这样折纸后的折痕规律就是：头节点为凹，左节点为凹，右节点为凸
 * 即为二叉树中序遍历
 */
public class Code16_PaperFolding {


    public static void main(String[] args) {
        printPaperFolding(3);
    }

    //N 对折次数：即为二叉树的高度
    public static void printPaperFolding(int N){
        process(1,N,true);//从第一次对折开始：i= 1； 折痕为凹 down = Ture
    }
    /**
     *
     * @param N 一共有N层
     * @param i 当前第几层
     * @param down 节点状态 false: 凸  ；true :凹
     */
    public static void process(int i,int N,boolean down){
        if (i>N){
            return;
        }
        process(i+1,N,true);//左节点为 凹节点
        System.out.print(down ? "凹 ":"凸 ");//中序打印
        process(i+1,N,false);//右节点为 凸节点
    }
}
