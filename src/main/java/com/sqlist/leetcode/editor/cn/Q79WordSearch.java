package com.sqlist.leetcode.editor.cn;

/**
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。 

 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 

 

 示例 1： 

 
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
"ABCCED"
输出：true
 

 示例 2： 

 
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
"SEE"
输出：true
 

 示例 3： 

 
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
"ABCB"
输出：false
 

 

 提示： 

 
 m == board.length 
 n = board[i].length 
 1 <= m, n <= 6 
 1 <= word.length <= 15 
 board 和 word 仅由大小写英文字母组成 
 

 

 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？ 
 Related Topics 数组 回溯 矩阵 👍 1028 👎 0

*/

/**
 * [79]单词搜索
 * @author SqList
 * @createTime 2021-09-16 12:40:00
 **/
public class Q79WordSearch {
    public static void main(String[] args) {
        Solution solution = new Q79WordSearch().new Solution();
        System.out.println(solution.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCCED"));
        System.out.println(solution.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "SEE"));
        System.out.println(solution.exist(new char[][]{{
            'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        }, "ABCB"));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 标准步骤
     */
    class Solution {
        public boolean exist(char[][] board, String word) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        boolean res = dfs(board, new boolean[board.length][board[0].length], word, i, j, 0);
                        if (res) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public boolean dfs(char[][] board, boolean[][] vis, String word, int i, int j, int k) {
            // 判断是否相等
            if (board[i][j] != word.charAt(k)) {
                return false;
            } else if (k == word.length() - 1) {
                return true;
            }

            // 计算下一次的位置
            vis[i][j] = true;
            int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            boolean res = false;
            for (int[] dis : dirs) {
                int iNew = i + dis[0];
                int jNew = j + dis[1];
                if (iNew >= 0 && iNew < board.length && jNew >= 0 && jNew < board[0].length) {
                    if (!vis[iNew][jNew]) {
                        boolean flag = dfs(board, vis, word, iNew, jNew, k + 1);
                        if (flag) {
                            res = true;
                            break;
                        }
                    }
                }
            }

            vis[i][j] = false;
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class SolutionSelf {
        public boolean exist(char[][] board, String word) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        boolean res = dfs(board, new boolean[board.length][board[0].length], word, i, j, 0);
                        if (res) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public boolean dfs(char[][] board, boolean[][] vis, String word, int i, int j, int k) {
            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
                return false;
            }

            if (vis[i][j] || word.charAt(k) != board[i][j]) {
                return false;
            }

            if (k == word.length() - 1) {
                return true;
            }

            vis[i][j] = true;
            boolean flag = dfs(board, vis, word, i + 1, j, k + 1)
                    || dfs(board, vis, word, i - 1, j, k + 1)
                    || dfs(board, vis, word, i, j + 1, k + 1)
                    || dfs(board, vis, word, i, j - 1, k + 1);

            if (!flag) {
                vis[i][j] = false;
            }

            return flag;
        }
    }
}