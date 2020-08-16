package com.atguigu.kmp;

/**
 * @author Leo
 * @date 2020/8/13 - 19:54
 */

public class ViolenceMatch {

    public static void main(String[] args) {
        //测试
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int index = violenceMatch(str1, str2);
        System.out.println("index = " + index);
    }

    //暴力匹配算法
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;//索引指向str1
        int j = 0;//索引指向str2
        while (i < s1Len && j < s2Len) {//保证匹配时不越界
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);//i回溯，回到这一轮起始的下一位
                j = 0;
            }
        }
        if (j == s2Len) {//匹配成功
            return i - j;
        } else {
            return -1;
        }
    }

}
