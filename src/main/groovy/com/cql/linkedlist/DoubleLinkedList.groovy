package com.cql.linkedlist

/**
 * 双向链表
 */
class DoubleLinkedList {

    public static void main(String[] args) {
        println("======双向链表的数据测试=======")
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList()
        //新增
        doubleLinkedList.addByOrder(new HeroNode2(2, "卢俊义", "玉麒麟"))
        doubleLinkedList.addByOrder(new HeroNode2(1, "宋江", "及时雨"))
        doubleLinkedList.addByOrder(new HeroNode2(3, "吴用", "智多星"))
        doubleLinkedList.addByOrder(new HeroNode2(4, "林冲", "豹子头"))
        //修改
       // doubleLinkedList.update(new HeroNode2(4, "公孙胜", "入云龙"))
        doubleLinkedList.show()
        //删除
       // doubleLinkedList.delete(3)
       // doubleLinkedList.show()

    }

    private HeroNode2 head = new HeroNode2(0, "", "")

    HeroNode2 getHead() {
        return head
    }

    //遍历
    void show() {
        //先判断链表是否为空,
        if (head .next == null) {
            System.out.println("链表为空")
            return
        }
        //因为头结点不能动，因此我们需要一个辅助变量temp来遍历链表
        //因为已经判断过了，该链表是不为空的，因此我们直接就可以将head.next赋予给temp
        HeroNode2 temp = head.next
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

    //添加一个节点到双向链表的最后
   void add(HeroNode2 heroNode2) {
       HeroNode2 temp = head
       while (true) {
           if (temp.next == null) {
               break
           }
           temp = temp.next
       }
       //退出while循环时，temp就指向了最后
       //默认是添加到链表的最后
       temp.next = heroNode2
       heroNode2.pre = temp
   }

   //按编号顺序从小到大进行插入
   void addByOrder(HeroNode2 heroNode2) {
       HeroNode2 temp = head
       boolean flag = false
       while (true) {
           if (temp.next == null) { //找到了末尾
               break
           }
           if (temp.next.no > heroNode2.no) {
               break
           } else if (temp.next.no == heroNode2.no) {
               flag = true
               break
           }
           temp = temp.next
       }
       if (flag) {
           println("${heroNode2.no} 已经存在不能进行排序插入了")
       } else {
           if (temp.next == null) {
               temp.next = heroNode2
               heroNode2.pre = temp
           } else {
               //如果temp是待插入节点位置的前一个
               //先将待插入节点和temp的之前的后面节点关联起来
               //关联和时候，先走待插入与原节点的联系关系，再走原节点和待插入节点的关系
               heroNode2.next = temp.next
               temp.next.pre = heroNode2
               heroNode2.pre = temp
               temp.next = heroNode2
           }
       }
   }

    //修改一个节点的内容，可以看到双向链表的节点内容的修改和单向链表几乎一样
    //只是节点类型改为了HeroNode2
    void update(HeroNode2 heroNode) { //根据新节点（也就是参数）的no来修改
        if (head.next == null) {
            //说明链表没有数据
            println("链表为空")
            return
        }
        HeroNode2 temp = head.next //定义一个辅助节点
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

    //从双向链表中删除一个节点
    //对于双向链表，可以直接找到要删除的节点本身，不需要找到待删除节点的前一个几点
    //找到后，自我删除即可
    void delete(int no) {
        if (head.next == null) {
            println("链表为空,无法删除")
            return
        }
        HeroNode2 temp = head.next //直接先指向第一个节点
        boolean flag = false
        while (true) {
            if (temp == null) {
                break
            }
            if (temp.no == no) {
                flag = true
                break
            }
            temp = temp.next
        }
        if (flag) {
            temp.pre.next = temp.next
            //如果待删除的节点是最后一个节点，next.pre会报NPE
            if (temp.next != null) {
                temp.next.pre = temp.pre
            }
        } else {
            printf("没有找到匹配的节点信息: %d", no)
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next; //指向下一个节点
    public HeroNode2 pre //指向上一个节点

    HeroNode2(int no, String name, String nickName) {
        this.no = no
        this.name = name
        this.nickName = nickName
    }


    @Override
    String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
