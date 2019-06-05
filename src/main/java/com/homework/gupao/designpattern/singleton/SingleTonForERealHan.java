package com.homework.gupao.designpattern.singleton;

/**
 * 单例模式 ：  ①私有构造器
 * 		 ②提供公共的访问实例方法
 * 		  🌂多线程访问 安全性问题 ： 可能出现不是唯一实例的情况
 * 		 ④序列化和反序列化出现不是唯一实例情况
 * 		 🌫反射绕过公共方法出现不适唯一实例情况
 * 	   懒汉式 ： 延迟初始化
 *   饿汉式 ：无论实例对象是否有用到,都先直接在堆空间中分配内存给到对象==》 容易造成浪费 
 *   
 *   //绝对线程安全，在线程还没出现以前就是实例化了，不可能存在访问安全问题
 * @author dudu
 *
 */
public class SingleTonForERealHan {
	
	//这个地方加final ： 无法修改
	//如果不加final 可能通过反射覆盖 或者 序列化
	//直接实例化 ： 如果有很多个这样的单例，但是又很多没有用到，就会浪费很多空间
	private static final SingleTonForERealHan singleTonLanHan = new SingleTonForERealHan();

	/**
	 * 私有构造器
	 */
	private SingleTonForERealHan () {
		
	}
	
	public static SingleTonForERealHan getInstance() {
		return singleTonLanHan;
	}
}
