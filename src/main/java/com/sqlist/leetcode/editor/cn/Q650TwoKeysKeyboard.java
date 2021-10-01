package com.sqlist.leetcode.editor.cn;

/**
最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作： 

 
 Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。 
 Paste（粘贴）：粘贴 上一次 复制的字符。 
 

 给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。 

 

 示例 1： 

 
输入：3
输出：3
解释：
最初, 只有一个字符 'A'。
第 1 步, 使用 Copy All 操作。
第 2 步, 使用 Paste 操作来获得 'AA'。
第 3 步, 使用 Paste 操作来获得 'AAA'。
 

 示例 2： 

 
输入：n = 1
输出：0
 

 

 提示： 

 
 1 <= n <= 1000 
 
 Related Topics 数学 动态规划 👍 394 👎 0

*/

import java.util.Arrays;

/**
 * [650]只有两个键的键盘
 * @author SqList
 * @createTime 2021-09-19 22:03:29
 **/
public class Q650TwoKeysKeyboard {
    public static void main(String[] args) {
        Solution solution = new Q650TwoKeysKeyboard().new Solution();
        // System.out.println(solution.minSteps(3));
        // System.out.println(solution.minSteps(1));
        // System.out.println(solution.minSteps(4));
        // System.out.println(solution.minSteps(5));
        // System.out.println(solution.minSteps(10));
        System.out.println(solution.minSteps(9));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * dp
     * 设 dp[i] 表示打印出 i 个 A 的最少操作次数。
     */
    class Solution {
        public int minSteps(int n) {
            int[] dp = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                dp[i] = 1001;
                for (int j = 1; j <= Math.sqrt(i); j++) {
                    if (i % j == 0) {
                        dp[i] = Math.min(dp[i], dp[j] + i / j);
                        dp[i] = Math.min(dp[i], dp[i / j] + j);
                    }
                }
            }

            return dp[n];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * dfs
     */
    class SolutionSelf {
        public int minSteps(int n) {
            if (n == 1) {
                return 0;
            }

            return find(n - 1, 1, 1) + 1;
        }

        /**
         *
         * @param n             剩余长度
         * @param nowLength     当前字符长度
         * @param k             复制的长度
         * @return
         */
        public int find(int n, int nowLength, int k) {
            if (n < 0) {
                return Integer.MAX_VALUE - 5;
            }
            if (n == 0) {
                return 0;
            }

            int pasteStep = find(n - k, nowLength + k, k);
            int copyAndPasteStep = find(n - nowLength, nowLength * 2, nowLength);
            if (copyAndPasteStep == Integer.MAX_VALUE - 5 && pasteStep == Integer.MAX_VALUE - 5) {
                return Integer.MAX_VALUE - 5;
            }

            return Math.min(pasteStep + 1, copyAndPasteStep + 2);
        }
    }
}