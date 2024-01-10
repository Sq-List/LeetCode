package com.sqlist.leetcode.editor.cn;
//给你一个仅由 大写 英文字符组成的字符串 s 。 
//
// 你可以对此字符串执行一些操作，在每一步操作中，你可以从 s 中删除 任一个 "AB" 或 "CD" 子字符串。 
//
// 通过执行操作，删除所有 "AB" 和 "CD" 子串，返回可获得的最终字符串的 最小 可能长度。 
//
// 注意，删除子串后，重新连接出的字符串可能会产生新的 "AB" 或 "CD" 子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ABFCACDB"
//输出：2
//解释：你可以执行下述操作：
//- 从 "ABFCACDB" 中删除子串 "AB"，得到 s = "FCACDB" 。
//- 从 "FCACDB" 中删除子串 "CD"，得到 s = "FCAB" 。
//- 从 "FCAB" 中删除子串 "AB"，得到 s = "FC" 。
//最终字符串的长度为 2 。
//可以证明 2 是可获得的最小长度。 
//
// 示例 2： 
//
// 
//输入：s = "ACBBD"
//输出：5
//解释：无法执行操作，字符串长度不变。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 仅由大写英文字母组成 
// 
//
// Related Topics 栈 字符串 模拟 👍 42 👎 0


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Q2696MinimumStringLengthAfterRemovingSubstrings {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Class<?> clz = Class.forName("com.sqlist.leetcode.editor.cn.Q2696MinimumStringLengthAfterRemovingSubstrings");
        String file = Objects.requireNonNull(clz.getResource("/testcase/Q2696")).getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            long start = System.currentTimeMillis();
            Solution solution = new Q2696MinimumStringLengthAfterRemovingSubstrings().new Solution();
            System.out.println(solution.minLength(line));
            
            long end = System.currentTimeMillis();
            System.out.println("cost: " + (end - start));
        }
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minLength(String s) {
            StringBuilder str = new StringBuilder();
            List<Character> list = new ArrayList<>(s.length());
            for (char c : s.toCharArray()) {
                if (c == 'A' || c == 'C') {
                    list.add(c);
                    continue;
                }

                if (!list.isEmpty()) {
                    // 说明之前已经有A或者C
                    if (c == 'B' && list.get(list.size() - 1) == 'A') {
                        list.remove(list.size() - 1);
                        continue;
                    } else if (c == 'D' && list.get(list.size() - 1) == 'C') {
                        list.remove(list.size() - 1);
                        continue;
                    } else {
                        // 如果不符合上面两种情况 中间了别的字符 那么这部分肯定是去不掉的
                        str.append(listToStr(list));
                        list.clear();
                    }
                }

                str.append(c);
            }

            if (!list.isEmpty()) {
                str.append(listToStr(list));
            }

            return str.length();
        }

        public String listToStr(List<Character> list) {
            StringBuilder sb = new StringBuilder();
            for (Character c : list) {
                sb.append(c);
            }
            return sb.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class SolutionSimple {
        public int minLength(String s) {
            while (s.contains("AB") || s.contains("CD")) {
                s = s.replaceAll("AB|CD", "");
            }
            return s.length();
        }
    }

}











