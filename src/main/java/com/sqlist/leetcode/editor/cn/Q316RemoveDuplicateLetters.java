package com.sqlist.leetcode.editor.cn;

/**
给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。 

 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同

 

 示例 1： 

 
输入：s = "bcabc"
输出："abc"
 

 示例 2： 

 
输入：s = "cbacdcbc"
输出："acdb" 

 

 提示： 

 
 1 <= s.length <= 10⁴ 
 s 由小写英文字母组成 
 
 Related Topics 栈 贪心 字符串 单调栈 👍 589 👎 0

*/

import java.util.Stack;

/**
 * @author SqList
 * @createTime 2021-09-01 15:40:13
 **/
public class Q316RemoveDuplicateLetters {
    public static void main(String[] args) {
        Solution solution = new Q316RemoveDuplicateLetters().new Solution();
        System.out.println(solution.removeDuplicateLetters("bcabc"));
        System.out.println(solution.removeDuplicateLetters("cbacdcbc"));
    }

    /**
     * 单调栈
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeDuplicateLetters(String s) {
            int[] count = new int[30];
            char[] sChars = s.toCharArray();
            for (char c : sChars) {
                count[c - 'a'] ++;
            }

            Stack<Character> stack = new Stack<>();
            boolean[] inStack = new boolean[30];
            for (char c : sChars) {
                int i = c - 'a';
                count[i] --;

                if (inStack[i]) {
                    continue;
                }

                // 栈顶字符大于当前字符 且 栈顶字符在当前字符后还有同样字符的  则出栈
                while (!stack.empty() && stack.peek() > c) {
                    if (count[stack.peek() - 'a'] == 0) {
                        break;
                    }

                    inStack[stack.pop() - 'a'] = false;
                }

                inStack[i] = true;
                stack.push(c);
            }

            StringBuilder sb = new StringBuilder();
            for (Character character : stack) {
                sb.append(character);
            }
            return sb.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}