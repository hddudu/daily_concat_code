package com.homework.gupao.designpattern.prototype.simple;

import java.util.List;

public class ConcretePrototypeAA implements Prototype{

	private int age;
	private String name;
	private List hobbies;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List getHobbies() {
		return hobbies;
	}
	public void setHobbies(List hobbies) {
		this.hobbies = hobbies;
	}
	
	@Override
	public Prototype copy() {
		ConcretePrototypeAA copy = new ConcretePrototypeAA();
		copy.setAge(this.age);
		copy.setHobbies(this.hobbies);
		copy.setName(this.name);
		return copy;
	}
}
