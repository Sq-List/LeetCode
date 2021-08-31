package com.sqlist.leetcode.editor.cn;

/**
这里有 n 个航班，它们分别从 1 到 n 进行编号。 

 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 
firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。 

 请你返回一个长度为 n 的数组 answer，其中 answer[i] 是航班 i 上预订的座位总数。 

 

 示例 1： 

 
输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
输出：[10,55,45,25,25]
解释：
航班编号        1   2   3   4   5
预订记录 1 ：   10  10
预订记录 2 ：       20  20
预订记录 3 ：       25  25  25  25
总座位数：      10  55  45  25  25
因此，answer = [10,55,45,25,25]
 

 示例 2： 

 
输入：bookings = [[1,2,10],[2,2,15]], n = 2
输出：[10,25]
解释：
航班编号        1   2
预订记录 1 ：   10  10
预订记录 2 ：       15
总座位数：      10  25
因此，answer = [10,25]
 

 

 提示： 

 
 1 <= n <= 2 * 10⁴ 
 1 <= bookings.length <= 2 * 10⁴ 
 bookings[i].length == 3 
 1 <= firsti <= lasti <= n 
 1 <= seatsi <= 10⁴ 
 
 Related Topics 数组 前缀和 👍 210 👎 0

*/

import java.util.Arrays;

/**
 * @author SqList
 * @createTime 2021-08-31 10:43:45
 **/
public class Q1109CorporateFlightBookings {
    public static void main(String[] args) {
        Solution solution = new Q1109CorporateFlightBookings().new Solution();
        System.out.println(Arrays.toString(solution.corpFlightBookings(new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}}, 5)));
        System.out.println(Arrays.toString(solution.corpFlightBookings(new int[][]{{1, 2, 10}, {2, 2, 15}}, 2)));
    }

    /**
     * 暴力 Violent
     */
    class SolutionViolent {
        public int[] corpFlightBookings(int[][] bookings, int n) {
            int[] res = new int[n];
            for (int i = 0; i < bookings.length; i++) {
                int[] booking = bookings[i];
                for (int j = booking[0] - 1; j <= booking[1] - 1; j++) {
                    res[j] += booking[2];
                }
            }
            return res;
        }
    }

    /**
     * 差分数组对应的概念是前缀和数组，
     * 对于数组 [1,2,2,4]，其差分数组为 [1,1,0,2]，
     * 差分数组的第 i 个数即为原数组的第 i-1 个元素和第 i 个元素的差值，
     * 也就是说我们对差分数组求前缀和即可得到原数组。
     *
     * 差分数组的性质是，
     * 当我们希望对原数组的某一个区间 [l,r] 施加一个增量 inc 时，
     * 差分数组 d 对应的改变是：d[l] 增加 inc，d[r+1] 减少 inc。
     * 这样对于区间的修改就变为了对于两个位置的修改。
     * 并且这种修改是可以叠加的，即当我们多次对原数组的不同区间施加不同的增量，我们只要按规则修改差分数组即可。
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] corpFlightBookings(int[][] bookings, int n) {
            int[] nums = new int[n];
            for (int[] booking : bookings) {
                nums[booking[0] - 1] += booking[2];
                if (booking[1] < n) {
                    nums[booking[1]] -= booking[2];
                }
            }

            for (int i = 1; i < n; i++) {
                nums[i] += nums[i - 1];
            }
            return nums;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}