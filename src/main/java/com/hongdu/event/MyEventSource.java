package com.hongdu.event;

import java.util.EventListener;
import java.util.HashSet;
import java.util.Set;

/**
 * 我的事件源：  需要注册事件监听(其实就是容器属性添加监听器对象（处理方法类）)
 * 我的事件： 事件状态对象的基类，它封装了事件源对象以及和事件相关的信息。 所有java的事件类都需要继承该类。
 * 事件监听器： 事件监听注册在事件源上，事件源属性或状态改变，调用相应监听器内的回调方法
 * @author Administrator
 *
 */
public class MyEventSource {

	private int flag = 0;
	//A tagging interface that all event listener interfaces must extend.
	Set<EventListener> listeners = new HashSet<>();
	
	//注册事件监听1
	public void addStateChangeListener(StateChangeListener listener) {
		listeners.add(listener);
	}
	//注册事件监听2
	public void addStateChangeToOneListener(StateChangeToOneListener listener) {
		listeners.add(listener);
	}
	//事件发生改变,通知注册在事件源上的所有事件做出相应的反映
	public void notifyAllListeners() {
		for(EventListener listener : listeners) {
			try {
				((StateChangeListener)listener).handleEvent(new MyEvent(this));
			} catch (Exception e) {
				if(flag == 1) {
					((StateChangeToOneListener)listener).handleEvent(new MyEvent(this));
				}
			}
		}
	}
	//状态的改变
	public void changeFlag() {
		flag = (flag == 0 ? 1 : 0);
		notifyAllListeners();
	}
	
	public int getFlag() {
		return flag;
	}
}
