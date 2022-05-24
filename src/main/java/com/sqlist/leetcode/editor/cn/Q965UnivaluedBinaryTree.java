package com.sqlist.leetcode.editor.cn;

/**
如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。 

 只有给定的树是单值二叉树时，才返回 true；否则返回 false。 

 

 示例 1： 

 

 输入：[1,1,1,1,1,null,1]
输出：true
 

 示例 2： 

 

 输入：[2,2,2,5,2]
输出：false
 

 

 提示： 

 
 给定树的节点数范围是 [1, 100]。 
 每个节点的值都是整数，范围为 [0, 99] 。 
 
 Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 138 👎 0

*/

import com.sqlist.leetcode.editor.cn.pojo.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * [965]单值二叉树
 * @author SqList
 * @createTime 2022-05-24 15:19:30
 **/
public class Q965UnivaluedBinaryTree {
    public static void main(String[] args) {
        Solution solution = new Q965UnivaluedBinaryTree().new Solution();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * bfs
     */
    class Solution {
        public boolean isUnivalTree(TreeNode root) {
            int num = root.val;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                if (node.val != num) {
                    return false;
                }

                queue.offer(node.left);
                queue.offer(node.right);
            }

            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * dfs
     */
    class SolutionDfs {
        public boolean isUnivalTree(TreeNode root) {
            int num = root.val;

            return dfs(root, num);
        }

        public boolean dfs(TreeNode root, int num) {
            if (root == null) {
                return true;
            }

            return root.val == num && dfs(root.left, num) && dfs(root.right, num);
        }
    }

}