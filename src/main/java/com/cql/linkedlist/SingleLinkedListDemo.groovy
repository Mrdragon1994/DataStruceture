package com.cql.linkedlist


public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(new HeroNode(1, "松江", "及时雨"))
//        singleLinkedList.add(new HeroNode(2, "卢俊义", "玉麒麟"))
//        singleLinkedList.add(new HeroNode(3, "吴用", "智多星"))
//        singleLinkedList.add(new HeroNode(4, "林冲", "豹子头"))
//        singleLinkedList.show()

        singleLinkedList.addByOrder(new HeroNode(1, "松江", "及时雨"))

        singleLinkedList.addByOrder(new HeroNode(4, "林冲", "豹子头"))
        singleLinkedList.addByOrder(new HeroNode(2, "卢俊义", "玉麒麟"))
        singleLinkedList.addByOrder(new HeroNode(3, "吴用", "智多星"))
        singleLinkedList.show()
        singleLinkedList.update(new HeroNode(2, "小卢", "豹子头"))
        singleLinkedList.show()
    }
}
//定义一个SingleLinkedList。来管理我们的英雄
class SingleLinkedList {
    //先初始化一个头结点,头结点不要动，且不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "")

    //当不考虑编号的顺序时,想办法找到链表的最后一个节点，让最后一个节点的next指向传入的新的Node
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

    //有序插入
    void addByOrder(HeroNode heroNode) {
        //因为头结点不能动，因此我们通过一个辅助指针来帮助我们找到添加的位置
        //因为是单链表（只能向后，不能向前，因为只有next域，没有front域），因此我们找的temp是位于添加位置的前一个节点
        HeroNode temp = head
        boolean flag = false //标识添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) { //说明temp已经在链表的最后
                break //直接插入到原链表的最后即可
            }
            if (temp.next.no > heroNode.no) { //表明位置就找到了，就是temp的后面
                break //表明在temp后添加
            } else if (temp.next.no == heroNode.no) {

                flag = true //说明编号存在
                break
            }
            temp = temp.next //后移，继续向后查找
        }
        //判断flag的值
        if (flag) {
            println("当前编号 ${heroNode.no} 已经存在")
        } else {
            heroNode.next = temp.next
            temp.next = heroNode
        }
    }
   //显示链表【遍历】
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

    //单链表的修改--修改节点的信息,根据no编号来修改，即no是不能被修改的
    void update(HeroNode heroNode) { //根据新节点（也就是参数）的no来修改
        if (head.next == null) {
            //说明链表没有数据
            println("链表为空")
            return
        }
        HeroNode temp = head.next //定义一个辅助节点
        boolean flag = false //表示是否找到该节点
        while (true) {
            if (temp == null) {
                break //说明链表遍历完毕了
            }
            if (temp.no == heroNode.no) { //表明找到了节点
                flag = true
                break
            }
            temp = temp.next
        }
        if (flag) { //表明找到了
            temp.name = heroNode.name
            temp.nickName = heroNode.nickName
        } else {
            println("没有该节点信息，不再执行修改操作")
        }
    }

    //删除节点
    void delete(HeroNode heroNode) {
        HeroNode temp = head
        boolean flag = false
        while (true) {
            if (temp.next == null) {
                break
            }
            if (temp.next.no == heroNode.no) {
                flag = true
                break
            }
            temp = temp.next
        }
        if (flag) {
            temp.next = temp.next.next
        } else {
            println("没有找到需要删除的节点")

        }
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