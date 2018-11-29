package com.hongdu.src.datastructurejava.fish;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FishArrayListBlockingQueue<E> {

    final Object[] items;

    //取元素的下标 ==> //这个存取元素的下标长度是有限的 不能无限增长
    int takeIndex;
    //存元素的下标 : ==> //这个存取元素的下标长度是有限的 不能无限增长
    int putIndex;
    //元素个数 :==>
    int count;

    final ReentrantLock lock;//使用全局锁 ==》 阻塞比较厉害的锁

    final Condition notEmpty;//信号量不是空的

    final Condition notFull;//信号量不是满的

    public FishArrayListBlockingQueue(int capacity) {
        this(capacity, false);
    }
    public FishArrayListBlockingQueue(int capacity, boolean fair) {
        if(capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.items = new Object[capacity];//初始化大小 []
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    /**
     * 检查是否为空
     * @param e
     */
    private void checkNotNull(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
    }

    //插入元素
    public boolean offer(E e) {
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        //先锁住
        lock.lock();
        try {
            if(count == items.length) {
                return false;
            } else {
                enqueue(e);
                return true;
            }
        } finally {
            lock.unlock();
        }
    }

    //插入元素 如果队列满了 那么原地等待 : 挂起线程 阻塞当前代码执行操作
    public void put(E e) throws Exception {
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        //可中断锁 可以响应程序的重要操作
        lock.lockInterruptibly();
        try {
            while(count == items.length) {// 只要是满的 一直阻塞 但是阻塞时间太长也不行 ==》 改成非阻塞锁
                notFull.await();
            }
            enqueue(e);
        } finally {
            lock.unlock();
        }
    }


    /**
     * 存储元素 进入队列
     * @param e
     */
    private void enqueue(E e) {
        final Object[] items = this.items;
        items[putIndex] = e;
        if(++putIndex == items.length) {
            putIndex++;
        }
        count++;
        //队列有元素了 （一旦有数据进来唤醒notEmpty上面的线程 （线程是挂在信号量（临界区（计算机有限资源上面的）））） 那么就唤醒空信号量的那个阻塞线程 ==>>这个有插入操作就进行唤醒操作
        notEmpty.signal();
    }

    /**
     * 获取元素
     * @return
     */
    public E poll() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try{
            if(count == 0) {
                return null;
            } else {
                return dequeue();
            }
        } finally {
            lock.unlock();
        }
    }

    //获取元素操作
    public E take() throws Exception{
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();//可中断锁
        try{
            while(count == 0) {
                notEmpty.await();
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取队列元素 ： 维护了一个获取元素的下标
     * @return
     */
    private E dequeue() {
        final Object[] items = this.items;
        E x = (E)items[takeIndex];
        items[takeIndex] = null;//取出元素后 把队列中的当前元素置空
        if( ++takeIndex == items.length) {
            takeIndex = 0;
        }
        count--;
        notFull.signal();//不是满的队列的时候
        return x;
    }

}
