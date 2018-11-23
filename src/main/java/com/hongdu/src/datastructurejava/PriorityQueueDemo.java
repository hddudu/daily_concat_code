package com.hongdu.src.datastructurejava;

import com.hongdu.src.datastructurejava.po.StudentPO;

import java.util.PriorityQueue;

/**
 * 优先队列:
 * API:
     * PriorityQueue()
     * PriorityQueue(Collection<? extends E> c)
     * PriorityQueue(int initialCapacity)
     * PriorityQueue(int initialCapacity, Comparator<? super E> comparator)
     * PriorityQueue(PriorityQueue<? extends E> c)
     * PriorityQueue(SortedSet<? extends E> c)
 * 常用方法函数:
     * add(E e)	添加元素
     * clear()	清空
     * contains(Object o)	检查是否包含当前参数元素
     * offer(E e)	添加元素
     * peek()	读取元素，（不删除）
     * poll()	取出元素，（删除）
     * remove(Object o)	删除指定元素
     * size()	返回长度
 */
public class PriorityQueueDemo {

    final PriorityQueue<StudentPO> queue = new PriorityQueue<>();

    public static void main(String[] args){
        StudentPO p1 = new StudentPO(95,"张三");
        StudentPO p2 = new StudentPO(89,"李四");
        StudentPO p3 = new StudentPO(89,"王五");
        StudentPO p4 = new StudentPO(67,"赵六");
        StudentPO p5 = new StudentPO(92,"红七");
        StudentPO p6 = new StudentPO(99,"天八");
    }
}
