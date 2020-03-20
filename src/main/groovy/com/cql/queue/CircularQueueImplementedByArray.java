package com.cql.queue;

import java.util.Scanner;

/**
 * 改变数组可以复用，从而转变为环形队列
 * 用数组模拟环形队列的思路分析如下：
 * 1） front变量的含义做一个调整，front就指向队列的第一个元素，不再指向第一个元素的前一个位置，也就是arr[front]就是队列的第一个元素；
 * 2） rear的含义也做一个调整，rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定
 * 3） 队列满的条件是：(rear + 1) % maxSize = front, 因为rear是不断增长的(rear是不需要再次置为0的)，因此取模可以实现循环
 * 4) 队列空的条件是： rear == front
 * 5） front的初始值是0， rear的初始值是0
 * 6） 队列中有效的数据的个数是 (rear + maxSize - front) % maxSize
 */
public class CircularQueueImplementedByArray {

    public static void main(String[] args) {
        CircularQueueImplementedByArray queueImplementedByArray = new CircularQueueImplementedByArray(5); //这里设定的初始值5，但是给队列的最大有效数据个数是4
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
    private int front; //表示队列的头指针，默认初始化为0了
    private int rear; //表示队列的尾指针，默认初始化为0了
    private int[] arr; //该数组用于存放数据，模拟队列

    /**
     * 初始化数组
     * @param arrMaxSize 初始化队列的大小
     */
    public CircularQueueImplementedByArray(int arrMaxSize) {
        this.maxSize = arrMaxSize; //数组的最大容量
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否已经满了
     * @return true 满了， false 没有满
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
            arr[rear] = n; //先加入数据，后将rear后移，因为rear指向的是最后一个数据的位置
            rear = (rear + 1) % maxSize;
        }
    }

    /**
     * 获取队列的数据
     * @return 返回的数据
     */
    public int getQueue() {
        if (isEmpty()) {
            //通过抛出异常处理
            throw new RuntimeException("队列空，无法取出元素");
        } else {
            //1、先把front对应的指保存到一个临时变量里
            //2、把front后移
            //3、返回临时变量的值
            int result = arr[front];
            front = (front + 1) % maxSize;
            return result;
        }
    }

    /**
     * 遍历队列所有数据
     */
    public void listQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        } else {
            //从front开始遍历，遍历 ()
            for (int i = front; i < front + size(); i++) {
                //这里考虑的是可能rear < front， 那么 i 在 front + size()后就可能比maxSize大，因此要取模
                System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
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
            return arr[front];
        }
    }

    /**
     * 求出当前队列有效数据的个数
     * @return
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }
}
