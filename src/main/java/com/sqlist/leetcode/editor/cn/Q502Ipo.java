package com.sqlist.leetcode.editor.cn;

/**
假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。 由于资源有限，它
只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。 

 给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。 

 最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。 

 总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。 

 答案保证在 32 位有符号整数范围内。 

 

 示例 1： 

 
输入：k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
输出：4
解释：
由于你的初始资本为 0，你仅可以从 0 号项目开始。
在完成后，你将获得 1 的利润，你的总资本将变为 1。
此时你可以选择开始 1 号或 2 号项目。
由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本。
因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。
 

 示例 2： 

 
输入：k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
输出：6
 

 

 提示： 

 
 1 <= k <= 10⁵ 
 0 <= w <= 10⁹ 
 n == profits.length 
 n == capital.length 
 1 <= n <= 10⁵ 
 0 <= profits[i] <= 10⁴ 
 0 <= capital[i] <= 10⁹ 
 
 Related Topics 贪心 数组 排序 堆（优先队列） 👍 147 👎 0

*/

import java.util.*;

/**
 * [502]IPO
 * @author SqList
 * @createTime 2021-09-08 14:19:52
 **/
public class Q502Ipo {
    public static void main(String[] args) {
        Solution solution = new Q502Ipo().new Solution();
        System.out.println(solution.findMaximizedCapital(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
        System.out.println(solution.findMaximizedCapital(3, 0, new int[]{1, 2, 3}, new int[]{0, 1, 2}));
        System.out.println(solution.findMaximizedCapital(10, 0, new int[]{1, 2, 3}, new int[]{0, 1, 2}));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private class Node {
            private int cap;
            private int pro;

            public Node(int cap, int pro) {
                this.cap = cap;
                this.pro = pro;
            }

            public int getCap() {
                return cap;
            }

            public int getPro() {
                return pro;
            }
        }

        public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
            Node[] nodes = new Node[capital.length];
            // O(n)
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = new Node(capital[i], profits[i]);
            }

            // O(nlogn)
            Arrays.sort(nodes, (o1, o2) -> {
                // 以cap从小到大 为 主要排序  cap相等的 以利润从大到小排序
                return o1.getCap() == o2.getCap() ? o2.getPro() - o1.getPro() : o1.getCap() - o2.getCap();
            });

            PriorityQueue<Node> profitsQueue = new PriorityQueue<>(capital.length, (o1, o2) -> o2.getPro() - o1.getPro());

            int idx = 0;
            while (k-- != 0) {
                // 利用排序了后的数组 就 可以使 遍历 capital 从 O(k*n) 变成 O(n) （每次只取其中的一段 总共k次才有可能将整个nodes遍历完）
                while (idx < nodes.length && nodes[idx].getCap() <= w) {
                    // k次 优先队列 往堆中添加元素 不超过O(nlogn)
                    profitsQueue.add(nodes[idx++]);
                }

                if (profitsQueue.isEmpty()) {
                    break;
                }
                // k次 优先队列 更新资本 O(klogn)
                Node peek = profitsQueue.poll();
                w += peek.getPro();
            }

            return w;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}