package com.cql.stack

/**
 * 用单向链表实现栈的功能
 * @Author ChangQilong
 * @Date 2020/3/20 22:17
 */
class StackByLinkedList {
    static void main(String[] args) {
        LinkedListStack linkedListStack = new LinkedListStack(3)
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
                    linkedListStack.list()
                    break
                case "push":
                    println("请输入一个数")
                    int value = scanner.nextInt()
                    Node node = new Node(value)
                    linkedListStack.push(node)
                    break
                case "pop":
                    try {
                        println(linkedListStack.pop())
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

class LinkedListStack {
    Node head = new Node(0) //头节点
    int maxSize //初始栈的最大容量

    LinkedListStack(int maxSize) {
        this.maxSize = maxSize
    }

    //栈满
    boolean isFull() {
        int count = 0
        Node temp = head
        while (true) {
            if (temp.next == null) {
                break
            }
            count++
            temp = temp.next
        }
        return count == maxSize
    }

    //栈空
    boolean isEmpty() {
        Node temp = head
        return temp.next == null
    }
    
    //入栈
    void push(Node node) {
        if (isFull()) {
            println("当前栈已满,无法再加入新元素")
            return
        }
        node.next = head.next
        head.next = node
    }
    
    //出栈
    int pop() throws RuntimeException{
        if (isEmpty()) {
            throw new RuntimeException("当前栈已空.无法再取出元素")
        }
        Node result = head.next
        head.next = head.next.next
        return result.number
    }
    
    //遍历
    void list() {
        Node temp = head
        if (isEmpty()) {
            println("栈已空,无法取出任何元素")
        }
        while (true) {
            if (temp.next == null) {
                break
            }
            printf("当前节点数据为:%d\n", temp.next.number)
            temp = temp.next
        }
    }
}

/**
 * 节点信息
 */
class Node {
    int number
    Node next

    Node(int number) {
        this.number = number
    }

    @Override
    String toString() {
        return "Node{" +
                "number=" + number +
                '}'
    }
}