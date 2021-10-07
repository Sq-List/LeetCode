package com.sqlist.leetcode.editor.cn;

/**
给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。 

 

 示例 1： 

 
输入：[3, 2, 1]
输出：1
解释：第三大的数是 1 。 

 示例 2： 

 
输入：[1, 2]
输出：2
解释：第三大的数不存在, 所以返回最大的数 2 。
 

 示例 3： 

 
输入：[2, 2, 3, 1]
输出：1
解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。 

 

 提示： 

 
 1 <= nums.length <= 10⁴ 
 -2³¹ <= nums[i] <= 2³¹ - 1 
 

 

 进阶：你能设计一个时间复杂度 O(n) 的解决方案吗？ 
 Related Topics 数组 排序 👍 277 👎 0

*/

import java.util.Arrays;

/**
 * [414]第三大的数
 * @author SqList
 * @createTime 2021-10-06 13:17:31
 **/
public class Q414ThirdMaximumNumber {
    public static void main(String[] args) {
        Solution solution = new Q414ThirdMaximumNumber().new Solution();
        System.out.println(solution.thirdMax(new int[]{3, 2, 1}));
        System.out.println(solution.thirdMax(new int[]{1, 2}));
        System.out.println(solution.thirdMax(new int[]{2, 2, 3, 1}));
        System.out.println(solution.thirdMax(new int[]{2, 2, 3, 2}));
        System.out.println(solution.thirdMax(new int[]{1, 2, 2}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int thirdMax(int[] nums) {
            return 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class SolutionSelf {
        public int thirdMax(int[] nums) {
            Arrays.sort(nums);
            if (nums.length >= 3) {
                int index = 1;
                int i = nums.length - 2;
                while (i >= 0 && index < 3) {
                    if (nums[i] != nums[i + 1]) {
                        index++;
                    }
                    i--;
                }
                if (index == 3) {
                    return nums[i + 1];
                }
            }

            return nums[nums.length - 1];
        }
    }
}