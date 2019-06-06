package com.homework.gupao.designpattern.prototype.depth;

import java.io.Serializable;

public class JinGuBang implements Serializable {

	public float h = 100;
	public float d = 10;
	
	
	public void changeBig() {
		this.h *= 2;
		this.d *= 2;
	}
	
	public void changeSmall() {
		this.h /= 2;
		this.d /= 2;
	}
}
