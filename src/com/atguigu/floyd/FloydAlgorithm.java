package com.atguigu.floyd;

import java.util.Arrays;

/**
 * @author Leo
 * @date 2020/8/15 - 19:48
 */

public class FloydAlgorithm {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 3};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 4};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{3, 4, N, N, 4, 6, 0};

        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.show();
    }

}

//创建图
class Graph {

    private char[] vertex;//存放顶点的数组
    private int[][] dis;//保存从各个顶点出发到其它顶点的距离
    private int[][] pre;//保存到达目标顶点的前驱顶点

    /**
     * 构造器
     *
     * @param length 大小
     * @param matrix 领接矩阵
     * @param vertex 顶点数组
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        //对pre数组初始化，存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    //显示pre数组和dis数组
    public void show() {
        //为了显示便于阅读，优化一下输出
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();
            for (int j = 0; j < dis.length; j++) {
                System.out.print("(" + vertex[i] + "到" + vertex[j] + "的最短距离：" + dis[i][j] + ") ");
            }
            System.out.println();
            System.out.println();
        }
    }

    //floyd算法
    public void floyd() {
        int len = 0;//保存距离
        //对中间顶点遍历，k就是中间顶点的下标
        for (int k = 0; k < dis.length; k++) {
            //从i顶点出发，经过中间顶点k，到达j顶点
            for (int i = 0; i < dis.length; i++) {
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];//更新前驱顶点
                    }
                }
            }
        }
    }
}