package com.hongdu.src.datastructurejava.tree.base.api.nodep.linknode;

/**
 * 双链表 : 参考 https://www.cnblogs.com/ChenD/p/7814906.html
 * 双向循环链表 ：
 * @param <E>
 */
public class DoubleLink<T> {

    /**
     * 内部类 : 定义基本的结构
     */
    private class Node<T> {
        private T t;
        private Node<T> prev;
        private Node<T> next;
        public Node(T t, Node<T> prev, Node<T> next) {
            this.t = t;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * 成员属性 ; 长度
     *  size
     *  头结点
     *  尾结点
     */
    private int size;
    private Node<T> head;

    /**
     * 初始化一个双链表 ; 一个头指针 head 在栈区开的一个引用
     *                    new Node<>() ==> 在堆区的一个对象 null
     *                    头指针的值 =null
     *                    头指针的上一个指针 = null
     *                    头指针的下一个指针 = null
     *                    最终指针都指向了一个地方
     */
    /**
     * 头结点不存储值 并且头结点初始化时 就一个头结点。
     * 所以头结点的前后节点都是自己
     * 并且这个链表的长度为0；
     */
    public DoubleLink() {
        head = new Node<>(null,null,null);
        head.prev = head.next;
        head = head.next;
        size = 0;
    }

    /**
     * @return 返回链表的长度
     */
    public int size() {
        return this.size;
    }

    /**
     * @return 判断是否为空链表
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 检查索引是否超出范围
     * @param index
     */
    public void checkIndex(int index) {
        if(index < 0 || index >= size) {
            throw  new IndexOutOfBoundsException("索引超出范围!");
        }
        return;
    }

    /**
     * 通过索引获取链表当中的结点
     */
    public Node<T> getNode(int index) {
        checkIndex(index);
        /**
         * 当链表索引小于size一半长度时 从前往后找 否则 从后往前找
         */
        if(index < (size / 2)) {
            Node<T> cur = head.next;
            for(int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur;
        }
        /**
         * 当索引值位于链表的后半段时，则从链表的另端开始找是最快的
         */
        Node<T> cur = head.prev;
        int newIndex = size - (index + 1);
        for(int i = 0; i < newIndex; i++) {
            cur = cur.prev;
        }
        return cur;
    }

    /**
     * @param cur
     * @return 获取当前节点的值
     */
    public T getValue(Node<T> cur) {
        return cur.t;
    }

    /**
     * @return 获取第一个结点的值
     */
    public T getFirst() {
        return getValue(getNode(0));//获取第一节点 然后获取第一个结点的值
    }

    /**
     * @return 获取最后一个结点的值
     */
    public T getLast() {
        return getValue(getNode(size - 1));
    }

    /**-- 新增操作 --**/
    /**
     * 在某个位置插入某个值
     */
    public void insert(int index, T value) {
        /**
         * ①插入的链表是空的===》双链表维护的两头结点的指针指向 ==> 忽略这个情况了 ==》 是空就在头结点位置插入
         * ②
         */
        if(index == 0) {
            Node<T> cur = new Node<>(value, head, head.next);//前一个结点是 head  下一个结点是 head的下一个
            head.next.prev = cur;//将头结点的下一个结点的 前指针 指向新结点
            head.next = cur;
            size++;
            return;
        }
        /**
         * 先根据给出的插入位置 ==》 找到该链表原来在此位置的结点 A->B->C ==>1 ==》 B 维护B与cur的指针指向
         */
        Node<T> node = getNode(index);
        /**
         * 放置的位置的前一个节点就是原放置位置节点的前置节点 而后节点就是放置位置节点 node ==>放置位置结点
         * D指向 A + D 指向B
         */
        Node<T> cur = new Node<T>(value, node.prev,node);
        /**
         * 放置完新结点后 ： 将原结点指针指向链进行修改: 被替代位置结点 ： 前指针指向 ==》cur
         *                                                                  后指针指向 ==》
         *
         */
        node.prev.next = node;//B的A 指向 D
        node.prev = cur;//B指向D
        size++;
    }

    /**
     * 向表头插入数据
     */
    public void insertTo(T t) {
        insert(0,t);//调用在某个位置插入数据的api
    }
    /**
     * 将元素插入到链表的尾部
     */
    public void insertTail(T t) {
        Node<T> cur = new Node<T>(t, head.prev, head);//head.prev: 代表头指向尾 部结点 ==》 head 代表最后一个结点的下一个结点 ==》行程循环
        //遵循两个原则 一 新插入节点的前一个节点的后一个节点为新节点。新节点的后一个节点的前一个节点是新节点
        head.prev.next = cur;//头结点的尾部指向 新结点
        head.prev = cur;//头结点的前一个结点==》 尾部指向当前结点
        size++;
    }

    /**
     * 删除结点方法: 删除某个索引处的 某个位置处的结点
     */
    public void del(int index) {
        checkIndex(index);
        Node<T> cur = getNode(index);
        //记住此时的指针还未断裂 ： 赋值以后才相当于断开
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        cur = null;//断裂所有指针
        size--;
        return;
    }

    /**
     * 删除第一个结点
     */
    public void delFirst() {
        del(0);
    }

    /**
     * 删除最后一个结点
     */
    public void delLst(){
        del(size -1 );
    }
}
