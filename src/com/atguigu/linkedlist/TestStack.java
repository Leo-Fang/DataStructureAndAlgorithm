package com.atguigu.linkedlist;

import java.util.Stack;

/**
 * @author Leo
 * @date 2020/8/2 - 10:20
 */
//演示栈Stack的基本使用
public class TestStack {

    public static void main(String[] args) {
        Stack<String> stack = new Stack();
        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        //出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());//pop：将栈顶的数据取出
        }
    }

}
