import java.util.*;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/6/19 12:26 上午
 * @desc ：图的广度优先遍历和深度优先遍历
 */
public class GraphBfsAndDfs {

    static class Node{
        public int value;
        public List<Node> nexts;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", nexts=" + nexts +
                    '}';
        }
    }

    public static void main(String[] args) {

    }


    public static void bfs(Node head){
        if (head == null)return;
        HashSet<Node> set = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        set.add(head);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            //广度优先遍历：出队打印
            System.out.println(cur.value);
            if (cur.nexts != null){
                for (Node next : cur.nexts) {
                    if (set.contains(next.value)){
                        queue.add(next);
                        set.add(next);
                    }
                }
            }
        }
    }

    public static void dfs(Node head){
        if (head == null )return;
        HashSet<Node> set = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        set.add(head);
        stack.add(head);
        //深度优先遍历：入栈打印
        System.out.println(head.value);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next.value)){
                    set.add(next);
                    stack.push(cur);//还需要将弹出的节点重新压栈
                    stack.push(next);//然后将子节点压栈
                    System.out.println(next.value);
                    break;//结束for循环，执行while循环，将子节点弹栈
                }
            }
        }
    }
}
