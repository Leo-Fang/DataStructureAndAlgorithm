package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Leo
 * @date 2020/8/5 - 10:22
 */

public class InsertSort {

    public static void main(String[] args) {
        /*int[] arr = {101, 34, 119, 1, -1, 89};

        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));

        //选择排序
        insertSort(arr);

        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));*/

        //测试插入排序算法的速度
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*800000);//生成一个[0,800000)的随机数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时刻：" + date1Str);

        insertSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序前的时刻：" + date2Str);
    }

    //插入排序
    public static void  insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;

        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            insertVal = arr[i];
            insertIndex = i - 1;
            //给insertVal找到插入的位置
            //insertIndex >= 0保证在给insertVal找位置时不会越界
            //insertVal < arr[insertIndex]表示待插入的数还没有找到插入的位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                //每次与前一位比较，如果比其小就交换位置
                arr[insertIndex + 1] = arr[insertIndex];
                //再与交换后的前一位比较大小
                insertIndex--;
            }
            //判断是否需要赋值操作
            if (insertIndex + 1 == i) {
                arr[insertIndex + 1] = insertVal;
            }
//            System.out.print("第" + i +"轮排序： ");
//            System.out.println(Arrays.toString(arr));
        }
    }

}