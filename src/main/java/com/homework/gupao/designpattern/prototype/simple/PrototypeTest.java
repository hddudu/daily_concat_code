package com.homework.gupao.designpattern.prototype.simple;

import java.util.ArrayList;
import java.util.List;

public class PrototypeTest {

	public static void main(String[] args) {
		// 创建一个具体的需要克隆的对象
		ConcretePrototypeAA concretePrototype = new ConcretePrototypeAA(); 
		 // 填充属性，方便测试
		concretePrototype.setAge(18);
		concretePrototype.setName("prototype");
		List hobbies = new ArrayList<String>();
		hobbies.add("游泳");
		hobbies.add("program");
		concretePrototype.setHobbies(hobbies);
		System.out.println(concretePrototype);//ConcretePrototypeAA@15db9742
		// 创建Client对象，准备开始克隆
		
		Client client = new Client(concretePrototype);//构造函数 初始化
		ConcretePrototypeAA copy1 = (ConcretePrototypeAA) client.startClone(concretePrototype);
		System.out.println(copy1);//ConcretePrototypeAA@6d06d69c
		
		copy1.getHobbies().add("克隆对象自己学习,修改克隆对象同时也修改了原对象，那么是浅克隆");//
		System.out.println("克隆对象中的引用类型地址值：" + copy1.getHobbies());
        System.out.println("原对象中的引用类型地址值：" + concretePrototype.getHobbies());
        System.out.println("对象地址比较："+(copy1.getHobbies() == concretePrototype.getHobbies()));
		
		
	}

}
