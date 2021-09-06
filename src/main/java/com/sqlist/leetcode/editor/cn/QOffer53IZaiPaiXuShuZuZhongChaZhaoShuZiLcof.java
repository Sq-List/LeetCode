package com.sqlist.leetcode.editor.cn;

/**
统计一个数字在排序数组中出现的次数。 

 

 示例 1: 

 
输入: nums = [5,7,7,8,8,10], target = 8
输出: 2 

 示例 2: 

 
输入: nums = [5,7,7,8,8,10], target = 6
输出: 0 

 

 提示： 

 
 0 <= nums.length <= 10⁵ 
 -10⁹ <= nums[i] <= 10⁹ 
 nums 是一个非递减数组 
 -10⁹ <= target <= 10⁹ 
 

 

 注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-
position-of-element-in-sorted-array/ 
 Related Topics 数组 二分查找 👍 203 👎 0

*/

/**
 * [剑指 Offer 53 - I]在排序数组中查找数字 I
 * @author SqList
 * @createTime 2021-09-06 20:40:34
 **/
public class QOffer53IZaiPaiXuShuZuZhongChaZhaoShuZiLcof {
    public static void main(String[] args) {
        Solution solution = new QOffer53IZaiPaiXuShuZuZhongChaZhaoShuZiLcof().new Solution();
        System.out.println(solution.search(new int[]{5,7,7,8,8,10}, 8));
        System.out.println(solution.search(new int[]{5,7,7,8,8,10}, 6));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int search(int[] nums, int target) {
            int resLeft = binarySearch(nums, target, true);
            if (resLeft >= nums.length || nums[resLeft] != target) {
                // 找不到这个数
                return 0;
            }
            int resRight = binarySearch(nums, target, false);
            return resRight - resLeft + 1;
        }

        public int binarySearch(int[] nums, int target, boolean leftEnd) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] == target) {
                    if (leftEnd) {
                        // 找左边界
                        right = mid - 1;
                    } else {
                        // 找右边界
                        left = mid + 1;
                    }
                }
            }

            if (leftEnd) {
                return left;
            } else {
                return right;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}