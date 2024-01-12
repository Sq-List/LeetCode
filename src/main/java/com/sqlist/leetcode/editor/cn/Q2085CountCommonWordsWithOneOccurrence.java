package com.sqlist.leetcode.editor.cn;
//给你两个字符串数组 words1 和 words2 ，请你返回在两个字符串数组中 都恰好出现一次 的字符串的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing",
//"leetcode","is"]
//输出：2
//解释：
//- "leetcode" 在两个数组中都恰好出现一次，计入答案。
//- "amazing" 在两个数组中都恰好出现一次，计入答案。
//- "is" 在两个数组中都出现过，但在 words1 中出现了 2 次，不计入答案。
//- "as" 在 words1 中出现了一次，但是在 words2 中没有出现过，不计入答案。
//所以，有 2 个字符串在两个数组中都恰好出现了一次。
// 
//
// 示例 2： 
//
// 
//输入：words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
//输出：0
//解释：没有字符串在两个数组中都恰好出现一次。
// 
//
// 示例 3： 
//
// 
//输入：words1 = ["a","ab"], words2 = ["a","a","a","ab"]
//输出：1
//解释：唯一在两个数组中都出现一次的字符串是 "ab" 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words1.length, words2.length <= 1000 
// 1 <= words1[i].length, words2[j].length <= 30 
// words1[i] 和 words2[j] 都只包含小写英文字母。 
// 
//
// Related Topics 数组 哈希表 字符串 计数 👍 27 👎 0


import com.sqlist.leetcode.editor.cn.util.StringToUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Q2085CountCommonWordsWithOneOccurrence {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Class<?> clz = Class.forName("com.sqlist.leetcode.editor.cn.Q2085CountCommonWordsWithOneOccurrence");
        String file = Objects.requireNonNull(clz.getResource("/testcase/Q2085")).getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] splitArr = line.split("&");
            String[] word1 = splitArr[0].substring(1, splitArr[0].length() - 1).split(",");
            String[] word2 = splitArr[1].substring(1, splitArr[1].length() - 1).split(",");

            long start = System.currentTimeMillis();
            Solution solution = new Q2085CountCommonWordsWithOneOccurrence().new Solution();
            System.out.println(solution.countWords(word1, word2));
            
            long end = System.currentTimeMillis();
            System.out.println("cost: " + (end - start));
        }
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countWords(String[] words1, String[] words2) {
            Map<String, Integer> word1Map = new HashMap<>();
            Map<String, Integer> word2Map = new HashMap<>();
            for (String s : words1) {
                word1Map.put(s, word1Map.getOrDefault(s, 0) + 1);
            }
            for (String s : words2) {
                word2Map.put(s, word2Map.getOrDefault(s, 0) + 1);
            }

            int ans = 0;
            for (String s : word1Map.keySet()) {
                if (word1Map.get(s) == 1 && word2Map.getOrDefault(s, 0) == 1) {
                    ans += 1;
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}











