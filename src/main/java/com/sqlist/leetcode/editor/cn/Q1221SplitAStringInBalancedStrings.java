package com.sqlist.leetcode.editor.cn;

/**
在一个 平衡字符串 中，'L' 和 'R' 字符的数量是相同的。 

 给你一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。 

 注意：分割得到的每个字符串都必须是平衡字符串。 

 返回可以通过分割得到的平衡字符串的 最大数量 。 

 

 示例 1： 

 
输入：s = "RLRRLLRLRL"
输出：4
解释：s 可以分割为 "RL"、"RRLL"、"RL"、"RL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
 

 示例 2： 

 
输入：s = "RLLLLRRRLR"
输出：3
解释：s 可以分割为 "RL"、"LLLRRR"、"LR" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
 

 示例 3： 

 
输入：s = "LLLLRRRR"
输出：1
解释：s 只能保持原样 "LLLLRRRR".
 

 示例 4： 

 
输入：s = "RLRRRLLRLL"
输出：2
解释：s 可以分割为 "RL"、"RRRLLRLL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
 

 

 提示： 

 
 1 <= s.length <= 1000 
 s[i] = 'L' 或 'R' 
 s 是一个 平衡 字符串 
 
 Related Topics 贪心 字符串 计数 👍 129 👎 0

*/

/**
 * [1221]分割平衡字符串
 * @author SqList
 * @createTime 2021-09-07 12:05:51
 **/
public class Q1221SplitAStringInBalancedStrings {
    public static void main(String[] args) {
        Solution solution = new Q1221SplitAStringInBalancedStrings().new Solution();
        System.out.println(solution.balancedStringSplit("RLRRLLRLRL"));
        System.out.println(solution.balancedStringSplit("RLLLLRRRLR"));
        System.out.println(solution.balancedStringSplit("LLLLRRRR"));
        System.out.println(solution.balancedStringSplit("RLRRRLLRLL"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int balancedStringSplit(String s) {
            int res = 0;
            int tmp = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == 'L') {
                    tmp ++;
                } else if (s.charAt(i) == 'R') {
                    tmp --;
                }

                if (tmp == 0) {
                    res ++;
                }
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}