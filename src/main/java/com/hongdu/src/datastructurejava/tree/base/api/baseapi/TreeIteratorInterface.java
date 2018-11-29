package com.hongdu.src.datastructurejava.tree.base.api.baseapi;

import java.util.Iterator;

/**
 * 由于对树的许多操作都少不了遍历，因此需要构造一个能够对树中的元素进行遍历的迭代器。本例中采用内部类的方式来实现迭代器，
 * 下面的定义的接口 TreeIteratorInterface 包含了生成不同迭代器的方法。让实现树的具体的JAVA类 implements TreeIteratorInterface<T>，
 * 然后该JAVA类再定义一个内部类 implements Iterator<T>，即可构造一个能够对树中元素进行遍历的迭代器
 * @param <E>
 *     为什么要让实现树的具体的JAVA类 implements TreeIteratorInterface<T>？
 *
 * 因为，TreeIteratorInterface<T>接口中定义了返回各种各样迭代器的方法，实现树的具体的JAVA类 的对象就可以调用这些方法获得迭代器了。
 * 在后面的例子中，我们定义了一个具体实现树数据结构的类 BinaryTree<T>，该类 implements BinaryTreeInterface<T>，
 * 而 BinaryTreeInterface<T> 又extends TreeIteratorInterface<T>，因而BinaryTree<T>的对象可以调用 TreeIteratorInterface<T>接口中的方法来获得迭代器了。
 */
public interface TreeIteratorInterface<E> {
    Iterator<E> getPreorderIterator();//获取前序遍历迭代器
    Iterator<E> getPostorderIterator();//获取后续遍历迭代器
    Iterator<E> getInorderIterator();//获取中序遍历迭代器
    Iterator<E> getLevelorderIterator();//获取层次遍历迭代器
}
