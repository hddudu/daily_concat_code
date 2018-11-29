package com.hongdu.src.lintcode;

/**
 * lintcode 码================>刷题网
 */
public class LintCodeBase {
    public static void main(String args[]) {
//        System.out.println(args);
        System.out.println(args.length);
        System.out.println(args);
        System.out.println("hi");
    }

    /**
     * 二叉树的序列化:
     */
    //基础数据结构 : 无操作api
    private static class TreeNode<E> {
        E e;
        TreeNode<E> lchild;
        TreeNode<E> rchild;
        public TreeNode(E e, TreeNode<E> lchild, TreeNode<E> rchild) {
            this.e = e;
            this.lchild = lchild;
            this.rchild = rchild;
        }
    }
}
