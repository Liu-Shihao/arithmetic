/**
 * @author ：LiuShihao
 * @date ：Created in 2022/6/17 11:50 下午
 * @desc ：约瑟夫问题
 */
public class Juesefe {

    public static void main(String[] args) {
//        System.out.println(m2(6,2));
        System.out.println(m(6,5));
    }

    /**
     * 解决方法一：数组
     * 用 1 和 0 代替是否存活
     * @param m 人数
     * @param k 间隔
     * @return
     */
    public static int m(int m ,int k){
        int[] arr = new int[m];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;//初始化数组 全部为1 存活
        }
        int ans  = 0;
        int count = 0;//计数器
        int num = 0; //死亡个数
        for (int i = 0; i <= arr.length; i++) {
            if (i  == arr.length) {
                i = -1;
                continue;//超出范围 从头开始
            }
            if (arr[i] == 1){
                //有效
                ++count;//计数 从0开始，遇到有序元素 +1
                if (count == k){
                    arr[i] = 0;//设置无效
                    ++num;//死亡个数+1
                    System.out.println("当前位置："+(i+1)+"死亡，死亡个数："+num);
                    count = 0;//重置计数器
                }
            }
            //结束条件 只剩一个人
            if (num == m-1){
                for (int i1 = 0; i1 < arr.length; i1++) {
                    if (arr[i1] == 1) {
                        ans = i1;
                        return ans+1;
                    }
                }
            }
            //无效元素 跳过
        }
        return ans;
    }

    //公式推导 s = (s+k) % i
    public static int m2(int m,int k){
        int s = 1;
        for (int i = 1; i <= m; i++) {
            s = (s+k) % i;
        }
        return s+1;
    }
}
