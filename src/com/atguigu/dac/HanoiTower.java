package com.atguigu.dac;

/**
 * @author Leo
 * @date 2020/8/13 - 14:55
 */

public class HanoiTower {

    public static void main(String[] args) {
        hanoiTower(4, 'A', 'B', 'C');
    }

    //汉诺塔（分治算法）
    public static void hanoiTower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘：" + a + "->" + c);
        } else {
            //如果num>=2
            //1.把上面的num-1个盘从a->b，移动过程中会用到c
            hanoiTower(num - 1, a, c, b);
            //2.把最下面的盘从a->c
            System.out.println("第" + num + "个盘：" + a + "->" + c);
            //3.把b塔上的所有盘从b->c，移动过程中会用到a
            hanoiTower(num - 1, b, a, c);
        }
    }

}
