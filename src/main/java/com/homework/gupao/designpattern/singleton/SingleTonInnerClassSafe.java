package com.homework.gupao.designpattern.singleton;

//这种形式兼顾饿汉式的内存浪费，也兼顾synchronized性能问题
public class SingleTonInnerClassSafe {
	
	private SingleTonInnerClassSafe() {
		//防止多个实例化： 
		if(SingleHolder.LAZY != null) {
			throw new RuntimeException("不允许创建多个实例!");
		}
	}
	
	//通过内部类实现安全单例模式： 并且是延迟初始化 懒汉式
	//默认不加载
	private static class SingleHolder {
		private static final SingleTonInnerClassSafe LAZY = new SingleTonInnerClassSafe();
	}
	//每一个关键字都不是多余的
    //static 是为了使单例的空间共享
    //保证这个方法不会被重写，重载
	public static final SingleTonInnerClassSafe getInstance() {
		//在返回结果以前，一定会先加载内部类
		return SingleHolder.LAZY;
	}

}
