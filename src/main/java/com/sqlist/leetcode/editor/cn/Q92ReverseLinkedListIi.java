package com.sqlist.leetcode.editor.cn;

import com.sqlist.leetcode.editor.cn.pojo.ListNode;

import java.util.Objects;

//给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
//表节点，返回 反转后的链表 。
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], left = 2, right = 4
//输出：[1,4,3,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [5], left = 1, right = 1
//输出：[5]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目为 n 
// 1 <= n <= 500 
// -500 <= Node.val <= 500 
// 1 <= left <= right <= n 
// 
//
// 
//
// 进阶： 你可以使用一趟扫描完成反转吗？ 
// Related Topics 链表 
// 👍 855 👎 0

/**
 * @author SqList
 * @createTime 2021-04-08 20:01:11
 **/
public class Q92ReverseLinkedListIi {
    public static void main(String[] args) {
        Solution solution = new Q92ReverseLinkedListIi().new Solution();
        ListNode five = new ListNode(5);
        ListNode four = new ListNode(4, five);
        ListNode three = new ListNode(3, four);
        ListNode two = new ListNode(2, three);
        ListNode one = new ListNode(1, two);

        one.printList();
        ListNode newList = solution.reverseBetween(one, 1, 4);
        one.printList();
        newList.printList();

        newList = solution.reverseBetween(newList, 1, 4);
        newList.printList();

        newList = solution.reverseBetween(newList, 1, 1);
        newList.printList();

        ListNode six = new ListNode(6);
        six.printList();
        newList = solution.reverseBetween(six, 1, 1);
        newList.printList();
    }

    class SolutionSelf {
        public ListNode reverseBetween(ListNode head, int left, int right) {

            int i = 1;
            ListNode pre = null;
            ListNode first = head;

            ListNode leftHeadBefore = null;
            while (Objects.nonNull(head)) {
                ListNode next = head.next;
                if (left <= i && i <= right) {
                    if (left == i) {
                        leftHeadBefore = pre;
                    } else if (right == i) {
                        if (left != 1) {
                            leftHeadBefore.next.next = head.next;
                            leftHeadBefore.next = head;
                        } else {
                            first.next = head.next;
                            first = head;
                        }
                        head.next = pre;
                        break;
                    } else {
                        head.next = pre;
                    }
                }

                pre = head;
                head = next;
                i++;
            }

            return first;
        }
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
        public ListNode reverseBetween(ListNode head, int left, int right) {
            ListNode dummyNode = new ListNode(Integer.MIN_VALUE, head);

            ListNode pre = dummyNode;
            for (int i = 1; i < left; i++) {
                pre = pre.next;
            }

            ListNode preF = pre;
            ListNode curr = pre.next;
            for (int i = left; i <= right; i++) {
                ListNode next = curr.next;
                curr.next = pre;

                pre = curr;
                curr = next;
            }

            preF.next.next = curr;
            preF.next = pre;

            return dummyNode.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}