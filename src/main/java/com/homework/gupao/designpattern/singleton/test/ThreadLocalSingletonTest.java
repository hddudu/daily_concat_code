package com.homework.gupao.designpattern.singleton.test;

import com.homework.gupao.designpattern.singleton.ThreadLocals.ThreadLocalSingleTon;

public class ThreadLocalSingletonTest {

	public static void main(String[] args) {
		//本线程内是唯一的实例
		    System.out.println(ThreadLocalSingleTon.getInstance());
		    //com.homework.gupao.designpattern.singleton.ThreadLocals.ThreadLocalSingleTon@15db9742
	        System.out.println(ThreadLocalSingleTon.getInstance());//一样
	        System.out.println(ThreadLocalSingleTon.getInstance());//一样
	        System.out.println(ThreadLocalSingleTon.getInstance());//一样
	        System.out.println(ThreadLocalSingleTon.getInstance());//一样
	        
	        Thread t1 = new Thread(new ExecutorThread());
	        Thread t2 = new Thread(new ExecutorThread());
	        t1.start();
	        t2.start();
	        System.out.println("End");

	}
}
