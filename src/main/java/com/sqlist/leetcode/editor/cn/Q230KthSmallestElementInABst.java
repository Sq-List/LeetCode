package com.sqlist.leetcode.editor.cn;

/**
给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。 

 

 示例 1： 

 
输入：root = [3,1,4,null,2], k = 1
输出：1
 

 示例 2： 

 
输入：root = [5,3,6,2,4,null,null,1], k = 3
输出：3
 

 

 

 提示： 

 
 树中的节点数为 n 。 
 1 <= k <= n <= 10⁴ 
 0 <= Node.val <= 10⁴ 
 

 

 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？ 
 Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 502 👎 0

*/

import com.sqlist.leetcode.editor.cn.pojo.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * [230]二叉搜索树中第K小的元素
 * @author SqList
 * @createTime 2021-10-17 21:13:51
 **/
public class Q230KthSmallestElementInABst {

    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Solution solution = new Q230KthSmallestElementInABst().new Solution();
        // System.out.println(solution.kthSmallest(stringToTreeNode("[3,1,4,null,2]"), 1));
        System.out.println(solution.kthSmallest(stringToTreeNode("[5,3,6,2,4,null,null,1]"), 3));
        System.out.println("-------------------");
        System.out.println(solution.kthSmallest(stringToTreeNode("[5,3,6,2,4,null,null,1]"), 4));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 中序遍历 - 递归
     */
    class Solution {
        private int res;
        private int rank;

        public int kthSmallest(TreeNode root, int k) {
            rank = 0;
            middle(root, k);
            return res;
        }

        private void middle(TreeNode root, int k) {
            if (root == null || rank > k) {
                return;
            }

            middle(root.left, k);
            System.out.println(root.val);
            rank ++;
            if (k == rank) {
                res = root.val;
                return;
            }
            middle(root.right, k);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 中序遍历 - 迭代
     * 迭代可以在找到答案后直接停止 不需要遍历整棵树
     */
    class SolutionMiddleIter {
        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> stack = new Stack<>();
            while (root != null || !stack.empty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                k--;
                if (k == 0) {
                    break;
                }
                root = root.right;
            }

            return root.val;
        }
    }
}