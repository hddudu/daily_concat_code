package com.hongdu.src.lintcode;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * delegation方式实现更加优雅一些，但是由于没有实现Map接口，所以线程同步就需要自己搞定了
 */
public class LRUCache2<K,V> {
    private final int MAX_CACHE_SIZE;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    LinkedHashMap<K,V> linkedHashMap;

    public LRUCache2(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
        //根据cacheSize和加载因子计算hashmap的capactiy，+1确保当达到cacheSize上限时不会触发hashmap的扩容，
        int capacitry = (int)Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTOR ) + 1;
        /**
         * 匿名函数覆写 父类
         */
        linkedHashMap = new LinkedHashMap(capacitry,DEFAULT_LOAD_FACTOR,true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return  size() > MAX_CACHE_SIZE;
            }
        };
    }

    public synchronized void put(K key, V value) {
        linkedHashMap.put(key, value);
    }

    public synchronized V get(K key) {
        return linkedHashMap.get(key);
    }

    public synchronized void remove(K key) {
        linkedHashMap.remove(key);
    }

    public synchronized Set<Map.Entry<K, V>> getAll() {
        return linkedHashMap.entrySet();
    }

    public synchronized int size() {
        return linkedHashMap.size();
    }

    public synchronized void clear() {
        linkedHashMap.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }

}
