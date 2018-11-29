package com.hongdu.src.datastructurejava.fish;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * unsafe 基础====================================》 jdk的底层基础
 * 链表的前面插入和后面插入=======================》 链表操作
 *
 * 链表 阻塞队列
 * 不限长度 ===============》链表特点 存取之间可以完成并发 ==》但是存和存之间没法并发 ===》 取和取之间也不能并发
 * @param <E>
 */
public class FishLinkedBlockingQueue<E> {

    static class Node<E> {
        E items;
        Node<E> next;
        Node(E e) {
            items = e;
        }
    }

    private final int capacity;//初始化容量

    private final AtomicInteger count = new AtomicInteger();//底层都是 unsafe实现的 ==> 这个是线程安全的

    /**
     * 双向链表的经典标识
     */
    Node<E> head;

    Node<E> last;

    //放锁: 存存锁 控制不能并发进行
    private final ReentrantLock takeLock = new ReentrantLock();

    //放入锁的信号量（其实就是一种资源）
    private final Condition notEmpty = takeLock.newCondition();

    //取锁 ： 取取锁 控制不能并发取
    private final ReentrantLock putLock = new ReentrantLock();

    private final Condition notFull = putLock.newCondition();

    public FishLinkedBlockingQueue() {
        this(Integer.MAX_VALUE);
    }
    public FishLinkedBlockingQueue(int capacity) {
        if(capacity < 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        last = head = new Node<>(null);// 初始化链表 ： 头尾一样 内容为null
    }

    //offer 插入元素 如果满了 就返回false
    public boolean offer(E e) {
        if(e == null) {
            throw new NullPointerException();
        }
        final AtomicInteger count = this.count;
        if(count.get() == capacity) {
            //满了 不干了
            return false;
        }
        int c = -1;
        Node<E> node = new Node<>(e);
        final ReentrantLock putLock =  this.putLock;
        putLock.lock();
        try{
            //为何还要重新获取一次
            if(count.get() < capacity) {
                enqueue(node);
                c = count.getAndIncrement();//获得并且加1
                //为何要发信号从notFull上解锁======================================>
                if(c + 1 < capacity) {
                    //就是放入了元素在队列上面后 还不是 满的 唤醒还在放元素的线程 ：
                    //当队列满了，两个取线程挂在空信号量的这个资源区上，然后存了一个元素 就唤醒一个取线程进行元素的取拿
                    notFull.signal();
                }
            }
        } finally {
            putLock.unlock();
        }
        //因为元素个数大于1了 所有队列非空
        if(c == 0) {
            notEmpty.signal();
        }
        return c >= 0;
    }

    /**
     * 在尾部添加元素
     * @param node
     */
    public void enqueue(Node<E> node) {
        Node<E> lastNode = last;
        lastNode.next = node;
        last = node;
    }

    /**
     * 忽略
     */
    private void signalNotEmpty() {

    }

    /**
     * 出队列元素
     * @return
     */
    public E dequeue() {
        Node<E> h = head;
        Node<E> first = h.next;
        h.next = h;// 第一个的下一个指向第一个 指针断裂
        E x = first.items;
        first.items = null;
        return x;
    }

    public E take() {
        E x = null;
        int c = -1;
        final AtomicInteger count = this.count;
        final ReentrantLock takeLock = this.takeLock;
        if(count.get() == 0) {
            return null;
        }
        takeLock.lock();
        try {
            if (count.get() > 0) {
                x = (E)dequeue();
            }
        } finally {
            takeLock.unlock();
        }
        //取完之后 如果长度 === 它的队列长度
        if(c == capacity) {
            notFull.signal();
        }
        return x;
    }

//    private void enqueue(E e) {
//
//    }
}
