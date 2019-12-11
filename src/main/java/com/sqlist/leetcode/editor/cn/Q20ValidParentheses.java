package com.sqlist.leetcode.editor.cn;
//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Q20ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new Q20ValidParentheses().new Solution();
        System.out.println(solution.isValid("()"));
        System.out.println(solution.isValid("()[]{}"));
        System.out.println(solution.isValid("(]"));
        System.out.println(solution.isValid("([)]"));
        System.out.println(solution.isValid("{[]}"));
        System.out.println(solution.isValid("){[]}("));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean isValid(String s) {
            if (s.length() % 2 != 0) {
                return false;
            }
            char[] chars = s.toCharArray();

            Map<Character, Character> map = new HashMap();
            map.put(']', '[');
            map.put(')', '(');
            map.put('}', '{');

            Stack<Character> stack = new Stack<>();
            try {
                for (int i = 0; i < chars.length; i++) {
                    char single = chars[i];
                    switch (single) {
                        case '{':
                        case '(':
                        case '[':
                            stack.push(single);
//                        System.out.println("push " + single);
                            break;
                        case '}':
                        case ')':
                        case ']':
                            char pop = stack.pop();
//                        System.out.println("pop " + pop);

                            if (!map.get(single).equals(pop)) {
//                            System.out.println(map.get(single) + " not equals " + pop);
                                throw new Exception();
                            }
                            break;
                    }
                }

                if (!stack.empty()) {
//                System.out.println("stack size: " + stack.size());
                    return false;
                }

                return true;
            } catch (Exception e) {
//            System.out.println(e.toString());
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
