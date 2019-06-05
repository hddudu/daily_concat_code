package com.homework.gupao.designpattern.singleton.synchronizetest;

/**
 * 
主要原因有：
1.存在共享数据 
2.多线程共同操作共享数据。
关键字synchronized可以保证在同一时刻，只有一个线程可以执行某个方法或某个代码块，
同时synchronized可以保证一个线程的变化可见（可见性），即可以代替volatile。
 */
public class Question1 implements Runnable{
	
	private static int count = 0;

	@Override
	public void run() {
		synchronized (Object.class) {//加锁后： 结果正确
			for (int i = 0; i < 10000; i++) {
				count++;
			}
		}
	}
	//主线程 再开启10个线程
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new Question1());
			thread.start();//启动10个线程
		}
		
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("result : " + count);
		//期望情况： 十个线程 都加10000次==》 十万
		//结果是： result : 38481
		//结果是： result : 39429
		//结果是： result : 34790
		//结果是： result : 46384
		//结果是： result : 41572
		//结果是： result : 45597
		//结果少了这么多？
	}
	
}
