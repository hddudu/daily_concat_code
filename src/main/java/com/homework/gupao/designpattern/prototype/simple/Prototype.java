package com.homework.gupao.designpattern.prototype.simple;

/**
 * 克隆抽象出成为一种规则 ： 
 * @author dudu
 *
 */
public interface Prototype {
	//不能用clone这个词
	Prototype copy();
}
