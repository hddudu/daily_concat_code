package com.hongdu.i;

/**
 * 观察者：
 * 	被回调的一个方法：
 * 		就是前面一个转台修改后,这个方法需要被调用
 * @author Administrator
 *
 */
public interface Observer {
	
	void update(String message);
}
