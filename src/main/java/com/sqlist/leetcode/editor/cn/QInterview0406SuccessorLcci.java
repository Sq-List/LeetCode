package com.sqlist.leetcode.editor.cn;

/**
设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。 

 如果指定节点没有对应的“下一个”节点，则返回null。 

 示例 1: 

 输入: root = [2,1,3], p = 1

  2
 / \
1   3

输出: 2 

 示例 2: 

 输入: root = [5,3,6,2,4,null,null,1], p = 6

      5
     / \
    3   6
   / \
  2   4
 /   
1

输出: null 
 Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 135 👎 0

*/

import com.sqlist.leetcode.editor.cn.pojo.TreeNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

/**
 * [面试题 04.06]后继者
 * @author SqList
 * @createTime 2022-05-16 13:05:19
 **/
public class QInterview0406SuccessorLcci {
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

        String file = Objects.requireNonNull(Q391PerfectRectangle.class.getResource("/testcase/QInterview0406")).getFile();
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            line = in.readLine();

            TreeNode ret = new QInterview0406SuccessorLcci().new SolutionIterate().inorderSuccessor(root, new TreeNode(Integer.parseInt(line)));

            System.out.println(Objects.nonNull(ret) ? ret.val : null);
        }

    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 中序遍历的下一个节点一定是以下情况：
     *      1. 右节点的左子树下最左的那个（右节点是特殊情况）
     *      2. 父节点
     */
    class Solution {
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            return find(root, p);
        }

        public TreeNode find(TreeNode root, TreeNode p) {
            if (root == null) {
                return null;
            }

            // 遍历左子树
            TreeNode treeNode = find(root.left, p);
            if (treeNode != null) {
                // 不为空 说明左子树中有结果
                return treeNode;
            }

            // 二叉搜索树特性
            if (root.val > p.val) {
                return root;
            }

            // 该节点及其左子树都找不到p节点 再继续从右节点找
            return find(root.right, p);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 遍历
     */
    class SolutionIterate {
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            Stack<TreeNode> stack = new Stack<>();

            boolean flag = false;
            TreeNode node = root;

            while (node != null || !stack.empty()) {
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }

                if (!stack.empty()) {
                    TreeNode pop = stack.pop();
                    if (flag) {
                        return pop;
                    } else if (p.val == pop.val) {
                        flag = true;
                    }

                    node = pop.right;
                }
            }

            return null;
        }
    }

}