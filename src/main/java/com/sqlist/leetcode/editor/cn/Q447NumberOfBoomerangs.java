package com.sqlist.leetcode.editor.cn;

/**
给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 
i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。 

 返回平面上所有回旋镖的数量。 
 

 示例 1： 

 
输入：points = [[0,0],[1,0],[2,0]]
输出：2
解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 

 示例 2： 

 
输入：points = [[1,1],[2,2],[3,3]]
输出：2
 

 示例 3： 

 
输入：points = [[1,1]]
输出：0
 

 

 提示： 

 
 n == points.length 
 1 <= n <= 500 
 points[i].length == 2 
 -10⁴ <= xi, yi <= 10⁴ 
 所有点都 互不相同 
 
 Related Topics 数组 哈希表 数学 👍 165 👎 0

*/

import java.util.HashMap;
import java.util.Map;

/**
 * [447]回旋镖的数量
 * @author SqList
 * @createTime 2021-09-13 10:07:54
 **/
public class Q447NumberOfBoomerangs {
    public static void main(String[] args) {
        Solution solution = new Q447NumberOfBoomerangs().new Solution();
        System.out.println(solution.numberOfBoomerangs(new int[][]{{0, 0}, {1, 0}, {2, 0}}));
        System.out.println(solution.numberOfBoomerangs(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
        System.out.println(solution.numberOfBoomerangs(new int[][]{{1, 1}}));
        System.out.println(solution.numberOfBoomerangs(new int[][]{{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, 1}}));
        System.out.println(solution.numberOfBoomerangs(new int[][]{{5, 5}, {4, 7}, {6, 5}, {6, 9}, {3, 7}, {4, 5}, {2, 5}, {4, 4}, {3, 0}}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numberOfBoomerangs(int[][] points) {
            int res = 0;
            Map<Integer, Integer> disCountMap = new HashMap<>((int)(points.length / 0.75) + 1);
            for (int i = 0; i < points.length; i++) {
                for (int j = 0; j < points.length; j++) {
                    if (i == j) {
                        continue;
                    }

                    int dis = (points[i][0] - points[j][0]) * (points[i][0] - points[j][0])
                            + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);

                    if (disCountMap.containsKey(dis)) {
                        disCountMap.put(dis, disCountMap.get(dis) + 1);
                    } else {
                        disCountMap.put(dis, 1);
                    }
                }

                for (Map.Entry<Integer, Integer> entry : disCountMap.entrySet()) {
                    if (entry.getValue() >= 2) {
                        res += (entry.getValue() - 1) * entry.getValue();
                    }
                }
                disCountMap.clear();
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}