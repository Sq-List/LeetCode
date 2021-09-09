package com.sqlist.leetcode.editor.cn;

/**
给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。 

 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。 

 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。 

 文本的最后一行应为左对齐，且单词之间不插入额外的空格。 

 说明: 

 
 单词是指由非空格字符组成的字符序列。 
 每个单词的长度大于 0，小于等于 maxWidth。 
 输入单词数组 words 至少包含一个单词。 
 

 示例: 

 输入:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
输出:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
 

 示例 2: 

 输入:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
输出:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
     因为最后一行应为左对齐，而不是左右两端对齐。       
     第二行同样为左对齐，这是因为这行只包含一个单词。
 

 示例 3: 

 输入:
words = ["Science","is","what","we","understand","well","enough","to","explain",

         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
输出:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
 
 Related Topics 字符串 模拟 👍 179 👎 0

*/

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [68]文本左右对齐
 * @author SqList
 * @createTime 2021-09-09 10:10:48
 **/
public class Q68TextJustification {
    public static void main(String[] args) {
        Solution solution = new Q68TextJustification().new Solution();
        System.out.println(JSON.toJSONString(solution.fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16)));
        System.out.println(JSON.toJSONString(solution.fullJustify(new String[]{"What","must","be","acknowledgment","shall","be"}, 16)));
        System.out.println(JSON.toJSONString(solution.fullJustify(new String[]{"Science","is","what","we","understand","well","enough","to","explain", "to","a","computer.","Art","is","everything","else","we","do"}, 20)));
        System.out.println(JSON.toJSONString(solution.fullJustify(new String[]{"ask","not","what","your","country","can","do","for","you","ask","what","you","can","do","for","your","country"}, 16)));
        System.out.println(JSON.toJSONString(solution.fullJustify(new String[]{"Listen","to","many,","speak","to","a","few."}, 6)));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> fullJustify(String[] words, int maxWidth) {
            //定义0-maxWidth个空格字符串，方便之后直接调用
            final String[] spaceArr = new String[maxWidth + 1];
            StringBuffer s = new StringBuffer();
            for(int i = 0; i< maxWidth+1; i++){
                spaceArr[i] = s.toString();
                s.append(" ");
            }

            // 表示每个循环下单词的下标范围
            int wordsIndexStart = 0;
            int wordsIndexEnd = 0;

            List<String> res = new ArrayList<>();
            while (true) {
                // 尝试出 该行最多可以有几个单词
                int lineLength = words[wordsIndexEnd++].length();
                while (wordsIndexEnd < words.length && (lineLength + words[wordsIndexEnd].length() + 1 <= maxWidth)) {
                    // 加1 是因为要多算一个空格
                    lineLength += words[wordsIndexEnd++].length() + 1;
                }

                if (wordsIndexEnd < words.length) {
                    int space = maxWidth - lineLength;
                    // 单词的个数
                    int n = wordsIndexEnd - wordsIndexStart;
                    if (n == 1) {
                        // 1个单词的时候特殊处理 防止分母为0
                        res.add(words[wordsIndexStart] + spaceArr[space]);
                    } else {
                        // 每个单词之间额外填充的空格个数
                        int everySpace = space / (n - 1);
                        // 需要比 everySpace 多一个的空格的 间隔数
                        int leftSpace = space - (n - 1) * everySpace;

                        StringBuilder sb = new StringBuilder(words[wordsIndexStart]);
                        for (int i = wordsIndexStart + 1; i < wordsIndexEnd; i++) {
                            // 因为之前计算 line长度 时 也算上了两个之间字符的一个空格
                            int tempSpace = everySpace + 1;
                            if (leftSpace-- > 0) {
                                // 当空格数不能平分放在每个单词之间 多出来的部分 从左到右依次多给一个空格
                                tempSpace += 1;
                            }

                            sb.append(spaceArr[tempSpace]).append(words[i]);
                        }

                        res.add(sb.toString());
                    }
                } else {
                    // 说明已经到最后一个字符
                    // 此时这个循环为最后一行
                    StringBuilder sb = new StringBuilder(words[wordsIndexStart]);
                    for (int i = wordsIndexStart + 1; i < wordsIndexEnd; i++) {
                        sb.append(" ").append(words[i]);
                    }
                    int rightSpace = maxWidth - lineLength;
                    sb.append(spaceArr[rightSpace]);
                    res.add(sb.toString());

                    return res;
                }

                wordsIndexStart = wordsIndexEnd;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}