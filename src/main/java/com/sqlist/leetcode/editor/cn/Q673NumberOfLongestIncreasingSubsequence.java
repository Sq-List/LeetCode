package com.sqlist.leetcode.editor.cn;

/**
给定一个未排序的整数数组，找到最长递增子序列的个数。 

 示例 1: 

 
输入: [1,3,5,4,7]
输出: 2
解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 

 示例 2: 

 
输入: [2,2,2,2,2]
输出: 5
解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 

 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。 
 Related Topics 树状数组 线段树 数组 动态规划 👍 460 👎 0

*/

import java.util.Arrays;

/**
 * [673]最长递增子序列的个数
 * @author SqList
 * @createTime 2021-09-20 19:59:38
 **/
public class Q673NumberOfLongestIncreasingSubsequence {
    public static void main(String[] args) {
        Solution solution = new Q673NumberOfLongestIncreasingSubsequence().new Solution();
        System.out.println(solution.findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
        System.out.println(solution.findNumberOfLIS(new int[]{2, 2, 2, 2, 2}));
        System.out.println(solution.findNumberOfLIS(new int[]{1, 2, 4, 3, 5, 4, 7, 2}));
        System.out.println(solution.findNumberOfLIS(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findNumberOfLIS(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }

            int[] dp = new int[nums.length];
            int[] count =  new int[nums.length];
            int max = 1;

            Arrays.fill(dp, 1);
            Arrays.fill(count, 1);

            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] > nums[i]) {
                        if (dp[i] + 1 > dp[j]) {
                            // 重置
                            dp[j] = dp[i] + 1;
                            count[j] = count[i];
                        } else if (dp[i] + 1 == dp[j]) {
                            count[j] += count[i];
                        }
                    }

                    if (dp[j] > max) {
                        max = dp[j];
                    }
                }
            }

            int ans = 0;
            for (int i = 0; i < dp.length; i++) {
                if (max == dp[i]) {
                    ans += count[i];
                }
            }

            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}