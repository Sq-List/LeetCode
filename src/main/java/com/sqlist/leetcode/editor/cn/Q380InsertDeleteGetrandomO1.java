package com.sqlist.leetcode.editor.cn;

/**
设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。 

 
 insert(val)：当元素 val 不存在时，向集合中插入该项。 
 remove(val)：元素 val 存在时，从集合中移除该项。 
 getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。 
 

 示例 : 

 
// 初始化一个空的集合。
RandomizedSet randomSet = new RandomizedSet();

// 向集合中插入 1 。返回 true 表示 1 被成功地插入。
randomSet.insert(1);

// 返回 false ，表示集合中不存在 2 。
randomSet.remove(2);

// 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
randomSet.insert(2);

// getRandom 应随机返回 1 或 2 。
randomSet.getRandom();

// 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
randomSet.remove(1);

// 2 已在集合中，所以返回 false 。
randomSet.insert(2);

// 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
randomSet.getRandom();
 
 Related Topics 设计 数组 哈希表 数学 随机化 👍 355 👎 0

*/

import java.util.*;

/**
 * @author SqList
 * @createTime 2021-09-01 00:33:22
 **/
public class Q380InsertDeleteGetrandomO1 {
    public static void main(String[] args) {
        RandomizedSet solution = new Q380InsertDeleteGetrandomO1().new RandomizedSet();
        System.out.println(solution.insert(0));
        System.out.println(solution.insert(1));
        System.out.println(solution.remove(0));
        System.out.println(solution.insert(2));
        System.out.println(solution.remove(1));
        System.out.println(solution.getRandom());
        // System.out.println(solution.getRandom());
        // System.out.println(solution.getRandom());
        // System.out.println(solution.getRandom());
        // System.out.println(solution.getRandom());
        // System.out.println(solution.getRandom());
        // System.out.println(solution.insert(3));
        // System.out.println(solution.insert(4));
        // System.out.println(solution.insert(5));
        // System.out.println(solution.getRandom());
        // System.out.println(solution.getRandom());
    }

    /**
     * 由于 `随机返回现有集合中的一项。每个元素应该有相同的概率被返回。 ` 所以一定要满足 `底层用数组实现，且数组必须是紧凑的。`
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class RandomizedSet {

        // 记录数字
        private List<Integer> list;

        // 记录数字到index
        private Map<Integer, Integer> valueToIndex;

        Random rand = new Random();

        /** Initialize your data structure here. */
        public RandomizedSet() {
            list = new ArrayList<>();
            valueToIndex = new HashMap<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (valueToIndex.containsKey(val)) {
                return false;
            }

            valueToIndex.put(val, list.size());
            list.add(list.size(), val);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!valueToIndex.containsKey(val)) {
                return false;
            }

            // 获取val的index
            Integer index = valueToIndex.get(val);
            // 获取list的最后一个value
            Integer lastValue = list.get(list.size() - 1);

            // 将list中 原来的val 的位置 替换为 lastValue
            list.set(index, lastValue);
            // 并更新valueToIndex 中 lastValue 的下标
            valueToIndex.put(lastValue, index);

            // 这步可不做  因为反正都要删除list的最后一个
            // list.set(list.size() - 1, val);
            // 删除最后一个
            list.remove(list.size() - 1);
            // 删除valueToIndex中的val
            valueToIndex.remove(val);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return list.get(rand.nextInt(list.size()));
        }
    }

    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */
    //leetcode submit region end(Prohibit modification and deletion)

}