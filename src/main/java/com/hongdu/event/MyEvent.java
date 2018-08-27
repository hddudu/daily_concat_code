package com.hongdu.event;

import java.util.EventObject;

/**
 * 事件对象 ： 相当于 事件观察者(事件被观察者)
 * @author Administrator
 *
 */
public class MyEvent extends EventObject {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int sourceState;

	public MyEvent(Object source) {
		super(source);
		sourceState = ((MyEventSource)source).getFlag();
	}

	public int getSourceState() {
		return sourceState;
	}

}
