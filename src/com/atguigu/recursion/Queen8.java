package com.atguigu.recursion;

/**
 * @author Leo
 * @date 2020/8/4 - 15:10
 */

public class Queen8 {

    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array，保存皇后放置位置的结果，如array = {0 , 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];
    static int count = 0;//统计有多少种解法
    static int judgeCount = 0;//统计判断了多少次

    public static void main(String[] args) {
        //测试
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有%d种解法\n", count);
        System.out.printf("一共判断了%d次\n", judgeCount);
    }

    //放置第n个皇后
    private void check(int n) {//n=1，表示放置第二个皇后
        if (n == max) {//n=8，8个皇后都放好了
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n放到该行的第1列
            array[n] = i;
            //判断当第n个皇后放置到第i列时是否冲突
            if (judge(n)) {//如果不冲突
                //接着放第n+1个皇后，开始递归
                check(n + 1);
            }
            //如果冲突，则开始下一个循环，i++，第n个皇后放置在本行的下一列
        }
    }

    //放置第n个皇后时，检测该皇后是否和前面已经摆放的皇后冲突

    /**
     * @param n 表示第n个皇后
     * @return
     */
    public boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //array[i] == array[n]：判断第n个皇后是否和前面的n-1个皇后在同一列
            //Math.abs(n - i) == Math.abs(array[n] - array[i])：判断第n个皇后是否和前面的n-1个皇后在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //定义一个方法，将皇后摆放位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
