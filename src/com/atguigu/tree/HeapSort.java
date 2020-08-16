package com.atguigu.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Leo
 * @date 2020/8/9 - 16:57
 */

public class HeapSort {

    public static void main(String[] args) {
        /*int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);*/

        //测试堆排序算法的速度
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0,800000)的随机数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时刻：" + date1Str);

        heapSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序前的时刻：" + date2Str);
    }

    //堆排序的方法
    public static void heapSort(int[] arr) {
        System.out.println("堆排序");
        int temp = 0;

        //分步完成
        /*adjustHeap(arr, 1, arr.length);
        System.out.println("第1次" + Arrays.toString(arr));
        adjustHeap(arr, 0, arr.length);
        System.out.println("第2次" + Arrays.toString(arr));*/

        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
//        System.out.println("数组：" + Arrays.toString(arr));

    }

    //将一个数组（二叉树）调整成一个大顶堆

    /**
     * 将以i对应的非叶子节点的树调整成大顶堆
     *
     * @param arr    待调整的数组
     * @param i      非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整，length是在逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素的值，保存在临时变量中
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {//k是i节点的左子节点
            if (k + 1 < length && arr[k] < arr[k + 1]) {//左子节点的值小于右子节点的值
                k++;//k指向右子节点
            }
            if (arr[k] > temp) {//如果子节点大于父节点
                arr[i] = arr[k];//把较大的值赋给当前节点
                i = k;//i指向k，继续循环比较
            } else {
                break;
            }
        }
        //当for循环结束后，已经将以i为父节点的树的最大值放在了局部的上面
        arr[i] = temp;//将temp值放到调整后的位置
    }

}
