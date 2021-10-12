package com.sqlist.leetcode.editor.cn;

/**
写一个程序，输出从 1 到 n 数字的字符串表示。 

 1. 如果 n 是3的倍数，输出“Fizz”； 

 2. 如果 n 是5的倍数，输出“Buzz”； 

 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。 

 示例： 

 n = 15,

返回:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
 
 Related Topics 数学 字符串 模拟 👍 108 👎 0

*/

import java.util.ArrayList;
import java.util.List;

/**
 * [412]Fizz Buzz
 * @author SqList
 * @createTime 2021-10-13 00:28:41
 **/
public class Q412FizzBuzz {
    public static void main(String[] args) {
        Solution solution = new Q412FizzBuzz().new Solution();
        System.out.println(solution.fizzBuzz(19));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> fizzBuzz(int n) {
            List<String> res = new ArrayList<>(n);
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 == 0) {
                    res.add("FizzBuzz");
                } else if (i % 3 == 0) {
                    res.add("Fizz");
                } else if (i % 5 == 0) {
                    res.add("Buzz");
                } else {
                    res.add(String.valueOf(i));
                }
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}