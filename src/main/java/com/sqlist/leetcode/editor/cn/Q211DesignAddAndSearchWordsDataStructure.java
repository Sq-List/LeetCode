package com.sqlist.leetcode.editor.cn;

/**
请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。 

 实现词典类 WordDictionary ： 

 
 WordDictionary() 初始化词典对象 
 void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配 
 bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回 false 。word 中可能包含一些 '.
' ，每个 . 都可以表示任何一个字母。 
 

 

 示例： 

 
输入：
["WordDictionary","addWord","addWord","addWord","search","search","search",
"search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
输出：
[null,null,null,null,false,true,true,true]

解释：
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
 

 

 提示： 

 
 1 <= word.length <= 500 
 addWord 中的 word 由小写英文字母组成 
 search 中的 word 由 '.' 或小写英文字母组成 
 最多调用 50000 次 addWord 和 search 
 
 Related Topics 深度优先搜索 设计 字典树 字符串 👍 303 👎 0

*/

import java.util.HashMap;
import java.util.Map;

/**
 * [211]添加与搜索单词 - 数据结构设计
 * @author SqList
 * @createTime 2021-10-19 09:47:43
 **/
public class Q211DesignAddAndSearchWordsDataStructure {
    public static void main(String[] args) {
        WordDictionary solution = new Q211DesignAddAndSearchWordsDataStructure().new WordDictionary();
        solution.addWord("apple");
        solution.addWord("mad");
        System.out.println(solution.search("..."));
        System.out.println(solution.search("a.p.e"));
        System.out.println(solution.search("a.p.e."));
        System.out.println(solution.search("a.pe."));
        solution.addWord("a");
        System.out.println(solution.search("."));
        System.out.println(solution.search("a"));
        System.out.println(solution.search("a."));
        System.out.println(solution.search(".."));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 字典树+dfs
     */
    class WordDictionary {

        class TrieNode {
            private boolean end;
            private Character letter;
            private String prefix;
            private Map<Character, TrieNode> suffixMap;

            public TrieNode(boolean end, Character letter, String prefix, Map<Character, TrieNode> suffixMap) {
                this.end = end;
                this.letter = letter;
                this.prefix = prefix;
                this.suffixMap = suffixMap;
            }
        }

        private TrieNode root;

        public WordDictionary() {
            root = new TrieNode(false, null, null, new HashMap<>());
        }

        public void addWord(String word) {
            TrieNode head = root;
            StringBuilder prefix = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                boolean end = i == (word.length() - 1);

                char c = word.charAt(i);
                prefix.append(c);
                if (!head.suffixMap.containsKey(c)) {
                    TrieNode temp = new TrieNode(end, c, prefix.toString(), new HashMap<>());
                    head.suffixMap.put(c, temp);
                } else if (!head.suffixMap.get(c).end) {
                    // 如果已经有以当前prefix为结尾的单词话 不能够更改end值
                    head.suffixMap.get(c).end = end;
                }

                head = head.suffixMap.get(c);
            }
        }

        public boolean search(String word) {
            TrieNode head = root;

            return dfs(head, word, 0);
        }

        private boolean dfs(TrieNode root, String word, int i) {
            if (word.length() == i) {
                return root.end;
            }

            char curr = word.charAt(i);
            if (curr == '.') {
                for (TrieNode child : root.suffixMap.values()) {
                    boolean dfs = dfs(child, word, i + 1);
                    if (dfs) {
                        return true;
                    }
                }
                return false;
            } else {
                if (!root.suffixMap.containsKey(curr)) {
                    return false;
                }
                return dfs(root.suffixMap.get(curr), word, i + 1);
            }
        }
    }

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}