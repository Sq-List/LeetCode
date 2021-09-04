package com.sqlist.leetcode.editor.cn;

/**
写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下： 

 
F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1. 

 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。 

 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。 

 

 示例 1： 

 
输入：n = 2
输出：1
 

 示例 2： 

 
输入：n = 5
输出：5
 

 

 提示： 

 
 0 <= n <= 100 
 
 Related Topics 记忆化搜索 数学 动态规划 👍 202 👎 0

*/

/**
 * [剑指 Offer 10- I]斐波那契数列
 * @author SqList
 * @createTime 2021-09-04 10:27:04
 **/
public class QOffer10IFeiBoNaQiShuLieLcof {
    public static void main(String[] args) {
        Solution solution = new QOffer10IFeiBoNaQiShuLieLcof().new Solution();
        System.out.println(solution.fib(0));
        System.out.println(solution.fib(1));
        System.out.println(solution.fib(2));
        System.out.println(solution.fib(3));
        System.out.println(solution.fib(4));
        System.out.println(solution.fib(5));
        System.out.println(solution.fib(10));
        System.out.println(solution.fib(20));
        System.out.println(solution.fib(30));
        System.out.println(solution.fib(40));
        System.out.println(solution.fib(50));
        System.out.println(solution.fib(60));
        System.out.println(solution.fib(70));
        System.out.println(solution.fib(80));
        System.out.println(solution.fib(90));
        System.out.println(solution.fib(100));
    }

    /**
     * 递归
     */
    class SolutionRecursion {

        int[] res;

        public int fib(int n) {
            res = new int[101];
            return f(n);
        }

        private int f(int n) {
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }

            if (res[n] != 0) {
                return res[n];
            }

            res[n] = (f(n - 1) + f(n - 2)) % 1000000007;
            // 933882264
            // 1000000007
            return res[n];
        }
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 迭代 Iteration
     */
    class Solution {

        public int fib(int n) {
            if (n == 0 || n == 1) {
                return n;
            }

            int a = 0, b = 1, sum = 0;
            for (int i = 2; i <= n; i++) {
                sum = (a + b) % 1000000007;
                a = b;
                b = sum;
            }

            return sum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}