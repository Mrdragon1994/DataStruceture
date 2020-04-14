package com.cql.stack

/**
 * 使用栈实现计算器的简易功能，支持两位数的加减乘除,先不考虑小括号问题，中缀表达式
 * @Author ChangQilong* @Date 2020/3/21 16:04
 * //实现思路：设置两个栈,第一个栈专门用来存放数字,第二个栈用来存放运算符;
 *     //1.通过一个index值,用来计算对字符串表达式进行扫描的索引，用来遍历字符串表达式;
 *     //2.如果index遍历到了数字,把数字放入到数栈
 *     //3.如果发现index扫描到的是符号，需要分两种情况解决
 *     //3.1. 如果当前符号栈为空，就直接入栈
 *     //3.2. 如果符号栈不为空，也就是有操作符，就要进行符号优先级的比较，
 *     //如果当前的操作符优先级小于或者等于栈中的操作符，就需要从数栈中POP出2个数，并且从符号栈中POP出一个符号，进行运算，将得到的结果PUSH到数栈中,然后将当前新的扫描到的操作符入符号栈
 *     //如果当前的操作符优先级大于栈中的操作符，则直接入符号栈
 *     //当表达式扫描完毕后，就顺序的从数栈和符号栈中，POP出相应的数和符号，并做最后的运算
 *     //最后数栈中只会有一个数字，就是表达式的结果
 */
class UseStackForCalculator {

    public static void main(String[] args) {
        String expression = "125*6+9-8"
        //创建两个栈，一个是数栈，一个是符号栈
        def numStack = new ArrayStack2(10)
        def opeStack = new ArrayStack2(10)
        //定义变量
        def index = 0 //索引,用来扫描
        def num1 = 0 //从栈弹出的第一个数字
        def num2 = 0 //从栈弹出的第二个数字
        def oper = 0 //从栈弹出的运算符
        def result = 0 //保存当前运算结果
        def ch = '' //每次扫描得到的字符暂存到ch中
        def keepNum = "" //用于拼接多位数的

        while (true) {
            ch = expression.substring(index, index+1).charAt(0) //取第一个

            //判断ch是数字还是符号
            if (numStack.isOper(ch)) { //如果是符号
                if (opeStack.isEmpty()) {
                    opeStack.push(ch) //如果符号栈为空，则直接入栈
                } else { //如果符号栈不为空，也就是有操作符，就要进行符号优先级的比较
                    if (opeStack.priorrity(ch) <= opeStack.priorrity(opeStack.peek2())) {
                        //从数栈pop两个数进行运算
                        num1 = numStack.pop()
                        num2 = numStack.pop()
                        oper = opeStack.pop()
                        result = numStack.cal(num1, num2, oper)
                        numStack.push(result)
                        opeStack.push(ch)
                    } else { //当前符号的优先级大于操作符栈中的优先级，直接入栈
                        opeStack.push(ch)
                    }
                }
            } else { //如果是数，则直接入数栈
                //要考虑多位数的情况
                //需要向expression的表达式的index再多看一位，如果是数字，需要再多看,直到是一个符号为止结束
                keepNum += ch
                //判断下一个字符是不是数字
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum))
                    keepNum = ""
                } else {
                    if (opeStack.isOper(expression.charAt(index + 1))) { //如果数字的下一位是符号了，那么就直接将当前的数字入栈
                        numStack.push(Integer.parseInt(keepNum))
                        keepNum = "" //再讲keepNum清空
                    }
                }
            }
            //指针+1，并判断是否扫描到最后
            index++
            if (index >= expression.length()) {
                break
            }
        }

        //当表达式扫描完毕后，就顺序的从数栈和符号栈中，POP出相应的数和符号，并做最后的运算
        while (true) {
            if (opeStack.isEmpty()) { //如果符号栈为空了，表明所有可用的符号已经被用光了，说明已经全部计算完成
                break
            } else {
                num1 = numStack.pop()
                num2 = numStack.pop()
                oper = opeStack.pop()
                result = numStack.cal(num1, num2, oper)
                numStack.push(result)
            }
        }
        def result2 = numStack.pop()
        printf("表达式：%s = %d", expression, result2)
    }
}


class ArrayStack2 {
    private def maxSize //栈的最大容量
    private int[] stack //栈的数据存入到该数组中
    private def top = -1 //初始化栈顶为-1

    ArrayStack2(def maxSize) {
        this.maxSize = maxSize
        stack = new int[maxSize]
    }

    //判断栈是否已满
    boolean isFull() {
        return top == maxSize - 1 //
    }

    //判断栈空
    boolean isEmpty() {
        return top == -1
    }

    //入栈操作
    void push(def value) {
        if (isFull()) {
            println("栈已满,无法再添加数据")
            return
        }
        stack[++top] = value
    }

    //出栈操作
    int pop() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("栈已空,无法再取出数据")
        }
        return stack[top--]
    }

    //查看栈顶第一个元素，但该元素没有真正出栈
    int peek() {
        return stack[top]
    }

    char peek2() {
        return stack[top]
    }

    //遍历栈,遍历时，需要从栈顶开始显示数据
    void list() {
        if (isEmpty()) {
            println("栈空,无法显示数据")
        }
        for (def i = top; i >= 0; i--) {
            printf("stack[%d]=%d\n", i, stack[i])
        }
    }

    //返回运算符优先级,优先级是程序员确定的,优先级使用数字表示，数字越大则优先级越高
    int priorrity(char oper) {
        if (oper == '*' as char || oper == '/' as char) {
            return 1
        } else if (oper == '+' as char || oper == '-' as char) {
            return 0
        } else {
            return -1 //假定目前计算器只能有加减乘除
        }
    }

    //判断是不是运算符
    boolean isOper(char value) {
        return value == '+'.toCharacter() || value == '-'.toCharacter() || value == '*'.toCharacter() || value == '/'.toCharacter()
    }

    //计算方法
    int cal(int num1, int num2, int oper) {
        def result = 0
        char ch = (char)oper
        switch (ch) {
            case '+':
                result = num1 + num2
                break
            case '-':
                result = num2 - num1 //减法要注意顺序哦,后弹出的要减去先弹出的
                break
            case '*':
                result = num1 * num2
                break
            case "/":
                result = num2 / num1 //除法也要注意顺序，后弹出的要减去先弹出的
                break
        }
        return result
    }
}