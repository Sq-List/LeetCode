package com.sqlist.leetcode.editor.cn;

/**
给你一个整数数组 arr 和一个整数 difference，请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 
difference 。 

 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。 

 

 示例 1： 

 
输入：arr = [1,2,3,4], difference = 1
输出：4
解释：最长的等差子序列是 [1,2,3,4]。 

 示例 2： 

 
输入：arr = [1,3,5,7], difference = 1
输出：1
解释：最长的等差子序列是任意单个元素。
 

 示例 3： 

 
输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
输出：4
解释：最长的等差子序列是 [7,5,3,1]。
 

 

 提示： 

 
 1 <= arr.length <= 10⁵ 
 -10⁴ <= arr[i], difference <= 10⁴ 
 
 Related Topics 数组 哈希表 动态规划 👍 74 👎 0

*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * [1218]最长定差子序列
 * @author SqList
 * @createTime 2021-11-05 00:02:36
 **/
public class Q1218LongestArithmeticSubsequenceOfGivenDifference {
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

    public static void main(String[] args) throws IOException {
        String file = Objects.requireNonNull(Q1218LongestArithmeticSubsequenceOfGivenDifference.class.getResource("/testcase/Q1218")).getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            int[] arr = stringToIntegerArray(line);
            line = bufferedReader.readLine();
            int difference = Integer.parseInt(line);

            long start = System.currentTimeMillis();
            Solution solution = new Q1218LongestArithmeticSubsequenceOfGivenDifference().new Solution();
            System.out.println(solution.longestSubsequence(arr, difference));
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * dp[i] 表示 以 i 为结尾的最长的等差子序列的长度
     */
    class Solution {
        public int longestSubsequence(int[] arr, int difference) {
            int ans = 1;
            Map<Integer, Integer> dp = new HashMap<>();
            for (int i : arr) {
                dp.put(i, dp.getOrDefault(i - difference, 0) + 1);
                ans = Math.max(ans, dp.get(i));
            }

            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * dp[i] 表示 以 arr[i] 为结尾的最长的等差子序列的长度
     */
    class SolutionDpHash {
        public int longestSubsequence(int[] arr, int difference) {
            int n = arr.length;
            int[] dp = new int[n];
            Arrays.fill(dp, 1);

            Map<Integer, List<Integer>> numToListMap = new HashMap<>(arr.length + 1, 1);
            for (int i = 0; i < arr.length; i++) {
                if (!numToListMap.containsKey(arr[i])) {
                    numToListMap.put(arr[i], new ArrayList<>());
                }
                numToListMap.get(arr[i]).add(i);
            }

            int ans = 1;
            for (int i = 0; i < n; i++) {
                List<Integer> list = numToListMap.getOrDefault(arr[i] - difference, new ArrayList<>());
                for (Integer tmp : list) {
                    if (tmp >= i) {
                        break;
                    }
                    dp[i] = Math.max(dp[i], dp[tmp] + 1);
                    ans = Math.max(ans, dp[i]);
                }
            }

            return ans;
        }
    }

    /**
     * dp[i] 表示 以 arr[i] 为结尾的最长的等差子序列的长度
     */
    class SolutionOld {
        public int longestSubsequence(int[] arr, int difference) {
            int n = arr.length;
            int[] dp = new int[n];
            Arrays.fill(dp, 1);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (arr[i] - arr[j] == difference) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }

            int ans = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                ans = Math.max(dp[i], ans);
            }

            return ans;
        }
    }
}