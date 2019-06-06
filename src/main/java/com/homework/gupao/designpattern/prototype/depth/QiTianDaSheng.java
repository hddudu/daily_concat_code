package com.homework.gupao.designpattern.prototype.depth;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * 首先是猴子， 齐天大圣是一个名字
 *   猴子
 * ||
 * 齐天大圣 孙悟空
 * @author dudu
 *
 */
public class QiTianDaSheng extends Monkey implements Cloneable, Serializable {
	
	public JinGuBang jinGuBang;
	
	public QiTianDaSheng() {
		//只是初始化
		this.birthday = new Date();
		this.jinGuBang = new JinGuBang();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return this.deepClone();
	}

	private Object deepClone() {
		try {
			//字节数组 输出流--》对象输出流 --》将一个对象放到这个IO流中
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(this);//输出当前这个对象
			
			oos.flush();
			oos.close();
			
			//字节数组输入流(以字节数组输出流的字节大小) --》 对象输入流 --》 从IO流中将字节读成一个对象
			ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bis);
			QiTianDaSheng copy =  (QiTianDaSheng) ois.readObject();//复制对象
			copy.birthday = new Date();
			return copy;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *  浅复制
	 * @param target
	 * @return
	 */
	public QiTianDaSheng shallowClone(QiTianDaSheng target) {
		QiTianDaSheng qiTianDaSheng = new QiTianDaSheng();
		qiTianDaSheng.height = target.height;
		qiTianDaSheng.weight = target.weight;
		
		qiTianDaSheng.jinGuBang = target.jinGuBang;
		qiTianDaSheng.birthday = new Date();
		return qiTianDaSheng;
	}
	
	

}
