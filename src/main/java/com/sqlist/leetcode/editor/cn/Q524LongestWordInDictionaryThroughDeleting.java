package com.sqlist.leetcode.editor.cn;

/**
给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。 

 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。 

 

 示例 1： 

 
输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
输出："apple"
 

 示例 2： 

 
输入：s = "abpcplea", dictionary = ["a","b","c"]
输出："a"
 

 

 提示： 

 
 1 <= s.length <= 1000 
 1 <= dictionary.length <= 1000 
 1 <= dictionary[i].length <= 1000 
 s 和 dictionary[i] 仅由小写英文字母组成 
 
 Related Topics 数组 双指针 字符串 排序 👍 164 👎 0

*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * [524]通过删除字母匹配到字典里最长单词
 * @author SqList
 * @createTime 2021-09-14 00:19:52
 **/
public class Q524LongestWordInDictionaryThroughDeleting {
    public static void main(String[] args) {
        Solution solution = new Q524LongestWordInDictionaryThroughDeleting().new Solution();
        System.out.println(solution.findLongestWord("abpcplea",
                Arrays.asList("ale","apple","monkey","plea")));
        System.out.println(solution.findLongestWord("abpcplea",
                Arrays.asList("a","b","c")));
        System.out.println(solution.findLongestWord("abpcplea",
                Arrays.asList("ale","apple","monkey","plea", "abpcplaaa","abpcllllll","abccclllpppeeaaaa")));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 动态规划 + 排序 + 双指针
     * dp[i][j] 表示 s 从i的位置向后 每个字母 第一次出现的位置
     */
    class Solution {
        public String findLongestWord(String s, List<String> dictionary) {
            int m = s.length();
            int[][] dp = new int[s.length() + 1][26];
            Arrays.fill(dp[m], m);

            for (int i = m - 1; i >= 0; i--) {
                for (int j = 0; j < 26; j++) {
                    if ((char)(j + 'a') == s.charAt(i)) {
                        dp[i][j] = i;
                    } else {
                        dp[i][j] = dp[i + 1][j];
                    }
                }
            }

            dictionary.sort((o1, o2) -> {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                return o2.length() - o1.length();
            });

            String res = "";
            for (String target : dictionary) {
                int i = 0, j = 0;
                while (i < m && j < target.length()) {
                    if (dp[i][target.charAt(j) - 'a'] < m) {
                        i = dp[i][target.charAt(j) - 'a'] + 1;
                        j++;
                    } else {
                        break;
                    }
                }

                if (j >= target.length()) {
                    return target;
                }
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 动态规划 + 双指针
     * dp[i][j] 表示 s 从i的位置向后 每个字母 第一次出现的位置
     */
    class SolutionDpAndDoublePoint {
        public String findLongestWord(String s, List<String> dictionary) {
            int m = s.length();
            int[][] dp = new int[s.length() + 1][26];
            Arrays.fill(dp[m], m);

            for (int i = m - 1; i >= 0; i--) {
                for (int j = 0; j < 26; j++) {
                    if ((char)(j + 'a') == s.charAt(i)) {
                        dp[i][j] = i;
                    } else {
                        dp[i][j] = dp[i + 1][j];
                    }
                }
            }

            String res = "";
            for (String target : dictionary) {
                int i = 0, j = 0;
                while (i < m && j < target.length()) {
                    if (dp[i][target.charAt(j) - 'a'] < m) {
                        i = dp[i][target.charAt(j) - 'a'] + 1;
                        j++;
                    } else {
                        break;
                    }
                }

                if (j >= target.length() && res.length() <= target.length()) {
                    if (res.length() < target.length() || res.compareTo(target) > 0) {
                        res = target;
                    }
                }
            }

            return res;
        }
    }

    /**
     * 排序 + 双指针
     */
    class SolutionSortAndDoublePoint {
        public String findLongestWord(String s, List<String> dictionary) {
            dictionary.sort((o1, o2) -> {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                return o2.length() - o1.length();
            });

            for (String target : dictionary) {
                int i = 0, j = 0;
                while (i < s.length() && j < target.length()) {
                    if (s.charAt(i) == target.charAt(j)) {
                        j++;
                    }
                    i++;
                }

                if (j >= target.length()) {
                    return target;
                }
            }

            return "";
        }
    }
}