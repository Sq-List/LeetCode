package com.sqlist.leetcode.editor.cn.pojo;

/**
 * TODO
 *
 * @author sqlist
 * @createTime 2021/9/28 01:17 周二
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
