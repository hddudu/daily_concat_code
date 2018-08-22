package com.hongdu.i;

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
	
	@Override
	public void delete() {

	}

	@Override
	public void add() {

	}

	@Override
	public void notifyAllObserver() {

	}

}
