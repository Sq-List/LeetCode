package com.sqlist.leetcode.editor.cn;

/**
给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。 

 区间 i 的 右侧区间 可以记作区间 j ，并满足 startj >= endi ，且 startj 最小化 。 

 返回一个由每个区间 i 的 右侧区间 在 intervals 中对应下标组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 
。 
 

 示例 1： 

 
输入：intervals = [[1,2]]
输出：[-1]
解释：集合中只有一个区间，所以输出-1。
 

 示例 2： 

 
输入：intervals = [[3,4],[2,3],[1,2]]
输出：[-1,0,1]
解释：对于 [3,4] ，没有满足条件的“右侧”区间。
对于 [2,3] ，区间[3,4]具有最小的“右”起点;
对于 [1,2] ，区间[2,3]具有最小的“右”起点。
 

 示例 3： 

 
输入：intervals = [[1,4],[2,3],[3,4]]
输出：[-1,2,-1]
解释：对于区间 [1,4] 和 [3,4] ，没有满足条件的“右侧”区间。
对于 [2,3] ，区间 [3,4] 有最小的“右”起点。
 

 

 提示： 

 
 1 <= intervals.length <= 2 * 10⁴ 
 intervals[i].length == 2 
 -10⁶ <= starti <= endi <= 10⁶ 
 每个间隔的起点都 不相同 
 
 Related Topics 数组 二分查找 排序 👍 188 👎 0

*/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * [436]寻找右区间
 * @author SqList
 * @createTime 2022-05-23 11:42:07
 **/
public class Q436FindRightInterval {

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static int[][] stringToInt2dArray(String input) {
        JSONArray jsonArray = JSON.parseArray(input);
        if (jsonArray.size() == 0) {
            return new int[0][0];
        }

        int[][] arr = new int[jsonArray.size()][];
        for (int i = 0; i < arr.length; i++) {
            JSONArray cols = jsonArray.getJSONArray(i);
            arr[i] = stringToIntegerArray(cols.toString());
        }
        return arr;
    }

    public static String integerArrayToString(int[] nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            int number = nums[index];
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException {
        String file = Objects.requireNonNull(Q436FindRightInterval.class.getResource("/testcase/Q436")).getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            int[][] intervals = stringToInt2dArray(line);

            long start = System.currentTimeMillis();
            Solution solution = new Q436FindRightInterval().new Solution();
            System.out.println(Arrays.toString(solution.findRightInterval(intervals)));
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }

    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] findRightInterval(int[][] intervals) {
            int[][] startInterval = new int[intervals.length][2];
            for (int i = 0; i < intervals.length; i++) {
                startInterval[i][0] = intervals[i][0];
                startInterval[i][1] = i;
            }
            Arrays.sort(startInterval, ((o1, o2) -> o1[0] - o2[0]));

            int[] result = new int[intervals.length];
            for (int i = 0; i < intervals.length; i++) {
                // 要取下标  即 startInterval 的第1位
                int target = -1;

                int left = 0, right = startInterval.length - 1;
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (intervals[i][1] > startInterval[mid][0]) {
                        left = mid + 1;
                    } else if (intervals[i][1] < startInterval[mid][0]) {
                        target = startInterval[mid][1];
                        right = mid - 1;
                    } else {
                        target = startInterval[mid][1];
                        right = mid - 1;
                    }
                }
                result[i] = target;
            }

            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}