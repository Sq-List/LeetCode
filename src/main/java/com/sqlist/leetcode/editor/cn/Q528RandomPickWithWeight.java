package com.sqlist.leetcode.editor.cn;

/**
给定一个正整数数组 w ，其中 w[i] 代表下标 i 的权重（下标从 0 开始），请写一个函数 pickIndex ，它可以随机地获取下标 i，选取下标 i 
的概率与 w[i] 成正比。 

 
 

 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3
) = 0.75（即，75%）。 

 也就是说，选取下标 i 的概率为 w[i] / sum(w) 。 

 

 示例 1： 

 输入：
["Solution","pickIndex"]
[[[1]],[]]
输出：
[null,0]
解释：
Solution solution = new Solution([1]);
solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。 

 示例 2： 

 输入：
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
输出：
[null,1,1,1,1,0]
解释：
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。

由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
诸若此类。
 

 

 提示： 

 
 1 <= w.length <= 10000 
 1 <= w[i] <= 10^5 
 pickIndex 将被调用不超过 10000 次 
 
 Related Topics 数学 二分查找 前缀和 随机化 👍 169 👎 0

*/

/**
 * TODO
 *
 * @author SqList
 * @createTime 2021-08-30 22:54:04
 **/
public class Q528RandomPickWithWeight {
    public static void main(String[] args) {
        Solution solution = new Q528RandomPickWithWeight().new Solution(new int[]{1, 2});
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());

        Solution solution1 = new Q528RandomPickWithWeight().new Solution(new int[]{1, 3});
        System.out.println(solution1.pickIndex());
        System.out.println(solution1.pickIndex());
        System.out.println(solution1.pickIndex());
        System.out.println(solution1.pickIndex());
        System.out.println(solution1.pickIndex());
        System.out.println(solution1.pickIndex());
        System.out.println(solution1.pickIndex());
        System.out.println(solution1.pickIndex());
        System.out.println(solution1.pickIndex());
        System.out.println(solution1.pickIndex());
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private int sum;
        /**
         * 前缀和
         */
        private int[] prefixArr;

        public Solution(int[] w) {
            sum = 0;
            prefixArr = new int[w.length + 1];

            for (int i = 0; i < w.length; i++) {
                prefixArr[i + 1] = prefixArr[i] + w[i];
                sum += w[i];
            }
        }

        public int pickIndex() {
            int x = (int) (Math.random() * sum) + 1;
            return binarySearch(prefixArr, x) - 1;
        }

        private int binarySearch(int[] prefixArr, int x) {
            int i = 1, j = prefixArr.length - 1;
            while (i < j) {
                int mid = (i + j) / 2;
                if (prefixArr[mid] < x) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }

            return i;
        }
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(w);
     * int param_1 = obj.pickIndex();
     */
    //leetcode submit region end(Prohibit modification and deletion)

}