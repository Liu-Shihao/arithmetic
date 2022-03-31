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
        public int allSIze;//以当节点为二叉树的所有节点数
        public int max;//左树最大值要比x小
        public int min;//右树最小值要比x大

        public Info(Node maxBSTSubtreeHead,int maxBSTSubtreeSize,int allSIze,int max,int min){
            this.maxBSTSubtreeHead = maxBSTSubtreeHead;
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
            this.allSIze = allSIze;
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
            Node maxBSTSubtreeHead ;
            int max = node.value;
            int min = node.value;
            int allSize = 1;
            int maxBSTSubtreeSize ;
            //分析可能性：
            if (leftInfo != null) {
                max = leftInfo.max;
                min = leftInfo.min;
                allSize += leftInfo.allSIze;
            }
            if (rightInfo != null) {
                max = rightInfo.max;
                min = rightInfo.min;
                allSize += rightInfo.allSIze;
            }
            int p1 = -1;
            if (leftInfo != null) {
                p1 = leftInfo.maxBSTSubtreeSize;//第一种可能性：左树的最大搜索二叉树的所有节点
            }
            int p2 = -1;//最大搜索树的节点数的 第二种可能性 ： 右树的最大搜索二叉树的所有节点
            if (rightInfo != null) {
                p2 = rightInfo.maxBSTSubtreeSize;
            }
            int p3 = -1;
            //判断以自己为节点的二叉树是否是搜索树：左和右树都是搜索树，并且 左树最大值<x<右树最小值
            boolean isLeftBST = leftInfo == null ? true : leftInfo.maxBSTSubtreeSize == leftInfo.allSIze;
            boolean isRightBST = rightInfo == null ? true : rightInfo.maxBSTSubtreeSize == rightInfo.allSIze;

            if (isLeftBST && isRightBST ){
                boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < node.value);
                boolean rightMinMoreX = rightInfo == null ? true : (node.value < rightInfo.min);
                if (leftMaxLessX && rightMinMoreX) {
                    //如果以上条件都成立，则说明该节点本身也是一个搜索二叉树
                    int leftSize = leftInfo == null ? 0 : leftInfo.allSIze;
                    int rightSize = rightInfo == null ? 0 : rightInfo.allSIze;
                    p3 = leftSize + rightSize +1;
                }
            }
            maxBSTSubtreeSize = Math.max(p1, Math.max(p2, p3));
            maxBSTSubtreeHead = maxBSTSubtreeSize == p1 ? node.left : maxBSTSubtreeSize == p2 ? node.right : node;
            //整合信息，返回该节点的信息
            return new Info(maxBSTSubtreeHead,maxBSTSubtreeSize,allSize,max,min);
        }

}
