package com.sqlist.leetcode.editor.cn;

/**
给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。 

 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。 

 

 
 

 示例 1： 

 输入：1
输出：true
 

 示例 2： 

 输入：10
输出：false
 

 示例 3： 

 输入：16
输出：true
 

 示例 4： 

 输入：24
输出：false
 

 示例 5： 

 输入：46
输出：true
 

 

 提示： 

 
 1 <= N <= 10^9 
 
 Related Topics 数学 计数 枚举 排序 👍 49 👎 0

*/

/**
 * [869]重新排序得到 2 的幂
 * @author SqList
 * @createTime 2021-10-28 00:01:24
 **/
public class Q869ReorderedPowerOf2 {
    public static void main(String[] args) {
        Solution solution = new Q869ReorderedPowerOf2().new Solution();
        System.out.println(solution.reorderedPowerOf2(1));
        System.out.println(solution.reorderedPowerOf2(10));
        System.out.println(solution.reorderedPowerOf2(16));
        System.out.println(solution.reorderedPowerOf2(24));
        System.out.println(solution.reorderedPowerOf2(46));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 预处理 + hash表
     */
    class Solution {
        public boolean reorderedPowerOf2(int n) {
            int num = 1;
            String nStr = numToStr(n);
            while (num <= 1e9) {
                if (nStr.equals(numToStr(num))) {
                    return true;
                }
                num *= 2;
            }

            return false;
        }

        private String numToStr(int num) {
            char[] cnt = new char[10];
            while (num > 0) {
                cnt[num % 10] ++;
                num /= 10;
            }

            return new String(cnt);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}