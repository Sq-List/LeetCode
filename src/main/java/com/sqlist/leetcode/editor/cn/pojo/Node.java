package com.sqlist.leetcode.editor.cn.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * TODO
 *
 * @author sqlist
 * @createTime 2021/9/24 10:41 周五
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Node {

    public int val;

    public Node prev;

    public Node next;

    public Node child;
}
