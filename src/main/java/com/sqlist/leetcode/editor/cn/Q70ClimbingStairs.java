package com.sqlist.leetcode.editor.cn;
/**
 * @author SqList
 * @createTime 2021-04-05 13:36:27
 **/
//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划 
// 👍 1579 👎 0


public class Q70ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new Q70ClimbingStairs().new Solution();
        System.out.println(solution.climbStairs(2));
        System.out.println(solution.climbStairs(3));
        System.out.println(solution.climbStairs(4));
    }

    /**
     * 递归方法
     */
    class SolutionRecursion {

        int[] mem;

        public int climbStairs(int n) {
            mem = new int[n + 1];
            return dp(n);
        }

        public int dp(int n) {
            if (n <= 0) {
                return 0;
            }
            if (n <= 2) {
                return n;
            }

            if (mem[n] != 0) {
                return mem[n];
            }

            int ans = dp(n - 1) + dp(n - 2);

            mem[n] = ans;
            return ans;
        }
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * dp数组
     */
    class Solution {

        int[] dp;

        public int climbStairs(int n) {
            if (n <= 0) {
                return 0;
            }
            if (n <= 2) {
                return n;
            }

            dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }

            return dp[n];
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}