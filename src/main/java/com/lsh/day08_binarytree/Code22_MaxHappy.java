package com.lsh.day08_binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/31 4:22 下午
 * @desc ：
 */
public class Code22_MaxHappy {

    public static void main(String[] args) {

        Employee employee5 = new Employee(5);
        Employee employee6 = new Employee(6);
        Employee employee1 = new Employee(1);
        Employee employee66 = new Employee(6);
        Employee employee7 = new Employee(7);
        Employee employee8 = new Employee(8);
        Employee boss = new Employee(10);

        ArrayList<Employee> employees56 = new ArrayList<>();
        ArrayList<Employee> employees1 = new ArrayList<>();
        ArrayList<Employee> employees678 = new ArrayList<>();

        employees56.add(employee5);
        employees56.add(employee6);

        employee66.nexts = employees56;

        employees1.add(employee1);
        employee7.nexts = employees1;

        employees678.add(employee66);
        employees678.add(employee7);
        employees678.add(employee8);

        boss.nexts = employees678;
        getMaxHappy1(boss);
        System.out.println();

    }

    public static class Employee{
        public int happy;
        public List<Employee> nexts;

        public Employee(int happy,List<Employee> nexts){
            this.happy = happy;
            this.nexts = nexts;
        }
        public Employee(int happy){
            this.happy = happy;
            this.nexts = new ArrayList<Employee>();
        }
    }
    public static class Info {
        public int no;
        public int yes;

        public Info(int no, int yes) {
            this.no = no;
            this.yes = yes;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "no=" + no +
                    ", yes=" + yes +
                    '}';
        }
    }

    public static int getMaxHappy1(Employee boss){
        Info info = process(boss);
        System.out.println(info);
        return Math.max(info.yes,info.no);

    }

    public static Info process(Employee x){
        if (x == null){
            return new Info(0,0);
        }
        int no = 0;//x 不来
        int yes = x.happy;//x 来
        for (Employee next : x.nexts) {
            Info nextInfo = process(next);
            no += Math.max(nextInfo.yes,nextInfo.no);
            yes += nextInfo.no;
        }
        System.out.println(x.happy+" :  yes:"+yes+", no:"+no);
        return new Info(no,yes);

    }

}
