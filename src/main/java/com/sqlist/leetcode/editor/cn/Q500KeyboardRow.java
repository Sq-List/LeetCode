package com.sqlist.leetcode.editor.cn;

/**
给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。 

 美式键盘 中： 

 
 第一行由字符 "qwertyuiop" 组成。 
 第二行由字符 "asdfghjkl" 组成。 
 第三行由字符 "zxcvbnm" 组成。 
 

 

 

 示例 1： 

 
输入：words = ["Hello","Alaska","Dad","Peace"]
输出：["Alaska","Dad"]
 

 示例 2： 

 
输入：words = ["omk"]
输出：[]
 

 示例 3： 

 
输入：words = ["adsdf","sfd"]
输出：["adsdf","sfd"]
 

 

 提示： 

 
 1 <= words.length <= 20 
 1 <= words[i].length <= 100 
 words[i] 由英文字母（小写和大写字母）组成 
 
 Related Topics 数组 哈希表 字符串 👍 183 👎 0

*/

import java.util.*;

/**
 * [500]键盘行
 * @author SqList
 * @createTime 2021-10-31 22:58:12
 **/
public class Q500KeyboardRow {
    public static void main(String[] args) {
        Solution solution = new Q500KeyboardRow().new Solution();
        System.out.println(Arrays.toString(solution.findWords(new String[]{"Hello","Alaska","Dad","Peace"})));
        System.out.println(Arrays.toString(solution.findWords(new String[]{"omk"})));
        System.out.println(Arrays.toString(solution.findWords(new String[]{"adsdf","sfd"})));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String[] findWords(String[] words) {
            String[] lines = new String[]{
                    "qwertyuiop",
                    "asdfghjkl",
                    "zxcvbnm"
            };

            Map<Character, Integer> charToLineMap = new HashMap<>(5, 1);
            for (int i = 0; i < 3; i++) {
                String line = lines[i];
                for (int j = 0; j < line.length(); j++) {
                    charToLineMap.put(line.charAt(j), i);
                }
            }

            List<String> ansList = new ArrayList<>(words.length + 1);
            for (String word : words) {
                int j = 1;
                String tmpWord = word.toLowerCase();
                int preIndex = charToLineMap.get(tmpWord.charAt(0));
                for (; j < word.length(); j++) {
                    Integer index = charToLineMap.get(tmpWord.charAt(j));
                    if (preIndex != index) {
                        break;
                    }
                }

                if (j == word.length()) {
                    ansList.add(word);
                }
            }

            return ansList.toArray(new String[0]);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}