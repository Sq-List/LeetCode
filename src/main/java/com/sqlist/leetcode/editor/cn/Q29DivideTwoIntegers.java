package com.sqlist.leetcode.editor.cn;

/**
给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。 

 返回被除数 dividend 除以除数 divisor 得到的商。 

 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2 

 

 示例 1: 

 输入: dividend = 10, divisor = 3
输出: 3
解释: 10/3 = truncate(3.33333..) = truncate(3) = 3 

 示例 2: 

 输入: dividend = 7, divisor = -3
输出: -2
解释: 7/-3 = truncate(-2.33333..) = -2 

 

 提示： 

 
 被除数和除数均为 32 位有符号整数。 
 除数不为 0。 
 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2³¹, 2³¹ − 1]。本题中，如果除法结果溢出，则返回 2³¹ − 1。
 
 Related Topics 位运算 数学 👍 687 👎 0

*/

/**
 * [29]两数相除
 * @author SqList
 * @createTime 2021-10-12 09:56:25
 **/
public class Q29DivideTwoIntegers {
    public static void main(String[] args) {
        Solution solution = new Q29DivideTwoIntegers().new Solution();
        // System.out.println(solution.divide(10, 3));
        // System.out.println(solution.divide(10, -3));
        // System.out.println(solution.divide(7, -3));
        // System.out.println(solution.divide(10, -2));
        // System.out.println(solution.divide(10, 2));
        // System.out.println(solution.divide(0, 2));
        // System.out.println(solution.divide(-2147483648, -1));
        // System.out.println(solution.divide(-2147483648, -2147483648));
        // System.out.println(solution.divide(-2147483647, 1));
        // System.out.println(solution.divide(-2147483647, -1));
        // System.out.println(solution.divide(-2147483648, -2));
        // System.out.println(solution.divide(-2147483648, 2));
        System.out.println(solution.divide(-2147483648, 1073741823));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 数学 - 利用二进制除法
     * 因为 二进制 只有 0 1 两种情况  所以在模拟二进制除法时 只需要判断当前状态的被除数与除数的大小即可
     *
     * 如果我们将被除数和除数都变为正数，那么可能会导致溢出。例如当被除数为 −2³¹ 时，它的相反数 2³¹ 产生了溢出。
     * 因此，我们可以考虑将被除数和除数都变为负数，这样就不会有溢出的问题，在编码时只需要考虑 1 种情况了。
     */
    class Solution {
        public int divide(int dividend, int divisor) {
            if (dividend == Integer.MIN_VALUE) {
                if (divisor == 1) {
                    return Integer.MIN_VALUE;
                }
                if (divisor == -1) {
                    return Integer.MAX_VALUE;
                }
            }
            // 考虑除数为最小值的情况
            if (divisor == Integer.MIN_VALUE) {
                return dividend == Integer.MIN_VALUE ? 1 : 0;
            }
            // 考虑被除数为 0 的情况
            if (dividend == 0) {
                return 0;
            }

            boolean flag = false;
            int a = dividend;
            int b = divisor;
            if (a > 0) {
                a = -a;
                flag = true;
            }
            if (b > 0) {
                b = -b;
                flag = !flag;
            }

            // 此时 a b 都为负数
            if (b < a) {
                return 0;
            }

            int tmp = b;
            int index = 0;
            while (a < tmp) {
                if (tmp < -1073741824) {
                    // -1073741824 为 -2^30
                    // -1073741825 为 -2^30 - 1    再乘以2  会溢出
                    break;
                }

                tmp <<= 1;
                index ++;
            }

            int ans = 0;
            while (index >= 0) {
                if (tmp >= a) {
                    a -= tmp;
                    ans |= (1L << index);
                }

                tmp >>= 1;
                index --;
            }

            return flag ? -ans : ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}