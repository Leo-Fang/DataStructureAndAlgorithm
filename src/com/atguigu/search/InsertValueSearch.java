package com.atguigu.search;

/**
 * @author Leo
 * @date 2020/8/6 - 19:12
 */

public class InsertValueSearch {

    public static void main(String[] args) {
        /*int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }*/

        int[] arr = {1,8,10,89,1000,1000,1234};
//        int index = insertValueSearch(arr, 0, arr.length - 1, 100);
        //这里要是查找的是100就翻车了，会出现ArrayIndexOutOfBoundsException异常，插值查找对数组要求比较严格
        int index = insertValueSearch(arr, 0, arr.length - 1, 89);
        System.out.println("Index = " + index);
    }

    //插值查找
    /**
     * 插值查找算法也要求数组是有序的
     *
     * @param arr
     * @param left
     * @param right
     * @param findValue
     * @return 如果没有找到就返回-1，如果找到就返回对应的索引值
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findValue) {
        //findValue<arr[0]和findValue>arr[arr.length-1]必须要有，否则得到的mid可能会越界
        if (left > right || findValue < arr[0] || findValue > arr[arr.length - 1]) {
            return -1;
        }
        //求出mid，自适应
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        if (findValue < arr[mid]) {//向左递归
            return insertValueSearch(arr, left, mid - 1, findValue);
        } else if (findValue > arr[mid]) {//向右递归
            return insertValueSearch(arr, mid + 1, right, findValue);
        } else {
            return mid;
        }
    }

}
