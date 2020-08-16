package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Leo
 * @date 2020/8/5 - 14:43
 */

public class ShellSort {

    public static void main(String[] args) {
        /*int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));

        //选择排序
        shellSort(arr);

        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));*/

        //测试希尔排序算法的速度
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*800000);//生成一个[0,800000)的随机数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时刻：" + date1Str);

//        shellSort(arr);
        shellSort2(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序前的时刻：" + date2Str);
    }

    //希尔排序
    public static void shellSort(int[] arr) {
        int temp = 0;
//        int count = 0;//记录希尔排序进行了几轮
        //先进行分组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
            //遍历各组中的所有元素，步长为gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于一个步长后的元素，就交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.print("第" + (++count) + "轮 ");
//            System.out.println(Arrays.toString(arr));
        }
    }

    //希尔排序的优化->移位法
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //利用插入排序的原理
                int index = i;
                int temp = arr[index];
                while (index - gap >= 0 && temp < arr[index - gap]) {
                    arr[index] = arr[index - gap];
                    index -= gap;
                }
                arr[index] = temp;
            }
        }
    }

}
