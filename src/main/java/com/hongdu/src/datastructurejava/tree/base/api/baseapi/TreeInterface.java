package com.hongdu.src.datastructurejava.tree.base.api.baseapi;

/**
 * 树的常用操作
 * @param <E>
 */
public interface TreeInterface<E> {
    E getRootData();
    int getHeight();
    int getNumberOfNodes();
    boolean isEmpty();
    void clear();
}
