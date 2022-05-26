package com.sqlist.leetcode.editor.cn;

/**
把字符串 s 看作是 “abcdefghijklmnopqrstuvwxyz” 的无限环绕字符串，所以 s 看起来是这样的： 

 
 "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd...." . 
 

 现在给定另一个字符串 p 。返回 s 中 唯一 的 p 的 非空子串 的数量 。 

 

 示例 1: 

 
输入: p = "a"
输出: 1
解释: 字符串 s 中只有一个"a"子字符。
 

 示例 2: 

 
输入: p = "cac"
输出: 2
解释: 字符串 s 中的字符串“cac”只有两个子串“a”、“c”。.
 

 示例 3: 

 
输入: p = "zab"
输出: 6
解释: 在字符串 s 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。
 

 

 提示: 

 
 1 <= p.length <= 10⁵ 
 p 由小写英文字母构成 
 
 Related Topics 字符串 动态规划 👍 236 👎 0

*/

import java.util.Arrays;

/**
 * [467]环绕字符串中唯一的子字符串
 * @author SqList
 * @createTime 2022-05-25 10:20:22
 **/
public class Q467UniqueSubstringsInWraparoundString {
    public static void main(String[] args) {
        Solution solution = new Q467UniqueSubstringsInWraparoundString().new Solution();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * dp
     */
    class Solution {
        public int findSubstringInWraproundString(String p) {
            int k = 0;
            // 表示 p 中以字符 α 结尾且在 s 中的子串的最长长度
            // 知道了最长长度 可以算出 以 α 结尾的子串个数 即 正好与最长长度相等
            int[] dp = new int[26];
            for (int i = 0; i < p.length(); i++) {
                if (i > 0 && (p.charAt(i) - p.charAt(i - 1) + 26) % 26 == 1) {   // 相邻 相减为 1 或 -25
                    k++;
                } else {
                    k = 1;
                }
                dp[p.charAt(i) - 'a'] = Math.max(dp[p.charAt(i) - 'a'], k);
            }
            return Arrays.stream(dp).sum();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}