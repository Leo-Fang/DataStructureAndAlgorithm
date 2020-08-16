package com.atguigu.prim;

import java.util.Arrays;

/**
 * @author Leo
 * @date 2020/8/14 - 14:16
 */

public class PrimAlgorithm {

    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexes = data.length;
        int[][] weight = {{10000, 5, 7, 10000, 10000, 10000, 3},
                {5, 10000, 10000, 9, 10000, 10000, 4},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}};
        //创建MGraph对象
        MGraph graph = new MGraph(vertexes);
        //创建MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, vertexes, data, weight);
        minTree.showGraph(graph);
        minTree.prim(graph, 0);
    }

}

//创建最小生成树
class MinTree {

    /**
     * 创建图的领接矩阵
     *
     * @param graph    图对象
     * @param vertexes 图对应的顶点的个数
     * @param data     图的顶点的值
     * @param weight   图的领接矩阵
     */
    public void createGraph(MGraph graph, int vertexes, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < vertexes; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < vertexes; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的领接矩阵
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * prim算法
     *
     * @param graph 图
     * @param v     表示从图的第几个顶点开始生成：A-0,B-1,C-2...
     */
    public void prim(MGraph graph, int v) {
        int[] visited = new int[graph.vertexes];//标记结点是否被访问过
        //初始化，visited[i]=0表示没有被访问过（java中默认为0，所以可以不写）
        /*for (int i = 0; i < graph.vertexes; i++) {
            visited[i] = 0;
        }*/
        //把当前结点标记为已访问
        visited[v] = 1;
        //定义h1和h2，记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        for (int k = 1; k < graph.vertexes; k++) {//k表示第k条边，总共有graph.vertexes-1条边
            //在剩下的没有连接的结点中选出距离最近的结点
            for (int i = 0; i < graph.vertexes; i++) {//遍历访问过的结点
                for (int j = 0; j < graph.vertexes; j++) {//遍历没有访问过的结点
                    //visited[i]==1、visited[j]==0：从已经访问过的结点中找出没有访问过的距离最小的结点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //
            System.out.println("<" + graph.data[h1] + "," + graph.data[h2] + ">" + " = " + minWeight);
            visited[h2] = 1;//找到结点后将其标记为已访问
            minWeight = 10000;
        }
    }

}

class MGraph {

    int vertexes;//图的结点个数
    char[] data;//存放结点数据
    int[][] weight;//存放边（权），也就是领接矩阵

    public MGraph(int vertexes) {
        this.vertexes = vertexes;
        data = new char[vertexes];
        weight = new int[vertexes][vertexes];
    }

}