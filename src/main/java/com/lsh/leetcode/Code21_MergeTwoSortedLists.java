package com.lsh.leetcode;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/3/16 10:21 上午
 * @desc ：21. Merge Two Sorted Lists 合并两个有序链表
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 *
 */
public class Code21_MergeTwoSortedLists {
  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
      if (list1 == null || list2 == null) return list1 == null ? list2 : list1;
      ListNode head = list1.val < list2.val ? list1 :list2;
      ListNode pre = head;
      list1 = head == list1 ? list1.next : list1;
      list2 = head == list2 ? list2.next : list2;

      while (list1 != null && list2 != null){
          if (list1.val < list2.val){
              pre.next = list1;
              list1 = list1.next;
          }else {
              pre.next = list2;
              list2 = list2.next;
          }
          pre = pre.next;
      }
      if (list1 != null)pre.next = list1;
      if (list2 != null)pre.next = list2;
      return head;
    }
}
