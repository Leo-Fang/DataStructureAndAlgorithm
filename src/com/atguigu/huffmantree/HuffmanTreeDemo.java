package com.atguigu.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Leo
 * @date 2020/8/9 - 20:12
 */

public class HuffmanTreeDemo {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    //创建赫夫曼树的方法
    /**
     * @param arr 需要创建赫夫曼树的数组
     * @return 返回创建好后的赫夫曼树的root节点
     */
    public static Node createHuffmanTree(int[] arr) {
        //遍历arr数组，将arr的每个元素创建一个Node，将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);
//            System.out.println("nodes = " + nodes);

            //取出根节点权值最小的两棵二叉树
            Node leftNode = nodes.get(0);//取出权值最小的节点
            Node rightNode = nodes.get(1);//取出权值第二小的节点
            //构建一棵新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent加入到nodes
            nodes.add(parent);
        }

        //返回赫夫曼树的root
        return nodes.get(0);
    }

    //前序遍历的方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树，不能遍历~~~");
        }
    }

}

//创建节点类
//为了让Node对象支持排序，要实现Comparable接口
class Node implements Comparable<Node> {

    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" + "value=" + value + '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排序
        return this.value - o.value;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

}
