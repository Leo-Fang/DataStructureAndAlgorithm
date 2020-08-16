package com.atguigu.dijkstra;

import java.util.Arrays;

/**
 * @author Leo
 * @date 2020/8/15 - 9:15
 */

public class DijkstraAlgorithm {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;//表示不可连接
        matrix[0] = new int[]{N, 5, 7, N, N, N, 3};
        matrix[1] = new int[]{5, N, N, 9, N, N, 4};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{3, 4, N, N, 4, 6, N};

        //创建Graph对象
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();

        graph.dijkstra(6);
        graph.showDijkstra();
    }

}

class Graph {

    private char[] vertex;//顶点数组
    private int[][] matrix;//领接矩阵
    private VisitedVertex vv;//已访问的顶点的集合

    //构造器
    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示图
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * Dijkstra算法
     *
     * @param index 出发顶点对应的下标
     */
    public void dijkstra(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);//更新index顶点到周围顶点的距离和前驱顶点
        for (int i = 1; i < vertex.length; i++) {
            index = vv.updateArr();//选择并返回新的访问顶点
            update(index);
        }
    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
    private void update(int index) {
        int len = 0;
        //遍历matrix[index]行
        for (int i = 0; i < matrix[index].length; i++) {
            //len表示出发顶点到index顶点的距离+从index顶点到i顶点的距离的和
            len = vv.getDis(index) + matrix[index][i];
            //如果i顶点没有被访问过，并且len小于出发顶点到i顶点的距离，就需要更新
            if (!vv.in(i) && len < vv.getDis(i)) {
                vv.updatePre(i, index);
                vv.updateDis(i, len);
            }
        }
    }

    //显示结果
    public void showDijkstra() {
        vv.show();
    }

}

//已访问顶点集合
class VisitedVertex {

    //记录各个顶点是否被访问过，1表示被访问过，0表示未被访问，会动态更新
    public int[] already_arr;
    //下标对应的值为前一个顶点下标，会动态更新
    public int[] pre_visited;
    //记录出发顶点到其它所有顶点的距离
    public int[] dis;

    //构造器
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis数组
        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1;//设置出发顶点为已被访问
        this.dis[index] = 0;//设置出发顶点的距离为0
    }

    /**
     * 功能：判断index顶点是否被访问过
     *
     * @param index 待判断的顶点
     * @return 如果被访问过就返回true，否则返回false
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 功能：更新出发顶点到index顶点的距离
     *
     * @param index    目标顶点
     * @param distence 距离
     */
    public void updateDis(int index, int distence) {
        dis[index] = distence;
    }

    /**
     * 功能：更新pre顶点的前驱顶点为index顶点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 功能：返回出发顶点到index顶点的距离
     *
     * @param index
     * @return
     */
    public int getDis(int index) {
        return dis[index];
    }

    //继续选择并返回新的访问顶点
    public int updateArr() {
        int min = 65535;
        int index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        //更新index顶点为被访问过
        already_arr[index] = 1;
        return index;
    }

    //显示最后的结果
    public void show() {
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();

        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "(" + i + ")");
            } else {
                System.out.print("N ");
            }
            count++;
        }
        System.out.println();
    }

}