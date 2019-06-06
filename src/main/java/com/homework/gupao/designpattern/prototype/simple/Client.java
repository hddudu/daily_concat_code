package com.homework.gupao.designpattern.prototype.simple;

public class Client {

	private Prototype prototype;

	public Client(Prototype prototype) {
		super();
		this.prototype = prototype;
	}
	
	public Prototype startClone(Prototype concretePrototype) {
		return concretePrototype.copy();
	}
}
