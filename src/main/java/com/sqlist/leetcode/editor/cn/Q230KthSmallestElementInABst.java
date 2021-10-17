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

import java.util.Stack;

/**
 * [230]二叉搜索树中第K小的元素
 * @author SqList
 * @createTime 2021-10-17 21:13:51
 **/
public class Q230KthSmallestElementInABst {
    public static void main(String[] args) {
        Solution solution = new Q230KthSmallestElementInABst().new Solution();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 中序遍历
     */
    class Solution {
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
    //leetcode submit region end(Prohibit modification and deletion)

}