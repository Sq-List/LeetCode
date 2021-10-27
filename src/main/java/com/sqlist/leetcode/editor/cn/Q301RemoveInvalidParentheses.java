package com.sqlist.leetcode.editor.cn;

/**
给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。 

 返回所有可能的结果。答案可以按 任意顺序 返回。 

 

 示例 1： 

 
输入：s = "()())()"
输出：["(())()","()()()"]
 

 示例 2： 

 
输入：s = "(a)())()"
输出：["(a())()","(a)()()"]
 

 示例 3： 

 
输入：s = ")("
输出：[""]
 

 

 提示： 

 
 1 <= s.length <= 25 
 s 由小写英文字母以及括号 '(' 和 ')' 组成 
 s 中至多含 20 个括号 
 
 Related Topics 广度优先搜索 字符串 回溯 👍 516 👎 0

*/

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * [301]删除无效的括号
 * @author SqList
 * @createTime 2021-10-27 00:48:41
 **/
public class Q301RemoveInvalidParentheses {
    public static void main(String[] args) {
        Solution solution = new Q301RemoveInvalidParentheses().new Solution();
        System.out.println(JSON.toJSONString(solution.removeInvalidParentheses("()())()")));
        System.out.println(JSON.toJSONString(solution.removeInvalidParentheses("(a)())()")));
        System.out.println(JSON.toJSONString(solution.removeInvalidParentheses(")(")));
        System.out.println(JSON.toJSONString(solution.removeInvalidParentheses("(((())")));
        long start = System.currentTimeMillis();
        System.out.println(JSON.toJSONString(solution.removeInvalidParentheses("))))))))))))))))))))aaaaa")));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 枚举状态
     */
    class Solution {
        public List<String> removeInvalidParentheses(String s) {
            Set<String> ans = new HashSet<>();
            List<Integer> leftIndexList = new ArrayList<>();
            List<Integer> rightIndexList = new ArrayList<>();

            // 计算错误括号的个数 - 1  可以分别数出 左边错误的 和 右边错误的 个数
            int lRemove = 0, rRemove = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    lRemove++;
                    leftIndexList.add(i);
                } else if (s.charAt(i) == ')') {
                    if (lRemove == 0) {
                        rRemove++;
                    } else {
                        lRemove--;
                    }
                    rightIndexList.add(i);
                }
            }

            List<Integer> leftMaskList = new ArrayList<>();
            List<Integer> rightMaskList = new ArrayList<>();
            // 用二进制的0表示对应的括号 枚举出所有两种括号所有的可能的情况
            for (int i = 0; i < (1 << leftIndexList.size()); i++) {
                if (Integer.bitCount(i) != lRemove) {
                    continue;
                }
                leftMaskList.add(i);
            }
            for (int i = 0; i < (1 << rightIndexList.size()); i++) {
                if (Integer.bitCount(i) != rRemove) {
                    continue;
                }
                rightMaskList.add(i);
            }

            // 将两种括号枚举出的所有可能 一一配对进行验证
            for (Integer leftMask : leftMaskList) {
                for (Integer rightMask : rightMaskList) {
                    valid(s, leftMask, leftIndexList, rightMask, rightIndexList, ans);
                }
            }

            return new ArrayList<>(ans);
        }

        private void valid(String s, Integer leftMark, List<Integer> leftIndexList, Integer rightMark, List<Integer> rightIndexList, Set<String> ans) {
            StringBuilder sb = new StringBuilder();
            int leftPos = 0;
            int rightPos = 0;
            int count = 0;

            for (int i = 0; i < s.length(); i++) {
                if (leftPos < leftIndexList.size() && i == leftIndexList.get(leftPos)) {
                    if ((leftMark & (1 << leftPos)) == 0) {
                        count ++;
                        sb.append(s.charAt(i));
                    }
                    leftPos++;
                } else if (rightPos < rightIndexList.size() && i == rightIndexList.get(rightPos)) {
                    if ((rightMark & (1 << rightPos)) == 0) {
                        count--;
                        sb.append(s.charAt(i));
                        if (count < 0) {
                            return;
                        }
                    }
                    rightPos++;
                } else {
                    sb.append(s.charAt(i));
                }
            }

            ans.add(sb.toString());
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * bfs
     */
    class SolutionBfs {
        public List<String> removeInvalidParentheses(String s) {
            List<String> ans = new ArrayList<>();
            Set<String> currSet = new HashSet<>();

            currSet.add(s);
            while (!currSet.isEmpty()) {
                for (String str : currSet) {
                    valid(str, ans);
                }
                if (ans.size() > 0) {
                    return ans;
                }

                Set<String> nextSet = new HashSet<>();
                for (String str : currSet) {
                    for (int i = 0; i < str.length(); i++) {
                        if (i != 0 && str.charAt(i) == str.charAt(i - 1)) {
                            continue;
                        }
                        if (str.charAt(i) == '(' || str.charAt(i) == ')') {
                            nextSet.add(str.substring(0, i) + str.substring(i + 1));
                        }
                    }
                }
                currSet = nextSet;
            }

            return ans;
        }

        private void valid(String s, List<String> ans) {
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                char curr = s.charAt(i);
                if (curr == '(') {
                    count++;
                } else if (curr == ')'){
                    count--;
                }

                if (count < 0) {
                    return;
                }
            }
            if (count != 0) {
                return;
            }

            ans.add(s);
        }
    }

    /**
     * dfs
     */
    class SolutionDfs {
        public List<String> removeInvalidParentheses(String s) {
            int count = 0;
            char[] sChars = s.toCharArray();

            int tmp = 0;
            // 计算错误括号的个数 - 2
            for (char sChar : sChars) {
                if (sChar == '(') {
                    tmp++;
                } else if (sChar == ')') {
                    tmp--;
                }

                if (tmp < 0) {
                    count += -tmp;
                    tmp = 0;
                }
            }
            count += tmp;

            Set<String> ans = new HashSet<>();

            dfs(Math.abs(count), 0, sChars, new Stack<>(), ans);

            return new ArrayList<>(ans);
        }

        private void dfs(int index, int pre, char[] sChars, Stack<Integer> continueStack, Set<String> ans) {
            if (index == 0) {
                valid(continueStack, sChars, ans);
                return;
            }

            for (int i = pre; i < sChars.length; i++) {
                // 如果遇到连续相同的括号我们只需要搜索一次即可
                if (i != pre && sChars[i] == sChars[i - 1]) {
                    continue;
                }

                if (sChars[i] == '(' || sChars[i] == ')') {
                    continueStack.push(i);
                    dfs(index - 1, i + 1, sChars, continueStack, ans);
                    continueStack.pop();
                }
            }
        }

        private void valid(Stack<Integer> continueStack, char[] sChars, Set<String> ans) {
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (int i = 0; i < sChars.length; i++) {
                if (continueStack.contains(i)) {
                    continue;
                }

                char curr = sChars[i];
                sb.append(curr);
                if (curr == '(') {
                    count++;
                } else if (curr == ')'){
                    count--;
                }

                if (count < 0) {
                    return;
                }
            }
            if (count != 0) {
                return;
            }

            ans.add(sb.toString());
        }
    }
}