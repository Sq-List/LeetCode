package com.sqlist.leetcode.editor.cn;

/**
给定一个二叉树，计算 整个树 的坡度 。 

 一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。
空结点的坡度是 0 。 

 整个树 的坡度就是其所有节点的坡度之和。 

 

 示例 1： 

 
输入：root = [1,2,3]
输出：1
解释：
节点 2 的坡度：|0-0| = 0（没有子节点）
节点 3 的坡度：|0-0| = 0（没有子节点）
节点 1 的坡度：|2-3| = 1（左子树就是左子节点，所以和是 2 ；右子树就是右子节点，所以和是 3 ）
坡度总和：0 + 0 + 1 = 1
 

 示例 2： 

 
输入：root = [4,2,9,3,5,null,7]
输出：15
解释：
节点 3 的坡度：|0-0| = 0（没有子节点）
节点 5 的坡度：|0-0| = 0（没有子节点）
节点 7 的坡度：|0-0| = 0（没有子节点）
节点 2 的坡度：|3-5| = 2（左子树就是左子节点，所以和是 3 ；右子树就是右子节点，所以和是 5 ）
节点 9 的坡度：|0-7| = 7（没有左子树，所以和是 0 ；右子树正好是右子节点，所以和是 7 ）
节点 4 的坡度：|(3+5+2)-(9+7)| = |10-16| = 6（左子树值为 3、5 和 2 ，和是 10 ；右子树值为 9 和 7 ，和是 16 
）
坡度总和：0 + 0 + 0 + 2 + 7 + 6 = 15
 

 示例 3： 

 
输入：root = [21,7,14,1,1,2,2,3,3]
输出：9
 

 

 提示： 

 
 树中节点数目的范围在 [0, 10⁴] 内 
 -1000 <= Node.val <= 1000 
 
 Related Topics 树 深度优先搜索 二叉树 👍 157 👎 0

*/

import com.sqlist.leetcode.editor.cn.pojo.TreeNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * [563]二叉树的坡度
 * @author SqList
 * @createTime 2021-11-18 00:25:54
 **/
public class Q563BinaryTreeTilt {

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
        String file = Objects.requireNonNull(Q563BinaryTreeTilt.class.getResource("/testcase/Q563")).getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            TreeNode treeNode = stringToTreeNode(line);

            long start = System.currentTimeMillis();
            Solution solution = new Q563BinaryTreeTilt().new Solution();
            System.out.println(solution.findTilt(treeNode));
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * dfs
     * 后序遍历
     */
    class Solution {

        private int ans;

        public int findTilt(TreeNode root) {
            ans = 0;
            dfs(root);
            return ans;
        }

        public int dfs(TreeNode curr) {
            if (Objects.isNull(curr)) {
                return 0;
            }

            int leftSum = dfs(curr.left);
            int rightSum = dfs(curr.right);

            ans += Math.abs(leftSum - rightSum);
            return curr.val + leftSum + rightSum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}