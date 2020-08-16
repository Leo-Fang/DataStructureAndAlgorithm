package com.atguigu.recursion;

/**
 * @author Leo
 * @date 2020/8/4 - 11:19
 */

public class MiGong {

    public static void main(String[] args) {
        //创建一个二维数字模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;
        //输出初始地图
        System.out.println("初始地图：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //小球找路
//        setWay(map,1,1);
        setWay2(map,1,1);
        //输出新地图
        System.out.println("小球走过并标识的地图：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //使用递归回溯给小球找路
    /*说明：
        1.map表示地图，ij表示从地图的哪个位置出发，如(1,1)
        2.如果小球能到map[6][5]位置，说明通路找到
        3.约定：若map[i][j]为0，则表示该点没有走过；为1表示墙；为2表示通路可以走；为3表示该点已走过，但走不通
        4.制定一个策略：下→右→上→左，如果该点走不通再回溯
     */

    /**
     *
     * @param map 地图
     * @param i 出发点的横坐标
     * @param j 出发点的纵坐标
     * @return 如果找到通路返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {//当前这个点还没有走过
                //按照策略：下→右→上→左 走
                map[i][j] = 2;//假定该点可以走
                if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {//向左走
                    return true;
                } else {
                    //说明该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {//map[i][j] != 0，则可能是1，2，3
                return false;
            }
        }
    }

    //修改策略：上→右→下→左
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {//当前这个点还没有走过
                //按照策略：上→右→下→左 走
                map[i][j] = 2;//假定该点可以走
                if (setWay2(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay2(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay2(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay2(map, i, j - 1)) {//向左走
                    return true;
                } else {
                    //说明该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {//map[i][j] != 0，则可能是1，2，3
                return false;
            }
        }
    }
}
