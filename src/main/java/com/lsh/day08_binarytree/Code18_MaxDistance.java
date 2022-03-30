package com.lsh.day08_binarytree;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/30 5:19 下午
 * @desc ：二叉树的最大距离问题
 *
 */
public class Code18_MaxDistance {

    public static class Info{
        public int height;
        public int maxDistance;

        public Info(int m,int h){
            height = h;
            maxDistance = m;
        }
    }

    public static Info process(Node node){
        if (node == null) {
            return new Info(0,0);
        }
        Info leftInfo= process(node.left);//获得左树信息
        Info rightInfo = process(node.right);//获得右树信息
        int hight= Math.max(leftInfo.height,rightInfo.height)+1;
        //p1:左树的
        int maxDistance  = Math.max(Math.max(leftInfo.maxDistance,rightInfo.maxDistance),leftInfo.height+rightInfo.height+1);

        return new Info(maxDistance,hight);

    }



}
