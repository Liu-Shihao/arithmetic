/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/5 7:43 下午
 * @desc ：
 */
public class Review20220205 {
    public static void main(String[] args) {
//        int[] arr = {6, 2, 7, 3, 1, 7, 9, 0};
//        printArr(arr);
//        selectionSort(arr);
//        bubbleSort1(arr);
//        bubbleSort2(arr);
//        insertionSore1(arr);
//        insertionSort2(arr);
//        printArr(arr);

        Node<Integer> head = new Node<>(1);
        head.next = new Node<>(2);
        head.next.next = new Node<>(3);
        //注意是个换头函数，链表反转后，head已经变换
        head = reverseLinkedList(head);
        while (head != null){
            System.out.print(head.value+" ");
            head = head.next;
        }
        System.out.println();
        System.out.println("=============");

        // null <->  1  <->   2   <->   3    <->   null
        DoubleNode<Integer> dHead = new DoubleNode<>(1);
        dHead.next = new DoubleNode<>(2);

        dHead.next.next = new DoubleNode<>(3);
        dHead.next.last = dHead;

        dHead.next.next.last = dHead.next;



//        DoubleNode<Integer> last = dHead.next.next;
//        while (last != null){
//            System.out.print(last.value+" ");
//            last = last.last;
//        }
        dHead = reverseDoubleLinkedList(dHead);
        while ( dHead != null ){
            System.out.print( dHead.value + " ");
            dHead = dHead.next;
        }
        System.out.println();



    }

    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();

    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 选择排序
     * 0 - n-1
     * 1 - n-1
     * 2 - n-1
     * @param arr
     */
    public static void selectionSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int minValueIndex = i;
            for (int j = minValueIndex+1; j < arr.length; j++) {
                minValueIndex = arr[minValueIndex] > arr[j] ? j : minValueIndex;
            }
            swap(arr,minValueIndex,i);
        }
    }

    public static void bubbleSort1(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length-i-1; j++) {
                if (arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                }
            }

        }
    }

    public static void bubbleSort2(int[] arr){
        for (int end = arr.length - 1 ; end > 0; end--) {
            for (int second = 1; second <= end ; second++) {
                if (arr[second]< arr[second-1]){
                    swap(arr,second,second-1);
                }
            }
        }
    }

    /**
     * 插入排序
     * 0 - n-1
     * 1 - n-1
     * 2 - n-1
     * end 为待排序元素，和前一位pre元素比较，
     * @param arr
     */
    public static void insertionSore1(int[] arr){
        for (int end = 1; end < arr.length; end++) {
            for (int pre = end-1; pre >= 0 && arr[pre] < arr[pre + 1] ; pre--) {
                swap(arr,pre,pre+1);
            }
        }
    }

    public static void insertionSort2(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int nextValueIndex = i;
            while (nextValueIndex-1>= 0 && arr[nextValueIndex] < arr[nextValueIndex-1]){
                swap(arr,nextValueIndex,nextValueIndex-1);
                nextValueIndex -- ;
            }
        }
    }

    public static class Node<V>{
        public V value;
        public Node<V> next;

        public Node(V v){
            value = v;
            next = null;
        }
    }
    public static class DoubleNode<V>{
        public V value;
        public DoubleNode<V> next;
        public DoubleNode<V> last;

        public DoubleNode(V v){
            value = v;
            next = null;
            last = null;
        }
    }


    public static Node reverseLinkedList(Node head){
        Node pre = null;
        Node next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;

    }

    public static DoubleNode reverseDoubleLinkedList(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;

        }
        return pre;
    }


}
