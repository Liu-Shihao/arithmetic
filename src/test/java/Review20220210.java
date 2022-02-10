/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/10 9:22 上午
 * @desc ：
 */
public class Review20220210 {

    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int v){
            val = v;
        }
    }

    public static void pre(TreeNode root){
        if (root == null){
            return;
        }
        System.out.print(root.val+" ");
        pre(root.left);
        pre(root.right);

    }
    public static void mid(TreeNode root){
        if (root == null){
            return;
        }
        mid(root.left);
        System.out.print(root.val+" ");
        mid(root.right);

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        pre(root);
        System.out.println();
        mid(root);
        System.out.println();




    }
}
