package com.sqlist.leetcode.editor.cn;

//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。 
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 👍 1767 👎 0


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * [72]编辑距离
 * @author SqList
 * @createTime 2021-08-19 17:28:26
 **/
public class Q72EditDistance {
    public static void main(String[] args) {
        Solution solution = new Q72EditDistance().new Solution();
        System.out.println(solution.minDistance("horse", "ros"));
        System.out.println(solution.minDistance("intention", "execution"));
        System.out.println(solution.minDistance("apple", "rad"));
        System.out.println(solution.minDistance("apple", ""));
        System.out.println(solution.minDistance("猪肉头头好你", "猪头你好"));
    }

    /**
     * 打印步骤
     */
    class SolutionWithStep {
        public int minDistance(String word1, String word2) {
            int[][] dp = new int[word1.length() + 1][word2.length() + 1];
            /**
             * 1 - 插入
             * 2 - 替换
             * 3 - 删除
             * 4 - 跳过 （不计次数）
             */
            int[][] choices = new int[word1.length() + 1][word2.length() + 1];

            // s1[0-i] 转换成 s2[0] 的所用次数 为 i
            for (int i = 1; i <= word1.length(); i++) {
                dp[i][0] = i;
                choices[i][0] = 3;
            }

            // s1[0] 转换成 s2[i] 的所用次数 为 i
            for (int i = 1; i <= word2.length(); i++) {
                dp[0][i] = i;
                choices[0][i] = 1;
            }

            for (int i = 1; i <= word1.length(); i++) {
                for (int j = 1; j <= word2.length(); j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];           // 跳过

                        choices[i][j] = 4;
                    } else {
                        int insert = dp[i][j - 1] + 1;
                        int replace = dp[i - 1][j - 1] + 1;
                        int delete = dp[i - 1][j] + 1;
                        dp[i][j] = min(
                                insert,     // 插入
                                replace,    // 替换
                                delete      // 删除
                        );

                        if (insert == dp[i][j]) {
                            choices[i][j] = 1;
                        } else if (replace == dp[i][j]) {
                            choices[i][j] = 2;
                        } else if (delete == dp[i][j]) {
                            choices[i][j] = 3;
                        }
                    }
                }
            }

            List<String> operate = new ArrayList<>();
            int i = word1.length();
            int j = word2.length();
            while (!(i == 0 && j == 0)) {
                int choice = choices[i][j];
                switch (choice) {
                    case 1:
                        operate.add("插入");
                        j--;
                        break;
                    case 2:
                        operate.add("替换");
                        i--; j--;
                        break;
                    case 3:
                        operate.add("删除");
                        i--;
                        break;
                    case 4:
                        operate.add("跳过");
                        i--; j--;
                        break;
                    default:
                }
            }

            Collections.reverse(operate);
            System.out.println(String.join(" ", operate));

            return dp[word1.length()][word2.length()];
        }

        public int min(int a, int b, int c) {
            return Math.min(a, Math.min(b, c));
        }
    }

    /**
     * dp[i][j] 表示 s1[0-i] 转换成 s2[0-j] 的所用次数
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDistance(String word1, String word2) {
            int[][] dp = new int[word1.length() + 1][word2.length() + 1];

            // s1[0-i] 转换成 s2[0] 的所用次数 为 i
            for (int i = 1; i <= word1.length(); i++) {
                dp[i][0] = i;
            }

            // s1[0] 转换成 s2[i] 的所用次数 为 i
            for (int i = 1; i <= word2.length(); i++) {
                dp[0][i] = i;
            }

            for (int i = 1; i <= word1.length(); i++) {
                for (int j = 1; j <= word2.length(); j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];           // 跳过
                    } else {
                        int insert = dp[i][j - 1] + 1;
                        int replace = dp[i - 1][j - 1] + 1;
                        int delete = dp[i - 1][j] + 1;
                        dp[i][j] = min(
                                insert,     // 插入
                                replace,    // 替换
                                delete      // 删除
                        );
                    }
                }
            }

            return dp[word1.length()][word2.length()];
        }

        public int min(int a, int b, int c) {
            return Math.min(a, Math.min(b, c));
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}