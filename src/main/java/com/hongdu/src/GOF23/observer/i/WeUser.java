package com.hongdu.src.GOF23.observer.i;

/**
 * 具體的我們的觀察者：
 * 	我们用户
 * @author Administrator
 *
 */
public class WeUser implements Observer {

	private String name;
	private String message;//具体被观察者也有信息message与订阅者的信息message进行了关联c
	
//	private boolean isLast = false;//TODO:是否最后一次收到消息==>类似与取消订阅的最后一次消息通知: 默认为false: 不是最后一次
	
	public WeUser(String name) {
		this.name = name;
	}
	
	@Override
	public void update(String message) {
		this.message = message;//内部订阅的信息来自主题(抽象被观察者发出的信息)
		//用户更新
		read();
	}

	/**
	 * 读取信息
	 */
	private void read() {
		System.out.println(this.name + "收到来自主题 : " + this.message);
	}

}
