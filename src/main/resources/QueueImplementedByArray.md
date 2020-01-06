# 前提
使用数组的结构来存储队列的数据，则队列数组的声明如下，其中maxSize是队列的最大容量。
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




