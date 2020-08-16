package com.atguigu.dynamic;

/**
 * @author Leo
 * @date 2020/8/13 - 16:24
 */

public class KnapsackProblem {

    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品的重量
        int[] v = {1500, 3000, 2000};//物品的价值
        int m = 4;//背包的容量
        int n = v.length;//物品的个数

        //创建二维数组，表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] value = new int[n + 1][m + 1];
        //初始化第一行和第一列（也可以不处理，默认为0）
        for (int i = 0; i < value.length; i++) {
            value[i][0] = 0;//将第一列设置为0
        }
        for (int i = 0; i < value[0].length; i++) {
            value[0][i] = 0;//将第一行设置为0
        }

        //记录放入物品的情况
        int[][] path = new int[n + 1][m + 1];

        //动态规划处理
        for (int i = 1; i < value.length; i++) {//不处理第一行
            for (int j = 1; j < value[0].length; j++) {//不处理第一列，j表示背包的容量
                if (w[i - 1] > j) {
                    value[i][j] = value[i - 1][j];
                } else {
//                    value[i][j] = Math.max(value[i - 1][j], v[i - 1] + value[i - 1][j - w[i - 1]]);
                    //为了记录放入物品的情况，不能直接使用上面的公式
                    if (value[i - 1][j] < v[i - 1] + value[i - 1][j - w[i - 1]]) {
                        value[i][j] = v[i - 1] + value[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        value[i][j] = value[i - 1][j];
                    }
                }
            }
        }

        //查看二维数组value的情况
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[i].length; j++) {
                System.out.print(value[i][j] + "\t");
            }
            System.out.println();
        }

        //输出最后放入的是哪些物品
        //遍历path。但是这样会把所有的放入情况都得到
        /*for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                if (path[i][j] == 1) {
                    System.out.printf("第%d个商品放入到背包\n", i);
                }
            }
        }*/
        int i = n;
        int j = m;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }

}
