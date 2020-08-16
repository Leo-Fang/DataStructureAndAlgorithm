package com.atguigu.search;

/**
 * @author Leo
 * @date 2020/8/6 - 14:59
 */

public class SequenceSearch {

    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int index = sequenceSearch(arr, 11);
        if (index == -1) {
            System.out.println("没有这个数~~~");
        } else {
            System.out.println("Index = " + index);
        }
    }

    //线性查找
    /**
     * 这里实现的线性查找是找到一个满足条件的值就返回
     *
     * @param arr
     * @param value
     * @return
     */
    public static int sequenceSearch(int[] arr, int value) {
        //线性查找是逐一比对，发现与目标值相同就返回索引值
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

}
