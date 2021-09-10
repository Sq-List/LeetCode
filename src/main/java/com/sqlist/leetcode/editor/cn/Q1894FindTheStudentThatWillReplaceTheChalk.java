package com.sqlist.leetcode.editor.cn;

/**
一个班级里有 n 个学生，编号为 0 到 n - 1 。每个学生会依次回答问题，编号为 0 的学生先回答，然后是编号为 1 的学生，以此类推，直到编号为 n -
 1 的学生，然后老师会重复这个过程，重新从编号为 0 的学生开始回答问题。 

 给你一个长度为 n 且下标从 0 开始的整数数组 chalk 和一个整数 k 。一开始粉笔盒里总共有 k 支粉笔。当编号为 i 的学生回答问题时，他会消耗 
chalk[i] 支粉笔。如果剩余粉笔数量 严格小于 chalk[i] ，那么学生 i 需要 补充 粉笔。 

 请你返回需要 补充 粉笔的学生 编号 。 

 

 示例 1： 

 输入：chalk = [5,1,5], k = 22
输出：0
解释：学生消耗粉笔情况如下：
- 编号为 0 的学生使用 5 支粉笔，然后 k = 17 。
- 编号为 1 的学生使用 1 支粉笔，然后 k = 16 。
- 编号为 2 的学生使用 5 支粉笔，然后 k = 11 。
- 编号为 0 的学生使用 5 支粉笔，然后 k = 6 。
- 编号为 1 的学生使用 1 支粉笔，然后 k = 5 。
- 编号为 2 的学生使用 5 支粉笔，然后 k = 0 。
编号为 0 的学生没有足够的粉笔，所以他需要补充粉笔。 

 示例 2： 

 输入：chalk = [3,4,1,2], k = 25
输出：1
解释：学生消耗粉笔情况如下：
- 编号为 0 的学生使用 3 支粉笔，然后 k = 22 。
- 编号为 1 的学生使用 4 支粉笔，然后 k = 18 。
- 编号为 2 的学生使用 1 支粉笔，然后 k = 17 。
- 编号为 3 的学生使用 2 支粉笔，然后 k = 15 。
- 编号为 0 的学生使用 3 支粉笔，然后 k = 12 。
- 编号为 1 的学生使用 4 支粉笔，然后 k = 8 。
- 编号为 2 的学生使用 1 支粉笔，然后 k = 7 。
- 编号为 3 的学生使用 2 支粉笔，然后 k = 5 。
- 编号为 0 的学生使用 3 支粉笔，然后 k = 2 。
编号为 1 的学生没有足够的粉笔，所以他需要补充粉笔。
 

 

 提示： 

 
 chalk.length == n 
 1 <= n <= 10⁵ 
 1 <= chalk[i] <= 10⁵ 
 1 <= k <= 10⁹ 
 
 Related Topics 数组 二分查找 前缀和 模拟 👍 23 👎 0

*/

/**
 * [1894]找到需要补充粉笔的学生编号
 * @author SqList
 * @createTime 2021-09-10 09:50:23
 **/
public class Q1894FindTheStudentThatWillReplaceTheChalk {
    public static void main(String[] args) {
        Solution solution = new Q1894FindTheStudentThatWillReplaceTheChalk().new Solution();
        System.out.println(solution.chalkReplacer(new int[]{5, 1, 5}, 22));
        System.out.println(solution.chalkReplacer(new int[]{5, 1, 5}, 21));
        System.out.println(solution.chalkReplacer(new int[]{3, 4, 1, 2}, 25));
        System.out.println(solution.chalkReplacer(new int[]{3, 4, 1, 2}, 23));
        System.out.println(solution.chalkReplacer(new int[]{2, 2, 2, 2}, 1));   // 0
        System.out.println(solution.chalkReplacer(new int[]{2, 2, 2, 2}, 2));   // 1
        System.out.println(solution.chalkReplacer(new int[]{2, 2, 2, 2}, 8));   // 0
        System.out.println(solution.chalkReplacer(new int[]{2, 2, 2, 2}, 9));   // 0
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 遍历
     */
    class Solution {
        public int chalkReplacer(int[] chalk, int k) {
            long sum = 0;
            for (int i : chalk) {
                sum += i;
            }

            k %= sum;
            for (int i = 0; i < chalk.length; i++) {
                if (k < chalk[i]) {
                    return i;
                }

                k -= chalk[i];
            }

            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 前缀和+二分
     */
    class SolutionPrefixAndBinary {
        public int chalkReplacer(int[] chalk, int k) {
            long[] prefixSums = new long[chalk.length + 1];
            for (int i = 1; i < prefixSums.length; i++) {
                prefixSums[i] = chalk[i - 1] + prefixSums[i - 1];
            }

            int remaining = (int)(k % prefixSums[chalk.length]);
            // prefixSums[i + 1] > remaining >= prefixSums[i]
            int i = binarySearchLeft(prefixSums, remaining);
            return prefixSums[i] == remaining ? i : i - 1;
        }

        public int binarySearchLeft(long[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;

            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    right = mid - 1;
                }
            }

            return left;
        }
    }

}