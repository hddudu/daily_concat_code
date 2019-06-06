package com.homework.gupao.designpattern.prototype.chonggou.noclone;

/**
 * 模板类
 * @author dudu
 *
 */
public class AdvTemplate {

	//广告信名称
	private String advSubject = "XX银行国庆信用卡抽奖活动";
	//广告信内容
	private String advContent = "国庆抽奖活动通知： 只要刷卡就有机会抽奖一百万";
	public String getAdvSubject() {
		return advSubject;
	}
	public String getAdvContent() {
		return advContent;
	}
}
