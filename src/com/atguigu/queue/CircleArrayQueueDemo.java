package com.atguigu.queue;

import java.util.Scanner;

/**
 * @author Leo
 * @date 2020/8/1 - 10:15
 */

public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        //测试
        System.out.println("数组模拟环形队列~~~");
        //创建一个队列
        CircleArray queue = new CircleArray(4);//数组容量为4，但队列的有效数据最大为3
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
class CircleArray {

    private int maxSize;//表示数组的最大容量
    private int front;//指向队列第一个元素的位置
    private int rear;//指向队列最后一个元素的位置
    private int[] arr;//该数组用于存放数据，模拟队列

    //创建队列的构造器
    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否已满
    public boolean isFull() {
        return (rear+1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //先判断队列是否已满
        if (isFull()) {
            System.out.println("队列已满，不能加入数据");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，需要考虑取模
        rear = (rear+1) % maxSize;
    }

    //获取队列中的数据（出队列）
    public int getQueue() {
        //先判断队列是否为空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("队列为空，不能取数据");
        }
        //1.先把front对应的值保存到一个临时变量中
        //2.将front后移，考虑取模
        //3.将临时保存的变量返回
        int value = arr[front];
        front = (front+1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据~~~");
            return;
        }

        //从front开始遍历，变量有效数据的个数
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n", i%maxSize, arr[i%maxSize]);
        }
    }

    //求当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据（不是取出数据）
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据~~~");
        }
        return arr[front];
    }

}