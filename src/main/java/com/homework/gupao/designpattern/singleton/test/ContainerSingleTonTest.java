package com.homework.gupao.designpattern.singleton.test;

import com.homework.gupao.designpattern.singleton.rongqi.SingleTonContainer;

public class ContainerSingleTonTest {
	
	//并发数为6的情况下仍无重复 安全
	public static void main(String[] args) {
		try {
			long start = System.currentTimeMillis();
			ConcurrentExcutor.execute(new ConcurrentExcutor.RunHandler() {
				@Override
				public void handler() {
					Object obj = SingleTonContainer.getInstance("com.homework.gupao.designpattern.singleton.Pojo");
					System.out.println(System.currentTimeMillis() + ": " + obj);
				}
			}, 10, 6);
			long end = System.currentTimeMillis();
			System.out.println("总耗时：" + (end - start) + " ms.");
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
}
