package com.hongdu.src.lintcode;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 *  LinkedHashMap:是Map接口的哈希表和链接列表实现，具有可预知的迭代顺序。
 *  * 由哈希表保证键的唯一性
 *  * 由链表保证键盘的有序(存储和取出的顺序一致)
 */

/**
 * LRU是Least Recently Used 的缩写，翻译过来就是“最近最少使用”，LRU缓存就是使用这种原理实现，简单的说就是缓存一定量的数据，
 * 当超过设定的阈值时就把一些过期的数据删除掉，比如我们缓存10000条数据，当数据小于10000时可以随意添加，当超过10000时就需要把新的数据添加进来，
 * 同时要把过期数据删除，以确保我们最大缓存10000条，那怎么确定删除哪条过期数据呢，采用LRU算法实现的话就是将最老的数据删掉，废话不多说，
 * 下面来说下Java版的LRU缓存实现
 *
 * //LinkedHashMap的一个构造函数，当参数accessOrder为true时，即会按照访问顺序排序，最近访问的放在最前，最早访问的放在后面
 * public LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder) {
 *         super(initialCapacity, loadFactor);
 *         this.accessOrder = accessOrder;
 * }
 *
 * //LinkedHashMap自带的判断是否删除最老的元素方法，默认返回false，即不删除老数据
 * //我们要做的就是重写这个方法，当满足一定条件时删除老数据
 * protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
 *         return false;
 * }
 *
 * Java里面实现LRU缓存通常有两种选择，一种是使用LinkedHashMap，一种是自己设计数据结构，使用链表+HashMap
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    /**
     * 最大缓存
     */
    private final int MAX_CACHE_SIZE;

    /**
     * 初始化缓存大小
     * @param cache
     */
    public LRUCache(int cache) {
        super((int)Math.ceil(cache / 0.75) + 1, 0.75f,true);
        MAX_CACHE_SIZE = cache;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX_CACHE_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<K,V> entry : entrySet()) {
            sb.append(String.format("%s:%s ",entry.getKey(),entry.getValue()));
        }
        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println("start...");

        lruCache1();
        lruCache2();
        lruCache3();
        lruCache4();

        System.out.println("over...");
    }


    public static void main(String[] args) throws Exception {
        System.out.println("start...");

        lruCache1();
        lruCache2();
        lruCache3();
        lruCache4();

        System.out.println("over...");
    }

    static   void lruCache1() {
        System.out.println();
        System.out.println("===========================LRU 链表实现===========================");
        LRUCache3<Integer, String> lru = new LRUCache3(5);
        lru.put(1, "11");
        lru.put(2, "11");
        lru.put(3, "11");
        lru.put(4, "11");
        lru.put(5, "11");
        System.out.println(lru.toString());
        lru.put(6, "66");
        lru.get(2);
        lru.put(7, "77");
        System.out.println(lru.get(4));//get获取的是value
        System.out.println(lru.toString());
        System.out.println();
    }

    static   <T> void lruCache2() {
        System.out.println();
        System.out.println("===========================LRU LinkedHashMap(inheritance)实现===========================");
        LRUCache2<Integer, String> lru = new LRUCache2(5);
        lru.put(1, "11");
        lru.put(2, "11");
        lru.put(3, "11");
        lru.put(4, "11");
        lru.put(5, "11");
        System.out.println(lru.toString());
        lru.put(6, "66");
        System.out.println(lru.toString());
        lru.get(2);
        System.out.println(lru.toString());
        lru.put(7, "77");
        lru.get(4);
        System.out.println(lru.toString());
        System.out.println();
    }

    static  void lruCache3() {
        System.out.println();
        System.out.println("===========================LRU LinkedHashMap(delegation)实现===========================");
        LRUCache2<Integer, String> lru = new LRUCache2(5);
        lru.put(1, "11");
        lru.put(2, "11");
        lru.put(3, "11");
        lru.put(4, "11");
        lru.put(5, "11");
        System.out.println(lru.toString());
        lru.put(6, "66");
        lru.get(2);
        lru.put(7, "77");
        lru.get(4);
        System.out.println(lru.toString());
        System.out.println();
    }
    static  void lruCache4() {
        System.out.println();
        System.out.println("===========================FIFO LinkedHashMap默认实现===========================");
        final int cacheSize = 5;
        LinkedHashMap<Integer, String> lru = new LinkedHashMap<Integer, String>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > cacheSize;
            }
        };
        lru.put(1, "11");
        lru.put(2, "11");
        lru.put(3, "11");
        lru.put(4, "11");
        lru.put(5, "11");
        System.out.println(lru.toString());
        lru.put(6, "66");
        lru.get(2);
        lru.put(7, "77");
        lru.get(4);
        System.out.println(lru.toString());
        System.out.println();
    }
 }
