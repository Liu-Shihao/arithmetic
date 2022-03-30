package com.lsh.day08_binarytree;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/9 3:32 下午
 * @desc ：判断两个二叉树是否一样
 *
 */
public class Code02_SameTree {

    /**
     * 判断两颗二叉树是否相同
     * @param head1
     * @param head2
     * @return
     */
    public static boolean isSameThree(Node head1, Node head2){
        // ^ 亦或 如果 即不同为真 相同为假。
        // （head1为空，head2不为空）亦或的结果为ture
        // （head1不为空，head2为空）亦或的结果也为ture
        // （head1为空，head2为空）亦或的结果也为 false
        // （head1不为空，head2不为空）亦或的结果也为 false
        if (head1 == null ^ head2 == null){
            //表示一个为空 一个不为空
            return false;
        }
        if (head1 == null && head2 == null){
            //如果两个都为空 ，则返回true
            return true;
        }
        //两者都不为空 ：比较头节点 、左节点结构、右节点结构
        return head1.value == head2.value && isSameThree(head1.left,head2.left)&& isSameThree(head1.right,head2.right);
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);

        head1.left.left = new Node(4);
        head1.left.right = new Node(5);

        head1.right.left = new Node(6);
        head1.right.right = new Node(7);

        Node head2 = new Node(1);
        head2.left = new Node(2);
        head2.right = new Node(3);

        head2.left.left = new Node(4);
        head2.left.right = new Node(5);

        head2.right.left = new Node(6);
//        head2.right.right = new TreeNode(7);
        System.out.println(isSameThree(head1,head2));
        System.out.println(isSameThree(head1,null));
        System.out.println(isSameThree(null,null));
    }
}
