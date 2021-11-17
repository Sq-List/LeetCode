package com.sqlist.leetcode.editor.cn;

/**
给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。这个矩形的左下顶点是 (
xi, yi) ，右上顶点是 (ai, bi) 。 

 如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。 
 

 示例 1： 

 
输入：rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
输出：true
解释：5 个矩形一起可以精确地覆盖一个矩形区域。 
 

 示例 2： 

 
输入：rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
输出：false
解释：两个矩形之间有间隔，无法覆盖成一个矩形。 

 示例 3： 

 
输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[3,2,4,4]]
输出：false
解释：图形顶端留有空缺，无法覆盖成一个矩形。 

 示例 4： 

 
输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
输出：false
解释：因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。 

 

 提示： 

 
 1 <= rectangles.length <= 2 * 10⁴ 
 rectangles[i].length == 4 
 -10⁵ <= xi, yi, ai, bi <= 10⁵ 
 
 Related Topics 数组 扫描线 👍 100 👎 0

*/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * [391]完美矩形
 * @author SqList
 * @createTime 2021-11-16 00:41:43
 **/
public class Q391PerfectRectangle {

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

    public static void main(String[] args) throws IOException {
        String file = Objects.requireNonNull(Q391PerfectRectangle.class.getResource("/testcase/Q391")).getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            int[][] rectangles = stringToInt2dArray(line);

            long start = System.currentTimeMillis();
            Solution solution = new Q391PerfectRectangle().new Solution();
            System.out.println(solution.isRectangleCover(rectangles));
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * hash表
     */
    class Point {
        int x;
        int y;

        public Point() {

        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    /**
     * 1. 将所有点都加入到set中去重，如果是精确覆盖，则肯定只剩下4个点
     * 2. 将1中的点组成的矩形计算面积，与 所有提供的矩形面积之和比较
     */
    class Solution {
        public boolean isRectangleCover(int[][] rectangles) {
            int targetSum = 0;
            Map<Point, Integer> point2CountMap = new HashMap<>((int)(rectangles.length * 4 / 0.75 + 1));
            for (int[] rectangle : rectangles) {
                Point leftDownPoint = new Point(rectangle[0], rectangle[1]);
                Point leftUpPoint = new Point(rectangle[0], rectangle[3]);
                Point rightUpPoint = new Point(rectangle[2], rectangle[3]);
                Point rightDownPoint = new Point(rectangle[2], rectangle[1]);

                point2CountMap.put(leftDownPoint, point2CountMap.getOrDefault(leftDownPoint, 0) + 1);
                point2CountMap.put(leftUpPoint, point2CountMap.getOrDefault(leftUpPoint, 0) + 1);
                point2CountMap.put(rightDownPoint, point2CountMap.getOrDefault(rightDownPoint, 0) + 1);
                point2CountMap.put(rightUpPoint, point2CountMap.getOrDefault(rightUpPoint, 0) + 1);

                targetSum += (rightUpPoint.y - leftDownPoint.y) * (rightUpPoint.x - leftDownPoint.x);
            }

            ArrayList<Point> pointList = new ArrayList<>(point2CountMap.size());
            for (Map.Entry<Point, Integer> entry : point2CountMap.entrySet()) {
                if (entry.getValue() % 2 == 1) {
                    pointList.add(entry.getKey());
                }
            }

            if (pointList.size() != 4) {
                return false;
            }

            Point firstPoint = pointList.get(0);
            Point secondPoint = null;
            for (int i = 1; i < pointList.size(); i++) {
                Point point = pointList.get(i);
                if (point.x != firstPoint.x && point.y != firstPoint.y) {
                    secondPoint = point;
                    break;
                }
            }

            if (Objects.isNull(secondPoint)) {
                return false;
            }

            int sum = Math.abs(secondPoint.x - firstPoint.x) * Math.abs(secondPoint.y - firstPoint.y);
            return sum == targetSum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}