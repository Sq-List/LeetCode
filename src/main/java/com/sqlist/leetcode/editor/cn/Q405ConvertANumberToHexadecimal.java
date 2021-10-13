package com.sqlist.leetcode.editor.cn;

/**
给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。 

 注意: 

 
 十六进制中所有字母(a-f)都必须是小写。 
 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
 给定的数确保在32位有符号整数范围内。 
 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。 
 

 示例 1： 

 
输入:
26

输出:
"1a"
 

 示例 2： 

 
输入:
-1

输出:
"ffffffff"
 
 Related Topics 位运算 数学 👍 171 👎 0

*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * [405]数字转换为十六进制数
 * @author SqList
 * @createTime 2021-10-02 10:35:09
 **/
public class Q405ConvertANumberToHexadecimal {
    public static void main(String[] args) {
        Solution solution = new Q405ConvertANumberToHexadecimal().new Solution();
        System.out.println(solution.toHex(26));
        System.out.println(solution.toHex(-1));
        System.out.println(solution.toHex(0));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 进制转换
     */
    class Solution {
        public String toHex(int _num) {
            if (_num == 0) {
                return "0";
            }

            long num = _num;
            if (num < 0) {
                num = (long)Math.pow(2, 32) + _num;
            }

            StringBuilder sb = new StringBuilder();
            while (num != 0) {
                long tmp = num % 16;
                if (tmp >= 10) {
                    sb.append((char)(tmp - 10 + 'a'));
                } else {
                    sb.append(tmp);
                }
                num /= 16;
            }

            return sb.reverse().toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 位运算
     */
    class SolutionBitOperation {
        public String toHex(int num) {
            if (num == 0) {
                return "0";
            }

            StringBuilder sb = new StringBuilder();
            while (num != 0) {
                int temp = num & 0xf; // 15
                if (temp >= 10) {
                    sb.append((char)(temp - 10 + 'a'));
                } else {
                    sb.append(temp);
                }
                num >>>= 4;
            }

            return sb.reverse().toString();
        }
    }

    /**
     * 位运算
     */
    class SolutionSelf {
        public String toHex(int num) {
            if (num == 0) {
                return "0";
            }

            List<String> resultList = new ArrayList<>();
            for (int i = 0; i < 8 && num != 0; i++) {
                int one = 1;
                int sum = 0;
                for (int j = 0; j < 4 && num != 0; j++) {
                    int tmp = num & 1;
                    sum += one * tmp;

                    num >>= 1;
                    one *= 2;
                }

                if (sum >= 10) {
                    resultList.add(String.valueOf((char)((sum - 10) + 'a')));
                } else {
                    resultList.add(String.valueOf(sum));
                }
            }

            Collections.reverse(resultList);
            return String.join("", resultList);
        }
    }
}