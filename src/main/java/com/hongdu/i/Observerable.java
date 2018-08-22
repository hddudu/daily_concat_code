package com.hongdu.i;

/**
 * 抽象被观察者角色:
 * 
 * @author Administrator
 *
 */
public interface Observerable {

	//集合删除
	void delete(Observer o);
	//集合新增
	void add(Observer o);
	//集合通知: 涉及到遍历
	void notifyAllObserver();
}
