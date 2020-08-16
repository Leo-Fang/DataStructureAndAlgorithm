package com.atguigu.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Leo
 * @date 2020/8/16 - 9:31
 */

public class HorseChessboard {

    public static void main(String[] args) {
        System.out.println("马踏棋盘算法开始运行~");
        X = 8;
        Y = 8;
        int row = 1;//马的初始位置
        int column = 1;
        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];//初始值都是false

        //测试耗时
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + " ms");

        //输出棋盘情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    private static int X;//棋盘的列数
    private static int Y;//棋盘的行数
    private static boolean[] visited;//标记棋盘的各个位置是否被访问过
    private static boolean finished;//标记棋盘的所有位置是否都被访问过

    /**
     * 功能：根据当前位置（Point对象）计算马还能走哪些位置（Point），并放入到一个集合中（最多有8个位置）
     *
     * @param curPoint 当前位置
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        //创建一个ArrayList
        ArrayList<Point> ps = new ArrayList<Point>();
        //创建一个Point
        Point p1 = new Point();
        //表示马可以走位置5
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马可以走位置6
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马可以走位置7
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马可以走位置0
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马可以走位置1
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //表示马可以走位置2
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //表示马可以走位置3
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //表示马可以走位置4
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    /**
     * 马踏棋盘算法
     *
     * @param chessboard 棋盘
     * @param row        当前位置的行（从0开始）
     * @param column     当前位置的列（从0开始）
     * @param step       第几步，初始就是第1步
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        visited[row * X + column] = true;//当前位置在棋盘上是第几格，标记为已访问
        //获取当前位置可以走的下一个位置集合
        ArrayList<Point> ps = next(new Point(column, row));
        //对ps进行排序，对ps所有Point对象的下一步的位置的数目进行非递减排序
        sort(ps);
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);//取出下一个可以走的位置
            if (!visited[p.y * X + p.x]) {//如果没有被访问过
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        //判断马是否完成了任务，如果没有完成，将整个棋盘置0
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    //根据当前这一步的所有的下一步可选位置，进行非递减排序
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取o1的下一步发所有位置个数
                int count1 = next(o1).size();
                //获取o1的下一步发所有位置个数
                int count2 = next(o2).size();
                if (count1<count2){
                    return -1;
                } else if (count1 == count2){
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }

}
