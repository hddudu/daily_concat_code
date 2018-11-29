package com.hongdu.src.datastructurejava.tree.base.api.binarytree;

import com.hongdu.src.datastructurejava.tree.base.api.baseapi.TreeInterface;
import com.hongdu.src.datastructurejava.tree.base.api.baseapi.TreeIteratorInterface;

/**
 * 二叉树接口 多继承 ： 树基本操作接口 + 迭代器接口 （基本操作接口 ===》抽出接口共性====》定义公用规则）
 *                      然后添加 个性化接口 ==============》 添加个性规则
 * @param <E>
 */
public interface BinaryTreeInterface<E> extends TreeInterface<E>, TreeIteratorInterface<E> {
    /**
     * 构造二叉树 ===========》 要玩起来 要先创造它
     * @param rootData 以rootData为根
     */
    void createTree(E rootData);

    /**
     * ==============================================>常以为这个用来做为初始化构造方法 =============》其实现在已经抽取到接口规则 规范中来了
     * 构造二叉树 ===========》 要玩起来 要先创造它
     * @param rootData 以rootData为根
     * @param leftTree leftTree为i左子树
     * @param rightTree right为右子树
     */
    void createTree(E rootData, BinaryTreeInterface<E> leftTree, BinaryTreeInterface<E> rightTree);
}
