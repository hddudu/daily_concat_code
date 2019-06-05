package com.homework.gupao.designpattern.singleton;

public class SingleTonForLanHan {
	
	//加final的对象可以实例化吗? 不可以实例化
//	private static final SingleTonForEHan singleTon = null;//
	private static SingleTonForLanHan singleTon = null;//
	
	/**
	 * 私有化构造器
	 */
	private SingleTonForLanHan() {
		
	}
	
	public static SingleTonForLanHan getInstance() {
		if(singleTon == null) {
			//线程一抢到cpu进入到这一行，然后cpu被线程2抢走，线程2也进入到这一行
			//那么线程一将 singleTon  实例化后，线程2也会再将 singleTon 实例化一次
			//那么结果是 ： 线程一的实例化被线程二覆盖
			//如果在覆盖前线程一的实例被引用了，那么后面的结果是实例对象莫名被修改了
//			singleTon = new SingleTonForEHan();//final修饰无法实例化
			singleTon = new SingleTonForLanHan();//所以延迟初始化需要去掉final
		}
		return singleTon;
	}
	//存在问题： 多线程下， 出现不是唯一实例的情况
}
