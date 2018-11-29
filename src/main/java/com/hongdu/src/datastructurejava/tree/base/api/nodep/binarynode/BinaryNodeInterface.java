package com.hongdu.src.datastructurejava.tree.base.api.nodep.binarynode;

/**
 * 二叉树节点接口 ====================》一切都 抽象为了接口
 * 与单链表一样，链表中有链表结点，二叉树中也需要定义表示结点的类。而这里定义的表示结点的类采用独立的外部类实现，而不是采用内部类来实现。
 * 在考虑表示结点的类时，我们先定义了一个抽象的接口来规定对结点的一些基本操作。（这也是为什么我觉得书中二叉树实现比较复杂的原因）
 * @param <E>
 */
public interface BinaryNodeInterface<E> {
    E getData();//返回节点数据部分
    void setData(E newData);//设置节点的数据域的值

    BinaryNodeInterface<E> getLeftChild();//获取结点的左孩子
    BinaryNodeInterface<E> getRightChild();//获取结点的右孩子

    void setLeftChild(BinaryNodeInterface<E> leftChild);//
    void setRightChild(BinaryNodeInterface<E> rightChild);//

    boolean hasLeftChild();//是否有左孩子
    boolean hasRightChild();//是否有右孩子

    boolean isLeaf();//检查是否是叶子结点

    int getNumberOfNodes();//计算以该结点为根的子树的结点数目

    int getHeight();//计算以该结点为根的子树的高度

    BinaryNodeInterface<E> copy();//复制以该结点为根的子树
}
