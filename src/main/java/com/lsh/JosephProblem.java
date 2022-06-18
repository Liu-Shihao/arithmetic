package com.lsh;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/6/18 10:07 下午
 * @desc ：约瑟夫问题 ： N个人围成一圈，第一个人从1开始报数，报M的将被杀掉，下一个人接着从1开始报。如此反复，最后剩下一个，求最后的胜利者。
 */
public class JosephProblem {

    public static void main(String[] args) {
//        System.out.println(joseph1(6,2));
//        System.out.println(joseph2(6,2));
        System.out.println(joseph3(6,2));
    }


    //规律
    public static int joseph1(int m ,int k){
        int s = 1;//存活者编号
        for (int i = 1; i <= m ; i++) {
            s = (s+k)%i;
        }
        return s+1;
    }
    //数组
    public static int joseph2(int m,int k){
        int ans = 0;
        int[] arr = new int[m];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        int count = 0,num = 0;
        for (int i = 0; i <= arr.length; i++) {
            if (i == arr.length) {
                i = -1;
                continue;
            }
            if (arr[i] == 1){
                //有效元素
                ++count;
                if (count == k){
                    arr[i] = 0;
                    ++num;//存活数+1
                    count = 0;//重置计数器
                }
            }
            if (num == m-1){
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j] == 1){
                        return j+1;
                    }
                }
            }
        }
        return ans;

    }

    static class Node{
       public int value;
       public Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    /**
     * 使用链表方法
     * @param m
     * @param k
     * @return
     */
    public static int joseph3(int m,int k){
        Node head = new Node(1);
        Node pre = head;
        for (int i = 2; i <= m; i++) {
            Node cur = new Node(i);
            pre.next = cur;
            pre = cur;
        }
        pre.next = head;//注意此处形成回环结构 最后一个节点 指向 头节点
        Node cur = head;//从头节点开始遍历
        pre = null;
        int count = 1;//计数从1开始
        while (cur.next != cur){
            if (count != k){
                //向下移动
                pre = cur;
                cur = cur.next;
                ++count;
            }else if (count == k){
                //删除节点
                pre.next = cur.next;
                System.out.println("删除节点："+cur.value);
                cur = cur.next;
                count = 1;//重置计数
            }
        }
        return cur.value;

    }

}
