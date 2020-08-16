package com.atguigu.kruskal;

import java.util.Arrays;

/**
 * @author Leo
 * @date 2020/8/14 - 17:11
 */

public class KruskalCase {

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {/*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/    {0, 12, INF, INF, INF, 16, 14},
                /*B*/    {12, 0, 10, INF, INF, 7, INF},
                /*C*/    {INF, 10, 0, 3, 5, 6, INF},
                /*D*/    {INF, INF, 3, 0, 4, INF, INF},
                /*E*/    {INF, INF, 5, 4, 0, 2, 8},
                /*F*/    {16, 7, 6, INF, 2, 0, 9},
                /*G*/    {14, INF, INF, INF, 8, 9, 0}};

        KruskalCase kruskalCase = new KruskalCase(vertexes, matrix);
        kruskalCase.print();
        EData[] edges = kruskalCase.getEdges();
        System.out.println("原始边：" + Arrays.toString(edges));
        kruskalCase.sortEdges(edges);
        System.out.println("排序后：" + Arrays.toString(edges));
        kruskalCase.kruskal();
    }

    private int edgeNum;//边的个数
    private char[] vertexes;//顶点数组
    private int[][] matrix;//领接矩阵
    private static final int INF = Integer.MAX_VALUE;//表示两个顶点不能连通

    //构造器
    public KruskalCase(char[] vertexes, int[][] matrix) {
        //初始化顶点数
        int vLen = vertexes.length;
        //初始化顶点（复制拷贝的方式）
        this.vertexes = new char[vLen];
        for (int i = 0; i < vertexes.length; i++) {
            this.vertexes[i] = vertexes[i];
        }
        //初始化边
        this.matrix = new int[vLen][vLen];
        for (int i = 0; i < vLen; i++) {
            for (int j = 0; j < vLen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边
        for (int i = 0; i < vLen; i++) {
            for (int j = i + 1; j < vLen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    //打印领接矩阵
    public void print() {
        System.out.println("领接矩阵：");
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //对边进行排序（冒泡排序）
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length - 1; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 获取字符所对应的下标
     *
     * @param ch 顶点对应的字符
     * @return 如果找到就返回顶点对应的下标，否则返回-1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == ch) {
                return i;//找到
            }
        }
        return -1;//没找到
    }

    /**
     * 获取图中的边，放到EData[]中
     *
     * @return 返回的形式为[EData{start=A, end=B, weight=12}, EData{start=A, end=F, weight=16},...]
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = i + 1; j < vertexes.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexes[i], vertexes[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 功能：获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同
     *
     * @param ends 记录各个顶点对应的终点是哪个，ends数组是在遍历过程中逐步形成的
     * @param i    表示传入的顶点对应的下标
     * @return 返回下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    //
    public void kruskal() {
        int index = 0;//表示最后结果数组的索引
        int[] ends = new int[edgeNum];//用于保存已有最小生成树中每个顶点在最小生成树中的终点
        //创建结果数组，保证最后的最小生成树
        EData[] result = new EData[edgeNum];
        //获取图中所有的边的集合
        EData[] edges = getEdges();
        //按照边的权值从小到大进行排序
        sortEdges(edges);
        //遍历edges数组，将边添加到最小生成树中
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的两个顶点
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);
            //获取顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            //判断是否形成了回路
            if (m != n) {//没有形成回路
                ends[m] = n;//设置m在已有最小生成树中的终点
                result[index++] = edges[i];
            }
        }
        //统计并打印最小生成树
        System.out.println("最小生成树为：");
        for (int i = 0; i < index; i++) {
            System.out.println(result[i]);
        }
    }

}

//创建一个EData类，表示一条边
class EData {

    char start;//边的一个端点
    char end;//边的另一个端点
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{<" + start + "," + end + ">=" + weight + '}';
    }

}