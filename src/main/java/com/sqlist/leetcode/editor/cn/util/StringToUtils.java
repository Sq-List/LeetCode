package com.sqlist.leetcode.editor.cn.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sqlist.leetcode.editor.cn.pojo.ListNode;

/**
 * TODO
 *
 * @author sqlist
 * @createTime 2023/12/28 15:20 周四
 */
public class StringToUtils {

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static int[][] stringToInt2dArray(String input) {
        JSONArray jsonArray = JSON.parseArray(input);
        if (jsonArray.size() == 0) {
            return new int[0][0];
        }

        int[][] arr = new int[jsonArray.size()][];
        for (int i = 0; i < arr.length; i++) {
            JSONArray cols = jsonArray.getJSONArray(i);
            arr[i] = stringToIntegerArray(cols.toString());
        }
        return arr;
    }

    public static ListNode stringToListNode(String input) {
        input = input.substring(1, input.length() - 1);
        String[] splitArr = input.split(",");

        ListNode pre = null;
        for (int i = splitArr.length - 1; i >= 0; i--) {
            pre = new ListNode(Integer.parseInt(splitArr[i]), pre);
        }

        return pre;
    }
}
