package com.sqlist.leetcode.editor.cn;

/**
给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。 

 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。 

 

 示例 1： 

 
输入：s = "bbbab"
输出：4
解释：一个可能的最长回文子序列为 "bbbb" 。
 

 示例 2： 

 
输入：s = "cbbd"
输出：2
解释：一个可能的最长回文子序列为 "bb" 。
 

 

 提示： 

 
 1 <= s.length <= 1000 
 s 仅由小写英文字母组成 
 
 Related Topics 字符串 动态规划 👍 606 👎 0

*/

/**
 * https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484666&idx=1&sn=e3305be9513eaa16f7f1568c0892a468&chksm=9bd7faf2aca073e4f08332a706b7c10af877fee3993aac4dae86d05783d3d0df31844287104e&scene=21#wechat_redirect
 *
 * @author SqList
 * @createTime 2021-08-26 10:59:13
 **/
public class Q516LongestPalindromicSubsequence {
    public static void main(String[] args) {
        Solution solution = new Q516LongestPalindromicSubsequence().new Solution();
        System.out.println(solution.longestPalindromeSubseq("bbbab"));
        System.out.println(solution.longestPalindromeSubseq("cbbd"));
        System.out.println(solution.longestPalindromeSubseq("dsdsdjsddsdsjdsfsfsjfsdsdslasddadsdslsds"));
    }

    /**
     * dp[i][j] 表示 s[i...j] 最长回文子序列 长度
     *
     * 与之前不同  dp[i][j] 依赖于 dp[i + 1][j - 1] 、 dp[i][j - 1] 、 dp[i + 1][j]
     * 要保证 dp[i + 1][j - 1] 、 dp[i][j - 1] 、 dp[i + 1][j] 先于 dp[i][j] 被计算
     * 遍历方式需要改变
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestPalindromeSubseq(String s) {
            int[][] dp = new int[s.length()][s.length()];

            // 初始化  只有一个字符时 最长回文子序列的长度为1
            for (int i = 0; i < s.length(); i++) {
                dp[i][i] = 1;
            }

            for (int i = s.length() - 1; i >= 0; i--) {
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                    }
                }
            }

            return dp[0][s.length() - 1];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}