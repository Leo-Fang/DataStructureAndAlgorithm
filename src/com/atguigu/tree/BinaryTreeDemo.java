package com.atguigu.tree;

/**
 * @author Leo
 * @date 2020/8/8 - 16:22
 */

public class BinaryTreeDemo {

    public static void main(String[] args) {
        //先创建一棵二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //手动创建二叉树
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);

        //测试
        /*System.out.println("前序遍历");
        binaryTree.preOrder();//1，2，3，5，4

        System.out.println("中序遍历");
        binaryTree.infixOrder();//2，1，5，3，4

        System.out.println("后序遍历");
        binaryTree.postOrder();//2，5，4，3，1*/

        //测试查找
        /*System.out.println("前序遍历查找");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if (resNode != null) {
            System.out.printf("该英雄信息为：no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.println("没有找到该英雄~~~");
        }*/

        /*System.out.println("中序遍历查找");
        HeroNode resNode = binaryTree.infixOrderSearch(5);
        if (resNode != null) {
            System.out.printf("该英雄信息为：no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.println("没有找到该英雄~~~");
        }*/

        /*System.out.println("后序遍历查找");
        HeroNode resNode = binaryTree.postOrderSearch(5);
        if (resNode != null) {
            System.out.printf("该英雄信息为：no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.println("没有找到该英雄~~~");
        }*/

        //测试删除节点
        System.out.println("删除前，前序遍历");
        binaryTree.preOrder();
//        binaryTree.deleteNode(5);
        binaryTree.deleteNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();
    }

}

//定义二叉树BinaryTree
class BinaryTree {

    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    //删除节点
    public void deleteNode(int no) {
        if (root != null) {
            //如果只有一个root节点，判断root是不是要删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.deleteNode(no);
            }
        } else {
            System.out.println("空树，无法删除~~~");
        }
    }

}

//先创建HeroNode节点
class HeroNode {

    private int no;
    private String name;
    private HeroNode left;//默认为null
    private HeroNode right;//默认为null

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

    //前序遍历的方法
    public void preOrder() {
        System.out.println(this);//先输出父节点
        //向左子树递归前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //向右子树递归前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历的方法
    public void infixOrder() {
        //向左子树递归中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);//输出父节点
        //向右子树递归中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历的方法
    public void postOrder() {
        //向左子树递归后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        //向右子树递归后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);//输出父节点
    }

    //前序查找
    /**
     * @param no 要查找的no
     * @return 如果找到就返回该Node，如果没有找到就返回null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历查找~~~");
        //比较当前节点是不是要找的节点
        if (this.no == no) {
            return this;
        }
        //判断当前节点的左子节点是否为空，如果不为空则向左递归前序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        //如果左子节点前序查找递归完后resNode不为空，说明在左子树找到了，就返回
        if (resNode != null) {
            return resNode;
        }
        //判断当前节点的右子节点是否为空，如果不为空则向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        //判断当前节点的左子节点是否为空，如果不为空则开始递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        //如果左子节点中序查找递归完后resNode不为空，说明在左子树找到了，就返回
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序遍历查找~~~");
        //如果没有找到，就和当前节点比较，如果是就返回当前节点
        if (this.no == no) {
            return this;
        }
        //否则在右子树中继续递归中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        //判断当前节点的左子节点是否为空，如果不为空则开始递归后序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        //如果左子节点后序查找递归完后resNode不为空，说明在左子树找到了，就返回
        if (resNode != null) {
            return resNode;
        }
        //否则在右子树中继续递归中序查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序遍历查找~~~");
        //如果都没有找到，就和当前节点比较，如果是就返回当前节点，否则就返回空
        if (this.no == no) {
            return this;
        } else {
            return null;
        }
    }

    //递归删除节点
    public void deleteNode(int no) {
        //如果当前节点的左子节点不为空，并且左子节点就是要删除的节点，那么就将this.left=null，并且返回
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //如果当前节点的右子节点不为空，并且右子节点就是要删除的节点，那么就将this.right=null，并且返回
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //向左子树进行递归删除
        if (this.left != null) {
            this.left.deleteNode(no);
        }
        //向右子树进行递归删除
        if (this.right != null) {
            this.right.deleteNode(no);
        }
    }

}