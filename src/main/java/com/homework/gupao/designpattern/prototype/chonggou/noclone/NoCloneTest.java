package com.homework.gupao.designpattern.prototype.chonggou.noclone;

import java.util.Random;
/**
 * 思考 ： 当邮件要发送的数量是600万的时候， 怎么发？ 一封邮件发送需要0.02秒，60万秒，太久了。
 * 多线程处理时： 直接不安全了， 存在共享变量， 容易修改收件人地址和收件人称谓
 * @author dudu
 *
 */
public class NoCloneTest {

	//发送广告信的数量
	private static int MAX_COUNT = 6;
	
	public static void main(String[] args) {
		//模拟发送邮件
		int i = 0;
		//把模板定义出来
		Mail mail = new Mail(new AdvTemplate());
		mail.setTail("XX银行版权所有");
		while(i < MAX_COUNT) {
			//以下是每封邮件不同的地方
			mail.setApplication(getRandomString(5) + " 先生(女士)");
			mail.setReceiver(getRandomString(5) + "@" + getRandomString(8) + ".com");
			//然后发送邮件
			sendMail(mail);
			i++;
		}
	}
	
	//发送邮件
	private static void sendMail(Mail mail) {
		System.out.println("标题： " + mail.getSubject() + "\t收件人： " + mail.getReceiver() + "\t...发送成功!");
	}

	//获得指定长度的随机字符串: 5 ==> 就是5个随机字符串
	public static String getRandomString(int maxLength) {
		String source = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb = new StringBuffer();
		Random rand = new Random();
		for (int i = 0; i < maxLength; i++) {
			sb.append(source.charAt(rand.nextInt(source.length())));
		}
		return sb.toString();
		
	}
}
