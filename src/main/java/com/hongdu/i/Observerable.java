package com.hongdu.i;

/**
 * 抽象被观察者角色:
 * 
 * @author Administrator
 *
 */
public interface Observerable {

	//集合删除
	void delete();
	//集合新增
	void add();
	//集合通知
	void notifyAllObserver();
}
