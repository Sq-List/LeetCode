package com.sqlist.leetcode.editor.cn;

/**
给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。 

 如果小数部分为循环小数，则将循环的部分括在括号内。 

 如果存在多个答案，只需返回 任意一个 。 

 对于所有给定的输入，保证 答案字符串的长度小于 10⁴ 。 

 

 示例 1： 

 
输入：numerator = 1, denominator = 2
输出："0.5"
 

 示例 2： 

 
输入：numerator = 2, denominator = 1
输出："2"
 

 示例 3： 

 
输入：numerator = 2, denominator = 3
输出："0.(6)"
 

 示例 4： 

 
输入：numerator = 4, denominator = 333
输出："0.(012)"
 

 示例 5： 

 
输入：numerator = 1, denominator = 5
输出："0.2"
 

 

 提示： 

 
 -2³¹ <= numerator, denominator <= 2³¹ - 1 
 denominator != 0 
 
 Related Topics 哈希表 数学 字符串 👍 279 👎 0

*/

import java.util.HashMap;
import java.util.Map;

/**
 * [166]分数到小数
 * @author SqList
 * @createTime 2021-10-03 11:26:34
 **/
public class Q166FractionToRecurringDecimal {
    public static void main(String[] args) {
        Solution solution = new Q166FractionToRecurringDecimal().new Solution();
        System.out.println(solution.fractionToDecimal(1, 2));
        System.out.println(solution.fractionToDecimal(2, 1));
        System.out.println(solution.fractionToDecimal(2, 2));
        System.out.println(solution.fractionToDecimal(1, 5));
        System.out.println(solution.fractionToDecimal(1, 50));
        System.out.println(solution.fractionToDecimal(4, 333));
        System.out.println(solution.fractionToDecimal(2, 3));
        System.out.println(solution.fractionToDecimal(1, 6));
        System.out.println(solution.fractionToDecimal(558898 - 55, 999900));
        System.out.println(solution.fractionToDecimal(-2147483648, -1));
        System.out.println(solution.fractionToDecimal(-2147483648, 1));
        System.out.println(solution.fractionToDecimal(2147483647, 1));
        System.out.println(solution.fractionToDecimal(2147483647, -1));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String fractionToDecimal(int _numerator, int _denominator) {
            long numerator = _numerator;
            long denominator = _denominator;

            if (numerator % denominator == 0) {
                return String.valueOf(numerator / denominator);
            }

            // 正负符号
            StringBuilder result = new StringBuilder();
            if ((_numerator > 0 && _denominator < 0)
                    || (_numerator < 0 && _denominator > 0)) {
                result.append("-");
            }

            // 整数部分
            numerator = Math.abs(numerator);
            denominator = Math.abs(denominator);
            result.append(numerator / denominator)
                    .append(".");

            // 小数部分
            StringBuilder sb = new StringBuilder();
            Map<Long, Integer> duplicateMap = new HashMap<>();
            numerator = numerator % denominator;
            for (int i = 0; numerator != 0 && !duplicateMap.containsKey(numerator); i++) {
                duplicateMap.put(numerator, i);
                numerator *= 10;
                long res = numerator / denominator;
                sb.append(res);

                numerator = numerator % denominator;
            }

            if (numerator == 0) {
                return result.append(sb).toString();
            } else {
                int duplicateStart = duplicateMap.get(numerator);
                if (duplicateStart >= 1) {
                    result.append(sb.substring(0, duplicateStart));
                }
                return result.append("(").append(sb.substring(duplicateStart)).append(")").toString();
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}