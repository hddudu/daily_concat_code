package com.homework.gupao.designpattern.prototype.chonggou.noclone;

/**
 * 发送邮件填充具体信息类
 * @author dudu
 *
 */
public class Mail {
	
	//收件人
	private String receiver;
	//邮件名称
	private String subject;
	//称谓
	private String application;
	//邮件内容
	private String content;
	//邮件的尾部一般都是加上“XXX版权所有”
	private String tail;
	
	public Mail(AdvTemplate advTemplate) {
		this.content = advTemplate.getAdvContent();
		this.subject = advTemplate.getAdvSubject();
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getTail() {
		return tail;
	}

	public void setTail(String tail) {
		this.tail = tail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
