package com.hongdu.src.lintcode;

import java.util.HashMap;

/**
 *
 * @param <K>
 * @param <V>
 */
public class LRUCache3<K, V> {

    private final int MAX_CACHE_SIZE;

    private Entry<K,V> first;
    private Entry<K,V> last;

    /**
     * hashMap + 双链表 ==的结构
     */
    private HashMap<K, Entry<K,V>> hashMap;

    public LRUCache3(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
        hashMap = new HashMap<K,Entry<K,V>>();
    }

    /**-- api 接口 ： put + get  + moveToFirst + removeLast +  --**/

    public void put(K key, V value) {
        Entry entry = getEntry(key);
        /**
         * 先判断链表 是否在hash表中存在
         */
        if(entry == null) {
            /**
             * 判断是否链表满了 ==》 满了先移除最少访问的尾巴元素
             */
            if(hashMap.size() >= MAX_CACHE_SIZE) {//这个是说的hash表满了 : 是数组表
                hashMap.remove(last.key);//自带移除方法 : 这个移除的是 Entry ==>是链表元素
                removeLast();
            }
            entry = new Entry();
            entry.key = key;// 设置hash表中元素
        }
        entry.value = value;
        moveToFirst(entry);//将最近访问的元素移到链表头部
        hashMap.put(key,entry);//再存储hash表 数组表
    }

    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null) return null;
        moveToFirst(entry);
        return entry.value;
    }

    /**
     * 首先删除移动的 结点 ==》 然后在头部插入
     * @param entry
     */
    private void moveToFirst(Entry entry) {
        if(entry == null) return;
        if(entry.pre != null) {
            entry.pre.next = entry.next;
        }
        if(entry.next != null) {
            entry.next.pre = entry.pre;//
        }
        if(entry == last) {//如果是最后一个结点 ： 那么把最后一个结点的指针前移
            last = last.pre;
        }
        /**
         * 移完之后 ： 如果首指针 或者 最后一个指针为null
         */
        if(first == null || last == null) {
            first = last = entry;
            return;
        }
        /**
         * 移动到首部: 单链表的头插法
         */
        entry.next = first;
        first.pre =entry;
        first = entry;
        entry.pre = null;
    }

    /**
     * 移除最后一个结点
     */
    private void removeLast() {
        if(last != null) {
            last = last.pre;//最后一个结点的指针指向倒数第二个
            if(last == null) {
                first = null;
            } else {
                last.next = null;//删除最后一个结点的指针
            }
        }
    }

    /**
     * 返回hash表中链表指针（头指针）
     * @param key
     * @return
     */
    public Entry<K,V> getEntry(K key) {
        return hashMap.get(key);
    }

    /**
     * 内部类
     * @param <K>
     * @param <V>
     */
    class Entry<K,V> {
        public Entry<K,V> pre;
        public Entry<K,V> next;
        public K key;
        public V value;
    }
}
