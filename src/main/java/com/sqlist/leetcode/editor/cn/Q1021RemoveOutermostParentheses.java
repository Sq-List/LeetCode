package com.sqlist.leetcode.editor.cn;

/**
有效括号字符串为空 ""、"(" + A + ")" 或 A + B ，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。 

 
 例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。 
 

 如果有效字符串 s 非空，且不存在将其拆分为 s = A + B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。 

 给出一个非空有效字符串 s，考虑将其进行原语化分解，使得：s = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。 

 对 s 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 s 。 

 

 示例 1： 

 
输入：s = "(()())(())"
输出："()()()"
解释：
输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。 

 示例 2： 

 
输入：s = "(()())(())(()(()))"
输出："()()()()(())"
解释：
输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
 

 示例 3： 

 
输入：s = "()()"
输出：""
解释：
输入字符串为 "()()"，原语化分解得到 "()" + "()"，
删除每个部分中的最外层括号后得到 "" + "" = ""。
 

 

 提示： 

 
 1 <= s.length <= 10⁵ 
 s[i] 为 '(' 或 ')' 
 s 是一个有效括号字符串 
 
 Related Topics 栈 字符串 👍 259 👎 0

*/

/**
 * [1021]删除最外层的括号
 * @author SqList
 * @createTime 2022-05-30 14:58:43
 **/
public class Q1021RemoveOutermostParentheses {
    public static void main(String[] args) {
        Solution solution = new Q1021RemoveOutermostParentheses().new Solution();
        System.out.println(solution.removeOuterParentheses("(()())(())"));
        System.out.println(solution.removeOuterParentheses("(()())(())(()(()))"));
        System.out.println(solution.removeOuterParentheses("()()"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeOuterParentheses(String s) {
            int unMatchLeftParentheses = 0;
            StringBuilder ans = new StringBuilder();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    if (unMatchLeftParentheses >= 1) {
                        ans.append(c);
                    }
                    unMatchLeftParentheses ++;
                } else {
                    unMatchLeftParentheses --;
                    if (unMatchLeftParentheses >= 1) {
                        ans.append(c);
                    }
                }
            }
            return ans.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}