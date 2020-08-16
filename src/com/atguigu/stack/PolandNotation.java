package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Leo
 * @date 2020/8/3 - 19:39
 */

public class PolandNotation {

    public static void main(String[] args) {
        //测试中缀表达式转后缀表达式并计算结果
        //中缀表达式转对应的List
        String expression = "1+((2+3)×4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式： " + infixExpressionList);

        //中缀表达式对应的List转后缀表达式对应的List
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式： " + suffixExpressionList);

        System.out.printf("%s = %d\n", expression, calculate(suffixExpressionList));

        //先定义一个逆波兰表达式。为了方便，逆波兰表达式中的数组和符号用空格隔开        //(3+4)×5-6  => 3 4 + 5 × 6 -
        //4*5-8+60+8/2 => 4 5 * 8 - 60 + 8 2 / +
//        String suffixExpression = "3 4 + 5 × 6 -";
        /*String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";

        //先将“3 4 + 5 × 6 -”放到ArrayList中
        //将ArrayList传递给一个方法，遍历ArrayList，配合栈完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println("list = " + list);

        int result = calculate(list);
        System.out.println("result = " + result);*/
    }

    //将中缀表达式转换成对应的List
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List，存放中缀表达式对应的内容
        List<String> list = new ArrayList<String>();
        int i = 0;//指针，用于遍历中缀表达式字符串
        String str;//用于拼接多位数
        char c;//每遍历到一个字符，就放到c中
        do {
            //如果c是一个非数字，加入到list中
            if ((c=s.charAt(i)) < 48 || (c=s.charAt(i)) > 57) {
                list.add("" + c);
                i++;
            } else {//如果是一个数字，考虑是否是多位数
                str = "";
                while (i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                list.add(str);
            }
        } while (i < s.length());
        return list;
    }

    //将中缀表达式对应的List转换成后缀表达式对应的List
    public static List<String> parseSuffixExpressionList(List<String> list) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>();//符号栈
        //说明：因为s2在整个转换过程中没有pop操作，而且后面还需要逆序输出
        //因此用栈比较麻烦，所以直接用List
//        Stack<String> s2 = new Stack<String>();//存储中间结果的栈
        List<String> s2 = new ArrayList<String>();

        //遍历list
        for (String item : list) {
            //如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号，则依次弹出s1栈顶的运算符并加入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将左括号从s1弹出，消除这对小括号
            } else {
                //比较item和s1栈顶运算符的优先级
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //将item压入s1
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;//因为s2是List，因此按顺序输出的就是对应的后缀表达式的List
    }


    //将逆波兰表达式，依次将数字和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression按空格分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //对逆波兰表达式进行计算
    public static int calculate(List<String> ls) {
        //创建栈，只需要一个栈即可
        Stack<String> stack = new Stack<String>();
        //遍历ls
        for (String item : ls) {
            if (item.matches("\\d+")) {//匹配的是多位整数
                stack.push(item);
            } else {
                //pop出两个数进行运算，再将结果入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("×") || item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }

}

//编写一个类Operation，可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 1;
    private static int DIV = 1;

    //定义一个方法，返回对应的优先级的数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "×":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }
        return result;
    }
}