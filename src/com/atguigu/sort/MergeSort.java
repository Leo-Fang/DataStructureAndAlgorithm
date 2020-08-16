package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Leo
 * @date 2020/8/5 - 21:55
 */

public class MergeSort {

    public static void main(String[] args) {
        /*int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        System.out.println("排序前： " + Arrays.toString(arr));

        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("排序后： " + Arrays.toString(arr));*/

        //测试归并排序算法的速度
        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = (int)(Math.random()*800000);//生成一个[0,800000)的随机数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时刻：" + date1Str);

        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序前的时刻：" + date2Str);
    }

    //分+合方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    //合并的方法

    /**
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//初始化i，左边有序序列的初始索引
        int j = mid + 1;//初始化j，右边有序序列的初始索引
        int t = 0;//指向temp数组的当前索引

        //先把左右两边的有序数据按照规则填充到temp数组，直到左右两边的有序序列有一边处理完毕为止
        while (i <= mid && j <= right) {
            //如果左边有序序列的当前元素<=右边有序序列的当前元素，就将左边的当前元素填充到temp数组
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {//反转将右边的当前元素填充到temp数组
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        //把右剩余数据一边的数据依次填充到temp中
        //左边有序序列还有剩余元素
        while (i <= mid) {
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        //右边有序序列还有剩余元素
        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        //将temp数组的元素拷贝到arr（并不是每次都拷贝所有）
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
