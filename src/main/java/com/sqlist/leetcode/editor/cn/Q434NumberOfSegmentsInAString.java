package com.sqlist.leetcode.editor.cn;

/**
统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。 

 请注意，你可以假定字符串里不包括任何不可打印的字符。 

 示例: 

 输入: "Hello, my name is John"
输出: 5
解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
 
 Related Topics 字符串 👍 143 👎 0

*/

/**
 * [434]字符串中的单词数
 * @author SqList
 * @createTime 2021-10-07 21:04:45
 **/
public class Q434NumberOfSegmentsInAString {
    public static void main(String[] args) {
        Solution solution = new Q434NumberOfSegmentsInAString().new Solution();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countSegments(String s) {
            char[] sChars = s.toCharArray();
            if (sChars.length == 0) {
                return 0;
            }

            boolean hasWord = false;

            int count = 0;
            for (char c : sChars) {
                if (c == ' ' && hasWord) {
                    hasWord = false;
                    count++;
                } else if (c != ' ') {
                    hasWord = true;
                }
            }

            if (sChars[sChars.length - 1] != ' ' && hasWord) {
                count ++;
            }

            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}