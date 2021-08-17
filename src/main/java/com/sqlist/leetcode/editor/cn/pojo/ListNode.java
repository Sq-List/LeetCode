package com.sqlist.leetcode.editor.cn.pojo;

import com.sqlist.leetcode.editor.cn.Q206ReverseLinkedList;

import java.util.Objects;

/**
 *
 *
 * @author sqlist
 * @createTime 2021/4/28 11:03 周三
 */
public class ListNode {

    private int val;
    public ListNode next;

    public ListNode() { }

    public ListNode(int val) { this.val = val; }

    public ListNode(int val, ListNode next) { this.val = val;this.next = next; }

    public void printList() {
        ListNode head = this;
        System.out.print("list: ");
        while (Objects.nonNull(head)) {
            System.out.print(head.val + " --> ");
            head = head.next;
        }
        System.out.println("null");
    }
}
