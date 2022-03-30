package com.lsh.day08_binarytree;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/11 11:00 上午
 * @desc ：能否组成路径和
 * 测试链接：https://leetcode.com/problems/path-sum
 * 从头节点开始到末尾叶节点位置能否组成要求路径和
 *
 */
public class Code10_PathSum {

    /**定义全局布尔类型变量*/
    public static boolean hasPathSum = false;

    /**
     * @param x 节点
     * @param preSum  上一个路径节点和
     * @param sum   要求的路径和
     */
    public static void process(Node x,int preSum,int sum){
        if (x.left == null && x.right==null){
            //到达链路末尾节点
            if (x.value + preSum == sum){
                hasPathSum = true;
            }
            return ;
        }
        preSum += x.value;
        if (x.left != null){
            process(x.left,preSum,sum);
        }
        if (x.right != null){
            process(x.right,preSum,sum);
        }
    }




    public static boolean hasPathSum(Node root, int targetSum) {
        if (root == null){
            return false;
        }
        hasPathSum = false;
        process(root,0,targetSum);
        return hasPathSum;
    }

    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(7);

        root.left.left = new Node(2);
        root.left.right = new Node(4);

        root.right.left = new Node(6);
        root.right.right = new Node(8);
        int targetSum = 12;
        System.out.println(hasPathSum(root,targetSum));
    }


}
