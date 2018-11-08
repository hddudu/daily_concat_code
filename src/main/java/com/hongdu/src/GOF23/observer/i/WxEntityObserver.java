package com.hongdu.src.GOF23.observer.i;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信公众号被很多人关注(许多观察者关注了这个主题： 如果主题修改,那么关注者收到相应的信息)
 * @author Administrator
 *
 */
public class WxEntityObserver implements Observerable {
	//集合<关注者><观察者>
	List<Observer> list;
	//观察者需要的信息
	private String message;
	
	public WxEntityObserver() {
		list = new ArrayList<>();
	}
	 

	@Override
	public void notifyAllObserver() {
		 for(int i = 0; i < list.size(); i++) {
	            Observer oserver = list.get(i);
//	            if(oserver instanceof WeUser) {
//	            	if(((WeUser)oserver).getLast())
//	            }
	        
	            	oserver.update(message);
		 }
	}

	@Override
	public void delete(Observer o) {
		if(!list.isEmpty()) {
			list.remove(o);
		}
//		if(o instanceof WeUser) {
//			((WeUser) o).setLast(true);
//		}
	}

	@Override
	public void add(Observer o) {
		list.add(o);
	}
	
	//推送信息
	public void setInfomation(String str) {
		this.message = str;
		System.out.println("微信服务更新消息:" + str);
		//通知所有的订阅者修改消息
		notifyAllObserver();
	}

}
