package com.sqlist.leetcode.editor.cn;

/**
给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 

 如果数组中不存在目标值 target，返回 [-1, -1]。 

 进阶： 

 
 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
 

 

 示例 1： 

 
输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4] 

 示例 2： 

 
输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1] 

 示例 3： 

 
输入：nums = [], target = 0
输出：[-1,-1] 

 

 提示： 

 
 0 <= nums.length <= 10⁵ 
 -10⁹ <= nums[i] <= 10⁹ 
 nums 是一个非递减数组 
 -10⁹ <= target <= 10⁹ 
 
 Related Topics 数组 二分查找 👍 1188 👎 0

*/

import java.util.Arrays;

/**
 * [34]在排序数组中查找元素的第一个和最后一个位置
 * @author SqList
 * @createTime 2021-09-06 20:09:17
 **/
public class Q34FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        Solution solution = new Q34FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        System.out.println(Arrays.toString(solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        System.out.println(Arrays.toString(solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
        System.out.println(Arrays.toString(solution.searchRange(new int[]{}, 0)));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            // 找左边界
            while (left <= right) {
                int mid = (left + right) >> 1;
                if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] == target) {
                    // 与二分查找的不同处
                    right = mid - 1;
                }
            }

            if (left >= nums.length || nums[left] != target) {
                return new int[]{-1, -1};
            }

            int resLeft = left;
            left = 0;
            right = nums.length - 1;
            // 找右边界
            while (left <= right) {
                int mid = (left + right) >> 1;
                if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] == target) {
                    // 与二分查找的不同处
                    left = mid + 1;
                }
            }

            return new int[]{resLeft, right};
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}