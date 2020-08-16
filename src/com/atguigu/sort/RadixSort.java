package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Leo
 * @date 2020/8/6 - 13:54
 */

public class RadixSort {

    public static void main(String[] args) {
        /*int[] arr = {53, 3, 542, 748, 14, 214};
        System.out.println("排序前： " + Arrays.toString(arr));

        radixSort(arr);
        System.out.println("排序后： " + Arrays.toString(arr));*/

        //测试基数排序算法的速度
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时刻：" + date1Str);

        radixSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序前的时刻：" + date2Str);
    }

    //基数排序
    public static void radixSort(int[] arr) {
        //1.得到数组中最大的数的位数
        int max = arr[0];//假设第一个数就是最大的数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();

        //2.定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //为了防止在放入数的时候数据溢出，每个一维数组的长度都定位arr.length
        //基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        //定义一个一维数组记录各个桶总共放入了多少个数据
        int[] bucketElementCounts = new int[10];

        //按照个、十、百、千……的顺序对每个数进行分组
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {//n表示位，初始是个位
            for (int j = 0; j < arr.length; j++) {
                //求出每个数对应位的值
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照桶的顺序依次取出数据放回原来的数组
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，取出放到原数组
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                //每取完一个桶中的数据，将记录该桶中之前存放了多少数据的bucketElementCounts[k]清空
                bucketElementCounts[k] = 0;
            }
//            System.out.println("第" + (i + 1) + "轮排序 " + Arrays.toString(arr));
        }
    }

}
