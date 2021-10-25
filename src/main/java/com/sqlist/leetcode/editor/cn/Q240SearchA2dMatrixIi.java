package com.sqlist.leetcode.editor.cn;

/**
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性： 

 
 每行的元素从左到右升序排列。 
 每列的元素从上到下升序排列。 
 

 

 示例 1： 

 
输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,2
3,26,30]], target = 5
输出：true
 

 示例 2： 

 
输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,2
3,26,30]], target = 20
输出：false
 

 

 提示： 

 
 m == matrix.length 
 n == matrix[i].length 
 1 <= n, m <= 300 
 -10⁹ <= matrix[i][j] <= 10⁹ 
 每行的所有元素从左到右升序排列 
 每列的所有元素从上到下升序排列 
 -10⁹ <= target <= 10⁹ 
 
 Related Topics 数组 二分查找 分治 矩阵 👍 755 👎 0

*/

/**
 * [240]搜索二维矩阵 II
 * @author SqList
 * @createTime 2021-10-25 01:08:19
 **/
public class Q240SearchA2dMatrixIi {
    public static void main(String[] args) {
        Solution solution = new Q240SearchA2dMatrixIi().new Solution();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Z 字形查找
     */
    class Solution {
        public boolean searchMatrix(int[][] matrixs, int target) {
            int i = 0, j = matrixs[0].length - 1;
            while (i < matrixs.length && j >= 0) {
                if (target == matrixs[i][j]) {
                    return true;
                } else if (target > matrixs[i][j]) {
                    i ++;
                } else {
                    j--;
                }
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 遍历 + 二分
     */
    class SolutionIterBinarySearch {
        public boolean searchMatrix(int[][] matrixs, int target) {
            for (int[] matrix : matrixs) {
                boolean res = binarySearch(matrix, target);
                if (res) {
                    return true;
                }
            }
            return false;
        }

        private boolean binarySearch(int[] arr, int target) {
            int l = 0, r = arr.length - 1;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (arr[mid] == target) {
                    return true;
                } else if (arr[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            return false;
        }
    }

    /**
     * 遍历
     */
    class SolutionIter {
        public boolean searchMatrix(int[][] matrixs, int target) {
            for (int[] matrix : matrixs) {
                for (int i : matrix) {
                    if (i == target) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

}