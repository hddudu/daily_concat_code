package com.hongdu.src.datastructurejava.tree.base;

/**
 * 单链表的基本结构:
 *      单链表的api：
 *           新增==》 链表的新增操作
 *           删除==》 直接斩断节点链
 *           修改==》 删除 修改 更新
 *           查询==》 查找
 *           ||
 *           ||
 *           ||==》衍生接口 ： 根据Object对象查找 ==》
 *           ||
 *           ||==》衍生接口： 根据index索引查找 ==》
 *           ||
 *           ||==》衍生接口： 求长度
 */
public class Node<E> {
    E e;
    Node<E> next;
    public Node(E e) {
        this.e = e;
    }
}
