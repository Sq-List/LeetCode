package com.sqlist.leetcode.editor.cn;
//给你一个链表的头节点 head 。 
//
// 移除每个右侧有一个更大数值的节点。 
//
// 返回修改后链表的头节点 head 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [5,2,13,3,8]
//输出：[13,8]
//解释：需要移除的节点是 5 ，2 和 3 。
//- 节点 13 在节点 5 右侧。
//- 节点 13 在节点 2 右侧。
//- 节点 8 在节点 3 右侧。
// 
//
// 示例 2： 
//
// 
//输入：head = [1,1,1,1]
//输出：[1,1,1,1]
//解释：每个节点的值都是 1 ，所以没有需要移除的节点。
// 
//
// 
//
// 提示： 
//
// 
// 给定列表中的节点数目在范围 [1, 10⁵] 内 
// 1 <= Node.val <= 10⁵ 
// 
//
// Related Topics 栈 递归 链表 单调栈 👍 61 👎 0


import com.sqlist.leetcode.editor.cn.pojo.ListNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

public class Q2487RemoveNodesFromLinkedList {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Class<?> clz = Class.forName("com.sqlist.leetcode.editor.cn.Q2487RemoveNodesFromLinkedList");
        String file = Objects.requireNonNull(clz.getResource("/testcase/Q2487")).getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            line = line.substring(1, line.length() - 1);
            String[] splitArr = line.split(",");

            ListNode pre = null;
            for (int i = splitArr.length - 1; i >= 0; i--) {
                pre = new ListNode(Integer.parseInt(splitArr[i]), pre);
            }

            pre.printList();
            long start = System.currentTimeMillis();
            Solution solution = new Q2487RemoveNodesFromLinkedList().new Solution();
            solution.removeNodes(pre).printList();
            
            long end = System.currentTimeMillis();
            System.out.println("cost: " + (end - start));
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
        public ListNode removeNodes(ListNode head) {
            head = reverse(head);
            for (ListNode p = head; p.next != null; ) {
                if (p.val > p.next.val) {
                    p.next = p.next.next;
                } else {
                    p = p.next;
                }
            }
            return reverse(head);
        }

        public ListNode reverse(ListNode head) {
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
    //leetcode submit region end(Prohibit modification and deletion)

    class SolutionStackTwo {
        public ListNode removeNodes(ListNode head) {
            Stack<ListNode> stack = new Stack<>();
            for (; head != null; head = head.next) {
                stack.push(head);
            }
            for (; !stack.empty(); stack.pop()) {
                if (head == null || stack.peek().val >= head.val) {
                    stack.peek().next = head;
                    head = stack.peek();
                }
            }
            return head;
        }
    }

    class SolutionRecursion {
        public ListNode removeNodes(ListNode head) {
            if (head == null) {
                return null;
            }
            head.next = removeNodes(head.next);
            if (head.next != null && head.val < head.next.val) {
                return head.next;
            } else {
                return head;
            }
        }
    }

    class SolutionStack {
        public ListNode removeNodes(ListNode head) {
            Stack<ListNode> stack = new Stack<>();
            ListNode first = new ListNode(0, head);

            ListNode curr = first;
            ListNode ans = null;
            int max = 0;
            while (curr.next != null) {
                curr = curr.next;

                if (stack.empty()) {
                    stack.add(curr);
                    ans = curr;
                    max = curr.val;
                } else {
                    if (curr.val > max) {
                        max = curr.val;
                        stack.clear();
                    } else {
                        // 前一个节点 < 当前节点 需要把之前所有 < 当前节点的节点删除
                        while (!stack.empty() && stack.peek().val < curr.val) {
                            stack.pop();
                        }
                    }

                    if (stack.empty()) {
                        // 如果之前所有的节点都小于当前 则 结果值的头结点从当前节点开始
                        ans = curr;
                        max = curr.val;
                    } else {
                        // 如果之前还存在 大于 当前节点 则之前节点的next需要设置为当前节点
                        stack.peek().next = curr;
                    }
                    // 将当前节点加入到stack中
                    stack.add(curr);
                }
            }

            return ans;
        }
    }
}











