package com.atguigu.kmp;

import java.util.Arrays;

/**
 * @author Leo
 * @date 2020/8/13 - 21:08
 */

public class KMPAlgorithm {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext(str2);
        System.out.println("next = " + Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index = " + index);
    }

    /**
     * kmp算法
     *
     * @param str1 源字符串
     * @param str2 查找子串
     * @param next 子串（str2）对应的部分匹配表
     * @return 如果找到返回第一次匹配的位置，否则返回-1
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];//!!!
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取一个字符串的部分匹配值
    public static int[] kmpNext(String dest) {
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串长度为1，则部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];//？？？
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
