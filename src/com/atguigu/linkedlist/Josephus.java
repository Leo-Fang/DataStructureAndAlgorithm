package com.atguigu.linkedlist;

/**
 * @author Leo
 * @date 2020/8/2 - 21:53
 */

public class Josephus {

    public static void main(String[] args) {
        //测试
        //创建环形单向链表
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        //boy报数出圈
        circleSingleLinkedList.countBoy(1,2,5);
    }

}

//创建一个环形单向链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = null;
    //添加boy，构建成一个环形链表
    public void addBoy(int nums) {
        //nums>=1才有意义
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针，帮助构建环形链表
        //使用for循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //注意第一个boy
            if (i == 1) {
                first = boy;
                first.setNext(boy);//构成环
                curBoy = first;//让curBoy指向第一个boy
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("没有任何boy~~~");
            return;
        }
        //first不能动，需要一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("boy的编号%d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {//说明已经遍历完
                break;
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }

    //根据用户的输入，计算出boy出圈的顺序

    /**
     *
     * @param startNo 表示从第几个boy开始数
     * @param countNum 表示数几下
     * @param nums 表示最初由多少个小孩
     */
    public  void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新设置");
            return;
        }
        //创建辅助指针，帮助boy出圈
        Boy helper = first;
        //将helper指向环形链表的最后一个节点，也就是first前一个节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //boy报数前，先让first指向初始boy的位置，需要移动startNo-1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当boy报数时，让first和helper同时移动countNum-1次，然后出圈
        //这是一个循环操作，直到圈中只有一个节点
        while (true) {
            if (helper == first) {//说明圈中只有一个节点了
                break;
            }
            //让first和helper同时移动
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点就是要出圈的节点
            System.out.printf("Boy%d出圈\n", first.getNo());
            //将first指向的节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留着圈中的Boy为%d\n", first.getNo());
    }
}

//创建一个Boy类，表示一个节点
class Boy {
    private int no;//编号
    private Boy next;//指向下一个节点，默认为null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}