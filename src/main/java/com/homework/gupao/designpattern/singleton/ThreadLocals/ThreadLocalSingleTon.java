package com.homework.gupao.designpattern.singleton.ThreadLocals;


public class ThreadLocalSingleTon {

	private static final ThreadLocal<ThreadLocalSingleTon> threadLocalSingle = 
			new ThreadLocal<ThreadLocalSingleTon>() {
				@Override
				protected ThreadLocalSingleTon initialValue() {
					return new ThreadLocalSingleTon();
				}
	};
	
	private ThreadLocalSingleTon(){}
	//线程内部变量 副本 独占一份
	public static ThreadLocalSingleTon getInstance() {
		return threadLocalSingle.get();
	}
}
