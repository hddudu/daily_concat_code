package com.homework.gupao.designpattern.prototype.chonggou.yesclone;

import com.homework.gupao.designpattern.prototype.chonggou.noclone.AdvTemplate;

public class YesMail implements Cloneable {

	private String receiver;
	
	private String subject;
	
	private String appelation;
	
	private String content;
	
	private String tail;

	
	
	public String getReceiver() {
		return receiver;
	}



	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getAppelation() {
		return appelation;
	}



	public void setAppelation(String appelation) {
		this.appelation = appelation;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getTail() {
		return tail;
	}



	public void setTail(String tail) {
		this.tail = tail;
	}


	

//	public YesMail(String subject, String content) {
//		super();
//		this.subject = subject;
//		this.content = content;
//	}
	public YesMail(AdvTemplate advTemplate) {
		this.content = advTemplate.getAdvContent();
		this.subject = advTemplate.getAdvSubject();
	}

	//克隆方法
	public YesMail clone() throws CloneNotSupportedException {
		YesMail mail = null;
		try {
			mail = (YesMail) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mail;
	}
	
	
}
