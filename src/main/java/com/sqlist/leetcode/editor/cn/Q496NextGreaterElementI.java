package com.sqlist.leetcode.editor.cn;

/**
给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。 

 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。 

 nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。 

 

 示例 1: 

 
输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
输出: [-1,3,-1]
解释:
    对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
    对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
    对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。 

 示例 2: 

 
输入: nums1 = [2,4], nums2 = [1,2,3,4].
输出: [3,-1]
解释:
    对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 

 

 提示： 

 
 1 <= nums1.length <= nums2.length <= 1000 
 0 <= nums1[i], nums2[i] <= 10⁴ 
 nums1和nums2中所有整数 互不相同 
 nums1 中的所有整数同样出现在 nums2 中 
 

 

 进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？ 
 Related Topics 栈 数组 哈希表 单调栈 👍 547 👎 0

*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * [496]下一个更大元素 I
 * @author SqList
 * @createTime 2021-10-26 11:26:39
 **/
public class Q496NextGreaterElementI {
    public static void main(String[] args) {
        Solution solution = new Q496NextGreaterElementI().new Solution();
        System.out.println(Arrays.toString(solution.nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2})));
        System.out.println(Arrays.toString(solution.nextGreaterElement(new int[]{2,4}, new int[]{1,2,3,4})));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 单调栈 + hash表
     */
    class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            Stack<Integer> stack = new Stack<>();
            Map<Integer, Integer> ansMap = new HashMap<>(nums2.length, 1);

            // 单调栈是 顶到底 是递增的
            // 倒着往栈里放
            for (int i = nums2.length - 1; i >= 0; i--) {
                int curr = nums2[i];
                // 判断当前与栈顶的大小（栈中最小的 最后一个加入的值）
                while (!stack.empty() && curr > stack.peek()) {
                    // 比当前小的 一定不可能是某一个的下一个最小值 因为都被当前值挡住了
                    stack.pop();
                }

                // 当前值的 下一个更大值
                ansMap.put(nums2[i], stack.empty() ? -1 : stack.peek());
                // 将自己也加入栈中与后续的数比对
                stack.push(nums2[i]);
            }

            int[] ans = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                ans[i] = ansMap.get(nums1[i]);
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}