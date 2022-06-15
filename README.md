Author： LiuShihao <br>
Update Time：2022年01月27日15:51:23
VIS标记：表示非常重要标记（VeryImportantSign）   
# day00
## 异或问题

# Day01
## 位运算
~ 取反
<< 左移  >> 右移 &(与) |(或) ^（亦或），位运算 比 +-*/效率高10倍以上

int范围：- 2^31  ~  2^31-1
最大数：01111111111111111111111111111111
最小数：10000000000000000000000000000000
0    ：00000000000000000000000000000000

# Day02 排序算法
基于比较的排序：
    Code1选择排序 n2 
    Code2冒泡排序 n2
    Code3插入排序 n2
    Code4归并排序 n*logN
    Code5快速排序 n*logN
    Code6堆排序   
不是基于比较的排序（对样本有要求）：
    Code7计数排序
    Code8基数排序
## 稳定性
稳定性是指同样大小的样本再排序之后不会改变相对次序

对基础数据类型来说，稳定性毫无意义；

对引用数据类型来说，稳定性意义很大；

有些算法可以实现稳定性，有些算法无论怎么也无法实现稳定性

 1. 选择排序 无法保证稳定性
 2. 冒泡排序和插入排序可以保证稳定性（如果相等，则停止交换）
 3. 归并排序 可以保证稳定性（在归并的时候，遇到相等的元素先拷贝左边可以保证稳定性，先拷贝右边则不能保证稳定）
 4. 快速排序 无法保证稳定性
 5. 堆排序 无法保证稳定性 
 
## 排序算法总结
1） 不基于比较的排序，对样本数据有严格要求，不易改写

2） 基于比较的排序，只要规定好两个样本怎么比大小就可以直接复用

3） 基于比较的排序，时间复杂度的极限是O(N*logN)

4） 时间复杂度O(N*logN)、额外空间复杂度低于O(N)、且稳定的基于比较的排序是不存在的。

5） 为了绝对的速度选快排、为了省空间选堆排、为了稳定性选归并

## 工程上对排序的改进
### 稳定性的考虑
Arrays.Sort()
1. 如果是基础数据类型，则底层代码通过快排算法实现（因为基础数据类型不需要稳定性，所以使用快拍算法）
2. 如果是引用数据类型，则底层会通过归并算法实现（需要保证引用数据类型排序的稳定性，所以使用归并算法）

### 充分利用O(N*logN) 和O(n2) 的各自优势
例如：
在数据量小于60的时候，选择插入排序（这个时候虽然插入排序的时间复杂度是n2,但是此时）
在数据量大于60的时候，可以选择快速排序（快排的时间复杂度为N*logN）

## 数据结构

# day03
对数器
# day04
## 二分法
在有序数组中找到num、找到大于等于num的最右的位置、小于等于num最左的位置
1. 找出数组中大于等于num的最左边的数
2. 在有序数组arr中小于等于num最右边的数
3. 局部最小值问题
## 归并问题
1. 小和问题
2. 逆序对问题
3. 大于右数2倍的数的个数

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

# day05 链表问题
## 快慢指针法
快指针 进2步
慢指针 进1步
当快指针走完的时候，慢指针刚好到达链表的中点位置
## 链表面试题常用数据结构和技巧
1. 容器（HashMap、数组、栈）
2. 快慢指针


## Code11:回文链表
给定一个单链表的头结构，判断是否是回文结构（回文：正序和倒序是一样的结构，如：12321、1221）（重要）
方式一：使用栈结构（先进后出：输入为链表的逆序），顺序遍历和栈输出顺序一致即为回文结构
方式二：找到链表的中点（偶数为上中点）位置，将中点节点断开，剩下节点反转指向中点节点

## Code12：复制带随机指针链表
复制带有随机指针的链表（重要）
方式一：借助HashMap容器
方式二：不使用容器

## Code13：两个链表相交问题
两个链表（可能有环可能无环）相交问题（重要）
方式一：借助HashSet容器
方式二：不使用容器，使用快慢指针方法

## Code14:链表划分问题
给定一个Value，将一个链表根据Value值划分为小于区域、等于区域、大于区域
方法一：借助数组，把链表放在数组中进行划分
方法二：借助六个节点变量进行划分

## 补充问题
1. 给出一个链表头节点和要删除节点，将删除节点删除，
（注意，此方法返回值应该是Node类型的，返回值不能是void类型，因为不知道是否要删除头节点，如果要删除头节点怎需要将新头节点返回，如果删除的不是头节点，则返回原头节点）

2.如果不给出头节点，只给出要删除的节点，怎么办？
抖机灵做法：将要删除节点的next节点的值复制到要删除节点，然后将要删除节点的next指针删除
但是这种做法存在问题：无法删除最后一个节点，因为单链表只有一个next指针，无法找到上一个节点，最后节点的next指针指向的null

## 单链表和双链表
1.单链表的反转
2.双链表的反转
3.单链表结构实现队列  
4.单链表实现栈
5.双链表实现双端队列
6.题目1：K个节点的组内逆序调整
7.题目2：两个链表相加
8.题目3：两个有序链表合并
15:用数组和链表实现栈结构


# day06  位图
## 位图
```text
>>1  等同于 ➗2
>>6  等同于 ➗64
<<1  等同于 x2
&(与)操作还是自己
```
1.位图的实现
2.位图实现加减乘除

# day07 比较器
1. 比较器，实现Comparator接口重写compare方法
2. 题目：使用小根堆实现合并K个升序列表

# day08 二叉树 
## 二叉树面试题
1. 递归方式实现二叉树的先序、中序、后序打印
    1. 非递归方式实现二叉树的先序、中序、后序打印
    2. 二叉树层级打印
2. 判断两个二叉树是否一样
3. 判断一颗二叉树是否是镜像树
4. 返回二叉树的最大深度
4. 求二叉树最宽的层有多少个节点
5. 先序数组和中序数组建出二叉树数并返回头节点
6. 二叉树按层遍历并收集节点
7. 是否是平衡树（左树和右树的高度差不超过1）
8. 是否为搜索二叉树（左树比头节点小，右树比头节点大）
9. 判断二叉树路径和
10. 收集二叉树路径和I
11. 收集二叉树路径和II
12. 二叉树的序列化和反序列化（先序、后序、层级序）
13. 多叉树与二叉树相互转换问题
14. 设计一个打印整颗树的打印函数
15. 是否是完全二叉树（非递归和递归套路两种解法）isCBT
16. 二叉树折纸问题
17. 二叉树后继节点问题（找到二叉树中序遍历中某个节点的后个节点）
18. 二叉树的最大距离
19. 判断二叉树是否是满的（即二叉树的所有节点数 是否等于 2^二叉树高度 - 1）
20. 返回二叉树的最大搜索子树的节点数
    1. 求二叉树的最大搜索子树的头节点 
21. 二叉树问题返回a和b的最低公共祖先
22. 多叉树求最大累加和问题（相邻上下层不能同时相加）
 ## 树形dp（二叉树的递归套路）
 可以解决面试中绝大多数的二叉树问题尤其是树型dp问题
 
 本质是利用递归遍历二叉树的便利性
 在树上做动态规划
1. 假设以X节点为头，假设可以向X左树和X右树要任何信息
2. 在上一步的假设下，讨论以X为头节点的树，得到答案的可能性（最重要）
3. 列出所有可能性后，确定到底需要向左树和右树要什么样的信息
4. 把左树信息和右树信息求全集，就是任何一棵子树都需要返回的信息S
5. 递归函数都返回S，每一棵子树都这么要求
6. 写代码，在代码中考虑如何把左树的信息和右树信息整合出整棵树的信息
 

# day09 堆
1. 优先队列 PriorityQueue 小根堆
2. 堆排序☆☆☆
3. 最大线段重合问题
4. 加强堆☆☆☆
5. 加强堆 等候区与得奖区算法题☆☆☆

# day11 前缀树 Prefix Tree /Trie
前缀树：
1. 插入
2. 删除
3. 查询以pre前缀开头的单词有多少个
4. 查询word单词出现过几次

# day12 贪心算法问题
1. 字符串字典序排序问题
2. 会议室问题
3. 黄金分割问题
4. 项目收益最大值问题
5. 灯照问题

# day13 并查集问题
1. 并查集（重要）
# day14 图结构
- Graph  用点结构和边结构来表示图结构
1. 图的宽度优先遍历（重要）
2. 图的深度优先遍历（重要）
3. 图的拓扑排序（重要）
    1. 图的拓扑排序i 点次
    2. 图的拓扑排序ii 深度
4. 最小生成树算法
    1. K算法（并查集）
    2. P算法（点解锁）
5. 迪瑞克斯拉算法
    1. 迪瑞克斯拉算法
    2. 迪瑞克斯拉算法升级（使用加强堆优化）
# day15 经典递归算法
暴力递归就是尝试 
1. 把问题转化为规模缩小了的同类问题的子问题
2. 有明确的不需要继续进行递归的条件(base case)
3. 有当得到了子问题的结果之后的决策过程
4. 不记录每一个子问题的解

1. 汉诺塔算法
2. 字符串所有子串、全排列及去重问题
2. 不借助额外空间结构逆序栈


# day16 动态规划问题
1. 斐波那契数列
2. 机器人走路问题
3. 拿牌问题
4. 背包问题
5. 字符串转换问题
6. 贴花字符串问题
7. 最长公共子序列问题


## 常见模型：
1. 从左往右
2. 范围
3. 样本模型
4. 业务限制









# 算法题
## Code01:
1. 如何不用额外变量交换两个数
2. 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
3. 怎么把一个int类型的数，提取出最右侧的1来 a&(-a)
4. 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
5. 一个数组中有一种数出现K次，其他数都出现了M次， M > 1,  K < M,找到出现了K次的数，要求，额外空间复杂度O(1)，时间复杂度O(N)
 
# day10
## 动态规划与暴力破解问题 

