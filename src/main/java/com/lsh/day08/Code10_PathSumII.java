package com.lsh.day08;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/11 1:55 下午
 * @desc ：收集达标路径和
 * https://leetcode.com/problems/path-sum-ii/
 */
public class Code10_PathSumII {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void process(TreeNode x,List<Integer> path,int preSum,int sum,List<List<Integer>> listPath){
        if (x.left == null && x.right == null){
            if (preSum + x.val == sum){
                path.add(x.val);
                listPath.add(copy(path));
                //在return之前需要将此元素在列表中删除，因为此时递归方法将要返回，到达右树位置
                path.remove(path.size()-1);
                return;
            }
        }
        path.add(x.val);
        preSum += x.val;
        if (x.left != null){
            process(x.left,path,preSum,sum,listPath);
        }
        if (x.right != null){
            process(x.right,path,preSum,sum,listPath);
        }
        path.remove(path.size()-1);

    }

    public static List<Integer> copy(List<Integer> list){
        ArrayList<Integer> newList = new ArrayList<>();
        newList.addAll(list);
        return newList;
    }


    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        ArrayList<Integer> path = new ArrayList<>();
        process(root, path, 0, targetSum, ans);
        return ans;
    }

}
