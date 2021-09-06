package com.sqlist.leetcode.editor.cn;

/**
已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。 

 不要使用系统的 Math.random() 方法。 

 
 

 

 示例 1: 

 
输入: 1
输出: [7]
 

 示例 2: 

 
输入: 2
输出: [8,4]
 

 示例 3: 

 
输入: 3
输出: [8,1,10]
 

 

 提示: 

 
 rand7 已定义。 
 传入参数: n 表示 rand10 的调用次数。 
 

 

 进阶: 

 
 rand7()调用次数的 期望值 是多少 ? 
 你能否尽量少调用 rand7() ? 
 
 Related Topics 数学 拒绝采样 概率与统计 随机化 👍 304 👎 0

*/

/**
 * TODO
 * [470]用 Rand7() 实现 Rand10()
 * @author SqList
 * @createTime 2021-09-05 22:56:00
 **/
public class Q470ImplementRand10UsingRand7 {
    public static void main(String[] args) {
        Solution solution = new Q470ImplementRand10UsingRand7().new Solution();
    }

    class SolBase {
        public int rand7() {
            return 0;
        }
    }

    /**
     * The rand7() API is already defined in the parent class SolBase.
     * public int rand7();
     * @return a random integer in the range 1 to 7
     */
    class SolutionSelf extends SolBase {
        public int rand10() {
            return (rand7() + rand7() + rand7() + rand7() + rand7() + rand7() + rand7() + rand7() + rand7() + rand7()) % 10 + 1;
        }
    }

    /**
     * 已知 rand_N() 可以等概率的生成[1, N]范围的随机数
     * 那么：
     * (rand_X() - 1) × Y + rand_Y() ==> 可以等概率的生成[1, X * Y]范围的随机数
     * 即实现了 rand_XY()
     *
     * 作者：kkbill
     * 链接：https://leetcode-cn.com/problems/implement-rand10-using-rand7/solution/cong-zui-ji-chu-de-jiang-qi-ru-he-zuo-dao-jun-yun-/
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * The rand7() API is already defined in the parent class SolBase.
     * public int rand7();
     * @return a random integer in the range 1 to 7
     */
    class Solution extends SolBase {
        public int rand10() {
            int num = 0;
            while (true) {
                num = (rand7() - 1) * 7 + rand7();  // rand49()
                if (num <= 40) {
                    return num % 10 + 1;
                }

                // rand9() 因为 生成 41到49 每个数也是等概率的 所以都减掉40 可以认为生成 1到9 也是等概率的  为rand9()
                num -= 40;
                num = (num - 1) * 7 + rand7();  // rand63()
                if (num <= 60) {
                    return num % 10 + 1;
                }

                // rand3()
                num -= 60;
                num = (num - 1) * 7 + rand7();  // rand21()
                if (num <= 20) {
                    return num % 10 + 1;
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}