package com.hongdu.src.datastructurejava.tree.base.api.nodep.linknode;

import com.hongdu.src.datastructurejava.tree.base.api.nodep.singlenode.MySingleLinkNode;

/**
 * 我的双链表基本结构 : 头指针 和 双向不一样 ==》头指针默认代表了一个外部接口 访问内部内容的开放接口
 */
public class MyDoubleLinkNode {
    int value;
    MyDoubleLinkNode prev;
    MyDoubleLinkNode next;
    public MyDoubleLinkNode(int value) {
        this.value = value;
        this.prev = null;//默认为null可以不写
        this.next = null;//默认为null可以不写
    }

    /**
     * 翻转双向链表基本api : 双向翻转同单向翻转
     * @param head
     * @return
     */
    public static MyDoubleLinkNode reverseDoubleList(MyDoubleLinkNode head) {
        MyDoubleLinkNode prev = null;
        MyDoubleLinkNode next = null;
        while (head != null) {
            next = head.next;//记录下当前结点的下一个节点
            head.next = prev;// 当前结点指向上一个结点(上一个链表的头结点)
            head.prev = next;//当前结点的上一个结点指向当前的下一个结点
            prev = head;
            head = next;
        }
        return prev;
    }
    //双向链表的构造


}
