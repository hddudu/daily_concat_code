package com.hongdu.src.datastructurejava.tree;

import com.hongdu.src.datastructurejava.tree.base.api.binarytree.BinaryTreeInterface;

import java.io.Serializable;
import java.util.Iterator;

/**
 * 二叉树 ==》 实现二叉树接口 ==》 实现了树的基本操作接口 ==》
 *  基本规范==》特性规范 ==》 实现
 *      如果不想全部实现 ==》 通过抽象类继承 来进行适配实现
 * @param <T>
 */
public class BinaryTree<T> implements BinaryTreeInterface<T>,Serializable {


    @Override
    public void createTree(T rootData) {

    }

    @Override
    public void createTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {

    }

    @Override
    public T getRootData() {
        return null;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getNumberOfNodes() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<T> getPreorderIterator() {
        return null;
    }

    @Override
    public Iterator<T> getPostorderIterator() {
        return null;
    }

    @Override
    public Iterator<T> getInorderIterator() {
        return null;
    }

    @Override
    public Iterator<T> getLevelorderIterator() {
        return null;
    }
}
