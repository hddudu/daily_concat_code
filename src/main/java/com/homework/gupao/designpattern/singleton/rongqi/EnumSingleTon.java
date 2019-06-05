package com.homework.gupao.designpattern.singleton.rongqi;

//常量中去使用，常量不就是用来大家都能够共用吗？
//通常在通用API中使用
public enum EnumSingleTon {
	
	INSTANCE;
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public static EnumSingleTon getInstance() {
		return INSTANCE;
	}
}
