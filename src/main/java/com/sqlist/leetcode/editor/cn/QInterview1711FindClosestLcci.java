package com.sqlist.leetcode.editor.cn;

/**
有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同
，你能对此优化吗? 

 示例： 

 
输入：words = ["I","am","a","student","from","a","university","in","a","city"], 
word1 = "a", word2 = "student"
输出：1 

 提示： 

 
 words.length <= 100000 
 
 Related Topics 数组 字符串 👍 67 👎 0

*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [面试题 17.11]单词距离
 * @author SqList
 * @createTime 2022-05-27 15:12:01
 **/
public class QInterview1711FindClosestLcci {
    public static void main(String[] args) {
        Solution solution = new QInterview1711FindClosestLcci().new Solution();
        System.out.println(solution.findClosest(new String[]{"I", "am", "a", "student", "from", "a", "university", "in", "a", "city"}, "from", "a"));
        System.out.println(solution.findClosest(new String[]{"I", "am", "a", "student", "from", "a", "university", "in", "a", "city"}, "I", "a"));
        System.out.println(solution.findClosest(new String[]{"I", "am", "a", "student", "from", "a", "university", "in", "a", "city"}, "city", "a"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 双指针
     */
    class Solution {

        private Map<String, List<Integer>> word2IndexMap = new HashMap<>();

        public int findClosest(String[] words, String word1, String word2) {
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (!word2IndexMap.containsKey(word)) {
                    word2IndexMap.put(word, new ArrayList<>());
                }
                word2IndexMap.get(word).add(i);
            }

            List<Integer> indexList1 = word2IndexMap.getOrDefault(word1, new ArrayList<>());
            List<Integer> indexList2 = word2IndexMap.getOrDefault(word2, new ArrayList<>());

            int p1 = 0, p2 = 0;
            int ans = Integer.MAX_VALUE;
            while (p1 < indexList1.size() && p2 < indexList2.size()) {
                if (indexList1.get(p1) > indexList2.get(p2)) {
                    while (++p2 < indexList2.size() && indexList1.get(p1) > indexList2.get(p2)) {}
                    ans = Math.min(ans, Math.abs(indexList1.get(p1) - indexList2.get(p2 - 1)));
                } else {
                    while (++p1 < indexList1.size() && indexList1.get(p1) < indexList2.get(p2)) {}
                    ans = Math.min(ans, Math.abs(indexList1.get(p1 - 1) - indexList2.get(p2)));
                }
            }

            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class SolutionOld {

        public int findClosest(String[] words, String word1, String word2) {
            int index1 = -1;
            int index2 = -1;

            boolean change = false;
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < words.length; i++) {
                if (word1.equals(words[i])) {
                    change = true;
                    index1 = i;
                }
                if (word2.equals(words[i])) {
                    change = true;
                    index2 = i;
                }

                if (change && index1 != -1 && index2 != -1) {
                    change = false;
                    ans = Math.min(ans, Math.abs(index1 - index2));
                }
            }

            return ans;
        }
    }

}