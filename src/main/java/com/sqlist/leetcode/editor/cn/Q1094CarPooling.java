package com.sqlist.leetcode.editor.cn;

/**
假设你是一位顺风车司机，车上最初有 capacity 个空座位可以用来载客。由于道路的限制，车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向，你可以将其
想象为一个向量）。 

 这儿有一份乘客行程计划表 trips[][]，
 其中 trips[i] = [num_passengers, start_location, end_location]
 包含了第 i 组乘客的行程信息：

 必须接送的乘客数量； 
 乘客的上车地点； 
 以及乘客的下车地点。 
 

 这些给出的地点位置是从你的 初始 出发位置向前行驶到这些地点所需的距离（它们一定在你的行驶方向上）。 

 请你根据给出的行程计划表和车子的座位数，来判断你的车是否可以顺利完成接送所有乘客的任务（当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请
返回 false）。 

 

 示例 1： 

 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
输出：false
 

 示例 2： 

 输入：trips = [[2,1,5],[3,3,7]], capacity = 5
输出：true
 

 示例 3： 

 输入：trips = [[2,1,5],[3,5,7]], capacity = 3
输出：true
 

 示例 4： 

 输入：trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
输出：true
 

 

 提示： 

 
 你可以假设乘客会自觉遵守 “先下后上” 的良好素质 
 trips.length <= 1000 
 trips[i].length == 3 
 1 <= trips[i][0] <= 100 
 0 <= trips[i][1] < trips[i][2] <= 1000 
 1 <= capacity <= 100000 
 
 Related Topics 数组 前缀和 排序 模拟 堆（优先队列） 👍 115 👎 0

*/

/**
 * @author SqList
 * @createTime 2021-08-31 19:12:16
 **/
public class Q1094CarPooling {
    public static void main(String[] args) {
        Solution solution = new Q1094CarPooling().new Solution();
        System.out.println(solution.carPooling(new int[][]{{2,1,5}, {3,3,7}}, 4));
        System.out.println(solution.carPooling(new int[][]{{2,1,5}, {3,3,7}}, 5));
        System.out.println(solution.carPooling(new int[][]{{2,1,5}, {3,5,7}}, 3));
        System.out.println(solution.carPooling(new int[][]{{3,2,7}, {3,7,9}, {8,3,9}}, 11));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean carPooling(int[][] trips, int capacity) {
            int max = -1;
            int[] cap = new int[1001];
            for (int[] trip : trips) {
                cap[trip[1]] += trip[0];
                cap[trip[2]] -= trip[0];
                if (trip[2] > max) {
                    max = trip[2];
                }
            }

            if (cap[0] > capacity) {
                return false;
            }

            for (int i = 1; i <= max; i++) {
                cap[i] += cap[i - 1];
                if (cap[i] > capacity) {
                    return false;
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}