package com.sqlist.leetcode.editor.cn;

/**
 给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。



 示例 1： 


 输入：a = 1, b = 2
 输出：3


 示例 2： 


 输入：a = 2, b = 3
 输出：5




 提示： 


 -1000 <= a, b <= 1000 

 Related Topics 位运算 数学 👍 506 👎 0

 */

/**
 * [371]两整数之和
 * @author SqList
 * @createTime 2021-09-26 14:54:04
 **/
public class Q371SumOfTwoIntegers {
    public static void main(String[] args) {
        Solution solution = new Q371SumOfTwoIntegers().new Solution();
        // System.out.println((2 ^ 3) | ((2 & 3) << 1));
        // System.out.println((2 ^ (-3)) | ((2 & (-3)) << 1));
        // System.out.println((10 ^ (-1)) | ((10 & (-1)) << 1));
        // System.out.println(Integer.toBinaryString(10));
        // System.out.println(Integer.toBinaryString(-1));
        // System.out.println(Integer.toBinaryString((0) & 5));
        // System.out.println(Integer.toBinaryString(0 << 1));
        // System.out.println(-1 << 1);
        System.out.println(solution.getSum(1, 2));
        System.out.println(solution.getSum(0, 9));
        System.out.println(solution.getSum(0, -9));
        System.out.println(solution.getSum(3, -9));
        System.out.println(solution.getSum(1000, 1000));
        System.out.println(solution.getSum(-1000, 1000));
        System.out.println(solution.getSum(-1000, -1000));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 二进制
     * a ^ b 可以认为是 二进制相加不进位的结果
     * 如果会进位 则表示当前为都为1 所以可以得到进位的数 a & b
     * 因为进位了所以左边应该为1  当前位应该为0 可得 二进制相加进位的结果为 (a & b) << 1
     * 所以就是求 (a & b) + ((a & b) << 1) 利用递归即可
     * (a & b) << 1 当 该值 成0时  说明 进位都加完了
     */
    class Solution {
        public int getSum(int a, int b) {
            return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 二进制
     * 拆位计算
     */
    class SolutionBinary {
        public int getSum(int a, int b) {
            // 进位标识
            int flag = 0;
            int ans = 0;
            for (int i = 0; i < 32; i++) {
                int e1 = (a >> i) & 1;
                int e2 = (b >> i) & 1;
                if (e1 == 1 && e2 == 1) {
                    ans |= (flag << i);
                    flag = 1;
                } else if (e1 == 0 && e2 == 0) {
                    ans |= (flag << i);
                    flag = 0;
                } else {
                    // 其中一个为 1 时
                    // 如果有flag = 1 那这次相加之后 flag = 1
                    // 如果有flag = 0 那这次相加之后 flag = 0
                    // 所以不需要更改flag的值
                    ans |= ((1 ^ flag) << i);
                }
            }
            return ans;
        }
    }
}