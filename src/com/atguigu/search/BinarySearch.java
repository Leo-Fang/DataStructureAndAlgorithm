package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo
 * @date 2020/8/6 - 15:19
 */

public class BinarySearch {

    public static void main(String[] args) {
        /*int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = binarySearch(arr, 0, arr.length - 1, 1000);
        System.out.println("Index = " + index);*/

        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        List<Integer> indexList = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("IndexList = " + indexList);
    }

    //二分查找
    /**
     * @param arr       要查找的数组
     * @param left      左索引
     * @param right     右索引
     * @param findValue 要查找的值
     * @return 如果没有找到就返回-1，如果找到就返回其索引值
     */
    public static int binarySearch(int[] arr, int left, int right, int findValue) {
        //当left>right时，说明整个数组已递归完毕，但没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (findValue < arr[mid]) {//向左递归
            return binarySearch(arr, left, mid - 1, findValue);
        } else if (findValue > arr[mid]) {//向右递归
            return binarySearch(arr, mid + 1, right, findValue);
        } else {
            return mid;
        }
    }

    //二分查找所有满足条件的元素的索引
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findValue) {
        //当left>right时，说明整个数组已递归完毕，但没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        if (findValue < arr[mid]) {//向左递归
            return binarySearch2(arr, left, mid - 1, findValue);
        } else if (findValue > arr[mid]) {//向右递归
            return binarySearch2(arr, mid + 1, right, findValue);
        } else {
            List<Integer> indexList = new ArrayList<Integer>();
            //向mid索引值的左边扫描，将所有等于findValue的元素的索引添加到集合中
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findValue) {//arr[temp]!=findValue说明左边没有等于findValue的元素了
                    break;
                }
                //将索引值添加到集合中
                indexList.add(temp);
                temp -= 1;//temp左移，继续往左找
            }

            indexList.add(mid);//arr[mid]==findValue，所以mid也要添加到集合中

            //向mid索引值的右边扫描，将所有等于findValue的元素的索引添加到集合中
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findValue) {
                    break;
                }
                indexList.add(temp);
                temp += 1;
            }
            return indexList;
        }
    }

}
