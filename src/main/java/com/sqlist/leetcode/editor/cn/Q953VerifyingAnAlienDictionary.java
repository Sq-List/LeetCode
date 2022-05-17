package com.sqlist.leetcode.editor.cn;

/**
某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。 

 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回false。

 

 示例 1： 

 
输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
输出：true
解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。 

 示例 2： 

 
输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
输出：false
解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。 

 示例 3： 

 
输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
输出：false
解释：当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' >'∅'，其中'∅' 是空白字符，定义为比任何其他字符都小（更多信息）。
 

 

 提示： 

 
 1 <= words.length <= 100 
 1 <= words[i].length <= 20 
 order.length == 26 
 在 words[i] 和 order 中的所有字符都是英文小写字母。 
 
 Related Topics 数组 哈希表 字符串 👍 163 👎 0
*/


import java.util.HashMap;
import java.util.Map;

/**
 * [953]验证外星语词典
 * @author SqList
 * @createTime 2022-05-17 11:23:37
 **/
public class Q953VerifyingAnAlienDictionary {
    public static void main(String[] args) {
        Solution solution = new Q953VerifyingAnAlienDictionary().new Solution();
        System.out.println(solution.isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        System.out.println(solution.isAlienSorted(new String[]{"word","world","row"}, "worldabcefghijkmnpqstuvxyz"));
        System.out.println(solution.isAlienSorted(new String[]{"apple","app"}, "abcdefghijklmnopqrstuvwxyz"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isAlienSorted(String[] words, String order) {
            Map<Character, Integer> orderMap = new HashMap<>((int) (order.length() / 0.75 + 1));
            for (int i = 0; i < order.toCharArray().length; i++) {
                orderMap.put(order.charAt(i), i);
            }

            for (int i = 0; i < words.length - 1; i++) {
                String headWord = words[i];
                String afterWord = words[i + 1];

                int j = 0;
                for (; j < headWord.toCharArray().length && j < afterWord.toCharArray().length; j++) {
                    int compare = compare(orderMap, headWord.charAt(j), afterWord.charAt(j));
                    if (compare == 1) {
                        break;
                    } else if (compare == -1) {
                        return false;
                    }
                }

                if (j == headWord.length() || j == afterWord.length()) {
                    if (headWord.length() > afterWord.length()) {
                        return false;
                    }
                }
            }

            return true;
        }

        private int compare(Map<Character, Integer> orderMap, char headChar, char afterChar) {
            if (orderMap.get(headChar).equals(orderMap.get(afterChar))) {
                return 0;
            }

            return orderMap.get(headChar) < orderMap.get(afterChar) ? 1 : -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}