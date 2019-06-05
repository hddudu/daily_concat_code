package com.homework.gupao.designpattern.singleton;

/** 参考 ： https://www.cnblogs.com/wl0000-03/p/5973039.html
 * ①java的内置锁：每个java对象都可以用做一个实现同步的锁，这些锁成为内置锁
 * 	线程进入同步代码块或方法的时候会自动获得该锁，在退出同步代码块或方法时会释放该锁。
 *         获得内置锁的唯一途径就是进入这个锁的保护的同步代码块或方法。
 *      Ⅱ java内置锁是一个互斥锁，这就是意味着最多只有一个线程能够获得该锁，
 *        当线程A尝试去获得线程B持有的内置锁时，线程A必须等待或者阻塞，知道线程B释放这个锁，
 *        如果B线程不释放这个锁，那么A线程将永远等待下去。
 * 3： java的对象锁和类锁：java的对象锁和类锁在锁的概念上基本上和内置锁是一致的，
 *        但是，两个锁实际是有很大的区别的，对象锁是用于对象实例方法，或者一个对象实例上的，
 *        类锁是用于类的静态方法或者一个类的class对象上的。我们知道，类的对象实例可以有很多个，
 *        但是每个类只有一个class对象，所以不同对象实例的对象锁是互不干扰的，但是每个类只有一个类锁。
 *        但是有一点必须注意的是，其实类锁只是一个概念上的东西，并不是真实存在的，它只是用来帮助我们理
 *        解锁定实例方法和静态方法的区别的        
 * @author dudu
 *
 */
public class SingleTonForLanHan2Synchronized2 {
	
	//加final的对象可以实例化吗? 不可以实例化
//	private static final SingleTonForEHan singleTon = null;//
	private static volatile SingleTonForLanHan2Synchronized2 singleTon = null;//
	
	/**
	 * 私有化构造器
	 */
	private SingleTonForLanHan2Synchronized2() {
		
	}
	/**
	 * 方法前面添加同步关键字 ： synchronized 解决多线程下单例不唯一情况
	 *   改进： 去掉方法前面的关键字
	 * 同步锁加到代码块面上去
	 *  synchronized : ①添加到方法前面 ： 粒度大
	 *  				Ⅱ添加到类前面 : 类锁 ： 粒度大
	 *  				🌂添加到实例对象 ： 对象锁 ： 粒度小
	 * @return
	 */
	public static SingleTonForLanHan2Synchronized2 getInstance() {
		if(singleTon == null) {//第一次检测
			//线程一抢到cpu进入到这一行，然后cpu被线程2抢走，线程2也进入到这一行
			//那么线程一将 singleTon  实例化后，线程2也会再将 singleTon 实例化一次
			//那么结果是 ： 线程一的实例化被线程二覆盖
			//如果在覆盖前线程一的实例被引用了，那么后面的结果是实例对象莫名被修改了
//			singleTon = new SingleTonForEHan();//final修饰无法实例化
			synchronized(SingleTonForLanHan2Synchronized2.class) {//对象锁 : 即使是这样,但是也可可能出现不安全的问题
				if(singleTon == null) {//第二次检测
					//同步块内的操作尽量保证原子性 ：
					//同步代码块内代码全部执行完： 
					singleTon = new SingleTonForLanHan2Synchronized2();//所以延迟初始化需要去掉final
					//非原子操作 ： 如果出现重排序：
					//1： 给 instance 分配内存
					//2：调用 Singleton 的构造函数来初始化成员变量
					//3： 将instance对象指向分配的内存空间（执行完这步 instance 就为非 null 了）
					//如果一个线程执行顺序是这样了： 132 ==》 
					//最后理解是这样： 线程一 抢到了锁， 然后线程2开始等待
					//线程一执行到了1，3==》 此时cpu时间片用完，需要再抢cpu，这个时候： 线程一是接着2继续执行，完成instance实例化的正确过程的；
					//但是这个时候线程2抢到了cpu，直接判断instance指向了非null（内存空间已经分配，对象指向了内存空间，只是内存空间中还没有调用构造函数）
					//解决： 对变量添加 volatile 关键字
				}
			}
		}
		return singleTon;
	}
	//存在问题： 多线程下， 出现不是唯一实例的情况
}
