package com.hongdu.event;

public class Test {

	public static void main(String[] args) {
		//事件源检测到改变
		//注册了 监听
		//那么会发生监听事件的处理
		MyEventSource mes = new MyEventSource();
		mes.addStateChangeListener(new StateChangeListener());
		mes.addStateChangeToOneListener(new StateChangeToOneListener());

		mes.changeFlag();
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		mes.changeFlag();
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		mes.changeFlag();
	}
}
