package com.sqlist.leetcode.editor.cn;

/**
几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？ 

 给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。 

 例 1： 

 
输入: m = 3, n = 3, k = 5
输出: 3
解释: 
乘法表:
1 2 3
2 4 6
3 6 9

第5小的数字是 3 (1, 2, 2, 3, 3).
 

 例 2： 

 
输入: m = 2, n = 3, k = 6
输出: 6
解释: 
乘法表:
1 2 3
2 4 6

第6小的数字是 6 (1, 2, 2, 3, 4, 6).
 

 注意： 

 
 m 和 n 的范围在 [1, 30000] 之间。 
 k 的范围在 [1, m * n] 之间。 
 
 Related Topics 数学 二分查找 👍 313 👎 0

*/

/**
 * [668]乘法表中第k小的数
 * @author SqList
 * @createTime 2022-05-20 11:47:58
 **/
public class Q668KthSmallestNumberInMultiplicationTable {
    public static void main(String[] args) {
        Solution solution = new Q668KthSmallestNumberInMultiplicationTable().new Solution();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 二分
     * 逆向思维 不找第k数是谁
     * 而是找 在 [1, m * n] 之间的数哪个是第k个数
     * 因为是有序的 所以可以使用二分
     */
    class Solution {
        public int findKthNumber(int m, int n, int k) {
            int left = 1, right = m * n;
            while (right >= left) {
                int mid = left + ((right - left) >> 1);
                int count = count(m, n, mid);
                if (count > k) {
                    right = mid - 1;
                } else if (count < k) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return left;
        }

        public int count(int m, int n, int x) {
            int ans = 0;
            for (int i = 1; i <= m; i++) {
                ans += Math.min(x / i, n);
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}