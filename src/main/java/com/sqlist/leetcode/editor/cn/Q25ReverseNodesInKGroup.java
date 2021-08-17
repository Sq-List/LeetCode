package com.sqlist.leetcode.editor.cn;

//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 进阶： 
//
// 
// 你可以设计一个只使用常数额外空间的算法来解决此问题吗？ 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2,3,4,5], k = 1
//输出：[1,2,3,4,5]
// 
//
// 示例 4： 
//
// 
//输入：head = [1], k = 1
//输出：[1]
// 
//
// 
// 
//
// 提示： 
//
// 
// 列表中节点的数量在范围 sz 内 
// 1 <= sz <= 5000 
// 0 <= Node.val <= 1000 
// 1 <= k <= sz 
// 
// Related Topics 递归 链表 
// 👍 1242 👎 0


import com.sqlist.leetcode.editor.cn.pojo.ListNode;

/**
 * @author SqList
 * @createTime 2021-08-15 16:05:04
 **/
public class Q25ReverseNodesInKGroup {
    public static void main(String[] args) {
        Solution solution = new Q25ReverseNodesInKGroup().new Solution();

        ListNode five = new ListNode(5);
        ListNode four = new ListNode(4, five);
        ListNode three = new ListNode(3, four);
        ListNode two = new ListNode(2, three);
        ListNode one = new ListNode(1, two);

        ListNode listNode = solution.reverseKGroup(one, 2);
        listNode.printList();
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
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummyNode = new ListNode(Integer.MIN_VALUE, head);

            if (k == 1) {
                return dummyNode.next;
            }

            ListNode curr = dummyNode;
            while (true) {
                boolean flag = true;

                ListNode pre = curr;
                ListNode start = curr.next;
                for (int i = 0; i <= k; i++) {
                    if (curr == null) {
                        flag = false;
                        break;
                    }

                    curr = curr.next;
                }

                if (!flag) {
                    break;
                }

                ListNode newHead = reverseList(start, curr);
                start.next = curr;
                pre.next = newHead;

                curr = start;
            }
            return dummyNode.next;
        }

        public ListNode reverseList(ListNode start, ListNode end) {
            ListNode pre = null;
            ListNode curr = start;
            while (curr != end) {
                ListNode next = curr.next;
                curr.next = pre;

                pre = curr;
                curr = next;
            }

            return pre;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}