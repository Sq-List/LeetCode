package com.sqlist.leetcode.editor.cn;

/**
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

 示例:

 输入: [0,1,0,3,12]
输出: [1,3,12,0,0]

 说明:


 必须在原数组上操作，不能拷贝额外的数组。
 尽量减少操作次数。

 Related Topics 数组 双指针 👍 1202 👎 0

*/

import java.util.Arrays;

/**
 * @author SqList
 * @createTime 2021-08-31 10:32:00
 **/
public class Q283MoveZeroes {
    public static void main(String[] args) {
        Solution solution = new Q283MoveZeroes().new Solution();
        int[] nums1 = {0, 1, 0, 3, 12};
        solution.moveZeroes(nums1);
        System.out.println(Arrays.toString(nums1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void moveZeroes(int[] nums) {
            int slow = 0, fast = 0;
            for (; fast < nums.length; fast++) {
                if (nums[fast] != 0) {
                    nums[slow++] = nums[fast];
                }
            }

            for (; slow < nums.length; slow++) {
                nums[slow] = 0;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}