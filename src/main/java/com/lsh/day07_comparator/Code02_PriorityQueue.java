package com.lsh.day07_comparator;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/9 11:59 上午
 * @desc ：优先队列 PriorityQueue
 * 1. PriorityQueue 小根堆 小的优先:PriorityQueue<Integer> head = new PriorityQueue<>();
 * 2. 通过比较器改造小跟堆，成为大跟堆：大的优先.PriorityQueue<Integer> head = new PriorityQueue<>(new MyComparator());
 * 3. 字符串的比较时按字典序
 */
public class Code02_PriorityQueue {

    public static class Student  {
        public String name;
        public int id;
        public int age;

        public Student(String name,int id,int age){
            this.name = name;
            this.id = id;
            this.age = age;
        }
    }

    public static class MyComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1<o2){
                return 1;
            }else if (o1 > o2){
                return -1;
            }else {
                return 0;
            }
        }
    }

    public static class IdComparator implements Comparator<Student>{
        /**
         * 如果返回负数，认为第一个数排在前面。
         * 如果返回正数，认为第二个数排在前面。
         * 如果返回0 ，两个一样大
         * @param o1
         * @param o2
         * @return
         */
        @Override
        public int compare(Student o1, Student o2) {
            return o2.id - o1.id;
        }
    }
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "ab";
        System.out.println(str1.compareTo(str2));

        PriorityQueue<Student> head = new PriorityQueue<>(new IdComparator());
        Student s1 = new Student("张三", 3, 35);
        Student s2 = new Student("李四", 2, 17);
        Student s3 = new Student("王五", 1, 25);
        Student s4 = new Student("赵六", 7, 28);
        head.add(s1);
        head.add(s2);
        head.add(s3);
        head.add(s4);
        System.out.println("=======");
        while (!head.isEmpty()){
            Student s = head.poll();
            System.out.println(s.name+","+s.id+","+s.age);
        }
        System.out.println("=======");

        TreeSet<Student> studentTreeSet = new TreeSet<>(new IdComparator());
        //Lambda表达式写法
//        TreeSet<Student> studentTreeSet = new TreeSet<>((o1,o2) ->{
//            return (o1.id-o2.id);
//        });
//        TreeSet<Student> studentTreeSet = new TreeSet<>(Comparator.comparingInt(o -> o.id));
        studentTreeSet.add(s1);
        studentTreeSet.add(s2);
        studentTreeSet.add(s3);
        studentTreeSet.add(s4);
        for (Student s : studentTreeSet) {
            System.out.println(s.name+","+s.id+","+s.age);

        }
    }
}
