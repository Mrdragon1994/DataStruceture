package com.cql.stack

/**
 * 用数组实现栈
 * @Author ChangQilong
 * @Date 2020/3/20 21:29
 */
class StackByArray {


     static void main(String[] args) {
        Thread.currentThread().setName("Thread-stack")
        def arrayStack = new ArrayStack(5)
        String key = ""
        def loop = true
        Scanner scanner = new Scanner(System.in)
        while (loop) {
            println("show: -> 显示栈")
            println("exit: -> 退出程序")
            println("push: -> 添加数据到栈")
            println("pop: -> 从栈取出数据")
            println("输入选择====")
            key = scanner.next()
            switch (key) {
                case "show":
                    arrayStack.list()
                    break
                case "push":
                    println("请输入一个数")
                    int value = scanner.nextInt()
                    arrayStack.push(value)
                    break
                case "pop":
                    try {
                        println(arrayStack.pop())
                    } catch(Exception e) {
                        println(e.getMessage())
                    }
                    break
                case "exit":
                    scanner.close()
                    loop = false
                    break
            }
        }
    }


}

class ArrayStack {
    private def maxSize //栈的最大容量
    private int[] stack //栈的数据存入到该数组中
    private def top = -1 //初始化栈顶为-1

    ArrayStack(def maxSize) {
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
    int pop() throws RuntimeException{
        if (isEmpty()) {
            throw new RuntimeException("栈已空,无法再取出数据")
        }
        return stack[top--]
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
}