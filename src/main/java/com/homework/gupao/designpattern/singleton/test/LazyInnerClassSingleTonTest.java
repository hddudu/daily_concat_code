package com.homework.gupao.designpattern.singleton.test;

import java.lang.reflect.Constructor;

import com.homework.gupao.designpattern.singleton.SingleTonInnerClassSafe;

public class LazyInnerClassSingleTonTest {

	public static void main(String[] args) {
		try {
			//很无聊的情况下，进行破坏
			Class<?> clazz = SingleTonInnerClassSafe.class;
			//通过反射拿到私有的构造方法
//			clazz.getConstructor(null);//不是这个方法获取构造器 ： 必须是声明的构造器
			Constructor c = clazz.getDeclaredConstructor(null);//无参 : getDeclaredConstructor
//			Constructor[] cs= clazz.getConstructors();//构造函数数组格式
			//强制访问，强吻，不愿意也要吻
			c.setAccessible(true);
			
			//暴力初始化
			Object o1 = c.newInstance();
			
			//调用了两次构造方法，相当于new了两次
            //犯了原则性问题，
			Object o2 = c.newInstance();

            System.out.println(o1 == o2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
