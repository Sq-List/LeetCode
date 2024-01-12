package com.sqlist.leetcode.editor.cn;
//给你一个字符串 word ，你可以向其中任何位置插入 "a"、"b" 或 "c" 任意次，返回使 word 有效 需要插入的最少字母数。 
//
// 如果字符串可以由 "abc" 串联多次得到，则认为该字符串 有效 。 
//
// 
//
// 示例 1： 
//
// 输入：word = "b"
//输出：2
//解释：在 "b" 之前插入 "a" ，在 "b" 之后插入 "c" 可以得到有效字符串 "abc" 。
// 
//
// 示例 2： 
//
// 输入：word = "aaa"
//输出：6
//解释：在每个 "a" 之后依次插入 "b" 和 "c" 可以得到有效字符串 "abcabcabc" 。
// 
//
// 示例 3： 
//
// 输入：word = "abc"
//输出：0
//解释：word 已经是有效字符串，不需要进行修改。 
// 
//
// 
//
// 提示： 
//
// 
// 1 <= word.length <= 50 
// word 仅由字母 "a"、"b" 和 "c" 组成。 
// 
//
// Related Topics 栈 贪心 字符串 动态规划 👍 59 👎 0


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Q2645MinimumAdditionsToMakeValidString {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Class<?> clz = Class.forName("com.sqlist.leetcode.editor.cn.Q2645MinimumAdditionsToMakeValidString");
        String file = Objects.requireNonNull(clz.getResource("/testcase/Q2645")).getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);

            long start = System.currentTimeMillis();
            Solution solution = new Q2645MinimumAdditionsToMakeValidString().new Solution();
            System.out.println(solution.addMinimum(line));
            
            long end = System.currentTimeMillis();
            System.out.println("cost: " + (end - start));
        }
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int addMinimum(String word) {
            if (word.length() == 1) {
                return 2;
            }

            int ans = 0;
            for (int i = 0; i < word.length(); ) {
                if (word.charAt(i) == 'c') {
                    // 补 a b
                    ans += 2;
                    i += 1;
                } else if (word.charAt(i) == 'b') {
                    if (i + 1 < word.length()) {
                        if (word.charAt(i +1) == 'c') {
                            // 补 a
                            ans += 1;
                            i += 2;
                        } else {
                            // 补 a c
                            ans += 2;
                            i += 1;
                        }
                    } else {
                        // 补 a c
                        ans += 2;
                        i += 1;
                    }
                } else if (word.charAt(i) == 'a') {
                    if (i + 1 < word.length()) {
                        if (word.charAt(i + 1) == 'b') {
                            if (i + 2 < word.length()) {
                                if (word.charAt(i + 2) == 'c') {
                                    // 完整
                                    i += 3;
                                } else {
                                    // 补 c
                                    ans += 1;
                                    i += 2;
                                }
                            } else {
                                // 补 c
                                ans += 1;
                                i += 2;
                            }
                        } else if (word.charAt(i + 1) == 'c') {
                            // 补 b
                            ans += 1;
                            i += 2;
                        } else {
                            // 补 b c
                            ans += 2;
                            i += 1;
                        }
                    } else {
                        // 补 b c
                        ans += 2;
                        i += 1;
                    }
                }
            }

            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}











