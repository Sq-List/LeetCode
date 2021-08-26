package com.sqlist.leetcode.editor.cn;

/**
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 

 进阶：你能尝试使用一趟扫描实现吗？ 

 

 示例 1： 

 
输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]
 

 示例 2： 

 
输入：head = [1], n = 1
输出：[]
 

 示例 3： 

 
输入：head = [1,2], n = 1
输出：[1]
 

 

 提示： 

 
 链表中结点的数目为 sz 
 1 <= sz <= 30 
 0 <= Node.val <= 100 
 1 <= n <= sz 
 
 Related Topics 链表 双指针 👍 1525 👎 0

*/

import com.sqlist.leetcode.editor.cn.pojo.ListNode;

/**
 * https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247488584&idx=1&sn=90f7956fd9d8320fcb81aaf33c3fe7f1&chksm=9bd7ea40aca06356cdb87ba86518c50646b48b8534d42625ba454c084187400b979c8d736a61&scene=21#wechat_redirect
 *
 * @author SqList
 * @createTime 2021-08-26 16:01:12
 **/
public class Q19RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new Q19RemoveNthNodeFromEndOfList().new Solution();

        ListNode five = new ListNode(5);
        ListNode four = new ListNode(4, five);
        ListNode three = new ListNode(3, four);
        ListNode two = new ListNode(2, three);
        ListNode one = new ListNode(1, two);

        solution.removeNthFromEnd(one, 2).printList();

        ListNode two1 = new ListNode(2, null);
        ListNode one1 = new ListNode(1, two1);
        solution.removeNthFromEnd(one1, 1).printList();
    }

    class SolutionTwo {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode fast = head;

            // 快指针先前进 n 步
            while (n-- > 0) {
                fast = fast.next;
            }

            if (fast == null) {
                // 如果此时快指针走到头了，
                // 说明倒数第 n 个节点就是第一个节点
                return head.next;
            }

            ListNode slow = head;
            // 让慢指针和快指针同步向前
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }

            // slow.next 就是倒数第 n 个节点，删除它
            slow.next = slow.next.next;

            return head;
        }
    }

    /**
     * 快慢指针
     */
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
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummyNode = new ListNode(Integer.MIN_VALUE, head);

            ListNode fast = dummyNode;
            while (n-- > 0) {
                fast = fast.next;
            }

            ListNode slow = dummyNode;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }

            slow.next = slow.next.next;

            return dummyNode.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}