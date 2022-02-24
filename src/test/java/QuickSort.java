import java.util.Arrays;
import java.util.Stack;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/24 2:06 下午
 * @desc ：快速排序de 时间复杂度为O(N * logN)，额外空间复杂度为O(logN)；
 *
 * 最好情况时间复杂度： O(N * logN)
 * 最差情况时间复杂度： O(N * N)
 * 所有情况的概率都是1/N，最后由数学定理证明额外空间复杂度最后收敛到O(N * logN)
 * 最好情况额外空间复杂度：O(N)
 * 最差情况额外空间复杂度：O(logN)
 * 所有情况的概率都是1/N，最后由数学定理证明额外空间复杂度最后收敛到O(logN)
 *
 */
public class QuickSort {

    public static void main(String[] args) {
        System.out.println("测试开始");
        int testTime = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = SortUtil.generateRandomArray(10, 100);
            int[] copyArray = SortUtil.copyArray(arr);
            Arrays.sort(arr);
            quickSort(copyArray);
            if (!SortUtil.isEqual(arr,copyArray)){
                System.out.println("出错了！");
                SortUtil.printArr(arr);
                SortUtil.printArr(copyArray);
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static void quickSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process(arr,0,arr.length-1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L >= R){
            //如果 L>R 无效范围，如果L=R只有一个数，都直接返回, 会存在L>R的无效情况
            return;
        }
        //3.0版本， 随机交换一个数字到R位置
        SortUtil.swap(arr,R,L+(int)(Math.random() * (R - L + 1)));
        int[] equals = netherlandsFlag(arr, L, R);
        process(arr, L, equals[0]-1);
        process(arr, equals[1]+1, R);
    }

    public static int[] netherlandsFlag(int[] arr,int L,int R){
        int less = L-1;//小于区 右边界
        int more = R;//大于区 左边界
        //指针从第一位开始
        int index = L;
        //当index指针还没有到达 大于区左边界
        while (index < more){
            //错误写法：
//            if (arr[index] == arr[R]){
//                //等于区域：index指针跳下一个
//                index++;
//            }
//            if (arr[index] < arr[R]){
//                //小于区：交换index和less+1（小于区下一位）的位置；  index跳下一位
//                SortUtil.swap(arr,index++,++less);
//            }
//            if (arr[index] > arr[R]){
//                //大于区：交换index和more-1（小于区下一位）的位置；index位置不变(因为交换过来的元素还为比较，需要重新比较)
//                SortUtil.swap(arr,index,--more);
//            }
            if (arr[index] > arr[R]) {
                SortUtil.swap(arr,index,--more);
            }else if (arr[index] < arr[R]){
                SortUtil.swap(arr,index++,++less);
            }else {
                //相等
                index++;
            }
        }
        //最后跳出循环，还需要将最后一位元素arr[R] 和 大于区的第一位元素 进行交换
        SortUtil.swap(arr,R,more);
        //最后返回中间相等的元素位置
        return new int[] {less+1,more};
    }

    //定义辅助类
    public static class Op{
        public int l;
        public int r;
        public Op(int l,int r){
            this.l = l;
            this.r = r;
        }
    }
    //快速排序 非递归实现
    public static void quickSort2(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int N = arr.length;
        SortUtil.swap(arr,N-1,(int)(Math.random() * N));
        //进行划分
        int[] equal = netherlandsFlag(arr, 0, N - 1);
        int el = equal[0];
        int er = equal[1];
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(0,el - 1));
        stack.push(new Op(er + 1,N-1));

//        LinkedList<Op> queue = new LinkedList<>();
//        queue.offer(new Op(0,el - 1));
//        queue.offer(new Op(0,el - 1));

        while (!stack.isEmpty()){
            //取出一个范围
            Op op = stack.pop();
            if (op.l < op.r){
                //随机交换一个元素到最后位置
                SortUtil.swap(arr,op.r,op.l+(int)(Math.random()*(op.r - op.l +1)));
                 equal = netherlandsFlag(arr, op.l, op.r);
                 el = equal[0];
                 er = equal[1];
                 //划分好的数组，重新将大于区 、小于区 加入栈中
                 stack.push(new Op(op.l,el-1));
                 stack.push(new Op(er+1,op.r));
            }
        }
    }
}
