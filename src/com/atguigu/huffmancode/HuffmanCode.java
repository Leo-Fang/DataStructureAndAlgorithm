package com.atguigu.huffmancode;

import java.io.*;
import java.util.*;

/**
 * @author Leo
 * @date 2020/8/10 - 20:05
 */

public class HuffmanCode {

    public static void main(String[] args) {
        //给定一个字符串
        /*String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);//40*/
        /*for (int i = 0; i < contentBytes.length; i++) {
            System.out.println(contentBytes[i]);
        }*/

        /*List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes = " + nodes);

        //创建的赫夫曼树
        System.out.println("赫夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        huffmanTreeRoot.preOrder();

        //生成的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println("生成的赫夫曼编码：" + huffmanCodes);

        //按照赫夫曼编码表转换成的赫夫曼编码
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : contentBytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        System.out.println(stringBuilder);

        //压缩后的赫夫曼编码
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        System.out.println("huffmanCodeBytes = " + Arrays.toString(huffmanCodeBytes));*/

        /*byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("huffmanCodeBytes = " + Arrays.toString(huffmanCodeBytes) + "长度=" + huffmanCodeBytes.length);

        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("原来的字符串 = " + new String(sourceBytes));*/

        //测试压缩文件
        /*String srcFile = "d://IDEA/src.jpg";
        String dstFile = "d://IDEA/dst.zip";

        zipFile(srcFile, dstFile);
        System.out.println("压缩文件OK~");*/

        //测试解压文件
        String zipFile = "d://IDEA/dst.zip";
        String dstFile = "d://IDEA/src2.jpg";
        unzipFile(zipFile, dstFile);
        System.out.println("解压OK~");
    }

    /**
     * 功能：对按照赫夫曼编码方式压缩的文件进行解压
     *
     * @param zipFile 准备解压的文件的全路径
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unzipFile(String zipFile, String dstFile) {
        //创建文件输入流
        InputStream is = null;
        //创建对象输入流
        ObjectInputStream ois = null;
        //创建文件输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes写入到目标路径
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    /**
     * 功能：将一个文件进行压缩
     *
     * @param srcFile 准备压缩的文件的全路径
     * @param dstFile 压缩后将压缩文件放到哪个路径
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建文件的输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;
        try {
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]数组
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //对源文件进行压缩
            byte[] huffmanBytes = huffmanZip(b);
            //利用文件的输出流存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //这里以对象流的方式写入赫夫曼编码是为了以后恢复源文件时使用
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 功能：对压缩后的赫夫曼编码进行解码
     *
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 按照赫夫曼编码表进行压缩后的字节数组
     * @return 原字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //得到huffmanBytes对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToString(!flag, b));
        }

        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为查询是反向的
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建一个集合存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                String key = stringBuilder.substring(i, i + count);//i不动，让count移动，直到匹配的编码表中的字符
                b = map.get(key);
                if (b == null) {//没有匹配到
                    count++;
                } else {//匹配到了
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i移动count的长度，进行匹配下一个字符
        }
        //for循环结束后，list中存储了所有的字符，再把list中的数据放入到byte[]中并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 功能：将一个byte转成一个二进制的字符串
     *
     * @param flag 标识是否需要补高位，如果是true则需要补高位，如果是false则不需要补高位，如果是最后一个字节无需补高位
     * @param b    传入的byte
     * @return 返回b对应的二进制的字符串（注意是按补码返回）
     */
    private static String byteToString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b;
        //如果是正数还要补高位
        if (flag) {
            temp |= 256;//按位或256（1 0000 0000）如：0000 0001 | 256 =>1 0000 0001
        }
        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 功能：将前面的方法封装起来，便于调用
     *
     * @param bytes
     * @return
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    /**
     * 功能：将字符串对应的byte[]数组，按照生成的赫夫曼编码表，返回一个压缩后的赫夫曼编码byte[]数组
     *
     * @param bytes        原始字符串对应的byte[]数组
     * @param huffmanCodes 生成的赫夫曼编码表
     * @return 返回按照赫夫曼编码表生成的压缩后的赫夫曼编码byte[]数组
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //将字符串转换成byte[]数组
        int len;//统计返回的赫夫曼编码byte[]数组的长度
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //上面的代码也可以用一行表示：int len = (stringBuilder.length() + 7) / 8;
        //创建压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {//最后的字符串可能没有8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte转换成一个byte，放入到byte数组中
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //生成赫夫曼树对应的赫夫曼编码
    //1.将赫夫曼编码表存放在Map中
    private static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    //2.在生成赫夫曼编码时，需要去拼接编码，定义一个StringBuilder存储某个叶子节点的路径
    private static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便，重载getCodes方法
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能：将传入的node节点的所有叶子节点的赫夫曼编码得到，
     *
     * @param node          传入节点
     * @param code          路径：左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {
            //判断当前node是叶子节点还是非叶子节点
            if (node.data == null) {//非叶子节点
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {//叶子节点
                //表示找到了某个叶子节点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    /**
     * @param bytes 字符串的字节数组
     * @return 返回一个Node的List，形如 nodes = [Node{data=32, weight=9}, Node{data=97, weight=5}...]
     */
    private static List<Node> getNodes(byte[] bytes) {
        //创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();

        //遍历bytes，统计每一个byte出现的次数->map[key,value]
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);//Map中还没有这个字符的数据，第一次存放
            } else {
                counts.put(b, count + 1);
            }
        }

        //把每个键值对转换成一个Node对象，并加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过List创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序，从小到大
            Collections.sort(nodes);
            //取出最小的二叉树
            Node leftNode = nodes.get(0);
            //取出第二小的二叉树
            Node rightNode = nodes.get(1);
            //创建一棵新的二叉树，它的根节点没有数据，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //将取出的两棵二叉树从nodes中删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树加入到nodes中
            nodes.add(parent);
        }
        //nodes中最后的节点就是赫夫曼树的根节点
        return nodes.get(0);
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空，无法遍历~~~");
        }
    }

}

//创建Node，带数据和权值
class Node implements Comparable<Node> {

    Byte data;//存放数据（字符），比如'a'=97，' '=32
    int weight;//权值，即字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" + "data=" + data + ", weight=" + weight + '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

}
