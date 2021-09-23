package com.sqlist.leetcode.editor.cn;

/**
给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。 

 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3ˣ 

 

 示例 1： 

 
输入：n = 27
输出：true
 

 示例 2： 

 
输入：n = 0
输出：false
 

 示例 3： 

 
输入：n = 9
输出：true
 

 示例 4： 

 
输入：n = 45
输出：false
 

 

 提示： 

 
 -2³¹ <= n <= 2³¹ - 1 
 

 

 进阶： 

 
 你能不使用循环或者递归来完成本题吗？ 
 
 Related Topics 递归 数学 👍 195 👎 0

*/

/**
 * [326]3的幂
 * @author SqList
 * @createTime 2021-09-23 10:31:56
 **/
public class Q326PowerOfThree {
    public static void main(String[] args) {
        Solution solution = new Q326PowerOfThree().new Solution();
        System.out.println(solution.isPowerOfThree(0));
        System.out.println(solution.isPowerOfThree(1));
        System.out.println(solution.isPowerOfThree(2));
        System.out.println(solution.isPowerOfThree(3));
        System.out.println(solution.isPowerOfThree(-3));
        System.out.println(solution.isPowerOfThree(-1));
        System.out.println(solution.isPowerOfThree(9));
        System.out.println(solution.isPowerOfThree(12));
        System.out.println(solution.isPowerOfThree(-2147483648));
        System.out.println(solution.isPowerOfThree(2147483647));
        System.out.println(solution.isPowerOfThree(2147483646));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 在题目给定的 32 位有符号整数的范围内，最大的 3 的幂为 3^19 = 1162261467
     * 我们只需要判断 n 是否是 3^19 的约数即可。
     */
    class Solution {
        public boolean isPowerOfThree(int n) {
            return n > 0 && 1162261467 % n == 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class SolutionIter {
        public boolean isPowerOfThree(int n) {
            if (n <= 0) {
                return false;
            }

            int i;
            for (i = n; i % 3 == 0; i /= 3) {}

            return i == 1;
        }
    }

}