package com.sqlist.leetcode.editor.cn;

/**
所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列
有时会对研究非常有帮助。 

 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。 

 

 示例 1： 

 
输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
输出：["AAAAACCCCC","CCCCCAAAAA"]
 

 示例 2： 

 
输入：s = "AAAAAAAAAAAAA"
输出：["AAAAAAAAAA"]
 

 

 提示： 

 
 0 <= s.length <= 10⁵ 
 s[i] 为 'A'、'C'、'G' 或 'T' 
 
 Related Topics 位运算 哈希表 字符串 滑动窗口 哈希函数 滚动哈希 👍 235 👎 0

*/

import java.util.*;
import java.util.stream.Collectors;

/**
 * [187]重复的DNA序列
 * @author SqList
 * @createTime 2021-10-08 10:38:41
 **/
public class Q187RepeatedDnaSequences {
    public static void main(String[] args) {
        Solution solution = new Q187RepeatedDnaSequences().new Solution();
        System.out.println(solution.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(solution.findRepeatedDnaSequences("AAAAAAAAAAAAA"));
        System.out.println(solution.findRepeatedDnaSequences("AAAAAAAAAA"));
        System.out.println(solution.findRepeatedDnaSequences("AAAAAAAAAAA"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> findRepeatedDnaSequences(String s) {
            int targetLength = 10;
            if (s.length() <= targetLength) {
                return Collections.emptyList();
            }

            List<String> ans = new ArrayList<>();
            Map<String, Integer> strCountMap = new HashMap<>(s.length() - targetLength + 1, 1);
            for (int i = 0; i < s.length() - targetLength + 1; i++) {
                String substring = s.substring(i, i + targetLength);
                strCountMap.put(substring, strCountMap.getOrDefault(substring, 0) + 1);
                if (strCountMap.get(substring) == 2) {
                    ans.add(substring);
                }
            }

            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}