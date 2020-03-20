package com.cql.linkedlist

/**
 * 约瑟夫问题,使用单向循环列表实现
 * @Author CQL
 *
 */
class JosephuProblem {

    //实现思路：创建三个变量first, current, newNode
    //=====插入的思路
    //1.先创建第一个节点，让first指向该节点，自我形成环形;
    //2.后面当我们每创建一个新的节点，就把该节点加入到已有的环形链表中;

    //=====遍历环形链表的思路
    //1.先让一个辅助指针(变量)，指向first节点;
    //2.然后通过一个while循环遍历该环形链表,当辅助指针.next == first时,就表明遍历结束

    static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList()
        circleSingleLinkedList.addBoy(5)
        circleSingleLinkedList.getList()
    }
}

/**
 * 单向循环链表
 */
class CircleSingleLinkedList {
    //创建一个first节点,现在first不再作为头指针了，而是直接就是第一个节点
    private Boy first = null

    //添加一个节点，构建成一个环形链表
    /**
     * 添加节点
     * @param nums 添加节点的数量
     */
    void addBoy(int nums) {
        if (nums <= 0) {
            println("参数错误:${nums}")
            return
        }
        Boy currentBoy = null //辅助指针
        //使用for循环来创建环形链表
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i)
            //考虑第一个节点的特殊情况
            if (i == 1) {
                first = boy
                first.setNext(first)  //自我构成环形链表
                currentBoy = first //让current指向第一个节点,后面变化的就是current，因为first不能动
            } else {
                currentBoy.setNext(boy) //先让上一个节点的next域指向新的节点
                boy.setNext(first)  //让新节点的next域指向头节点
                currentBoy = boy
            }
        }
    }

    /**
     * 遍历当前循环链表
     */
    void getList() {
        if (first == null) {
            println("循环链表无任何节点")
            return
        }
        Boy currentBoy = first
        while (true) {
            println(currentBoy)
            if (currentBoy.getNext() == first) {
                break //表明当前的current已经是最后一个节点了,不需要再向后遍历了
            }
            currentBoy = currentBoy.getNext()
        }
    }

    //出圈问题
}


/**
 * 节点信息
 */
class Boy {
    private int no //编号
    private Boy next //指向下一个节点的指针域,默认为空

    Boy(int no) {
        this.no = no
    }

    int getNo() {
        return no
    }

    void setNo(int no) {
        this.no = no
    }

    Boy getNext() {
        return next
    }

    void setNext(Boy next) {
        this.next = next
    }


    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}