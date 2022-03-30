package com.lsh.day08_binarytree;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/30 8:25 下午
 * @desc ：返回二叉树的最大搜索子树的节点数
 *
 *             5
 *        3        8
 *     1     6   7   9
 *       2
 *  此二叉树整体不是搜索树，但是以3位头和以8位头的子树是搜索树，返回最大的搜索树的节点数，所有应该返回4
 */
public class Code20_MaxSubBSTSize {

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);

        head.left.left = new Node(1);
        head.left.right = new Node(6);

        head.left.left.right = new Node(2);

        head.right.left = new Node(7);
        head.right.right = new Node(9);

        System.out.println(getMaxSubBSTSize(head));

    }


    public static class Info{
        public int maxBSTSubtreeSize;//最大搜索树的节点数
        public int allSize;//以x为头的子树的节点数(如果maxBSTSubtreeSize==allSize，说明以x为头节点的子树就是搜索树)
        public int max;//左树最大值要比x小
        public int min;//右树最小值要比x大

        public Info(int maxBSTSubtreeSize,int allSize,int max,int min){
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
            this.allSize = allSize;
            this.max = max;
            this.min = min;
        }
    }
        public static int getMaxSubBSTSize(Node head){
            if (head == null){
                return 0;
            }
            return process(head).maxBSTSubtreeSize;
        }



        public static Info process(Node node){
            if (node == null) {
                return null;//如果节点为null，返回null信息，在上游注意进行判空
            }
            Info leftInfo = process(node.left);//获得左数信息
            Info rightInfo = process(node.right);//获得右树信息
            int max = node.value;
            int min = node.value;
            int allSize = 1;//初始值为1表示自己节点个数
            if (leftInfo != null) {
                //如果左树信息不为空，更新max和min和allSize信息
                max = Math.max(leftInfo.max, max);
                min = Math.min(leftInfo.min, min);
                allSize += leftInfo.allSize;
            }
            if (rightInfo != null) {
                //如果右树信息不为空，更新max和min和allSize信息
                max = Math.max(rightInfo.max, max);
                min = Math.min(rightInfo.min, min);
                allSize += rightInfo.allSize;
            }
            int p1 = -1;//最大搜索树的节点数的 第一中可能性
            if (leftInfo != null) {
                p1 = leftInfo.maxBSTSubtreeSize;//第一种可能性：左树的最大搜索二叉树的所有节点
            }
            int p2 = -1;//最大搜索树的节点数的 第二种可能性 ： 右树的最大搜索二叉树的所有节点
            if (rightInfo != null) {
                p2 = rightInfo.maxBSTSubtreeSize;
            }
            int p3 = -1;//最大搜索树的节点数的 第三种可能性：左右树都是搜索二叉树，则该节点的整颗子树就是最大的搜索二叉树
            //leftBST 左树是否是搜索树
            boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
            //rightBST 右树是否是搜索树
            boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);

            //如果左右树都是搜索树，在判断左树的最大值是否小于该节点的值，右树的最小值是否大于该节点的值
            if (leftBST && rightBST) {
                boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < node.value);
                boolean rightMinMoreX = rightInfo == null ? true : (node.value < rightInfo.min);
                if (leftMaxLessX && rightMinMoreX) {
                    //如果以上条件都成立，则
                    int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                    int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                    //最大搜索树的节点数为左数的所有节点数 + 右树的所有节点数 + 节点本身
                    p3 = leftSize + rightSize + 1;
                }
            }
            //整合信息，返回该节点的信息
            return new Info(Math.max(p1, Math.max(p2, p3)), allSize, max, min);

        }

}
