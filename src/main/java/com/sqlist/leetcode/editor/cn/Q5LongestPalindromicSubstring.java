package com.sqlist.leetcode.editor.cn;

//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3969 👎 0


/**
 * @author SqList
 * @createTime 2021-08-17 16:37:11
 **/
public class Q5LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new Q5LongestPalindromicSubstring().new Solution();

        System.out.println(solution.longestPalindrome("abba"));
        System.out.println(solution.longestPalindrome("abbac"));
        System.out.println(solution.longestPalindrome("abac"));
        System.out.println(solution.longestPalindrome("a"));
        System.out.println(solution.longestPalindrome("acc"));
        System.out.println(solution.longestPalindrome("fdshjkchuiwenciskewicewlcw"));
    }

    class SolutionSelf {
        public String longestPalindrome(String s) {
            String result = "";
            for (int i = 0; i < s.length(); i++) {
                String tmpResultA = "";
                for (int j = 0; i - j >= 0 && i + j < s.length(); j++) {
                    int a = i - j;
                    int b = i + j;
                    if (s.charAt(a) != s.charAt(b)) {
                        break;
                    }
                    tmpResultA = s.substring(a, b + 1);
                }

                String tmpResultB = "";
                for (int j = 0; i - j >= 0 && i + j + 1 < s.length(); j++) {
                    int a = i - j;
                    int b = i + j + 1;
                    if (s.charAt(a) != s.charAt(b)) {
                        break;
                    }
                    tmpResultB = s.substring(a, b + 1);
                }

                String tmpResult = tmpResultA.length() > tmpResultB.length() ? tmpResultA : tmpResultB;
                result = tmpResult.length() > result.length() ? tmpResult : result;
            }
            return result;
        }
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPalindrome(String s) {
            String result = "";
            for (int i = 0; i < s.length(); i++) {
                String tempA = palindrome(s, i, i);
                String tempB = palindrome(s, i, i + 1);

                result = tempA.length() > result.length() ? tempA : result;
                result = tempB.length() > result.length() ? tempB : result;
            }
            return result;
        }

        public String palindrome(String s, int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left --;
                right ++;
            }

            return s.substring(left + 1, right);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}