package com.sqlist.leetcode.editor.cn;

/**
给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。 

 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 

 

 示例 1： 

 
输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l",
"v"]], words = ["oath","pea","eat","rain"]
输出：["eat","oath"]
 

 示例 2： 

 
输入：board = [["a","b"],["c","d"]], words = ["abcb"]
输出：[]
 

 

 提示： 

 
 m == board.length 
 n == board[i].length 
 1 <= m, n <= 12 
 board[i][j] 是一个小写英文字母 
 1 <= words.length <= 3 * 10⁴ 
 1 <= words[i].length <= 10 
 words[i] 由小写英文字母组成 
 words 中的所有字符串互不相同 
 
 Related Topics 字典树 数组 字符串 回溯 矩阵 👍 493 👎 0

*/

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [212]单词搜索 II
 * @author SqList
 * @createTime 2021-09-16 15:50:05
 **/
public class Q212WordSearchIi {
    public static void main(String[] args) {
        Solution solution = new Q212WordSearchIi().new Solution();
        System.out.println(JSON.toJSONString(solution.findWords(new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        }, new String[]{"eat", "oath"})));
        System.out.println(JSON.toJSONString(solution.findWords(new char[][]{
                {'a', 'b'},
                {'c', 'd'}
        }, new String[]{"abcb", "oath"})));
        System.out.println(JSON.toJSONString(solution.findWords(new char[][]{
                {'o', 'a', 'b', 'n'},
                {'o', 't', 'a', 'e'},
                {'a', 'h', 'k', 'r'},
                {'a', 'f', 'l', 'v'}
        }, new String[]{"oa", "oaa"})));
        System.out.println(JSON.toJSONString(solution.findWords(new char[][]{
                {'a', 'b'},
                {'a', 'a'}
        }, new String[]{"aba","baa","bab","aaab","aaa","aaaa","aaba"})));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 字典树+dfs+回溯
     */
    class Solution {
        class Node {
            private boolean end;
            private Character letter;
            private String prefix;
            private Map<Character, Node> suffixMap;

            public Node(boolean end, Character letter, String prefix, Map<Character, Node> suffixMap) {
                this.end = end;
                this.letter = letter;
                this.prefix = prefix;
                this.suffixMap = suffixMap;
            }
        }

        public List<String> findWords(char[][] board, String[] words) {
            Node root = new Node(false, null, null, new HashMap<>());
            // 建立字典树
            for (String word : words) {
                Node head = root;
                StringBuilder prefix = new StringBuilder();
                for (int i = 0; i < word.length(); i++) {
                    boolean end = i == (word.length() - 1);

                    char c = word.charAt(i);
                    prefix.append(c);
                    if (!head.suffixMap.containsKey(c)) {
                        Node temp = new Node(end, c, prefix.toString(), new HashMap<>());
                        head.suffixMap.put(c, temp);
                    } else if (!head.suffixMap.get(c).end) {
                        // 如果已经有以当前prefix为结尾的单词话 不能够更改end值
                        head.suffixMap.get(c).end = end;
                    }

                    head = head.suffixMap.get(c);
                }
            }

            List<String> resList = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (root.suffixMap.containsKey(board[i][j])) {
                        dfs(resList, board, new boolean[board.length][board[0].length], root.suffixMap.get(board[i][j]), i, j);
                    }
                }
            }

            return resList;
        }

        public void dfs(List<String> resList, char[][] board, boolean[][] vis, Node head, int i, int j) {
            // 判断是否是最后的字字母
            if (head.end) {
                resList.add(head.prefix);
                head.end = false;
            }
            if (head.suffixMap.isEmpty()) {
                return;
            }

            // 计算下一次的位置
            vis[i][j] = true;
            int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] dis : dirs) {
                int iNew = i + dis[0];
                int jNew = j + dis[1];
                if (iNew >= 0 && iNew < board.length && jNew >= 0 && jNew < board[0].length) {
                    if (!vis[iNew][jNew] && head.suffixMap.containsKey(board[iNew][jNew])) {
                        dfs(resList, board, vis, head.suffixMap.get(board[iNew][jNew]), iNew, jNew);
                    }
                }
            }

            vis[i][j] = false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}