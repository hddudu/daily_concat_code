package com.homework.gupao.designpattern.prototype.chonggou.yesclone;

import java.util.Random;

import com.homework.gupao.designpattern.prototype.chonggou.noclone.AdvTemplate;
import com.homework.gupao.designpattern.prototype.chonggou.noclone.Mail;

public class YesCloneClient {

	private static int MAX_COUNT = 6;
	
	public static void main(String[] args) throws Exception {

		//模拟发送邮件
		int i = 0;
		//把模板定义出来 这个本来是从数据库中获取的 ： 数据库字段： 主题 + 内容
		YesMail mail = new YesMail(new AdvTemplate());
		mail.setTail("XX银行版权所有!");
		while(i < MAX_COUNT) {
			//
			YesMail copyMail = mail.clone();
			mail.setAppelation(getRandomString(5) + " 先生(女士)");
			mail.setReceiver(getRandomString(5) + "@" + getRandomString(8) + ".com");
			//然后发送邮件
			sendMail(copyMail);
		}
	}
	
	private static void sendMail(YesMail mail) {
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
