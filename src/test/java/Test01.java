import java.util.*;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/6/19 4:25 下午
 * @desc ：快排 、 归并排序 、堆排序
 */
public class Test01 {

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            int[] generate = ArrUtil.generate(20, 20);
            mergeSort(generate);
            ArrUtil.print(generate);
        }

    }


    public static void quickSort(int[] arr){
        if (arr==null ||arr.length < 2)return;
        process(arr,0,arr.length-1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L >=R){
            return;
        }
        int randomIndex = (int)(Math.random() * (R-L+1))+L;//R-L+1 表示当前数组的长度
        swap(arr,randomIndex,R);//随机交换元素到最后一位
        int[] equal = partition(arr,L,R);//根据arr[R] 进行划分，返回等于区域的第一位和最后一位的下标
        process(arr,L,equal[0]);
        process(arr,equal[1]+1,R);
    }

    private static int[] partition(int[] arr, int L, int R) {
        int lessR = L-1;//小于区域的右边界
        int moreL = R;//大于区域的左边界
        int index = L;//从L开始比较
        while (index < moreL){
            if (arr[index] < arr[R]){
                //小于区域
                swap(arr,index++,++lessR);
            }else if (arr[index]>arr[R]){
                //大于区域
                swap(arr,index,--moreL);
            }else {
                //相等
                index++;
            }
        }
        //当index和moreL相遇，结束循环 此时index == moreL
        //最后需要交换arr[R]和arr[moreL]的位置
        swap(arr,R,moreL);
        return new int[]{lessR+1,moreL};
    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }



    public static void mergeSort(int[] arr){
        if (arr == null || arr.length<2)return;
        process2(arr,0,arr.length-1);

    }

    private static void process2(int[] arr, int L, int R) {
        if (L==R) return;;
        int mid = L+(R-L)/2;
        process(arr,L,mid);
        process(arr,mid+1,R);
        merge(arr,L,mid,R);

    }

    private static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int index = 0, p1 = L, p2 = M+1;
        while (p1 <= M && p2 <= R){
            help[index++] = arr[p1]<=arr[p2] ? arr[p1++] :arr[p2++];
        }
        while (p1<=M){
            help[index++] = arr[p1++];
        }
        while (p2<=R){
            help[index++] = arr[p2++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L++] = help[i];
        }
    }


    //TODO 2022年06月19日16:57:05 堆排序
    public static void heapSort(int[] arr){
        if (arr==null || arr.length<2)return;

        for (int i = arr.length-1;i>=0;i--){
            heapify(arr,i,arr.length-1);
        }



    }

    private static void heapify(int[] arr, int i, int i1) {



    }


    static class Node{
        public int value;
        public List<Node> nexts;

        public Node(int value) {
            this.value = value;
        }
    }


    public static void BFS(Node head){
        if (head == null) return;
        HashSet<Node> set = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        set.add(head);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)){
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }


    public static void DFS(Node head){
        if (head == null )return;
        HashSet<Node> set = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        set.add(head);
        System.out.println(head.value);

        while (!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)){
                    set.add(next);
                    stack.push(cur);
                    stack.push(next);
                    System.out.println(next.value);
                    break;
                }

            }
        }

    }

}
