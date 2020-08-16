package com.atguigu.stack;

/**
 * @author Leo
 * @date 2020/8/3 - 15:25
 */

public class Calculator {

    public static void main(String[] args) {
        //定义一个计算表达式
        String expression = "9-7*1*1+2";
        //创建一个数栈和一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keepNum = "";//用于拼接多位数
        //开始while循环扫描expression
        while(true) {
            //依次得到expression中的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么再做相应的处理
            if (operStack.isOper(ch)) {//如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    //符号栈非空时判断运算符的优先级
                    //优先级<=时
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算的结果入数栈
                        numStack.push(res);
                        //把当前的运算符入符号栈
                        operStack.push(ch);
                    } else {
                        //优先级>时
                        operStack.push(ch);
                    }
                } else {
                    //符号栈为空直接入栈
                    operStack.push(ch);
                }
            } else {
                //如果是数字，直接入栈
//                numStack.push(ch - 48);//字符和对应的数字之间差48
                //处理多位数时，不能发现是一个数就立即入栈，index需要向expression的后一位再看一位
                //如果是数就再扫描下一位，如果是符号才入栈，所以需要一个变量来拼接字符串
                keepNum += ch;
                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是数字还是运算符
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        //清空keepNum!!!!!!
                        keepNum = "";
                    }
                }
            }
            //让index后移，并判断是否扫描到expression的最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号进行运算
        while (true) {
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字，即结果
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);//运算结果入数栈
        }
        //将数栈最后的数pop出
        int result = numStack.pop();
        System.out.printf("%s = %d\n", expression, result);

    }

}

//创建一个栈
class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //获取栈顶的值（但不pop）
    public int peek() {
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //空栈
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //先判断是否栈满
        if (isFull()) {
            System.out.println("栈满，无法入栈~~~");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        //先判断栈是否是空栈
        if (isEmpty()) {
            throw new RuntimeException("空栈，没有数据~~~");//抛出异常
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈，需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("空栈，没有数据~~~");
            return;
        }
        //从栈顶开始
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级，优先级使用数字表示
    //数字越大，优先级越高
    public int priority(int oper) {
        //假定目前表达式中的运算符只有：+ - * /
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;//res用于存放计算的结果
        switch (oper) {
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}