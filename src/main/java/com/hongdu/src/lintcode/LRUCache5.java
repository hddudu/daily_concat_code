package com.hongdu.src.lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class LRUCache5 {
    class ListNode {
        public int key, val;//key 是hash的key ==》val是ListNode的data
        public ListNode next;

        public ListNode(int key, int val) {
            this.key = key;//通过key定义了==>链表
            this.val = val;
            this.next = null;//默认不连下一个结点
        }
    }

    private int capacity, size;
    private ListNode dummy, tail;
    private Map<Integer, ListNode> keyToPrev;

    /**
     * 初始化容器 空间大小
     * @param capacity
     */
    public LRUCache5(int capacity) {
        this.capacity = capacity;
        this.keyToPrev = new HashMap<>();//初始化的时候才进行实例化 ++》避免浪费空间(新建对象)
        /**
         * 初始化 双链表的头结点
         */
        this.dummy = new ListNode(0,0);
        this.tail = this.dummy;//头尾一样
    }

    private void moveToTail(int key) {
        ListNode prev = keyToPrev.get(key);//获取hash桶的位置 ==>也就是双链表的头结点位置
        ListNode curt = prev.next;//获取头结点的下一个位置
        /**
         * 判断是否是尾巴结点==>那么就不用移动了
         */
        if(tail == curt) {
            return;
        }
        prev.next = prev.next.next;//先后移动当前结点
        tail.next = curt;//尾巴结点的一下一个拼接上

        if(prev.next != null) {
            keyToPrev.put(prev.next.key, prev);
        }

        keyToPrev.put(curt.key, tail);
        tail = curt;
    }
    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        if (!keyToPrev.containsKey(key)) {
            return -1;
        }

        moveToTail(key);

        // the key has been moved to the end
        return tail.val;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        // get method will move the key to the end of the linked list
        if (get(key) != -1) {
            ListNode prev = keyToPrev.get(key);
            prev.next.val = value;
            return;
        }

        if (size < capacity) {
            size++;
            ListNode curt = new ListNode(key, value);
            tail.next = curt;
            keyToPrev.put(key, tail);

            tail = curt;
            return;
        }

        // replace the first node with new key, value
        ListNode first = dummy.next;
        keyToPrev.remove(first.key);

        first.key = key;
        first.val = value;
        keyToPrev.put(key, dummy);

        moveToTail(key);
    }
}