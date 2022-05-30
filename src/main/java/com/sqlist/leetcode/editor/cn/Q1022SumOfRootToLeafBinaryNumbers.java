package com.sqlist.leetcode.editor.cn;

/**
给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。 

 
 例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。 
 

 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。 

 返回这些数字之和。题目数据保证答案是一个 32 位 整数。 

 

 示例 1： 

 
输入：root = [1,0,1,0,1,0,1]
输出：22
解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 

 示例 2： 

 
输入：root = [0]
输出：0
 

 

 提示： 

 
 树中的节点数在 [1, 1000] 范围内 
 Node.val 仅为 0 或 1 
 
 Related Topics 树 深度优先搜索 二叉树 👍 184 👎 0

*/

import com.sqlist.leetcode.editor.cn.pojo.TreeNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * [1022]从根到叶的二进制数之和
 * @author SqList
 * @createTime 2022-05-30 15:14:07
 **/
public class Q1022SumOfRootToLeafBinaryNumbers {

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

    public static void main(String[] args) throws IOException {
        String file = Objects.requireNonNull(Q1022SumOfRootToLeafBinaryNumbers.class.getResource("/testcase/Q1022")).getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            Solution solution = new Q1022SumOfRootToLeafBinaryNumbers().new Solution();
            int ret = solution.sumRootToLeaf(root);
            System.out.println(ret);
        }

    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int sumRootToLeaf(TreeNode root) {
            return dfs(root, 0);
        }

        private int dfs(TreeNode root, int preSum) {
            if (root == null) {
                return 0;
            }

            int sum = (preSum << 1) | root.val;
            if (root.left == null && root.right == null) {
                return sum;
            }

            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}