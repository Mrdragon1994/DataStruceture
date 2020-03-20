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
        circleSingleLinkedList.addBoy(100)
     //   circleSingleLinkedList.getList()
        println(circleSingleLinkedList.rollBack(6,3,100))
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
    //因为是环形的，所以需要创建一个辅助指针，指向待删除的节点的前一个节点;所以，我们在创建的时候，要先指向first的前一个,也就是尾部节点
    //最初,先将first和辅助指针移动到首次报数的位置（start - 1）次
    //当报数前，让first和辅助指针同时移动，移动（m-1）次
    //此时，就可以将first指向的节点踢出链表,让first向前移动一次（first = first.next），然后让辅助指针的next指向first
    /**
     *
     * @param start 从第几个值开始数
     * @param count 要数几下后剔除一个值
     * @param nums 初始化时节点的个数
     * @return
     */
    List<Boy> rollBack(int start, int count, int nums) {
        List<Boy> result = new ArrayList<>()
        //先对数据进行校验
        if (first == null || start < 1 || start > nums) {
            println("参数有误")
            return
        }
        //先创建一个辅助指针
        Boy temp = first
        //让赋值指针指向first的前一节点，也就是尾部节点
        while (true) {
            if (temp.getNext() == first) {
                break
            }
            temp = temp.getNext()
        }
        //最开始时，两者先移动到最初报数的位置
        for (int i = 0; i < start - 1; i++) {
            first = first.getNext()
            temp = temp.getNext()
        }
        //
        while (true) {
            if (temp == first) { //说明只有一个节点了
                result.add(first)
                break
            }
            //让first和temp移动（count - 1）次
            for (int j = 0; j < count - 1; j++) {
                first = first.getNext()
                temp = temp.getNext()
            }
            //此时first指向的节点，就是要被剔除的节点
            result.add(first)
            first = first.getNext()
            temp.setNext(first)
        }
        return result
    }
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