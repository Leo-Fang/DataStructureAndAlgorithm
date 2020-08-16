package com.atguigu.tree;

/**
 * @author Leo
 * @date 2020/8/9 - 9:28
 */

public class ArrayBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //创建一个ArrayBinaryTree
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);

        System.out.println("前序遍历：");
        arrayBinaryTree.preOrder();
        System.out.println("中序遍历：");
        arrayBinaryTree.infixOrder();
        System.out.println("后序遍历：");
        arrayBinaryTree.postOrder();
    }

}

//创建一个ArrayBinaryTree类，实现顺序存储二叉树的遍历
class ArrayBinaryTree {

    private int[] arr;//存储数据节点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载preOrder
    public void preOrder() {
        this.preOrder(0);
    }

    //重载infixOrder
    public void infixOrder() {
        this.infixOrder(0);
    }

    //重载postOrder
    public void postOrder() {
        this.postOrder(0);
    }

    //顺序存储二叉树的前序遍历
    /**
     *
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        //如果数组为空或者arr.length=0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树前序遍历的方式遍历~~~");
            return;
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向左递归遍历
        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        //向右递归
        if ((2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //顺序存储二叉树的中序遍历
    public void infixOrder(int index) {
        //如果数组为空或者arr.length=0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树中序遍历的方式遍历~~~");
            return;
        }
        //向左递归遍历
        if ((2 * index + 1) < arr.length) {
            infixOrder(2 * index + 1);
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向右递归
        if ((2 * index + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    //顺序存储二叉树的后序遍历
    public void postOrder(int index) {
        //如果数组为空或者arr.length=0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树后序遍历的方式遍历~~~");
            return;
        }
        //向左递归遍历
        if ((2 * index + 1) < arr.length) {
            postOrder(2 * index + 1);
        }
        //向右递归
        if ((2 * index + 2) < arr.length) {
            postOrder(2 * index + 2);
        }
        //输出当前元素
        System.out.println(arr[index]);
    }

}
