package com.sqlist.leetcode.editor.cn;

import java.util.Arrays;

/**
 * @author SqList
 * @createTime 2021-04-02 15:28:58
 **/
//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。 
//
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nu
//ms2 的元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//输出：[1,2,2,3,5,6]
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1], m = 1, nums2 = [], n = 0
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m + n 
// nums2.length == n 
// 0 <= m, n <= 200 
// 1 <= m + n <= 200 
// -109 <= nums1[i], nums2[i] <= 109 
// 
// Related Topics 数组 双指针 
// 👍 817 👎 0


public class Q88MergeSortedArray {
    public static void main(String[] args) {
        Solution solution = new Q88MergeSortedArray().new Solution();
        int[] nums1 = new int[]{1, 2, 3, 9, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        solution.merge(nums1, 4, nums2, 3);

        System.out.println(Arrays.toString(nums1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 1： 先插入后排序
         * 2： 克隆一个，一个个比较，小的插入数据（如下）
         */
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int[] nums1Clone = nums1.clone();

            int i = 0, mi = 0, ni = 0;
            for (; i < m + n && ni < n && mi < m; i++) {
                nums1[i] = nums1Clone[mi] < nums2[ni] ? nums1Clone[mi++] : nums2[ni++];
            }

            for (; mi < m; mi++, i++) {
                nums1[i] = nums1Clone[mi];
            }

            for (; ni < n; ni++, i++) {
                nums1[i] = nums2[ni];
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}