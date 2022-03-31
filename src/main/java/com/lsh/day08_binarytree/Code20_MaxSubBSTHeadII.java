package com.lsh.day08_binarytree;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/30 8:25 下午
 * @desc ：返回二叉树的最大搜索子树的头节点
 *
 *             5
 *        3        8
 *     1     6   7   9
 *       2
 *  此二叉树整体不是搜索树，但是以3位头和以8位头的子树是搜索树，返回最大的搜索树的头节点，所以应该返回3节点
 */
public class Code20_MaxSubBSTHeadII {

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);

        head.left.left = new Node(1);
        head.left.right = new Node(6);
        head.left.left.right = new Node(2);

        head.right.left = new Node(7);
        head.right.right = new Node(9);
//        head.right.right.right = new Node(11);

        System.out.println(getMaxSubBSTHead(head).value);

    }


    public static class Info{
        public Node maxBSTSubtreeHead;//最大搜索树的头节点
        public int maxBSTSubtreeSize;//最大搜索树的节点数
        public int max;//左树最大值要比x小
        public int min;//右树最小值要比x大

        public Info(Node maxBSTSubtreeHead,int maxBSTSubtreeSize,int max,int min){
            this.maxBSTSubtreeHead = maxBSTSubtreeHead;
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
            this.max = max;
            this.min = min;
        }
    }
        public static Node getMaxSubBSTHead(Node head){
            if (head == null){
                return head;
            }
            return process(head).maxBSTSubtreeHead;
        }



        public static Info process(Node node){
            if (node == null) {
                return null;//如果节点为null，返回null信息，在上游注意进行判空
            }
            Info leftInfo = process(node.left);//获得左数信息
            Info rightInfo = process(node.right);//获得右树信息
            Node maxBSTSubtreeHead = null;
            int max = node.value;
            int min = node.value;
            int maxBSTSubtreeSize = 0 ;
            //分析可能性：
            if (leftInfo != null) {
                max = Math.max(leftInfo.max, max);
                min = Math.min(leftInfo.min, min);
                maxBSTSubtreeHead = leftInfo.maxBSTSubtreeHead;
                maxBSTSubtreeSize = leftInfo.maxBSTSubtreeSize;
            }
            if (rightInfo != null) {
                max = Math.max(rightInfo.max, max);
                min = Math.min(rightInfo.min, min);
                if (rightInfo.maxBSTSubtreeSize > maxBSTSubtreeSize) {
                    maxBSTSubtreeHead = rightInfo.maxBSTSubtreeHead;
                    maxBSTSubtreeSize = rightInfo.maxBSTSubtreeSize;
                }
            }
            //判断左树节点是否是搜索二叉树的头节点
            //判断右树节点是否是搜索二叉树的头节点
            //并且判断左树最大值是否小于节点值，右树最小值是都大于节点值
            //如果以上条件都满足，则该节点就是最大搜索二叉子树

            if ((leftInfo == null ? true : (leftInfo.maxBSTSubtreeHead == node.left && leftInfo.max < node.value))
                    && (rightInfo == null ? true : (rightInfo.maxBSTSubtreeHead == node.right && rightInfo.min > node.value))) {
                maxBSTSubtreeHead = node;
                maxBSTSubtreeSize = (leftInfo == null ? 0 : leftInfo.maxBSTSubtreeSize)
                        + (rightInfo == null ? 0 : rightInfo.maxBSTSubtreeSize) + 1;
            }
            return new Info(maxBSTSubtreeHead,maxBSTSubtreeSize,max,min);
        }

}
