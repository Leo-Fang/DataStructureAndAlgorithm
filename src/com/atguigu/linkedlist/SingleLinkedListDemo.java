package com.atguigu.linkedlist;

import java.util.Stack;

/**
 * @author Leo
 * @date 2020/8/1 - 14:00
 */

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //测试
        //创建节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");
        HeroNode hero5 = new HeroNode(5,"555","555");
        HeroNode hero6 = new HeroNode(6,"666","666");
        HeroNode hero7 = new HeroNode(7,"777","777");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();

        //添加元素
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero5);
        singleLinkedList.add(hero7);

        singleLinkedList2.add(hero2);
        singleLinkedList2.add(hero4);
        singleLinkedList2.add(hero6);

        /*//按照编号的顺序添加
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        //修改前
        System.out.println("修改前：");
        singleLinkedList.list();

        //修改节点
        HeroNode newHeroNode = new HeroNode(2,"小卢","玉麒麟~~");
        singleLinkedList.update(newHeroNode);

        //显示链表
        System.out.println("修改后：");
        singleLinkedList.list();

        //删除节点
        singleLinkedList.delete(1);
        singleLinkedList.delete(4);

        //删除后的链表
        System.out.println("删除后的链表：");
        singleLinkedList.list();

        //单链表中有效节点的个数
        System.out.println("有效节点的个数=" + getLength(singleLinkedList.getHead()));

        //获取倒数第k个节点
        HeroNode res = findLastIndexNode(singleLinkedList.getHead(),1);
        System.out.println("res=" + res);*/

        //原链表
        System.out.println("原链表：");
        singleLinkedList.list();
        singleLinkedList2.list();

        /*System.out.println("反转后的链表：");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();*/

        //测试逆序打印，没有改变链表本身的结构
        /*System.out.println("测试逆序打印单链表：");
        reversePrint(singleLinkedList.getHead());*/

        //测试链表是否有序
        /*boolean b = isOrdered(singleLinkedList.getHead());
        System.out.println(b);*/

        //合并两个有序单链表
        SingleLinkedList newSingleLinkedList = mergeSingleLinkedList(singleLinkedList, singleLinkedList2);
        newSingleLinkedList.list();
    }

    /**
     *
     * @param head 链表的头节点
     * @return 返回链表有效节点的个数
     */
    //求单链表中有效节点的个数
    public static int getLength(HeroNode head) {
        if (head.next == null) {//空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助变量
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //查找单链表中倒数第index个节点
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //如果链表为空，返回null
        if (head.next == null) {
            return null;
        }
        //先求链表的长度（节点个数）
        int size = getLength(head);
        //遍历size-index位置，即倒数第index个节点
        //先判断index是否有效
        if (index < 0 || index > size) {
            return null;
        }
        //定义辅助遍历，for循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0; i < size-index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //单链表反转
    public static void reverseList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助的指针（变量）帮助遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点cur的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的节点，每遍历一个，就将其取出，放在新的链表reversHead的最前端
        while (cur != null) {
            next = cur.next;//暂时保存当前节点的下一个节点
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;//将cur连接到新的链表上
            cur = next;//让cur后移
        }
        //将head.next指向reverseHead.next，实现链表反转
        head.next = reverseHead.next;
    }

    //方式2：利用栈结构逆序打印链表
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表，不能打印
        }
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //判断单链表是否有序
    public static boolean isOrdered(HeroNode head) {
        boolean flag;//表示是否有序
        HeroNode temp = head.next;
        //思路：先得到链表的长度length，再定义一个计数器count，如果前一个节点的序号比后一个节点的序号小，count就加1
        //如果count==length-1，就说明是有序，否则无序
        int length = getLength(head);
        int count = 0;
        //先判断链表是否为空
        if (temp == null) {
            throw new RuntimeException("链表为空");
        }
        //判断链表是否就一个元素
        if (temp.next == null) {
            flag = true;
        }
        while (temp.next != null) {
            if (temp.no < temp.next.no) {
                count++;
                temp = temp.next;
            } else {
                break;
            }
        }
        if (count == length-1) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    //合并两个有序单链表，合并后仍有序
    public static SingleLinkedList mergeSingleLinkedList(SingleLinkedList s1, SingleLinkedList s2) {
        //先判断两个单链表是否有序
        if (!(isOrdered(s1.getHead()) && isOrdered(s2.getHead()))) {
            throw new RuntimeException("链表无序");
        }
        SingleLinkedList newSingleLinkedList = new SingleLinkedList();
        newSingleLinkedList = s1;
        HeroNode temp = s2.getHead().next;
        while (temp != null) {
            HeroNode next = temp.next;//用next预存temp下一个节点的值
            newSingleLinkedList.addByOrder(temp);
            temp = next;
        }
        return newSingleLinkedList;
    }

}

//定义SingleLinkedList管理我们的英雄
class SingleLinkedList {
    //初始化头节点，头节点不动，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //第一种方式添加英雄：添加节点到单向链表，不考虑编号顺序的时候
    //先找到当前链表的最后节点，再将这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不动，因此需要一个辅助变量temp
        HeroNode temp = head;
        //遍历链表，找到最后的节点
        while (true) {
            //链接最后一个节点的next为null
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //退出while循环时，temp指向了链表的最后，将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }

    //第二种方式添加英雄：根据排名将英雄插入到指定位置，如果已有该排名，则添加失败，并给出提示
    public void addByOrder(HeroNode heroNode) {
        //头节点不能动，因此仍然需要一个辅助指针（变量）帮助来找到添加的位置
        //因为是单链表，找到的temp是位于添加位置的前一个节点，否则添加不了
        HeroNode temp = head;
        boolean flag = false;//flag表示要添加的编号是否存在，默认为false不存在
        while (true) {
            if (temp.next == null) {//说明temp已经在链表最后了，直接插入即可
                break;
            }
            if (temp.next.no > heroNode.no) {//位置已找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {//说明要添加的英雄编号已存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag的值
        if (flag) {//flag为真说明编号已存在，不能添加
            System.out.printf("准备插入的英雄编号%d已存在，不能重复添加\n", heroNode.no);
        } else {
            //插入到链表中temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点：根据no编号修改（no编号不能改）
    //根据newHeroNode的no来修改
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //根据no找到需要修改的节点
        HeroNode temp = head.next;//定义一个辅助变量
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;//已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                flag = true;//已找到
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到编号为%d的节点，无法修改\n", newHeroNode.no);
        }
    }

    //删除节点
    //1.head不能动，因此需要一个temp辅助节点找到待删除节点的前一个节点
    //2.在比较时，是temp.next.no和需要删除的节点的no比较
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;//表示是否找到待删除的节点
        while (true) {
            if (temp.next == null) {//已经到链表的最后
                break;
            }
            if (temp.next.no == no) {//找到待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的编号%d不存在\n", no);
        }
    }

    //遍历显示链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //因为head节点不动，因此需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }
}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //重写toString方法
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ",name=" + name + ",nickName=" + nickName + "]";
    }
}