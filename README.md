Author： LiuShihao <br>
Update Time：2022年01月27日15:51:23

# Day01
## 位运算
~ 取反
<< 左移  >> 右移 &(与) |(或) ^（亦或），位运算 比 +-*/效率高10倍以上

int范围：- 2^31  ~  2^31-1
最大数：01111111111111111111111111111111
最小数：10000000000000000000000000000000
0    ：00000000000000000000000000000000

## 选择排序
2022年01月26日17:29:02
# Day02
## 冒泡排序

## 插入排序
2022年01月27日15:49:34-----22年春节前最后一个工作日

## 数据结构

# day03
## 对数器
# day04
## 二分法
在有序数组中找到num、找到大于等于num的最右的位置、小于等于num最左的位置
## 局部最小值问题

## 时间复杂度
最高阶。
选择排序、冒泡排序、插入排序的时间复杂度为：O(n^2)。
常数的时间复杂度：O(1)。 
二分法的时间复杂度为：O(logN),以2为底，一般都忽略底数，因为都非常小。这个复杂度是非常低的，比O(N)还低
估算算法的时间复杂度要用最差情况来估算。
动态数组:ArrayList的动态扩容并不影响时间复杂度，O(1)
## 哈希表和有序表（HashMap TreeMap）
在哈希表中，基本数据类型（int、Integer、String等）是按值传递的，引用数据类型（自定义对象）是按引用传递的。

HashMap 的时间复杂度为O(1)
TreeMap 的时间复杂对为O(logN)

# day05
## 单链表和双链表
1.单链表的反转
2.双链表的反转
3.单链表结构实现队列  
4.单链表实现栈
5.双链表实现双端队列
6.题目1：K个节点的组内逆序调整
7.题目2：两个链表相加
8.题目3：两个有序链表合并
# day06 
## 位图
```text
>>1  等同于 ➗2
>>6  等同于 ➗64
<<1  等同于 x2
&(与)操作还是自己
```
1.位图的实现
2.位图实现加减乘除

# day07
1. 比较器，实现Comparator接口重写compare方法
2. 题目：使用小根堆实现合并K个升序列表

# # day08
1. 二叉树 
2. 判断两个二叉树是否一样
3. 判断一颗二叉树是否是镜像树
4. 返回二叉树的最大深度
5. 先序数组和中序数组建出二叉树数并返回头节点
6. 二叉树按层遍历并收集节点
7. 判断一棵树是否是平衡树（左树和右树的高度差不超过1）
8. 判断是否为搜索二叉树（左树比头节点小，右树比头节点大）
9. 判断二叉树路径和
