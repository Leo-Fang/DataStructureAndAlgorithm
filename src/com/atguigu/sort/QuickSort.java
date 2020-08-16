package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Leo
 * @date 2020/8/5 - 16:46
 */

public class QuickSort {

    public static void main(String[] args) {
        /*int[] arr = {-9, 78, 0, 23, -567, 70};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));

        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));*/

        //测试快速排序算法的速度
        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = (int)(Math.random()*800000);//生成一个[0,800000)的随机数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时刻：" + date1Str);

        quickSort(arr, 0, arr.length - 1);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序前的时刻：" + date2Str);
    }

    //快速排序
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;//左索引，初始指向最左边
        int r = right;//右索引，初始指向最右边
        //中轴值，以此进行分组
        int pivot = arr[(left + right) / 2];
        int temp = 0;//临时变量，交换位置时使用
        //开始循环，将比pivot值小的放左边，比pivot值大的放右边
        while (l < r) {
            //在pivot左边一直找，找到>=pivot值的才退出循环
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot右边一直找，找到<=pivot值的才退出循环
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果l>=r，说明pivot左边全是<=pivot的数，右边全是>=pivot的数，就退出循环
            if (l >= r) {
                break;
            }
            //交换，把找到的在pivot左边但>=pivot的数与在pivot右边但<=pivot的数交换位置
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //下面这两个if没搞的太清楚~~~难顶
            //如果交换完后发现arr[l]==pivot，则需要r--
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后发现arr[r]==pivot，则需要l++
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        //如果l==r，必须l++，r--，否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }

}
