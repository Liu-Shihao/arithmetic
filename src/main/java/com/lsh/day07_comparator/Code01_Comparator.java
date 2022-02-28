package com.lsh.day07_comparator;

import java.util.*;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/9 10:53 上午
 * @desc ：比较器：自定义对象排序需要实现Comparator接口并重写compare方法，Arrays.sort(students,new IdComparator());
 * 1. 使用Arrays.sort(arr)方法可以从小到大对基本数据类型进行排序
 * 2. Arrays.sort(students,new IdComparator());   students为数组
 * 3. studentArrayList.sort(new IdComparator());  studentArrayList为List
 *	// 任何比较器：
 * 	// compare方法里，遵循一个统一的规范：
 * 	// 返回负数的时候，认为第一个参数应该排在前面
 * 	// 返回正数的时候，认为第二个参数应该排在前面
 * 	// 返回0的时候，认为无所谓谁放前面
 *
 */
public class Code01_Comparator {

    /**
     * 如果需要比较自定义对象
     */
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
//            if (o1.id<o2.id){
//                return -1;
//            }else if (o1.id>o2.id){
//                return 1;
//            }else {
//                return 0;
//            }
            return o1.id - o2.id;
        }
    }
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 0, 1, 4, 7, 2};
        Arrays.sort(arr);
        printArr(arr);
        Student s1 = new Student("张三", 3, 35);
        Student s2 = new Student("李四", 2, 17);
        Student s3 = new Student("王五", 1, 25);
        Student s4 = new Student("赵六", 7, 28);

        Student[] students = {s1, s2, s3,s4};
        printStudents(students);
        Arrays.sort(students,new IdComparator());
        System.out.println("==================");
        printStudents(students);
        System.out.println("==================");
        List<Student> studentArrayList = Arrays.asList(s1, s2, s3, s4);
        for (Student  s :studentArrayList){
            System.out.println(s.name+","+s.id+","+s.age);
        }
        System.out.println("==================");

        studentArrayList.sort(new IdComparator());
        for (Student  s :studentArrayList){
            System.out.println(s.name+","+s.id+","+s.age);
        }

        System.out.println("==================");

        //使用lambda表达式写法：注意如果根据排序规则对象重复了，则对象不会覆盖。
//        TreeMap<Student, Object> treeMap = new TreeMap<>((o1, o2) -> o1.id != o2.id ? o1.id-o2.id : o1.age-o2.age);
        TreeMap<Student, String> treeMap = new TreeMap<>(new IdComparator());
        treeMap.put(s1,"我是学生1");
        treeMap.put(s2,"我是学生2");
        treeMap.put(s3,"我是学生3");
        treeMap.put(s4,"我是学生4");
        for (Student s :treeMap.keySet()){
            System.out.println(s.name+","+s.id+","+s.age);
        }
    }

    public static void printArr(int[]arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    public static void printStudents(Student[]students){
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].name+","+students[i].id+","+students[i].age);
        }
    }
}
