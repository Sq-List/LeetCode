package com.sqlist.leetcode.editor.cn;

/**
返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。 

 注意：该题与 316 https://leetcode.com/problems/remove-duplicate-letters/ 相同 

 

 示例 1： 

 
输入：s = "bcabc"
输出："abc"
 

 示例 2： 

 
输入：s = "cbacdcbc"
输出："acdb" 

 

 提示： 

 
 1 <= s.length <= 1000 
 s 由小写英文字母组成 
 
 Related Topics 栈 贪心 字符串 单调栈 👍 110 👎 0

*/

import java.util.Stack;

/**
 * @author SqList
 * @createTime 2021-09-01 17:27:03
 **/
public class Q1081SmallestSubsequenceOfDistinctCharacters {
    public static void main(String[] args) {
        Solution solution = new Q1081SmallestSubsequenceOfDistinctCharacters().new Solution();
        System.out.println(solution.smallestSubsequence("bcabc"));
        System.out.println(solution.smallestSubsequence("cbacdcbc"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String smallestSubsequence(String s) {
            int[] count = new int[28];
            char[] sChars = s.toCharArray();
            for (char c : sChars) {
                count[c - 'a'] ++;
            }

            StringBuilder sb = new StringBuilder();
            boolean[] vis = new boolean[28];
            Stack<Character> stack = new Stack<>();
            for (char c : sChars) {
                int i = c - 'a';
                count[i] --;

                if (vis[i]) {
                    continue;
                }

                while (sb.length() != 0 && sb.charAt(sb.length() - 1) > c) {
                    if (count[sb.charAt(sb.length() - 1) - 'a'] == 0) {
                        break;
                    }

                    vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                    sb.deleteCharAt(sb.length() - 1);
                }

                vis[i] = true;
                sb.append(c);
            }

            return sb.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}