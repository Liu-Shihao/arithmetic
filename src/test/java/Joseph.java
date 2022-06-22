/**
 * @author ：LiuShihao
 * @date ：Created in 2022/6/19 3:22 下午
 * @desc ：
 */
public class Joseph {

    public static void main(String[] args) {
//        System.out.println(joseph1(6,2));
        System.out.println(joseph2(6,2));
    }

    static class Node{
        int value;
        Node next;

        public Node(int value){
            this.value = value;
            this.next = null;
        }
    }

    public static int joseph1(int m,int k){
        Node head = new Node(1);
        Node pre = head;
        for (int i = 2; i <= m; i++) {
            Node cur = new Node(i);
            pre.next = cur;
            pre = cur;
        }
        pre.next = head;//指向头节点，形成回环

        int count = 1;//计数
        pre = null;
        Node cur = head;
        while (cur.next != cur){//如果节点的next指针指向自己，则说明只剩下一个节点，结束循环
            if (count<k){
                //下一位节点
                pre = cur;
                cur = cur.next;
                count++;//计数+1
            }else if (count == k){
                //删除节点
                pre.next = cur.next;
                cur = cur.next;
                count = 1;//重置计数
            }
        }
        return cur.value;
    }


    public static int joseph2(int m,int k){
        int[] arr = new int[m];//创建长度为m的数组
        for (int i = 0; i < m; i++) {
            arr[i] = 1;//初始化数组 1 为有效，0 为无效
        }
        int index = 0;//下标
        int count = 0;//计数器
        int num = 0;//无效节点数
//       for(;index<=m;index++){
//           if (index == m){
//               index = -1;//如果超出数组范围，则从头开始
//               continue;
//           }
//           if (arr[index] == 1){
//               //元素有效
//               count++;//计数+1
//               if (count == k){
//                   arr[index] = 0;//设置无效
//                   count = 0;//重置计数器
//                   ++num;//无效数+1
//               }
//           }
//           if (num == m-1){
//               for (int i = 0; i < m; i++) {
//                   if (arr[i] == 1){
//                       return i+1;
//                   }
//               }
//           }
//       }
       while (true){
           if (index == m){
               index = 0;//从头开始
               continue;
           }
           if (arr[index] == 1){
               count++;//有效元素。计数+1
               if (count == k){
                   arr[index] = 0;
                   count = 0;
                   ++num;
               }
           }
           if (num == m-1){
               for (int i = 0; i < m; i++) {
                   if (arr[i] == 1){
                       return i+1;
                   }
               }
           }
           index++;
       }
    }

    public static int joseph3(int m,int k){
        int s = 0;
        for (int i = 1;i <= m;i++){
            s = (s+k)%i;
        }
        return s+1;

    }
}
