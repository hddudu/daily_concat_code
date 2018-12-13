package com.hongdu.src.lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * lintcode 码================>刷题网
 */
public class LintCodeBase {
    public static void main(String args[]) {
        Map<String,String> s = new HashMap<>();
        s.put("1","1");

//        System.out.println(args);
        System.out.println(args.length);
        System.out.println(args);
        System.out.println("hi");
    }

    /**
     * 二叉树的序列化:
     */
    //基础数据结构 : 无操作api
    private static class TreeNode<E> {
        E e;
        TreeNode<E> lchild;
        TreeNode<E> rchild;
        public TreeNode(E e, TreeNode<E> lchild, TreeNode<E> rchild) {
            this.e = e;
            this.lchild = lchild;
            this.rchild = rchild;
        }
    }
    /**
     * 为最近最少使用（LRU）缓存策略设计一个数据结构，它应该支持以下操作：获取数据（get）和写入数据（set）。
     * 获取数据get(key)：如果缓存中存在key，则获取其数据值（通常是正数），否则返回-1。
     * 写入数据set(key, value)：如果key还没有在缓存中，则写入其数据值。当缓存达到上限，它应该在写入新数据之前删除最近最少使用的数据用来腾出空闲位置。
     * 分析结果： ==》 最近最少 ===》 链表 ==》 双链表
     *              get
     *              set
     *              KV结构 ===》 字典表 ====》 符号表 ===》 key value
     *        ①哈希表中存放key值与指向双链表节点的指针，故可以通过key值快速查找对应的链表节点
     *        ②双链表中缓存数据，每命中一个数据或新增一个数据，就将其移动或添加到链表头部，
     *              这样可以保证链表尾部的节点就是最近最少用的数据，当缓存已满时若添加新数据即可将尾部节点从链表中删除。
     *        ③使用双链表的目的是：对于链表中的任意一个节点，我们都能够方便地找到它的前驱。
     *
     */


    /**
     * 给一个来自已经排过序的循环链表的节点，写一个函数来将一个值插入到循环链表中，并且保持还是有序循环链表。
     * 给出的节点可以是链表中的任意一个单节点。返回插入后的新链表。
     * 3->5->1 是一个循环链表，所以 3 是 1 的下一个节点。3->5->1 与 5->1->3 相同
     *   1->5->3->1 循环
     */

}
