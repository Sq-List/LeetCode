package com.sqlist.leetcode.editor.cn;

/**
给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。 

 

 示例： 

 输入: "sea", "eat"
输出: 2
解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 

 

 提示： 

 
 给定单词的长度不超过500。 
 给定单词中的字符只含有小写字母。 
 
 Related Topics 字符串 动态规划 👍 238 👎 0

*/

import java.util.Arrays;

/**
 * [583]两个字符串的删除操作
 * @author SqList
 * @createTime 2021-09-25 01:36:56
 **/
public class Q583DeleteOperationForTwoStrings {
    public static void main(String[] args) {
        Solution solution = new Q583DeleteOperationForTwoStrings().new Solution();
        System.out.println(solution.minDistance("sea", "eat"));
        System.out.println(solution.minDistance("abcd", "cd"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 动态规划 - 最长公共子序列
     * 可以将题意转换成 求 word1跟word2的最长公子序列长度 * 2 去减两个word1.length+word2.length
     * dp[i][j] 表示 word1[0-i-1] 与 word2[0-j-1] 最长公共子序列长度
     */
    class Solution {
        public int minDistance(String word1, String word2) {
            int[][] dp = new int[word1.length() + 1][word2.length() + 1];
            int n = word1.length(), m = word2.length();

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                }
            }

            return n + m - 2 * dp[word1.length()][word2.length()];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 动态规划
     * 可以将题意转换成 将 word1 通过删改 变成 word2
     * dp[i][j] 表示 word1[0-i-1] 变成 word2[0-j-1] 所需的最小步数
     */
    class SolutionDp {
        public int minDistance(String word1, String word2) {
            int[][] dp = new int[word1.length() + 1][word2.length() + 1];
            for (int i = 0; i <= word1.length(); i++) {
                dp[i][0] = i;
            }

            for (int i = 0; i <= word2.length(); i++) {
                dp[0][i] = i;
            }

            for (int i = 1; i <= word1.length(); i++) {
                for (int j = 1; j <= word2.length(); j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                    }
                }
            }

            return dp[word1.length()][word2.length()];
        }
    }
}