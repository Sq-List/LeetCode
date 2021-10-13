package com.sqlist.leetcode.editor.cn;

/**
你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。 

 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。 

 

 示例 1： 

 
输入：n = 5
输出：2
解释：因为第三行不完整，所以返回 2 。
 

 示例 2： 

 
输入：n = 8
输出：3
解释：因为第四行不完整，所以返回 3 。
 

 

 提示： 

 
 1 <= n <= 2³¹ - 1 
 
 Related Topics 数学 二分查找 👍 149 👎 0

*/

/**
 * [441]排列硬币
 * @author SqList
 * @createTime 2021-10-10 11:03:15
 **/
public class Q441ArrangingCoins {
    public static void main(String[] args) {
        Solution solution = new Q441ArrangingCoins().new Solution();
        System.out.println(solution.arrangeCoins(0));
        System.out.println(solution.arrangeCoins(1));
        System.out.println(solution.arrangeCoins(2));
        System.out.println(solution.arrangeCoins(3));
        System.out.println(solution.arrangeCoins(4));
        System.out.println(solution.arrangeCoins(5));
        System.out.println(solution.arrangeCoins(8));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 利用 求和公式 （首项 + 末项）*项数/2
     * 可得 (1 + i) * i / 2
     * 再由题意可得  (1 + i) * i / 2 >= n
     * 即 (1 + i) * i / 2 - n >= 0  =>  i * i / 2 + i / 2 - n >= 0
     * 解得 最大的整数
     */
    class Solution {
        public int arrangeCoins(int n) {
            double a = 0.5, b = 0.5, c = -n;
            return (int)((-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a));
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}