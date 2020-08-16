package com.atguigu.linkedlist;

/**
 * @author Leo
 * @date 2020/8/2 - 16:30
 */

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        //测试
        //创建节点
        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");
        HeroNode2 hero5 = new HeroNode2(5,"555","555");

        //创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        /*doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        //显示链表
        System.out.println("原双向链表：");
        doubleLinkedList.list();

        //修改
        HeroNode2 newHeroNode = new HeroNode2(4,"公孙胜","入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的双向链表：");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.delete(3);
        System.out.println("删除后的双向链表");
        doubleLinkedList.list();*/

        //有序添加节点
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero5);
        doubleLinkedList.list();
    }

}

//创建一个双向链表的类
class DoubleLinkedList {
    //初始化头节点，头节点不动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0,"","");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //因为head节点不动，因此需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
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

    //添加
    public void add(HeroNode2 heroNode) {
        //因为head节点不动，因此需要一个辅助变量temp
        HeroNode2 temp = head;
        //遍历链表，找到最后的节点
        while (true) {
            //链接最后一个节点的next为null
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //退出while循环时，temp指向了链表的最后
        //将最后这个节点temp的next指向新的节点，将新节点的pre指向temp，形成双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //有序添加，如果已有该排名，则添加失败，并给出提示
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
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
            if (temp.next == null) {
                temp.next = heroNode;
                heroNode.pre = temp;
            } else {
                heroNode.next = temp.next;
                heroNode.pre = temp;
                temp.next.pre = heroNode;
                temp.next = heroNode;
            }
        }
    }

    //修改节点
    public void update(HeroNode2 newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //根据no找到需要修改的节点
        HeroNode2 temp = head.next;//定义一个辅助变量
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
    //对于双向链表，可以直接找到要删除的节点，然后进行自我删除即可
    public void delete(int no) {
        //判断当前链表是否为空
        if (head.name == null) {
            System.out.println("链表为空，无法删除~~~");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false;//表示是否找到待删除的节点
        while (true) {
            if (temp == null) {//已经到链表的最后
                break;
            }
            if (temp.no == no) {//找到待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag
        if (flag) {
            temp.pre.next = temp.next;
            //如果是最后一个节点，就不需要执行下面这句，否则会出现空指针异常
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的编号%d不存在\n", no);
        }
    }
}

//定义HeroNode2，每个HeroNode2对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;//指向下一个节点，默认为null
    public HeroNode2 pre;//指向前一个节点，默认为null

    //构造器
    public HeroNode2(int no, String name, String nickName) {
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