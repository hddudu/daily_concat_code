package com.homework.gupao.designpattern.singleton.synchronizetest;

//一个线程获取了该对象的锁之后，其他线程来访问其他synchronized实例方法现象 举栗
public class SyncDemo2 {

	public synchronized void method1() {
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    public void method2() {
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }
    
    public static void main(String[] args) {
		final SyncDemo2 test = new SyncDemo2();
		new Thread(new Runnable() {
			@Override
			public void run() {
				test.method1();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				test.method2();
			}
		}).start();
	}
    /**
     * 分析：当线程1还在执行时，线程2也执行了，所以当其他线程来访问非synchronized修饰的方法时是可以访问的
     * 此处思考一个问题：为什么分布式环境下synchronized失效？如何解决这种情况？
     */
}
