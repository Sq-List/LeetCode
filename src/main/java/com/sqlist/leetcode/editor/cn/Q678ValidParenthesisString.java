package com.sqlist.leetcode.editor.cn;

/**
给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则： 

 
 任何左括号 ( 必须有相应的右括号 )。 
 任何右括号 ) 必须有相应的左括号 ( 。 
 左括号 ( 必须在对应的右括号之前 )。 
 * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。 
 一个空字符串也被视为有效字符串。 
 

 示例 1: 

 
输入: "()"
输出: True
 

 示例 2: 

 
输入: "(*)"
输出: True
 

 示例 3: 

 
输入: "(*))"
输出: True
 

 注意: 

 
 字符串大小将在 [1，100] 范围内。 
 
 Related Topics 栈 贪心 字符串 动态规划 👍 370 👎 0

*/

import java.util.Stack;

/**
 * [678]有效的括号字符串
 * @author SqList
 * @createTime 2021-09-12 22:56:46
 **/
public class Q678ValidParenthesisString {
    public static void main(String[] args) {
        Solution solution = new Q678ValidParenthesisString().new Solution();
        System.out.println(solution.checkValidString("()"));
        System.out.println(solution.checkValidString("(*)"));
        System.out.println(solution.checkValidString("(*))"));
        System.out.println(solution.checkValidString("())"));
        System.out.println(solution.checkValidString("())*"));
        System.out.println(solution.checkValidString("(()()"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 贪心
     * 遍历s 计算 未匹配的左括号个数的可能范围
     */
    class Solution {
        public boolean checkValidString(String s) {
            // 未匹配左括号的最小值-最大值
            int min = 0, max = 0;
            for (char c : s.toCharArray()) {
                if ('(' == c) {
                    min++;
                    max++;
                } else if (')' == c) {
                    min = Math.max(min - 1, 0);
                    if (--max < 0) {
                        return false;
                    }
                } else if ('*' == c) {
                    min = Math.max(min - 1, 0);
                    max++;
                }
            }

            return min == 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 栈
     * 利用 左括号栈、星号栈 遍历s 用星号补充 左括号不全的情况
     * 遍历完s后  若左括号栈不为空  遍历
     */
    class SolutionStack {
        public boolean checkValidString(String s) {
            Stack<Integer> leftParenthesisStack = new Stack<>();
            Stack<Integer> asteriskStack = new Stack<>();

            for (int i = 0; i < s.length(); i++) {
                if ('(' == s.charAt(i)) {
                    leftParenthesisStack.add(i);
                } else if ('*' == s.charAt(i)) {
                    asteriskStack.add(i);
                } else if (')' == s.charAt(i)) {
                    if (!leftParenthesisStack.empty()) {
                        leftParenthesisStack.pop();
                    } else if (!asteriskStack.empty()) {
                        asteriskStack.pop();
                    } else {
                        return false;
                    }
                }
            }

            while (!leftParenthesisStack.empty() && !asteriskStack.empty()) {
                Integer leftParenthesisPop = leftParenthesisStack.pop();
                Integer asteriskPop = asteriskStack.pop();
                if (asteriskPop < leftParenthesisPop) {
                    return false;
                }
            }

            return leftParenthesisStack.isEmpty();
        }
    }

    /**
     * Self
     * 正反遍历
     */
    class Solution2Traversal {
        public boolean checkValidString(String s) {
            char[] chars = s.toCharArray();

            int halfParenthesis = 0;
            int numOfAny = 0;
            for (char aChar : chars) {
                if ('(' == aChar) {
                    halfParenthesis++;
                } else if (')' == aChar) {
                    if (halfParenthesis == 0 && numOfAny >= 1) {
                        numOfAny--;
                    } else if (halfParenthesis > 0) {
                        halfParenthesis--;
                    } else {
                        return false;
                    }
                } else {
                    numOfAny++;
                }
            }

            halfParenthesis = 0;
            numOfAny = 0;
            for (int i = chars.length - 1; i >= 0 ; i--) {
                char aChar = chars[i];
                if (')' == aChar) {
                    halfParenthesis++;
                } else if ('(' == aChar) {
                    if (halfParenthesis == 0 && numOfAny >= 1) {
                        numOfAny--;
                    } else if (halfParenthesis > 0) {
                        halfParenthesis--;
                    } else {
                        return false;
                    }
                } else {
                    numOfAny++;
                }
            }

            return true;
        }
    }
}