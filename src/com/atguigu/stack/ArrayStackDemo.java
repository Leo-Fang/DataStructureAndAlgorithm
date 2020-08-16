package com.atguigu.stack;

import java.util.Scanner;

/**
 * @author Leo
 * @date 2020/8/3 - 11:07
 */

public class ArrayStackDemo {

    public static void main(String[] args) {
        //测试
        //创建一个ArrayStack对象表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制是否退出循环
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show：显示栈的内容");
            System.out.println("exit：退出程序");
            System.out.println("push：添加数据到栈（入栈）");
            System.out.println("pop：从栈中取出数据（出栈）");
            System.out.println("请选择您的操作");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个整数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("输入无效~");
                    break;
            }
        }
        System.out.println("程序退出~~~");
    }

}

//定义一个ArrayStack表示栈
class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //空栈
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //先判断是否栈满
        if (isFull()) {
            System.out.println("栈满，无法入栈~~~");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        //先判断栈是否是空栈
        if (isEmpty()) {
            throw new RuntimeException("空栈，没有数据~~~");//抛出异常
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈，需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("空栈，没有数据~~~");
            return;
        }
        //从栈顶开始
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}