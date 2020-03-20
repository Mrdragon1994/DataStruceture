package com.cql.queue;


import java.util.Scanner;

/**
 * 用数组模拟队列的实现功能
 * 缺陷：数组不能重复使用，因为当rear=maxSize时，会被判定队列已满不能在添加新数据进去
 * @Author CQL
 * @Date 2020-01-06
 */
class QueueImplementedByArray {

    public static void main(String[] args) {
        QueueImplementedByArray queueImplementedByArray = new QueueImplementedByArray(5);
        char key;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show) : 显示队列");
            System.out.println("e(exit) : 退车程序");
            System.out.println("a(add) : 添加数据到队列");
            System.out.println("g(get) : 从队列中取出数据");
            System.out.println("h(head) : 显示队列的头数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    try {
                        queueImplementedByArray.listQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    queueImplementedByArray.addQueue(value);
                    break;
                case 'g':
                    try {
                        int result =  queueImplementedByArray.getQueue();
                        System.out.println("取出的数据是：" + result);
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int result = queueImplementedByArray.pickQueue();
                        System.out.println("头数据是：" + result);
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    System.out.println("程序已退出");
                    break;
                default:
                    scanner.close();
                    loop = false;
                    System.out.println("程序已退出");
                    break;
            }
        }
    }

    private int maxSize; //表示数组的最大容量
    private int front; //表示队列的头指针
    private int rear; //表示队列的尾指针
    private int[] arr; //该数组用于存放数据，模拟队列

    /**
     * 初始化数组
     * @param arrMaxSize 初始化队列的大小
     */
    public QueueImplementedByArray(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; //表明指向队列头部,front是指向队列第一数据的前一个位置
        rear = -1; //直接指向尾部的具体数据,即包含尾部的数据，即rear对应的就是队列尾的数据的下标
        //PS:front指向的是队列第一个数据的前一个位置，而rear指向队列最后一个数据的位置
    }

    /**
     * 判断队列是否已经满了
     * @return true 满了， false 没有满
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     * @return true 空， false 非空
     */
    public boolean isEmpty() {
        return  front == rear;
    }

    /**
     * 向队列中添加数据
     * @param n 需要添加到队列中的数据
     */
    public void addQueue(int n) {
        //先判断队列是否已经满了
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        } else {
            arr[++rear] = n; //先将rear后移，在加入数据，因为rear指向的是最后一个数据的位置
        }
    }

    /**
     * 获取队列的数据
     * @return 返回的数据
     */
    public int getQueue() {
        if (isEmpty()) {
            System.out.println("队列空，无法取出元素");
            //通过抛出异常处理
            throw new RuntimeException("队列空，无法取出元素");
        } else {
            return arr[++front];
        }
    }

    /**
     * 遍历队列所有数据
     */
    public void listQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        } else {
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("arr[%d]=%d\n", i, arr[i]);
            }
        }
    }

    /**
     * 显示队列的头数据,和取出头数据是不一样的，因为front没有动，仍旧保持原大小，而取出数据后，front是要向后推移的
     */
    public int pickQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，无法取出元素");
        } else {
            return arr[front+1];
        }
    }
}
