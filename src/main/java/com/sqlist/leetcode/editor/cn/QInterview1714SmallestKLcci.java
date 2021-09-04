package com.sqlist.leetcode.editor.cn;

/**
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 * <p>
 * 示例：
 * <p>
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 * <p>
 * Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 100 👎 0
 */

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * [面试题 17.14]最小K个数
 * @author SqList
 * @createTime 2021-09-03 10:11:16
 **/
public class QInterview1714SmallestKLcci {
    public static void main(String[] args) {
        Solution solution = new QInterview1714SmallestKLcci().new Solution();
        System.out.println(Arrays.toString(solution.smallestK(new int[]{1, 3, 5, 7, 2, 4, 6, 8}, 4)));
    }

    /**
     * 排序
     */
    class SolutionSort {
        public int[] smallestK(int[] arr, int k) {
            Arrays.sort(arr);
            return Arrays.copyOf(arr, k);
        }
    }

    // -----------------------------------------------

    /**
     * 最大堆
     */
    class MaxHeap {

        private int N = 0;

        private int[] heap;

        public MaxHeap(int cap) {
            heap = new int[cap + 1];
        }

        public int left(int root) {
            return root * 2;
        }

        public int right(int root) {
            return root * 2 + 1;
        }

        public int parent(int root) {
            return root / 2;
        }

        public int max() {
            return heap[1];
        }

        public void insert(int val) {
            N++;
            heap[N] = val;
            swim(N);
        }

        public int delMax() {
            int minVal = heap[1];
            change(1, N);
            N--;
            sink(1);
            return minVal;
        }

        /**
         * 上浮
         */
        private void swim(int k) {
            // 到最上层就不能在上浮了
            while (k > 1 && less(parent(k), k)) {
                change(parent(k), k);
                k = parent(k);
            }
        }

        /**
         * 下沉
         */
        private void sink(int k) {
            // 到最下层就不能在下沉了
            while (left(k) <= N) {
                // 先假设左子节点更小
                int miner = left(k);
                // 如果右子节点存在 则比较一下大小
                if (right(k) <= N && less(miner, right(k))) {
                    miner = right(k);
                }

                if (less(miner, k)) {
                    break;
                }

                change(miner, k);
                k = miner;
            }
        }

        /**
         * heap[i] < heap[j] 返回true
         */
        protected boolean less(int i, int j) {
            return heap[i] < heap[j];
        }

        private void change(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }

    class SolutionMaxHeap {
        public int[] smallestK(int[] arr, int k) {
            MaxHeap maxHeap = new MaxHeap(k);
            for (int i = 0; i < k; i++) {
                maxHeap.insert(arr[i]);
            }

            for (int i = k; i < arr.length; i++) {
                if (arr[i] < maxHeap.max()) {
                    maxHeap.delMax();
                    maxHeap.insert(arr[i]);
                }
            }

            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = maxHeap.delMax();
            }
            return res;
        }
    }

    // -----------------------------------------------

    /**
     * 最小堆
     */
    class MinHeap {

        private int N = 0;

        private int[] heap;

        public MinHeap(int cap) {
            heap = new int[cap + 1];
        }

        public int left(int root) {
            return root * 2;
        }

        public int right(int root) {
            return root * 2 + 1;
        }

        public int parent(int root) {
            return root / 2;
        }

        public int max() {
            return heap[1];
        }

        public void insert(int val) {
            N++;
            heap[N] = val;
            swim(N);
        }

        public int delMin() {
            int minVal = heap[1];
            change(1, N);
            N--;
            sink(1);
            return minVal;
        }

        /**
         * 上浮
         */
        private void swim(int k) {
            // 到最上层就不能在上浮了
            while (k > 1 && less(parent(k), k)) {
                change(parent(k), k);
                k = parent(k);
            }
        }

        /**
         * 下沉
         */
        private void sink(int k) {
            // 到最下层就不能在下沉了
            while (left(k) <= N) {
                // 先假设左子节点更小
                int miner = left(k);
                // 如果右子节点存在 则比较一下大小
                if (right(k) <= N && less(miner, right(k))) {
                    miner = right(k);
                }

                if (less(miner, k)) {
                    break;
                }

                change(miner, k);
                k = miner;
            }
        }

        /**
         * heap[i] < heap[j] 返回true
         */
        protected boolean less(int i, int j) {
            return heap[i] > heap[j];
        }

        private void change(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }

    class SolutionMinHeap {
        public int[] smallestK(int[] arr, int k) {
            MinHeap minHeap = new MinHeap(arr.length);
            for (int i = 0; i < k; i++) {
                minHeap.insert(arr[i]);
            }

            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = minHeap.delMin();
            }
            return res;
        }
    }

    // -----------------------------------------------

    /**
     * 利用java中的 PriorityQueue 类
     */
    class SolutionPriorityQueue {
        public int[] smallestK(int[] arr, int k) {
            int[] res = new int[k];
            if (k == 0) { // 排除 0 的情况
                return res;
            }

            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
            for (int i = 0; i < k; i++) {
                priorityQueue.add(arr[i]);
            }

            for (int i = k; i < arr.length; i++) {
                if (priorityQueue.peek() > arr[i]) {
                    priorityQueue.poll();
                    priorityQueue.add(arr[i]);
                }
            }

            for (int i = 0; i < k; i++) {
                res[i] = priorityQueue.poll();
            }
            return res;
        }
    }

    // -----------------------------------------------

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * TODO
     * 快排思想
     */
    class Solution {
        public int[] smallestK(int[] arr, int k) {
            return new int[0];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}