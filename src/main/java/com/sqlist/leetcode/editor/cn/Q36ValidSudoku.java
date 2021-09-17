package com.sqlist.leetcode.editor.cn;

/**
请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。 

 
 数字 1-9 在每一行只能出现一次。 
 数字 1-9 在每一列只能出现一次。 
 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
 

 数独部分空格内已填入了数字，空白格用 '.' 表示。 

 注意： 

 
 一个有效的数独（部分已被填充）不一定是可解的。 
 只需要根据以上规则，验证已经填入的数字是否有效即可。 
 

 

 示例 1： 

 
输入：board = 
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
输出：true
 

 示例 2： 

 
输入：board = 
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
输出：false
解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的
。 

 

 提示： 

 
 board.length == 9 
 board[i].length == 9 
 board[i][j] 是一位数字或者 '.' 
 
 Related Topics 数组 哈希表 矩阵 👍 586 👎 0

*/

/**
 * [36]有效的数独
 * @author SqList
 * @createTime 2021-09-17 01:00:38
 **/
public class Q36ValidSudoku {
    public static void main(String[] args) {
        Solution solution = new Q36ValidSudoku().new Solution();
        System.out.println(solution.isValidSudoku(new char[][]{
                {'5','3','.','.','7','.','.','.','.'}
                ,{'6','.','.','1','9','5','.','.','.'}
                ,{'.','9','8','.','.','.','.','6','.'}
                ,{'8','.','.','.','6','.','.','.','3'}
                ,{'4','.','.','8','.','3','.','.','1'}
                ,{'7','.','.','.','2','.','.','.','6'}
                ,{'.','6','.','.','.','.','2','8','.'}
                ,{'.','.','.','4','1','9','.','.','5'}
                ,{'.','.','.','.','8','.','.','7','9'}
        }));

        System.out.println(solution.isValidSudoku(new char[][]{
                {'8','3','.','.','7','.','.','.','.'}
                ,{'6','.','.','1','9','5','.','.','.'}
                ,{'.','9','8','.','.','.','.','6','.'}
                ,{'8','.','.','.','6','.','.','.','3'}
                ,{'4','.','.','8','.','3','.','.','1'}
                ,{'7','.','.','.','2','.','.','.','6'}
                ,{'.','6','.','.','.','.','2','8','.'}
                ,{'.','.','.','4','1','9','.','.','5'}
                ,{'.','.','.','.','8','.','.','7','9'}
        }));

        System.out.println(solution.isValidSudoku(new char[][]{
                {'7','.','.','.','4','.','.','.','.'},
                {'.','.','.','8','6','5','.','.','.'},
                {'.','1','.','2','.','.','.','.','.'},
                {'.','.','.','.','.','9','.','.','.'},
                {'.','.','.','.','5','.','5','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','2','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
        }));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 一次遍历 + 状态压缩
     */
    class Solution {
        public boolean isValidSudoku(char[][] board) {
            short[] col = new short[9];
            short[] row = new short[9];
            short[] square = new short[9];

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] != '.') {
                        int num = board[i][j] - '1';
                        int squareNum = (i / 3) * 3 + j / 3;
                        if (((col[j] >> num) & 1) == 1
                            || ((row[i] >> num) & 1) == 1
                            || ((square[squareNum] >> num) & 1) == 1) {
                            return false;
                        } else {
                            col[j] |= (1 << num);
                            row[i] |= (1 << num);
                            square[squareNum] |= (1 << num);
                        }
                    }
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 一次遍历
     */
    class SolutionOneIter {
        public boolean isValidSudoku(char[][] board) {
            boolean[][] col = new boolean[9][9];
            boolean[][] row = new boolean[9][9];
            boolean[][] square = new boolean[9][9];

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] != '.') {
                        int num = board[i][j] - '1';
                        if (col[j][num]) {
                            return false;
                        } else {
                            col[j][num] = true;
                        }

                        if (row[i][num]) {
                            return false;
                        } else {
                            row[i][num] = true;
                        }

                        int squareNum = (i / 3) * 3 + j / 3;
                        if (square[squareNum][num]) {
                            return false;
                        } else {
                            square[squareNum][num] = true;
                        }
                    }
                }
            }
            return true;
        }
    }

    class SolutionModeIter {
        public boolean isValidSudoku(char[][] board) {
            // 遍历9个正方形
            int[][] centerPoints = new int[][]{
                    {1, 1}, {1, 4}, {1, 7},
                    {4, 1}, {4, 4}, {4, 7},
                    {7, 1}, {7, 4}, {7, 7},
            };
            int[][] dirs = new int[][]{
                    {-1, -1}, {-1, 0}, {-1, 1},
                    {0, -1}, {0, 0}, {0, 1},
                    {1, -1}, {1, 0}, {1, 1},
            };

            for (int[] centerPoint : centerPoints) {
                boolean[] vis = new boolean[10];
                for (int[] dir : dirs) {
                    int nextI = centerPoint[0] + dir[0];
                    int nextJ = centerPoint[1] + dir[1];
                    if (board[nextI][nextJ] != '.') {
                        if (!vis[board[nextI][nextJ] - '0']) {
                            vis[board[nextI][nextJ] - '0'] = true;
                        } else {
                            return false;
                        }
                    }
                }
            }

            // 遍历9行
            for (int i = 0; i < board.length; i++) {
                boolean[] vis = new boolean[10];
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] != '.') {
                        if (!vis[board[i][j] - '0']) {
                            vis[board[i][j] - '0'] = true;
                        } else {
                            return false;
                        }
                    }
                }
            }

            // 遍历9列
            for (int j = 0; j < board[0].length; j++) {
                boolean[] vis = new boolean[10];
                for (int i = 0; i < board.length; i++) {
                    if (board[i][j] != '.') {
                        if (!vis[board[i][j] - '0']) {
                            vis[board[i][j] - '0'] = true;
                        } else {
                            return false;
                        }
                    }
                }
            }

            return true;
        }
    }
}