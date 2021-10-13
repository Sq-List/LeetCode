package com.sqlist.leetcode.editor.cn;

/**
给你一份旅游线路图，该线路图中的旅行线路用数组 paths 表示，其中 paths[i] = [cityAi, cityBi] 表示该线路将会从 cityAi 
直接前往 cityBi 。请你找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市。 

 题目数据保证线路图会形成一条不存在循环的线路，因此恰有一个旅行终点站。 

 

 示例 1： 

 
输入：paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
输出："Sao Paulo" 
解释：从 "London" 出发，最后抵达终点站 "Sao Paulo" 。本次旅行的路线是 "London" -> "New York" -> "Lima" 
-> "Sao Paulo" 。
 

 示例 2： 

 
输入：paths = [["B","C"],["D","B"],["C","A"]]
输出："A"
解释：所有可能的线路是：
"D" -> "B" -> "C" -> "A". 
"B" -> "C" -> "A". 
"C" -> "A". 
"A". 
显然，旅行终点站是 "A" 。
 

 示例 3： 

 
输入：paths = [["A","Z"]]
输出："Z"
 

 

 提示： 

 
 1 <= paths.length <= 100 
 paths[i].length == 2 
 1 <= cityAi.length, cityBi.length <= 10 
 cityAi != cityBi 
 所有字符串均由大小写英文字母和空格字符组成。 
 
 Related Topics 哈希表 字符串 👍 74 👎 0

*/

import java.util.*;

/**
 * [1436]旅行终点站
 * @author SqList
 * @createTime 2021-10-01 15:30:43
 **/
public class Q1436DestinationCity {
    public static void main(String[] args) {
        Solution solution = new Q1436DestinationCity().new Solution();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String destCity(List<List<String>> paths) {
            List<String> fromList = new ArrayList<>();
            List<String> toList = new ArrayList<>();
            for (List<String> path : paths) {
                fromList.add(path.get(0));
                toList.add(path.get(1));
            }

            toList.removeAll(fromList);
            return toList.get(0);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class SolutionSelf {
        public String destCity(List<List<String>> paths) {
            Map<String, String> pathMap = new HashMap<>(paths.size(), 1);
            List<String> dest = new ArrayList<>(paths.size());
            for (List<String> path : paths) {
                pathMap.put(path.get(0), path.get(1));
                dest.remove(path.get(0));
                if (!pathMap.containsKey(path.get(1))) {
                    dest.add(path.get(1));
                }
            }
            return dest.get(0);
        }
    }
}