package com.hongdu.src.datastructurejava.tree.base.api.nodep;

import com.hongdu.src.datastructurejava.tree.base.api.nodep.binarynode.BinaryNodeInterface;

/**
 * 二叉树的节点的实现类
 * 支持序列化
 * @param <T>
 */
public class BinaryNode<T> implements BinaryNodeInterface<T>,java.io.Serializable {

    private T data;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    /**
     * 实现思路 ： 首先还是构造器
     *              默认空 ==》装饰模式
     * @return
     */
    public BinaryNode() {
        this(null);
    }
    public BinaryNode(T data) {
        this(data,null,null);
    }
    public BinaryNode(T data,BinaryNode<T> leftChild,BinaryNode<T> rightChild) {
        this.data = data;
        this.left = leftChild;
        this.right = rightChild;
    }



    @Override
    public T getData() {
        return this.data;
    }

    @Override
    public void setData(T newData) {
        this.data = newData;
    }

    @Override
    public BinaryNodeInterface<T> getLeftChild() {
        return this.left;
    }

    @Override
    public BinaryNodeInterface<T> getRightChild() {
        return this.right;
    }

    @Override
    public void setLeftChild(BinaryNodeInterface<T> leftChild) {
        this.left = (BinaryNode)leftChild;
    }

    @Override
    public void setRightChild(BinaryNodeInterface<T> rightChild) {
        this.right = (BinaryNode)rightChild;
    }

    @Override
    public boolean hasLeftChild() {
        return this.left != null;
    }

    @Override
    public boolean hasRightChild() {
        return this.right != null;
    }

    @Override
    public boolean isLeaf() {
        return (this.left == null) && (this.right == null);
    }

    //返回以该结点为根的子树中的结点的个数(包括根结点)
    @Override
    public int getNumberOfNodes() {
        int leftNumbers = 0;
        int rightNumbers = 0;
        if(left != null) {
            leftNumbers = left.getNumberOfNodes();
        }
        if(right != null) {
            rightNumbers = right.getNumberOfNodes();
        }
        return 1 + leftNumbers + rightNumbers;//一棵树的结点数 ： 本身 + 左子树结点数 + 右子树结点数
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public BinaryNodeInterface<T> copy() {
        return null;
    }
}
