package com.homework.gupao.designpattern.singleton.test;

import com.homework.gupao.designpattern.singleton.ThreadLocals.ThreadLocalSingleTon;

public class ExecutorThread implements Runnable {

	@Override
	public void run() {
//		SingleTonForLanHan singleTon = SingleTonForLanHan.getInstance();
		//getInstance的时候，会进行调用实例化备份本地副本，成为唯一本线程内一个独占实例
		ThreadLocalSingleTon singleTon = ThreadLocalSingleTon.getInstance();
		System.out.println(Thread.currentThread().getName() + " : " + singleTon);
	}

}
