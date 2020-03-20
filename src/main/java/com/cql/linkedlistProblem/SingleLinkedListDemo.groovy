package com.cql.linkedlistProblem

/**
 * 单向链表
 */
public class SingleLinkedListProblem {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(new HeroNode(1, "松江", "及时雨"))
        singleLinkedList.add(new HeroNode(2, "卢俊义", "玉麒麟"))
        singleLinkedList.add(new HeroNode(3, "吴用", "智多星"))
        singleLinkedList.add(new HeroNode(4, "林冲", "豹子头"))
        singleLinkedList.reverseList(singleLinkedList.getHead())
        singleLinkedList.show()
//        singleLinkedList.show()

    }
}
//定义一个SingleLinkedList。来管理我们的英雄
class SingleLinkedList {
    //先初始化一个头结点,头结点不要动，且不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "")

    HeroNode getHead() {
        return head
    }

    void add (HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历的temp，让其等于head
        HeroNode temp = head;
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break
            }
            temp = temp.next //如果没有找到，就将temp后移
        }
        //当退出while循环时，temp就指向了链表的最后
        temp.next = heroNode
    }

/**
     *
     * @param heroNode 链表的头结点
     * @return 返回的是链表的有效节点数
     */
    //获取单链表的节点个数（如果是带头结点的，需要把头结点去掉）
    static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0
        }
        int length = 0;
        //定义一个辅助指针,这里直接抛弃了头结点
        HeroNode temp = head.next
        while (temp != null) {
            length++
            temp = temp.next
        }
        return length
    }

    //查找单链表倒数第K个节点
    //1、接收的参数为head节点， 接收一个index，index表示的是倒数第index个
    //2、先把链表从头到尾遍历，获取链表的总长度，就是getLength(HerdNode head)方法
    //3、从链表的开始，进行遍历，遍历到（size - index）个
    //这里开始的节点是第一个节点，不是头结点
    HeroNode getLastIndex(HeroNode head, int index) {
        if (head.next == null) {
            return null //表明链表为空
        }
        //链表总的节点数
        int size = getLength(head)
        //先做index的校验
        if (index <= 0 || index > size) {
            return null
        }
        HeroNode temp = head.next
        for (int i = 0; i < size - index; i++) {
            temp = temp.next
        }
        return temp
    }

    void show() {
        //先判断链表是否为空,
        if (head.next == null) {
            System.out.println("链表为空")
            return
        }
        //因为头结点不能动，因此我们需要一个辅助变量temp来遍历链表
        //因为已经判断过了，该链表是不为空的，因此我们直接就可以将head.next赋予给temp
        HeroNode temp = head.next
        while (true) {
            if (temp == null) {
                break
            }
            //输出节点信息
            println(temp.toString())
            //将节点后移
            temp = temp.next
        }
    }


    //单链表反转
    void reverseList(HeroNode head) {
        //如果当前链表为空或者只有一个节点就无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return
        }
        //定义一个辅助指针,帮助我们遍历原来的链表
        HeroNode current = head.next
        HeroNode next = null //指向当前节点（current）的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "")
        //遍历原来的链表
        while (current != null) {
            next = current.next //先暂时保存当前节点的下一个节点，以为后面需要使用
            current.next = reverseHead.next //将新链表头部的下一个节点信息复制给current的next属性，在这里current.next是当前节点的next属性，并不是代表current的下一个节点
            reverseHead.next = current
            current = next
        }
        head.next = reverseHead.next
    }

}


//定义HeroNode,每个HeroNode对象都是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next; //指向下一个节点

    HeroNode(int no, String name, String nickName) {
        this.no = no
        this.name = name
        this.nickName = nickName
    }
    @Override
    String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}