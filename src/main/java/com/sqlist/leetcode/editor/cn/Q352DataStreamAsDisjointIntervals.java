package com.sqlist.leetcode.editor.cn;

/**
给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。 

 实现 SummaryRanges 类： 

 
 
 
 SummaryRanges() 使用一个空数据流初始化对象。 
 void addNum(int val) 向数据流中加入整数 val 。 
 int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。 
 

 

 示例： 

 
输入：
["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", 
"getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
[[], [1], [], [3], [], [7], [], [2], [], [6], []]
输出：
[null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], 
null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]

解释：
SummaryRanges summaryRanges = new SummaryRanges();
summaryRanges.addNum(1);      // arr = [1]
summaryRanges.getIntervals(); // 返回 [[1, 1]]
summaryRanges.addNum(3);      // arr = [1, 3]
summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
summaryRanges.addNum(7);      // arr = [1, 3, 7]
summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
 

 

 提示： 

 
 0 <= val <= 10⁴ 
 最多调用 addNum 和 getIntervals 方法 3 * 10⁴ 次 
 
 
 

 

 进阶：如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办? 
 Related Topics 设计 二分查找 有序集合 👍 144 👎 0

*/

import java.util.*;

/**
 * [352]将数据流变为多个不相交区间
 * @author SqList
 * @createTime 2021-10-14 11:39:26
 **/
public class Q352DataStreamAsDisjointIntervals {
    public static void main(String[] args) {
        SummaryRanges solution = new Q352DataStreamAsDisjointIntervals().new SummaryRanges();
        solution.addNum(1);
        System.out.println(Arrays.deepToString(solution.getIntervals()));
        solution.addNum(3);
        System.out.println(Arrays.deepToString(solution.getIntervals()));
        solution.addNum(7);
        System.out.println(Arrays.deepToString(solution.getIntervals()));
        solution.addNum(2);
        System.out.println(Arrays.deepToString(solution.getIntervals()));
        solution.addNum(6);
        System.out.println(Arrays.deepToString(solution.getIntervals()));
        solution.addNum(9);
        System.out.println(Arrays.deepToString(solution.getIntervals()));
        solution.addNum(10);
        System.out.println(Arrays.deepToString(solution.getIntervals()));
        solution.addNum(8);
        System.out.println(Arrays.deepToString(solution.getIntervals()));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 二分+优化维护区间
     * 直接使用 TreeMap 或 TreeSet
     */
    class SummaryRanges {

        TreeMap<Integer, Integer> res;

        public SummaryRanges() {
            res = new TreeMap<>();
        }

        public void addNum(int val) {
            Map.Entry<Integer, Integer> leftEntry = res.floorEntry(val);
            Map.Entry<Integer, Integer> rightEntry = res.ceilingEntry(val);

            if ((leftEntry != null && leftEntry.getKey() <= val && val <= leftEntry.getValue())
                    || (rightEntry != null && rightEntry.getKey() <= val && val <= rightEntry.getValue())) {
                // pass
            } else {
                boolean left = leftEntry != null && leftEntry.getValue() == val - 1;
                boolean right = rightEntry != null && rightEntry.getKey() == val + 1;
                if (left && right) {
                    res.put(leftEntry.getKey(), rightEntry.getValue());
                    res.remove(rightEntry.getKey());
                } else if (left) {
                    res.put(leftEntry.getKey(), val);
                } else if (right) {
                    res.put(val, rightEntry.getValue());
                    res.remove(rightEntry.getKey());
                } else {
                    res.put(val, val);
                }
            }
        }

        public int[][] getIntervals() {
            int size = res.size();
            int[][] ans = new int[size][2];
            int index = 0;
            for (Map.Entry<Integer, Integer> entry : res.entrySet()) {
                int left = entry.getKey(), right = entry.getValue();
                ans[index][0] = left;
                ans[index][1] = right;
                ++index;
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 并查集 优化
     */
    class SummaryRangesUnionFindOpt {

        private int[] father;
        // 使用有序的set存储左边界
        private Set<Integer> set = new TreeSet<>();

        public SummaryRangesUnionFindOpt() {
            father = new int[10003];
            Arrays.fill(father, -1);
        }

        public void addNum(int val) {
            if (father[val] == -1) {
                father[val] = val;
                set.add(val);
                // 先尝试合并右边
                union(val, val + 1);
                union(val - 1, val);
            }
        }

        private int find(int i) {
            if (father[i] != i) {
                return father[i] = find(father[i]);
            }
            return father[i];
        }

        private void union(int x, int y) {
            if (0 <= x && x <= 10000 && father[x] != -1 && father[y] != -1) {
                int xf = find(x);
                int yf = find(y);
                if (xf != yf) {
                    father[xf] = yf;
                    // 可以合并，set中删除右边的那个数
                    set.remove(y);
                }
            }
        }

        public int[][] getIntervals() {
            List<int[]> res = new ArrayList<>();
            for (int left : set) {
                res.add(new int[]{left, find(left)});
            }
            return res.toArray(new int[res.size()][2]);
        }
    }

    /**
     * 并查集
     */
    class SummaryRangesUnionFind {

        private int[] father;

        public SummaryRangesUnionFind() {
            father = new int[10003];
            Arrays.fill(father, -1);
        }

        public void addNum(int val) {
            if (father[val] == -1) {
                father[val] = val;
                // 先尝试合并右边
                union(val, val + 1);
                union(val - 1, val);
            }
        }

        private int find(int i) {
            if (father[i] != i) {
                return father[i] = find(father[i]);
            }
            return father[i];
        }

        private void union(int x, int y) {
            if (0 <= x && x <= 10000 && father[x] != -1 && father[y] != -1) {
                int xf = find(x);
                int yf = find(y);
                if (xf != yf) {
                    father[xf] = yf;
                }
            }
        }

        public int[][] getIntervals() {
            List<int[]> res = new ArrayList<>();
            for (int i = 0; i <= 10000; i++) {
                if (father[i] != -1) {
                    int start = i;
                    // 快速找到右边界
                    int end = find(i);
                    res.add(new int[]{start, end});
                    i = end;
                }
            }
            return res.toArray(new int[res.size()][2]);
        }
    }

    /**
     * 二分+优化维护区间
     * 直接使用 TreeMap 或 TreeSet
     */
    class SummaryRangesTreeMap {

        TreeMap<Integer, Integer> res;

        public SummaryRangesTreeMap() {
            res = new TreeMap<>();
        }

        public void addNum(int val) {
            Map.Entry<Integer, Integer> leftEntry = res.floorEntry(val);
            Map.Entry<Integer, Integer> rightEntry = res.ceilingEntry(val);

            if ((leftEntry != null && leftEntry.getKey() <= val && val <= leftEntry.getValue())
                    || (rightEntry != null && rightEntry.getKey() <= val && val <= rightEntry.getValue())) {
                // pass
            } else {
                boolean left = leftEntry != null && leftEntry.getValue() == val - 1;
                boolean right = rightEntry != null && rightEntry.getKey() == val + 1;
                if (left && right) {
                    res.put(leftEntry.getKey(), rightEntry.getValue());
                    res.remove(rightEntry.getKey());
                } else if (left) {
                    res.put(leftEntry.getKey(), val);
                } else if (right) {
                    res.put(val, rightEntry.getValue());
                    res.remove(rightEntry.getKey());
                } else {
                    res.put(val, val);
                }
            }
        }

        public int[][] getIntervals() {
            int size = res.size();
            int[][] ans = new int[size][2];
            int index = 0;
            for (Map.Entry<Integer, Integer> entry : res.entrySet()) {
                int left = entry.getKey(), right = entry.getValue();
                ans[index][0] = left;
                ans[index][1] = right;
                ++index;
            }
            return ans;
        }
    }

    /**
     * 二分+朴素维护区间
     */
    class SummaryRangesSelf {

        List<int[]> res;

        public SummaryRangesSelf() {
            res = new ArrayList<>();
        }

        public void addNum(int val) {
            int n = res.size();
            if (n == 0) {
                res.add(new int[]{val, val});
                return;
            }

            int l = 0, r = res.size() - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                int[] curr = res.get(mid);
                if (curr[0] <= val && val <= curr[1]) {
                    return;
                } else if (val < curr[0]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            // 此时 r + 1 == l
            boolean left = 0 <= r && res.get(r)[1] == val - 1;
            boolean right = l <= res.size() - 1 && res.get(l)[0] == val + 1;
            // 插入与删除 复杂度为 O(n)
            if (left && right) {
                // 最开始没有想到这种情况
                res.get(r)[1] = res.get(l)[1];
                res.remove(l);
            } else if (left) {
                res.get(r)[1] = val;
            } else if (right) {
                res.get(l)[0] = val;
            } else {
                res.add(l, new int[]{val, val});
            }
        }

        public int[][] getIntervals() {
            int n = res.size();
            int[][] ans = new int[n][2];
            for (int i = 0; i < n; i++) {
                ans[i] = res.get(i);
            }

            return ans;
        }
    }
}