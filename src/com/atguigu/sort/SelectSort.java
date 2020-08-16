package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Leo
 * @date 2020/8/5 - 9:12
 */

public class SelectSort {

    public static void main(String[] args) {
        /*int[] arr = {101, 34, 119, 1, -1, 90, 123};

        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));

        //选择排序
        selectSort(arr);

        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));*/

        //测试选择排序算法的速度
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*800000);//生成一个[0,800000)的随机数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时刻：" + date1Str);

        selectSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序前的时刻：" + date2Str);
    }

    //选择排序
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;//假定每轮循环的首位是最小值
            int min = arr[minIndex];
            for (int j = i + 1; j < arr.length; j++) {
                //如果后面的数比假定的最小值小，就用minIndex记录下最小值的索引，min记录下最小值
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                    min = arr[minIndex];
                }
            }
            //交换，将每轮循环的最小值放在最前面
            if (minIndex != i) {//如果最小值就是当前最小值，就不用交换
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
//            System.out.println("第" + (i + 1) + "次");
//            System.out.println(Arrays.toString(arr));
        }
    }

}
