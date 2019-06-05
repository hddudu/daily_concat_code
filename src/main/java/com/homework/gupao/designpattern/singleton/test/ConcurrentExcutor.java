package com.homework.gupao.designpattern.singleton.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 并发环境工具类
 * 相比new Thread ：
那你就out太多了，new Thread的弊端如下：

a. 每次new Thread新建对象性能差。
b. 线程缺乏统一管理，可能无限制新建线程，相互之间竞争，及可能占用过多系统资源导致死机或oom。
c. 缺乏更多功能，如定时执行、定期执行、线程中断。
相比new Thread，Java提供的四种线程池的好处在于：
a. 重用存在的线程，减少对象创建、消亡的开销，性能佳。
b. 可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞。
c. 提供定时执行、定期执行、单线程、并发数控制等功能。
------------------------------------------------
Java通过Executors提供四种线程池，分别为：
1： newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	好处： 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
2： newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
	创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等
3： newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
	创建一个定长线程池，支持定时及周期性任务执行
4： newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
	创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
------------------------------------------------

线程池的各种使用场景:
https://blog.csdn.net/qq_17045385/article/details/79820847

 * @author dudu
 *
 */
public class ConcurrentExcutor {

	/**
	 * @param runHandler
	 * @param executeCount 发起请求总数 
	 * @param concurrentCount 同时并发执行的线程数
	 * @throws Exception
	 */
	public static void execute(final RunHandler runHandler, int executeCount, int concurrentCount) throws Exception {
		//一个可缓存的线程池： 相当于缓存线程， 就是线程最后调度的是cpu
		ExecutorService executorService = Executors.newCachedThreadPool();
		//控制信号量，此处用于控制并发的线程数
		final Semaphore semaphore = new Semaphore(concurrentCount);
		//闭锁 可实现计数量递减
		final CountDownLatch countDownLatch = new CountDownLatch(executeCount);
		for (int i = 0; i < executeCount; i++) {
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					//执行此方法用于获取执行许可，当总计未释放的许可数不超过executeCount时,
                    //则允许同性，否则线程阻塞等待，知道获取到许可
					try {
						semaphore.acquire();
						runHandler.handler();
						//释放许可
						semaphore.release();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					countDownLatch.countDown();
				}
			});
		}
		countDownLatch.await();//线程阻塞，知道闭锁值为0时，阻塞才释放，继续往下执行
		executorService.shutdown();
	}
	
	public interface RunHandler{
        void handler();
    }
}
