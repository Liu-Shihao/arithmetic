package com.lsh.leetcode;

/**
 * @author ：LiuShihao
 * @date ：Created in 2022/2/28 8:17 下午
 * @desc ：4. Median of Two Sorted Arrays
 * @link ： https://leetcode.com/problems/median-of-two-sorted-arrays/
 * 给定两个大小分别为m和n的有序数组nums1和nums2，返回这两个有序数组的中值。
 */
public class Code04_MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = {2,4,6,8};
        int[] nums2 = {1,3,5,7};
        System.out.println(findMedianSortedArrays(nums1,nums2));

    }

    /**
     * 首先将两个数组归并
     * 如果长度是奇数，则直接取最中间的元素的值
     * 如果长度是偶数，则取中间的两位元素的平均值
     * @param nums1
     * @param nums2
     * @return
     */
    public static  double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double ans = 0.0;
        if (nums1 == null && nums2 == null){
            return ans;
        }
        int m = nums1.length ;
        int n = nums2.length ;
        int[] arr = new int[m+n];
        int length = arr.length;
        int p1 = 0;
        int p2 = 0;
        int index = 0;
        while (p1 < m && p2 < n){
            arr[index++] = nums1[p1] < nums2[p2] ? nums1[p1++] : nums2[p2++];
        }
        while (p1<m){
            arr[index++] = nums1[p1++];
        }
        while (p2<n){
            arr[index++] = nums2[p2++];
        }
//        (length & 1) == 1 //如果等于0 说明长度是偶数，如果等于1 说明长度是奇数
//        if ((length & 1) == 1){
//            ans = (double)arr[length>>1];
//        }else {
//            int mid = (length-1) >>1;
//            ans = (double)((double)arr[mid] + (double)arr[mid+1])/2;
//        }
//        return  ans;
        return (length & 1) == 1 ?  (double)arr[length>>1]: (double)((double)arr[(length-1) >>1] + (double)arr[((length-1) >>1)+1])/2;

    }
}
