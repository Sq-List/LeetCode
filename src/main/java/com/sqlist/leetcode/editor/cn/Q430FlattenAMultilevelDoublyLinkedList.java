package com.sqlist.leetcode.editor.cn;

/**
多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多
级数据结构，如下面的示例所示。 

 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。 

 

 示例 1： 

 输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
输出：[1,2,3,7,8,11,12,9,10,4,5,6]
解释：

输入的多级列表如下图所示：



扁平化后的链表如下图：


 

 示例 2： 

 输入：head = [1,2,null,3]
输出：[1,3,2]
解释：

输入的多级列表如下图所示：

  1---2---NULL
  |
  3---NULL
 

 示例 3： 

 输入：head = []
输出：[]
 

 

 如何表示测试用例中的多级链表？ 

 以 示例 1 为例： 

  1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL 

 序列化其中的每一级之后： 

 [1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
 

 为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。 

 [1,2,3,4,5,6,null]
[null,null,7,8,9,10,null]
[null,11,12,null]
 

 合并所有序列化结果，并去除末尾的 null 。 

 [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12] 

 

 提示： 

 
 节点数目不超过 1000 
 1 <= Node.val <= 10^5 
 
 Related Topics 深度优先搜索 链表 双向链表 👍 252 👎 0

*/

import com.sqlist.leetcode.editor.cn.pojo.Node;

import java.util.Stack;

/**
 * [430]扁平化多级双向链表
 * @author SqList
 * @createTime 2021-09-24 10:41:00
 **/
public class Q430FlattenAMultilevelDoublyLinkedList {
    public static void main(String[] args) {
        Solution solution = new Q430FlattenAMultilevelDoublyLinkedList().new Solution();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 前序遍历
     */
    class Solution {
        public Node flatten(Node head) {
            if (head == null) {
                return null;
            }

            dfs(head);
            return head;
        }

        public Node dfs(Node curr) {
            Node child = curr.child;
            Node last = curr;

            if (child != null) {
                Node next = curr.next;
                // 返回下一级链表的最后一个非null节点
                last = dfs(child);

                curr.next = child;
                child.prev = curr;
                curr.child = null;

                last.next = next;
                if (next != null) {
                    next.prev = last;
                }
            }

            if (last.next == null) {
                return last;
            }

            return dfs(last.next);
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    /**
     * 栈
     */
    class SolutionStack {
        public Node flatten(Node head) {
            Stack<Node> stack = new Stack<>();

            Node curr = head;
            while (curr != null) {
                // 首先判断 curr的child
                if (curr.child != null) {
                    // 如果curr的next为null 则不加入stack
                    if (curr.next != null) {
                        // 这一级链表的余下的链表
                        stack.push(curr.next);
                    }

                    // 变换curr与curr.child的指针
                    curr.next = curr.child;
                    curr.child.prev = curr;
                    curr.child = null;

                    // 移到到curr.child
                    curr = curr.next;
                    continue;
                }

                // 接着判断 curr的next
                if (curr.next != null) {
                    // 没有 child 有next 则直接移动到next
                    curr = curr.next;
                } else {
                    // curr.next == null
                    // next为null时 说明这一级的链表走到头了 需要从stack中取出上一级链表 剩下的链表的头
                    // stack 为空 说明每一级链表都走到头了 退出
                    if (stack.empty()) {
                        break;
                    }

                    // 取出上一级链表 剩下链表的头
                    Node pop = stack.pop();
                    // 变换curr与pop的指针
                    curr.next = pop;
                    pop.prev = curr;
                    // 移动到 上一级链表 剩下链表的头 pop
                    curr = pop;
                }
            }

            return head;
        }
    }

}