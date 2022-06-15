/**
 * @author ：LiuShihao
 * @date ：Created in 2022/6/15 10:05 下午
 * @desc ：约瑟夫环问题
 * 约瑟夫问题是个著名的问题：
 *  N个人围成一圈，第一个人从1开始报数，报M的将被杀掉，下一个人接着从1开始报。如此反复，最后剩下一个，求最后的胜利者。
 *
 *  两种方法：
 *  1.总结公式：
 *  2.构建环状链表 通过计数器控制报数，提出节点 最后剩余一个自己执指向自己的节点
 *
 */
public class Josephus {

    public static void main(String[] args) {

        System.out.println(Josephus1(6, 5));
        System.out.println(Josephus2(6, 5));

    }

    public static int Josephus1(int n,int k){
        int s = 0;//
        for (int i = 1; i <= n; i++) {
            s = (s+k)%i;
        }
        return s+1;
    }

    /**
     *
     * @param N  总数
     * @param M  步长
     * @return
     */
    public static int Josephus2(int N,int M){
        Node head = new Node(1);
        Node pre = head;
        for (int i = 2; i <= N; i++) {
            Node cur = new Node(i);
            pre.next = cur;
            pre = cur;
        }
        pre.next = head;//指向头节点
//        while (true){
//            System.out.println(pre.index);
//            pre = pre.next;
//        }

        int n = M-1;
        Node cur = head;
        Node pre2 = null;
        while (cur.next != cur){
            if (n > 0){
//                System.out.println("节点："+cur.index + "报数："+n);
                pre2 = cur;
                cur = cur.next;
                --n;
            }else if (n == 0){
                n = M - 1;
//                System.out.println(n);
                System.out.println("剔除节点"+cur.index);
                pre2.next = cur.next;
                cur  = cur.next;

            }
        }
        return cur.index;
    }

    static class Node{
       public  int index;
       public Node next;

        public Node(int index) {
            this.index = index;
            this.next = null;
        }
    }



}
