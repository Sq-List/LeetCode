package com.sqlist.leetcode.editor.cn;

/**
峰值元素是指其值严格大于左右相邻值的元素。 

 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。 

 你可以假设 nums[-1] = nums[n] = -∞ 。 

 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。 

 

 示例 1： 

 
输入：nums = [1,2,3,1]
输出：2
解释：3 是峰值元素，你的函数应该返回其索引 2。 

 示例 2： 

 
输入：nums = [1,2,1,3,5,6,4]
输出：1 或 5 
解释：你的函数可以返回索引 1，其峰值元素为 2；
 或者返回索引 5， 其峰值元素为 6。
 

 

 提示： 

 
 1 <= nums.length <= 1000 
 -2³¹ <= nums[i] <= 2³¹ - 1 
 对于所有有效的 i 都有 nums[i] != nums[i + 1] 
 
 Related Topics 数组 二分查找 👍 552 👎 0

*/

/**
 * [162]寻找峰值
 * @author SqList
 * @createTime 2021-09-15 10:07:34
 **/
public class Q162FindPeakElement {
    public static void main(String[] args) {
        Solution solution = new Q162FindPeakElement().new Solution();
        System.out.println(solution.findPeakElement(new int[]{1, 2, 3, 1}));
        System.out.println(solution.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
        System.out.println(solution.findPeakElement(new int[]{1, 2, 3, 1, 6, 5, 4}));
        System.out.println(solution.findPeakElement(new int[]{1}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 如果我们从一个位置开始，不断地向高处走，那么最终一定可以到达一个峰值位置。
     * 因此，我们首先在 [0,n) 的范围内随机一个初始位置 i，随后根据 nums[i−1],nums[i],nums[i+1] 三者的关系决定向哪个方向走：
     * 如果 nums[i−1]<nums[i]>nums[i+1]，那么位置 i 就是峰值位置，我们可以直接返回 ii 作为答案；
     * 如果 nums[i−1]<nums[i]<nums[i+1]，那么位置 i 处于上坡，我们需要往右走，即 i←i+1；
     * 如果 nums[i−1]>nums[i]>nums[i+1]，那么位置 i 处于下坡，我们需要往左走，即 i←i−1；
     * 如果 nums[i−1]>nums[i]<nums[i+1]，那么位置 i 位于山谷，两侧都是上坡，我们可以朝任意方向走。
     *
     * 如果我们规定对于最后一种情况往右走，那么可得，当位置 i 不是峰值位置时：
     * 如果 nums[i]<nums[i+1]，那么我们往右走；
     * 如果 nums[i]>nums[i+1]，那么我们往左走。
     */
    class Solution {
        public int findPeakElement(int[] nums) {
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] < nums[mid + 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 二分
     */
    class SolutionSelf {
        public int findPeakElement(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }

            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (mid == 0 || mid == nums.length - 1) {
                    if ((mid == 0 && nums[mid] > nums[mid + 1])
                            || (mid == nums.length - 1 && nums[mid] > nums[mid - 1])) {
                        return mid;
                    }
                } else if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                    return mid;
                }

                if (nums[mid] < nums[mid + 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return -1;
        }
    }
}