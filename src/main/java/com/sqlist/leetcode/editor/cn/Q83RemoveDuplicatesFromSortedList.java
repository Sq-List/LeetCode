package com.sqlist.leetcode.editor.cn;

/**
存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。 

 返回同样按升序排列的结果链表。 

 

 示例 1： 

 
输入：head = [1,1,2]
输出：[1,2]
 

 示例 2： 

 
输入：head = [1,1,2,3,3]
输出：[1,2,3]
 

 

 提示： 

 
 链表中节点数目在范围 [0, 300] 内 
 -100 <= Node.val <= 100 
 题目数据保证链表已经按升序排列 
 
 Related Topics 链表 👍 632 👎 0

*/

import com.sqlist.leetcode.editor.cn.pojo.ListNode;

/**
 * @author SqList
 * @createTime 2021-08-28 16:42:37
 **/
public class Q83RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        Solution solution = new Q83RemoveDuplicatesFromSortedList().new Solution();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode dummyNode = new ListNode(Integer.MIN_VALUE, head);

            ListNode fast, slow;
            fast = slow = dummyNode;
            while (fast != null) {
                if (fast.val != slow.val) {
                    slow.next = fast;
                    slow = slow.next;
                }

                fast = fast.next;
            }

            slow.next = null;
            return dummyNode.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}