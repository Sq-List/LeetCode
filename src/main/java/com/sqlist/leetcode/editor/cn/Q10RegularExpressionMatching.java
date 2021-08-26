package com.sqlist.leetcode.editor.cn;

/**
给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 

 
 '.' 匹配任意单个字符 
 '*' 匹配零个或多个前面的那一个元素 
 

 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
 

 示例 1： 

 
输入：s = "aa" p = "a"
输出：false
解释："a" 无法匹配 "aa" 整个字符串。
 

 示例 2: 

 
输入：s = "aa" p = "a*"
输出：true
解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 

 示例 3： 

 
输入：s = "ab" p = ".*"
输出：true
解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 

 示例 4： 

 
输入：s = "aab" p = "c*a*b"
输出：true
解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 

 示例 5： 

 
输入：s = "mississippi" p = "mis*is*p*."
输出：false 

 

 提示： 

 
 0 <= s.length <= 20 
 0 <= p.length <= 30 
 s 可能为空，且只包含从 a-z 的小写字母。 
 p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。 
 保证每次出现字符 * 时，前面都匹配到有效的字符 
 
 Related Topics 递归 字符串 动态规划 👍 2318 👎 0

*/

/**
 * @author SqList
 * @createTime 2021-08-24 10:10:48
 **/
public class Q10RegularExpressionMatching {
    public static void main(String[] args) {
        Solution solution = new Q10RegularExpressionMatching().new Solution();
        System.out.println(solution.isMatch("aa", "a"));
        System.out.println(solution.isMatch("aa", "a*"));
        System.out.println(solution.isMatch("ab", ".*"));
        System.out.println(solution.isMatch("fdshjckewhjkchw", ".*"));
        System.out.println(solution.isMatch("aab", "c*a*b"));
        System.out.println(solution.isMatch("mississippi", "mis*is*p*."));
    }

    /**
     * TODO
     *
     * @author sqlist
     * @createTime 2021/8/26 10:40
     */
    class SolutionFor {
        public boolean isMatch(String s, String p) {
            return false;
        }
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isMatch(String s, String p) {
            byte[][] memo = new byte[s.length() + 1][p.length() + 1];
            return dp(memo, s, 0, p, 0);
        }

        public boolean dp(byte[][] memo, String s, int i, String p, int j) {
            if (memo[i][j] != 0) {
                return byteToBoolean(memo[i][j]);
            }

            String tempS = s.substring(i);
            String tempP = p.substring(j);

            if (tempP.length() == 0) {
                return tempS.length() == 0;
            }

            boolean ans = false;
            boolean first = tempS.length() > 0 && (tempP.charAt(0) == tempS.charAt(0) || tempP.charAt(0) == '.');
            if (tempP.length() >= 2 && tempP.charAt(1) == '*') {
                ans = dp(memo, s, i, p, j + 2)    // 匹配该字符0次 或 跳过该字符和'*'
                    || (first && dp(memo, s, i + 1, p, j));     // tempS[0] 与 tempP[0] 匹配 移动s
            } else {
                ans = first && dp(memo, s, i + 1, p, j + 1);
            }

            memo[i][j] = ans ? (byte)1 : (byte)-1;
            return ans;
        }

        public boolean byteToBoolean(byte a) {
            return a == 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}