# 前提
使用**数组的结构**来存储队列的数据，则队列数组的声明如下，其中maxSize是队列的最大容量。
class Queue {
    def arr = new int[]
    int maxSize
    int front
    int rear
}

front和rear分别标记队列的前后端，front会随着数据的输出而改变，rear会随着数据的输入而改变

# 常用操作
addQueue() {
    1)、 添加数据时,rear + 1，但是需要判断front == rear？ ，因为此时队列为空
    2)、 若尾指针rear小于队列的最大下表maxSize，才可以存入数据
}

# 总结
1}、队列是一个有序列表，可以用数组或链表来实现;
2）、队列符合先进先出（FIFO）的逻辑；
3)、数组模拟队列的实现需要4个成员变量，数组arr, 最大容量maxSize,队列头的前一个位置front,队列尾的位置rear
4)、队列为空的条件是front == rear, 队列满的条件是rear = maxSize - 1


# 存在的问题
目前数组使用一次就不能再次使用了，因为rear 达到 maxSize - 1时，会被判定为数组满，无法在继续
添加数据

# 优化方式
使用算法对数组改进为环形数组（用取模的方式完成）


