/**
 * @author ：LiuShihao
 * @date ：Created in 2022/6/13 3:48 下午
 * @desc ：
 */
public class ArrUtil {

    public static int[] generate(int length,int maxValue){
        int l  = (int)(Math.random() * length)+1;
        int[] arr = new int[l];
        for (int i = 0; i < l; i++) {
            arr[i] = (int)(Math.random() * maxValue)+1;
        }
        return arr;
    }




    public static void swap(int[] arr,int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void print(int[] arr){
        for (int i : arr) {
            System.out.print(i +" ");
        }

    }

    public static void main(String[] args) {
        int[] generate = generate(10, 20);
        print(generate);
    }

}
