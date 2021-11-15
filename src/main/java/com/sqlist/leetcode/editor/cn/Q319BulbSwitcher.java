package com.sqlist.leetcode.editor.cn;

/**
初始时有 n 个灯泡处于关闭状态。第一轮，你将会打开所有灯泡。接下来的第二轮，你将会每两个灯泡关闭一个。 

 第三轮，你每三个灯泡就切换一个灯泡的开关（即，打开变关闭，关闭变打开）。第 i 轮，你每 i 个灯泡就切换一个灯泡的开关。直到第 n 轮，你只需要切换最后一个
灯泡的开关。 

 找出并返回 n 轮后有多少个亮着的灯泡。 

 

 示例 1： 

 

 
输入：n = 3
输出：1 
解释：
初始时, 灯泡状态 [关闭, 关闭, 关闭].
第一轮后, 灯泡状态 [开启, 开启, 开启].
第二轮后, 灯泡状态 [开启, 关闭, 开启].
第三轮后, 灯泡状态 [开启, 关闭, 关闭]. 

你应该返回 1，因为只有一个灯泡还亮着。
 

 示例 2： 

 
输入：n = 0
输出：0
 

 示例 3： 

 
输入：n = 1
输出：1
 

 

 提示： 

 
 0 <= n <= 10⁹ 
 
 Related Topics 脑筋急转弯 数学 👍 281 👎 0

*/

/**
 * [319]灯泡开关
 * @author SqList
 * @createTime 2021-11-15 23:03:57
 **/
public class Q319BulbSwitcher {
    public static void main(String[] args) {
        Solution solution = new Q319BulbSwitcher().new Solution();
        System.out.println(solution.bulbSwitch(10));
        long start = System.currentTimeMillis();
        System.out.println(solution.bulbSwitch((int) 1e9));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(solution.bulbSwitch(0));
        System.out.println(solution.bulbSwitch(1));
        System.out.println(solution.bulbSwitch(2));
        System.out.println(solution.bulbSwitch(3));
        System.out.println(solution.bulbSwitch(4));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 一个编号为 x 的灯泡经过 n 轮后处于打开状态的充要条件为「该灯泡被切换状态次数为奇数次」。
     * 所以 灯泡编号拥有偶数个质因数的一定是暗的，灯泡编号拥有奇数个质因数的一定是亮的(完全平方数)
     * 1到n之间的完全平方数的个数等于根号n
     * eg: 1<=x**2<=9 1<=x<=3 一个平方根对应一个完全平方数 所以完全平方数也是3个
     */
    class Solution {
        public int bulbSwitch(int n) {
            return (int) Math.sqrt(n + 0.5);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 1001000010000001000000
     * 1后面的零 每隔一次 多加两个
     *
     * 可得 (0 + ?) * ans >= 2 * n
     * 所以 求出 ? 即可的 ans
     */
    class SolutionSelf {
        public int bulbSwitch(int n) {
            if (n == 0) {
                return 0;
            }

            if (n <= 3) {
                return 1;
            }

            int sum = 3, step = 3;
            int ans = 1;

            while (sum < n) {
                step += 2;
                sum += step;
                ans++;
            }

            return ans;
        }
    }
}