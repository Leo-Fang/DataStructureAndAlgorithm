package com.atguigu.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Leo
 * @date 2020/8/12 - 19:24
 */

public class Graph {

    public static void main(String[] args) {
        /*int n = 5;//结点的个数
        String[] vertexes = {"A", "B", "C", "D", "E"};*/
        int n = 8;
        String[] vertexes = {"1", "2", "3", "4", "5", "6", "7", "8"};

        //创建图对象
        Graph graph = new Graph(n);
        //循环添加顶点
        for (String vertex : vertexes) {
            graph.insertVertex(vertex);
        }
        //添加边
        /*graph.insertEdge(0, 1, 1);//A-B
        graph.insertEdge(0, 2, 1);//A-C
        graph.insertEdge(1, 2, 1);//B-C
        graph.insertEdge(1, 3, 1);//B-D
        graph.insertEdge(1, 4, 1);//B-E*/

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        //显示领接矩阵
        graph.showGraph();

        //测试深度优先遍历
        System.out.println("深度优先遍历：");
        graph.dfs();

        //测试广度优先遍历
        System.out.println("广度优先遍历：");
        graph.bfs();
    }

    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的领接矩阵
    private int numOfEdges;//表示边的数目
    private boolean[] isVisited;//记录某个顶点是否被访问

    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    //插入顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     第一个顶点的下标，A-0,B-1,C-2,D-3,E-4
     * @param v2     第二个顶点的下标
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //获取顶点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //获取边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //获取顶点下标对应的数据：0-A,1-B,2-C,3-D,4-E
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //获取v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 获取第一个领接结点的下标
     *
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //根据前一个领接节点的下标来获取下一个领接结点
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    private void dfs(boolean[] isVisited, int v) {//v第一次就是0
        //访问该领接结点并输出
        System.out.print(getValueByIndex(v) + "->");
        //将该节点设置为已访问
        isVisited[v] = true;
        //查找节点的第一个领接结点
        int w = getFirstNeighbor(v);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w已经被访问过了
            w = getNextNeighbor(v, w);
        }
    }

    //对dfs进行重载，遍历所有的结点并进行dfs
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
        System.out.println();
    }

    //广度优先遍历算法
    private void bfs(boolean[] isVisited, int v) {
        int u;//表示队列的头结点对应的下标
        int w;//领接结点
        //队列，记录结点访问的顺序
        LinkedList queue = new LinkedList();
        //访问结点，输出结点信息
        System.out.print(getValueByIndex(v) + "->");
        //标记为已访问
        isVisited[v] = true;
        //将结点加入队列
        queue.addLast(v);

        while (!queue.isEmpty()) {
            //取出队列的头结点下标
            u = (Integer) queue.removeFirst();
            //得到第一个领接结点的下标
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {//没有被访问过
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    //加入队列
                    queue.addLast(w);
                }
                //以u为前驱结点，找w后面的下一个领接结点
                w = getNextNeighbor(u, w);
            }
        }
    }

    //遍历所有的结点，都进行广度优先搜索
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
        System.out.println();
    }

}