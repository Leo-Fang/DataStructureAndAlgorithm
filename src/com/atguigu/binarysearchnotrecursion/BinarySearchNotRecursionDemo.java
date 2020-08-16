package com.atguigu.binarysearchnotrecursion;

/**
 * @author Leo
 * @date 2020/8/13 - 14:06
 */

public class BinarySearchNotRecursionDemo {

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 100);
        System.out.println("index = " + index);
    }

    /**
     * 功能：二分查找的非递归实现
     *
     * @param arr    待查找的数组，arr是升序排列
     * @param target 待查找的数
     * @return 如果找到返回对应的下标，如果没有找到返回-1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

}