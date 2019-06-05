package com.homework.gupao.designpattern.singleton.rongqi;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//Spring中的做法，就是用这种注册式单例
public class SingleTonContainer {

	private SingleTonContainer() {
		
	}
	private static Map<String, Object> ioc = new ConcurrentHashMap<String, Object>();
	public static Object getInstance(String className) {
		synchronized(ioc) {
			if(!ioc.containsKey(className)) {
				Object obj = null;
				try {
					obj = Class.forName(className).newInstance();
					ioc.put(className, obj);//类名 ： 类实例对象（类似于一个类名 一个 实例对象）
				} catch (Exception e) {
					e.printStackTrace();
				}
				return obj;
			} else {
				return ioc.get(className);//如果类名已经存在 ==》 说明实例对象已经存在 直接返回
			}
		}
	}
}
