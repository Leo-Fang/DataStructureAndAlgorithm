package com.atguigu.search;

import java.util.Arrays;

/**
 * @author Leo
 * @date 2020/8/6 - 21:14
 */

public class FibonacciSearch {

    public static int maxSize = 40;//斐波那契数列元素的个数，根据要查找数列的规模做适当调整

    public static void main(String[] args) {
//        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int[] arr = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            arr[i] = i;
        }
        int index = fibonacciSearch(arr, 3794);
        System.out.println("Index = " + index);
    }

    //因为mid=low+F(k-1)-1，需要使用到斐波那契数列，因此需要先获取到一个斐波那契数列
    //用非递归的方法得到一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //斐波那契查找
    /**
     * @param arr
     * @param key 需要查找的值
     * @return 如果没有找到就返回-1，如果找到就返回对应的索引值
     */
    public static int fibonacciSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;//存放mid值
        int[] f = fib();//获取斐波那契数列
        //获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k]的值可能大于arr的长度，因此需要使用Arrays类构造一个新的数组，并指向arr
        int[] temp = Arrays.copyOf(arr, f[k] - 1);
        //用arr数组最后的值填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        //使用while循环来寻找key
        while (low <= high) {//只要满足这个条件就一直循环
            mid = low + f[k - 1] - 1;
            //说明一下下面的k--和k-=2：
            //数组长度为f[k]-1 = (f[k-1]-1) + (f[k-2]-1) +1，最后这个+1的1可以看成是mid的位置
            //向mid左边查找时，数组长度就是f[k-1]-1，所以是k--
            //向mid右边查找时，数组长度就是f[k-2]-1，所以是k-2
            if (key < temp[mid]) {//向mid左边查找
                high = mid - 1;
                k--;
            } else if (key > temp[mid]) {//向mid右边查找
                low = mid + 1;
                k -= 2;
            } else {
                //需要确定返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {//high后面都是实际数组中没有的
                    return high;
                }
            }
        }
        return -1;
    }

}
