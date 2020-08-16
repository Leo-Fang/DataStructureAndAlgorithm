package com.atguigu.queue;

import java.util.Scanner;

/**
 * @author Leo
 * @date 2020/7/31 - 20:25
 */

public class ArrayQueueDemo {

    public static void main(String[] args) {
        //测试
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key;//接收用户的输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个提示菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列的头部数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's'://显示队列
                    queue.showQueue();
                    break;
                case 'a'://添加数据
                    System.out.println("请输入一个数字");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g'://取数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是：%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h'://查看头部数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列的头部数据是：%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e'://退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序已退出~~~");
    }

}

//使用数组模拟队列——编写一个ArrayQueue类
class ArrayQueue {

    private int maxSize;//表示数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数组用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;//指向队列的头部的前一个位置
        rear = -1;//指向队列的尾部，即队列的最后一个数据
    }

    //判断队列是否已满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //先判断队列是否已满
        if (isFull()) {
            System.out.println("队列已满，不能加入数据");
            return;
        }

        rear++;
        arr[rear] = n;
    }

    //获取队列中的数据（出队列）
    public int getQueue() {
        //先判断队列是否为空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("队列为空，不能取数据");
        }

        front++;//因为front是指向队列头部的前一个位置
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据~~~");
            return;
        }

        //遍历
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //显示队列的头数据（不是取出数据）
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据~~~");
        }
        return arr[front + 1];
    }

}