package com.atguigu.avl;

/**
 * @author Leo
 * @date 2020/8/11 - 20:34
 */

public class AVLTreeDemo {

    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        //创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        //添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        //遍历
        System.out.println("中序遍历：");
        avlTree.infixOrder();

        /*System.out.println("没有平衡处理前：");
        System.out.println("树的高度 = " + avlTree.getRoot().height());
        System.out.println("树的左子树的高度 = " + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树的高度 = " + avlTree.getRoot().rightHeight());*/

        System.out.println("单旋转平衡处理后：");
        System.out.println("树的高度 = " + avlTree.getRoot().height());
        System.out.println("树的左子树的高度 = " + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树的高度 = " + avlTree.getRoot().rightHeight());
        System.out.println("AVLTree的根节点：" + avlTree.getRoot());
    }

}

//创建AVL树
class AVLTree {

    private Node root;

    public Node getRoot() {
        return root;
    }

    //添加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历二叉排序树
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历~");
        }
    }

    //查找准备删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找准备删除的节点的父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //删除节点
    public void deleteNode(int value) {
        if (root == null) {
            return;
        } else {
            //找到准备删除的节点targetNode
            Node targetNode = search(value);
            //如果没有找到准备删除的节点
            if (targetNode == null) {
                return;
            }
            //如果当前这棵二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                return;
            }
            //找到准备删除的节点的父节点
            Node parent = searchParent(value);

            //如果准备删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是其父节点的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {//删除有两棵子树的节点
                int minVal = deleteRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {//删除只有一棵子树的节点
                if (targetNode.left != null) {//如果准备删除的节点有左子节点
                    if (parent != null) {
                        if (parent.left.value == value) {//如果targetNode是parent的左子节点
                            parent.left = targetNode.left;
                        } else {//如果targetNode是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {//如果准备删除的节点有有子节点
                    if (parent != null) {
                        if (parent.left.value == value) {//如果targetNode是parent的左子节点
                            parent.left = targetNode.right;
                        } else {//如果targetNode是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    /**
     * 功能：删除以node为根节点的二叉排序树的最小节点，并返回它的value
     *
     * @param node 传入的节点（当中二叉排序树的根节点）
     * @return 返回以node为根节点的二叉排序树的最小节点的值
     */
    public int deleteRightTreeMin(Node node) {
        Node target = node;
        //循环查找左子节点，就会找最小值
        while (target.left != null) {
            target = target.left;
        }
        //这时target指向了值最小的那个节点，删除节点
        deleteNode(target.value);
        return target.value;
    }

}

//创建节点
class Node {

    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" + "value=" + value + '}';
    }

    //添加节点的方法
    public void add(Node node) {
        if (node == null) {
            return;
        }

        //判断准备添加的节点的value，和当前子树的根节点的value的关系
        if (node.value < this.value) {
            //如果当前子树的根节点的左子节点为null
            if (this.left == null) {
                this.left = node;
            } else {
                //递归的向左子树添加
                this.left.add(node);
            }
        } else {//准备添加的节点的value大于当前子树的根节点的value
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        //每当添加完一个节点后，如果（右子树的高度-左子树的高度）>1，左旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果当前节点的右子树的左子树的高度大于当前节点的右子树的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                //先对当前节点的右子树进行右旋转
                right.rightRotate();
                //再对当前节点进行左旋转
                leftRotate();
            } else {
                //直接进行左旋转
                leftRotate();
            }
            return;//!!!必须要，不然
        }

        //每当添加完一个节点后，如果（左子树的高度-右子树的高度）>1，右旋转
        if (leftHeight() - rightHeight() > 1) {
            //如果当前节点的左子树的右子树的高度大于当前节点的左子树的左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前节点的左子树进行左旋转
                left.leftRotate();
                //再对当前节点进行右旋转
                rightRotate();
            } else {
                //直接进行右旋转
                rightRotate();
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 功能：查找准备删除的节点
     *
     * @param value 准备删除的节点的值
     * @return 如果找到就返回该节点，否则就返回null
     */
    public Node search(int value) {
        if (value == this.value) {//就是当前节点
            return this;
        } else if (value < this.value) {//查找的节点的值小于当前节点的值，向左子树递归查找
            //如果当前节点的左子节点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {//查找的节点的值不小于当前节点的值，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 功能：查找准备删除节点的父节点
     *
     * @param value 准备删除的节点的值
     * @return 返回要删除的节点的父节点，如果没有就返回null
     */
    public Node searchParent(int value) {
        //如果当前节点就是准备删除的节点的父节点，就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);//向左子树递归查找
            } else if (value > this.value && this.right != null) {
                return this.right.searchParent(value);//向右子树递归查找
            } else {
                return null;//没有找到父节点
            }
        }
    }

    //返回以该节点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        } else {
            return left.height();
        }
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        } else {
            return right.height();
        }
    }

    //左旋转
    private void leftRotate() {
        //创建新的节点，value等于当前根节点的value
        Node newNode = new Node(value);
        //把新节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //把新节点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的value替换成当前节点的右子树的value
        value = right.value;
        //把当前节点的右子树设置成当前节点的右子树的右子树
        right = right.right;
        //把当前节点的左子树设置成新节点
        left = newNode;
    }

    //右旋转
    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

}