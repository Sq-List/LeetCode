package com.sqlist.leetcode.editor.cn;

/**
将非负整数 num 转换为其对应的英文表示。 

 

 示例 1： 

 
输入：num = 123
输出："One Hundred Twenty Three"
 

 示例 2： 

 
输入：num = 12345
输出："Twelve Thousand Three Hundred Forty Five"
 

 示例 3： 

 
输入：num = 1234567
输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 

 示例 4： 

 
输入：num = 1234567891
输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven 
Thousand Eight Hundred Ninety One"
 

 

 提示： 

 
 0 <= num <= 2³¹ - 1 
 
 Related Topics 递归 数学 字符串 👍 175 👎 0

*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [273]整数转换英文表示
 * @author SqList
 * @createTime 2021-10-11 09:31:00
 **/
public class Q273IntegerToEnglishWords {
    public static void main(String[] args) {
        Solution solution = new Q273IntegerToEnglishWords().new Solution();
        // System.out.println(solution.numberToWords(123));
        // System.out.println(solution.numberToWords(12345));
        // System.out.println(solution.numberToWords(1234567));
        // System.out.println(solution.numberToWords(1234567891));
        // System.out.println(solution.numberToWords(120340));
        System.out.println(solution.numberToWords(1000000));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String numberToWords(int num) {
            if (num == 0) {
                return "Zero";
            }

            Map<String, String> singleNumToWordsMap = new HashMap<>(10, 1);
            singleNumToWordsMap.put("0", "");
            singleNumToWordsMap.put("1", "One");
            singleNumToWordsMap.put("2", "Two");
            singleNumToWordsMap.put("3", "Three");
            singleNumToWordsMap.put("4", "Four");
            singleNumToWordsMap.put("5", "Five");
            singleNumToWordsMap.put("6", "Six");
            singleNumToWordsMap.put("7", "Seven");
            singleNumToWordsMap.put("8", "Eight");
            singleNumToWordsMap.put("9", "Nine");

            Map<String, String> doubleNumToWordsMap = new HashMap<>(18, 1);
            doubleNumToWordsMap.put("10", "Ten");
            doubleNumToWordsMap.put("11", "Eleven");
            doubleNumToWordsMap.put("12", "Twelve");
            doubleNumToWordsMap.put("13", "Thirteen");
            doubleNumToWordsMap.put("14", "Fourteen");
            doubleNumToWordsMap.put("15", "Fifteen");
            doubleNumToWordsMap.put("16", "Sixteen");
            doubleNumToWordsMap.put("17", "Seventeen");
            doubleNumToWordsMap.put("18", "Eighteen");
            doubleNumToWordsMap.put("19", "Nineteen");
            doubleNumToWordsMap.put("20", "Twenty");
            doubleNumToWordsMap.put("30", "Thirty");
            doubleNumToWordsMap.put("40", "Forty");
            doubleNumToWordsMap.put("50", "Fifty");
            doubleNumToWordsMap.put("60", "Sixty");
            doubleNumToWordsMap.put("70", "Seventy");
            doubleNumToWordsMap.put("80", "Eighty");
            doubleNumToWordsMap.put("90", "Ninety");

            List<String> stageNameList = new ArrayList<>(4);
            stageNameList.add("");
            stageNameList.add("Thousand ");
            stageNameList.add("Million ");
            stageNameList.add("Billion ");

            int index = 0;
            List<String> stageList = new ArrayList<>();
            while (num != 0) {
                int levelNum = num % 1000;
                int levelHundNum = levelNum / 100;
                int levelTenUnitNum = levelNum % 100;
                int levelTenNum = levelTenUnitNum / 10;
                int levelUnitNum = levelTenUnitNum % 10;

                StringBuilder sb = new StringBuilder();
                if (levelHundNum != 0) {
                    sb.append(singleNumToWordsMap.get(String.valueOf(levelHundNum))).append(" Hundred ");
                }
                if (levelTenNum == 1) {
                    sb.append(doubleNumToWordsMap.get(String.valueOf(levelTenUnitNum))).append(" ");
                } else {
                    if (levelTenNum != 0) {
                        sb.append(doubleNumToWordsMap.get(levelTenNum + "0")).append(" ");
                    }
                    if (levelUnitNum != 0) {
                        sb.append(singleNumToWordsMap.get(String.valueOf(levelUnitNum))).append(" ");
                    }
                }

                if (sb.length() != 0) {
                    sb.append(stageNameList.get(index));
                }

                stageList.add(sb.toString());

                index ++;
                num /= 1000;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = stageList.size() - 1; i >= 0; i--) {
                sb.append(stageList.get(i));
            }
            return sb.toString().trim();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}