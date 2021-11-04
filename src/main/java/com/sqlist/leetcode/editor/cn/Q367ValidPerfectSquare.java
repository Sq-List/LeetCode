package com.sqlist.leetcode.editor.cn;

/**
给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。 

 进阶：不要 使用任何内置的库函数，如 sqrt 。 

 

 示例 1： 

 
输入：num = 16
输出：true
 

 示例 2： 

 
输入：num = 14
输出：false
 

 

 提示： 

 
 1 <= num <= 2^31 - 1 
 
 Related Topics 数学 二分查找 👍 251 👎 0

*/

/**
 * [367]有效的完全平方数
 * @author SqList
 * @createTime 2021-11-04 00:41:41
 **/
public class Q367ValidPerfectSquare {
    public static void main(String[] args) {
        Solution solution = new Q367ValidPerfectSquare().new Solution();
        System.out.println(solution.isPerfectSquare(16));
        System.out.println(solution.isPerfectSquare(14));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 二分
     */
    class Solution {
        public boolean isPerfectSquare(int num) {
            int l = 1, r = 46340;
            while (l <= r) {
                int mid = (l + r) >> 1;
                if (mid * mid == num) {
                    return true;
                } else if (mid * mid > num) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}