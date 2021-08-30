package com.sqlist.leetcode.editor.cn;

/**
 * 给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。
 * <p>
 * 子数组 定义为原数组中的一个连续子序列。
 * <p>
 * 请你返回 arr 中 所有奇数长度子数组的和 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,4,2,5,3]
 * 输出：58
 * 解释：所有奇数长度子数组和它们的和为：
 * [1] = 1
 * [4] = 4
 * [2] = 2
 * [5] = 5
 * [3] = 3
 * [1,4,2] = 7
 * [4,2,5] = 11
 * [2,5,3] = 10
 * [1,4,2,5,3] = 15
 * 我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
 * <p>
 * 示例 2：
 * <p>
 * 输入：arr = [1,2]
 * 输出：3
 * 解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。
 * <p>
 * 示例 3：
 * <p>
 * 输入：arr = [10,11,12]
 * 输出：66
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= 1000
 * <p>
 * Related Topics 数组 前缀和 👍 96 👎 0
 */

/**
 * @author SqList
 * @createTime 2021-08-29 12:40:09
 **/
public class Q1588SumOfAllOddLengthSubarrays {
    public static void main(String[] args) {
        Solution solution = new Q1588SumOfAllOddLengthSubarrays().new Solution();
        System.out.println(solution.sumOddLengthSubarrays(new int[]{1, 4, 2, 5, 3}));
        System.out.println(solution.sumOddLengthSubarrays(new int[]{1, 2}));
        System.out.println(solution.sumOddLengthSubarrays(new int[]{10, 11, 12}));
        System.out.println(solution.sumOddLengthSubarrays(new int[]{13, 18, 10, 19, 18, 4, 5, 3, 2, 4, 5, 67, 4, 3, 4, 5, 5, 6, 4, 2, 4, 5, 4, 3, 2, 3, 4, 5, 5, 3, 3, 3, 4, 4, 4, 4, 8, 6, 4, 6, 7, 89, 96, 4, 4, 5, 6, 6, 7, 7, 8, 5, 3, 3}));
    }

    /**
     * 暴力 O(n3)
     */
    class SolutionViolent {
        public int sumOddLengthSubarrays(int[] arr) {
            int ans = 0;
            for (int start = 0; start < arr.length; start++) {
                for (int length = 1; start + length <= arr.length; length += 2) {
                    int end = start + length - 1;
                    for (int i = start; i <= end; i++) {
                        ans += arr[i];
                    }
                }
            }

            return ans;
        }
    }

    /**
     * 前缀和 O(n2)
     * 前缀和数组 prefixSum
     * prefixSums[0]=0;
     * i 从1到n时，prefixSums[i] 表示数组 arr 从 0 到 i−1 的元素和
     */
    class SolutionPrefix {
        public int sumOddLengthSubarrays(int[] arr) {
            int[] prefixSum = new int[arr.length + 1];
            for (int i = 0; i < arr.length; i++) {
                prefixSum[i + 1] = prefixSum[i] + arr[i];
            }

            int ans = 0;
            for (int start = 0; start < arr.length; start++) {
                for (int length = 1; start + length <= arr.length; length += 2) {
                    int end = start + length - 1;
                    ans += prefixSum[end + 1] - prefixSum[start];
                }
            }
            return ans;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 数学方式 O(n)
     * 计算出每一个数在奇数长度数组的出现的次数 同样可以得出结果
     * 对于每一个数arr[i]来说 出现在奇数长度的数组中 arr[0...i-1] 与 arr[i+1...n] 的个数应该同为奇数或偶数
     */
    class Solution {
        public int sumOddLengthSubarrays(int[] arr) {
            int ans = 0;
            int n = arr.length;

            for (int i = 0; i < n; i++) {
                // 下标为 i 的位置左边有几个数字
                int leftCount = i;
                // 下标为 i 的位置右边有几个数字
                int rightCount = n - i - 1;

                // 情况一: 位置 i 左边选奇数个数, 右边选奇数个数.
                // 为什么这样选呢 ? 因为: 奇数 + 1 + 奇数 = 奇数
                // 在位置 i 的左边可以选多少种奇数
                int leftOdd = (leftCount + 1) >> 1;
                // 在位置 i 的右边可以选多少种奇数
                int rightOdd = (rightCount + 1) >> 1;
                //位置 i 左右两边都选奇数的话一共有多少种可选情况:
                //假设左边可选有 n 种, 右边可选有 m 种, 那么总情况一共有: n * m, 其实就是多少种组合嘛

                // 情况二: 位置 i 左边选偶数个数, 右边选偶数个数.
                // 为什么这样选呢 ? 因为: 偶数 + 1 + 偶数 = 奇数
                // 在位置 i 的左边可以选多少种偶数
                int leftEven = (leftCount >> 1) + 1;
                // 在位置 i 的右边可以选多少种偶数
                int rightEven = (rightCount >> 1) + 1;
                //位置 i 左右两边都选偶数的话一共有多少种可选情况:
                //假设左边可选有 n 种, 右边可选有 m 种, 那么总情况一共有: n * m, 其实就是多少种组合嘛


                ans += arr[i] * (leftOdd * rightOdd + leftEven * rightEven);
            }

            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}