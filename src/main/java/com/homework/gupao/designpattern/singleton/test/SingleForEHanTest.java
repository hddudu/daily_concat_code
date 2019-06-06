package com.homework.gupao.designpattern.singleton.test;

public class SingleForEHanTest {

	/**
	 * 首先创建多线程 并发环境： 
	 * 那么就是多个线程 ： ①线程池方式
	 * 			  Ⅱ 手动实现线程 ： 1： 继承Thread 2： 实现Runnable 3： 实现Callable
	 * @param args
	 */
	public static void main(String[] args) {
		//类似简单的并发环境
		Thread t1 = new Thread(new ExecutorThread());
		Thread t2 = new Thread(new ExecutorThread());
		t1.start();
		t2.start();
		System.out.println("End");//会先执行主线程 然后是其他的 ： 出现了不同的单例对象 不安全
	}
}
