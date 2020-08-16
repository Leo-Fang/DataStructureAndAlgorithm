package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Leo
 * @date 2020/8/4 - 21:20
 */

public class BubbleSort {

    public static void main(String[] args) {
        //给出一个数组
        /*int[] arr = {3, 9, -1, 10, -2};

        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));

        //冒泡排序
        bubbleSort(arr);

        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));*/

        //测试冒泡排序算法的速度
        //创建一个有80000个随机数的数组，再进行排序
        int[] arr = new int[80000];
        //给数组进行赋值
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*800000);//生成一个[0,800000)的随机数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时刻：" + date1Str);

        bubbleSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序前的时刻：" + date2Str);
    }

    //将冒泡排序封装成一个方法
    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;//标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前面的数比后面的数大，就交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.println("第" + (i + 1) + "趟排序后的数组：");
//            System.out.println(Arrays.toString(arr));
            //优化
            if (!flag) {//在一趟排序中一次交换都没有发生过，提取结束循环
                break;
            } else {
                flag = false;//重置flag，进行下一个循环的判断
            }
        }
    }

}
