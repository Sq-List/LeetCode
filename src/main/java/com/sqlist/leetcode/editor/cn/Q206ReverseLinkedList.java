package com.sqlist.leetcode.editor.cn;

import com.sqlist.leetcode.editor.cn.pojo.ListNode;

import java.util.Objects;

//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1670 👎 0

/**
 * @author SqList
 * @createTime 2021-04-08 20:14:20
 **/
public class Q206ReverseLinkedList {
    public static void main(String[] args) {
        Solution solution = new Q206ReverseLinkedList().new Solution();
        
        ListNode five = new ListNode(5, null);
        ListNode four = new ListNode(4, five);
        ListNode three = new ListNode(3, four);
        ListNode two = new ListNode(2, three);
        ListNode one = new ListNode(1, two);

        one.printList();

        ListNode reverseList = solution.reverseList(one);
        reverseList.printList();

        solution.reverseList(null);
    }

    /**
     * 迭代 方法
     */
    class SolutionIteration {
        public ListNode reverseList(ListNode head) {
            ListNode pre = null;
            while (Objects.nonNull(head)) {
                ListNode next = head.next;
                head.next = pre;

                pre = head;
                head = next;
            }
            return pre;
        }
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 递归 方法
     */
    class Solution {
        public ListNode reverseList(ListNode head) {
            if (Objects.isNull(head) || Objects.isNull(head.next)) {
                return head;
            }
            ListNode newHead = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return newHead;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}