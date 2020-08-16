package com.atguigu.tree.threadedbinarytree;

/**
 * @author Leo
 * @date 2020/8/9 - 10:37
 */

public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        //测试中序线索化二叉树功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "tim");

        //二叉树，手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试10号节点
        HeroNode leftNode = node5.getLeft();
        System.out.println("10号节点的前驱节点：" + leftNode);
        HeroNode rightNode = node5.getRight();
        System.out.println("10号节点的后继节点：" + rightNode);

        //测试线索化二叉树的遍历
        System.out.println("使用线索化的方式遍历线索化二叉树：");
        threadedBinaryTree.threaderList();
    }

}

//定义线索化二叉树ThreadedBinaryTree类
class ThreadedBinaryTree {

    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //为了实现线索化，需要创建一个指向当前节点的前驱节点的指针
    //在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre = null;

    //重载threadedNodes方法
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //对二叉树进行中序线索化的方法

    /**
     * @param node 当前需要线索化的节点
     */
    public void threadedNodes(HeroNode node) {
        //如果节点为空就不能线索化
        if (node == null) {
            return;
        }

        //线索化左子树
        threadedNodes(node.getLeft());

        //线索化当前节点
        //处理前驱节点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型，指向前驱节点
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //!!!每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;

        //线索化右子树
        threadedNodes(node.getRight());
    }

    //遍历线索化二叉树的方法
    public void threaderList() {
        //定义一个变量，存储当前遍历的节点，从root开始
        HeroNode node = root;
        while (node != null) {
            //如果leftType==0，说明指向的是左子树，所以要继续往下找
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //退出循环，说明找到了leftType==1的节点，打印当前节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1) {
                //获取当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //如果rightType!=1，就替换这个节点
            node = node.getRight();
        }
    }

}

//创建HeroNode
class HeroNode {

    private int no;
    private String name;
    private HeroNode left;//默认为null
    private HeroNode right;//默认为null

    //创建两个标记
    //如果leftType为0，表示指向的是左子树，如果为1，表示指向的是前驱节点
    private int leftType;
    //如果rightType为0，表示指向的是右子树，如果为1，表示指向的是后继节点
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name=\"" + name + '\"' + '}';
    }

}