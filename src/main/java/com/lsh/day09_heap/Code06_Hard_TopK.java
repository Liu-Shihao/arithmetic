package com.lsh.day09_heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/21 11:17 下午
 * @desc ：加强堆 等候区与得奖区算法题
 * 给定一个整型数组，int[] arr；和一个布尔类型数组，boolean[] op
 * 两个数组一定等长，假设长度为N，arr[i]表示客户编号，op[i]表示客户操作
 * arr = [ 3   ,   3   ,   1   ,  2,      1,      2,      5…
 * op = [ T   ,   T,      T,     T,      F,      T,       F…
 * 依次表示：3用户购买了一件商品，3用户购买了一件商品，1用户购买了一件商品，2用户购买了一件商品，1用户退货了一件商品，2用户购买了一件商品，5用户退货了一件商品…
 * 一对arr[i]和op[i]就代表一个事件：
 * 用户号为arr[i]，op[i] == T就代表这个用户购买了一件商品
 * op[i] == F就代表这个用户退货了一件商品
 * 现在你作为电商平台负责人，你想在每一个事件到来的时候，
 * 都给购买次数最多的前K名用户颁奖。
 * 所以每个事件发生后，你都需要一个得奖名单（得奖区）。
 * 得奖系统的规则：
 * 1，如果某个用户购买商品数为0，但是又发生了退货事件，
 *      则认为该事件无效，得奖名单和上一个事件发生后一致，例子中的5用户
 * 2，某用户发生购买商品事件，购买商品数+1，发生退货事件，购买商品数-1
 * 3，每次都是最多K个用户得奖，K也为传入的参数
 *       如果根据全部规则，得奖人数确实不够K个，那就以不够的情况输出结果
 * 4，得奖系统分为得奖区和候选区，任何用户只要购买数>0，
 *       一定在这两个区域中的一个
 * 5，购买数最大的前K名用户进入得奖区，
 *       在最初时如果得奖区没有到达K个用户，那么新来的用户直接进入得奖区
 * 6，如果购买数不足以进入得奖区的用户，进入候选区
 * 7，如果候选区购买数最多的用户，已经足以进入得奖区，
 *      该用户就会替换得奖区中购买数最少的用户（大于才能替换），
 *      如果得奖区中购买数最少的用户有多个，就替换最早进入得奖区的用户
 *      如果候选区中购买数最多的用户有多个，机会会给最早进入候选区的用户
 * 8，候选区和得奖区是两套时间，
 *      因用户只会在其中一个区域，所以只会有一个区域的时间，另一个没有
 *      从得奖区出来进入候选区的用户，得奖区时间删除，
 *      进入候选区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i）
 *      从候选区出来进入得奖区的用户，候选区时间删除，
 *      进入得奖区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i）
 * 9，如果某用户购买数==0，不管在哪个区域都离开，区域时间删除，
 *      离开是指彻底离开，哪个区域也不会找到该用户
 *      如果下次该用户又发生购买行为，产生>0的购买数，
 *      会再次根据之前规则回到某个区域中，进入区域的时间重记
 */
public class Code06_Hard_TopK {


    public static class Data {
        public int[] arr;
        public boolean[] op;

        public Data(int[] a, boolean[] o) {
            arr = a;
            op = o;
        }
    }

    // 为了测试
    public static Data randomData(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        boolean[] op = new boolean[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
            op[i] = Math.random() < 0.5 ? true : false;
        }
        return new Data(arr, op);
    }
    public static boolean sameAnswer(List<List<Integer>> ans1, List<List<Integer>> ans2) {
        if (ans1.size() != ans2.size()) {
            return false;
        }
        for (int i = 0; i < ans1.size(); i++) {
            List<Integer> cur1 = ans1.get(i);
            List<Integer> cur2 = ans2.get(i);
            if (cur1.size() != cur2.size()) {
                return false;
            }
            cur1.sort((a, b) -> a - b);
            cur2.sort((a, b) -> a - b);
            for (int j = 0; j < cur1.size(); j++) {
                if (!cur1.get(j).equals(cur2.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int maxValue = 10;
        int maxLen = 10;
        int maxK = 6;
        int testTimes = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Data testData = randomData(maxValue, maxLen);
            int k = (int) (Math.random() * maxK) + 1;
            int[] arr = testData.arr;
            boolean[] op = testData.op;
            List<List<Integer>> ans1 = topK(arr, op, k);
            List<List<Integer>> ans2 = compare(arr, op, k);
            if (!sameAnswer(ans1, ans2)) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.println(arr[j] + " , " + op[j]);
                }
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }


    public static class FindTopK {
        private HashMap<Integer,Customer> customers;
        private HeapGreater<Customer> waitArea;
        private HeapGreater<Customer> winnerArea;
        private final int limit;

        public FindTopK(int limit) {
            customers = new HashMap<>();
            waitArea = new HeapGreater<>(new WaitAreaComparator());
            winnerArea = new HeapGreater<>(new WinningAreaComparator());
            this.limit = limit;
        }
        //处理当前事件arr[i]
        public  void operate(int time,int id,boolean bool){
            if (!bool && !customers.containsKey(id)) {
                return;
            }
            if (!customers.containsKey(id)) {
                customers.put(id, new Customer(id, 0, 0));
            }
            Customer c = customers.get(id);
            if (bool) {
                c.butNum++;
            } else {
                c.butNum--;
            }
            if (c.butNum == 0) {
                customers.remove(id);
            }
            if (!waitArea.contains(c) && !winnerArea.contains(c)) {
                if (winnerArea.size() < limit) {
                    c.enterTime = time;
                    winnerArea.push(c);
                } else {
                    c.enterTime = time;
                    waitArea.push(c);
                }
            } else if (waitArea.contains(c)) {
                if (c.butNum == 0) {
                    waitArea.remove(c);
                } else {
                    waitArea.resign(c);
                }
            } else {
                if (c.butNum == 0) {
                    winnerArea.remove(c);
                } else {
                    winnerArea.resign(c);
                }
            }
            //进行移动
            daddyMove(time);
        }

        private void daddyMove(int time) {
            if (waitArea.isEmpty()) {
                return;
            }
            if (winnerArea.size() < limit) {
                Customer p = waitArea.pop();
                p.enterTime = time;
                winnerArea.push(p);
            } else {
                if (waitArea.peek().butNum > winnerArea.peek().butNum) {
                    Customer oldDaddy = winnerArea.pop();
                    Customer newDaddy = waitArea.pop();
                    oldDaddy.enterTime = time;
                    newDaddy.enterTime = time;
                    winnerArea.push(newDaddy);
                    waitArea.push(oldDaddy);
                }
            }
        }

        //获得此时获奖区中的所有顾客ID
        public List<Integer> getTopK(){
            List<Customer> allElements = winnerArea.getAllElements();
            ArrayList<Integer> topK = new ArrayList<>();
            for (Customer customer : allElements) {
                topK.add(customer.id);
            }
            return topK;
        }
    }

    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k){
        List<List<Integer>> ans = new ArrayList<>();
        FindTopK findTopK = new FindTopK(k);
        for (int i = 0; i < arr.length; i++) {
            findTopK.operate(i, arr[i], op[i]);
            ans.add(findTopK.getTopK());
        }
        return ans;
    }


    /**
     * 定义比较器方法
     * @param arr  顾客ID
     * @param op   事件
     * @param k    返回topK
     * @return
     */
    public static List<List<Integer>> compare(int[] arr, boolean[] op, int k){
        HashMap<Integer,Customer> map = new HashMap<>();
        ArrayList<Customer> waitArea = new ArrayList<>();//候选区
        ArrayList<Customer> winningArea = new ArrayList<>();//得奖区
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buyOrReturn = op[i];
            if (!buyOrReturn && !map.containsKey(id)){
                //发生退货并且之前没有出现过这个用户（即用户购买数为0，从map中移除）
                ans.add(getCurAns(winningArea));
                continue;
            }
            //剩下3中情况：
            //1.退货，但是购买数不为0（map中存在该顾客）
            //2.买货，购买数不为0   （map中存在该顾客）
            //3.买货，但是购买数为0  （map中不存在该顾客，第一次购买的顾客）
            //如果map中不存在该顾客，第一次购买
            if (!map.containsKey(id)){
                //暂时初始化购买数和进入时间，后续再做调整
                map.put(id,new Customer(id,0,0));
            }
            //此时获得顾客（map中一定存在该顾客）
            Customer customer = map.get(id);
            if (buyOrReturn){
                customer.butNum++;
            }else {
                customer.butNum--;
            }
            if (customer.butNum == 0 ){
                //如果顾客的购买数为0 ，则从map中移除
                map.remove(id);
            }
            //如果等候区和得奖取都没有该顾客（说明该顾客第一次购买）(否则就说明顾客已经存在在某一个区域了)
            if (!waitArea.contains(customer) && !winningArea.contains(customer)){

                if (winningArea.size() < k) {
                    customer.enterTime = i;
                    winningArea.add(customer);//进入得奖区
                } else  {
                    customer.enterTime = i;
                    waitArea.add(customer);//进入等候区
                }
            }
            //顾客进入相应的区域后
            //把此时得奖区和候选区中购买数为0的顾客移除
            cleanZeroBuy(waitArea);
            cleanZeroBuy(winningArea);

            //分别对得奖区和候选区进行排序：得奖区按照购买数从小到大、时间从早到晚排序；候选区按照购买数从大到小、时间从早到晚排序
            waitArea.sort(new WaitAreaComparator());
            winningArea.sort(new WinningAreaComparator());

            //进行移动：
            // 1.如果候选区为空，则不替换
            // 2.如果候选区有数据，并且得奖区未满,则直接进入得奖区
            // 3.候选区的第一位时候超过了得奖区的第一位，则进行替换
            move(waitArea, winningArea, k, i);

            ans.add(getCurAns(winningArea));
        }
        return ans;
    }

    public static void move(ArrayList<Customer> waitArea, ArrayList<Customer> winningArea, int k, int time) {
        if (waitArea.isEmpty()) {
            //如果候选区为空，不需要替换
            return;
        }
        // 候选区不为空，并且得奖区未满K
        if (winningArea.size() < k) {
            Customer c = waitArea.get(0);
            c.enterTime = time;
            //直接加入得奖区
            winningArea.add(c);
            //等待区中移除该顾客
            waitArea.remove(0);
        } else {
            // 等奖区满了，候选区有顾客 并且等候区的第一位顾客购买的数量比得奖区第一位顾客的数量多，则需进行交换
            if (waitArea.get(0).butNum > winningArea.get(0).butNum) {
                Customer oldDaddy = winningArea.get(0);
                winningArea.remove(0);
                Customer newDaddy = waitArea.get(0);
                waitArea.remove(0);
                newDaddy.enterTime = time;
                oldDaddy.enterTime = time;
                winningArea.add(newDaddy);
                waitArea.add(oldDaddy);
            }
        }
    }

    public static void cleanZeroBuy(ArrayList<Customer> arr) {
        List<Customer> noZero = new ArrayList<>();
        for (Customer c : arr) {
            if (c.butNum != 0) {
                noZero.add(c);
            }
        }
        arr.clear();
        for (Customer c : noZero) {
            arr.add(c);
        }
    }

    //等待区规则：购买数大的放前面（倒序），如果购买数一样则根据进入时间早的放前面（正序）
    public static class WaitAreaComparator implements Comparator<Customer>{
        //正序：（o1-o2）
        //返回正数，则o2排在前面
        //返回负数，则o1排在前面
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.butNum != o2.butNum ? o2.butNum - o1.butNum: o1.enterTime - o2.enterTime;
        }
    }
    //得奖区规则：购买数大的放后面（正序），如果购买数一样则根据进入时间早的放前面（正序）（谁买的早谁先从得奖区出去）
    public static class WinningAreaComparator implements Comparator<Customer>{

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.butNum != o2.butNum ? o1.butNum - o2.butNum: o1.enterTime - o2.enterTime;
        }
    }

    public static List<Integer> getCurAns(ArrayList<Customer> winningArea) {
        List<Integer> ans = new ArrayList<>();
        for (Customer c : winningArea) {
            ans.add(c.id);
        }
        return ans;
    }

    /**
     * 定义Customer类
     */
    public static class Customer {
        public int id;//顾客ID
        public int butNum;//消费数量
        public int enterTime;//进入区域时间

        public Customer(int id,int butNum,int enterTime){
            this.id = id;
            this.butNum = butNum;
            this.enterTime = enterTime;
        }
    }



}
