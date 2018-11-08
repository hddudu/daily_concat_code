package com.hongdu.src.GOF23.observer.i;

public class Test {

	public static void main(String[] args) {
		WeUser wu = new WeUser("zhangsan");//观察者1
		WeUser wu2 = new WeUser("lisi");//观察者2
		WeUser wu3 = new WeUser("wangwu");//观察者3
		
		WxEntityObserver server = new WxEntityObserver();//被观察者
		server.add(wu);
		server.add(wu2);
		server.add(wu3);
		
		//主题发生改变 : message-->notifyAllObser()-->update()-->read()==>消息传递
		server.setInfomation("JAVA还可以!");
		
		//lisi取消订阅
		server.delete(wu2);
		server.setInfomation("李四取消订阅消息!");//通知到其他人: 李四不会收到消息
		//如果我想先让李四收到最后一次消息呢?
		//采用一个延迟生效(或者)延迟加载的机制可以?
		//
		
	}
}
