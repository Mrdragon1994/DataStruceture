# 用数组实现环形队列的思路
1）、front变量指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素；
2）、rear变量指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定；
3）、当队列满时，(rear + 1) % maxSize = front；
4）、当队列空时，rear == front；
5）、front的初始值为0，rear的初始值也为0；
6）、队列中有效的数据个数是：（rear - front + maxSize） % maxSize