package com.hongdu.test.test;

public class Client2 {
	
	public static void main(String[] args) throws Exception {
		new Excutor("192.168.10.203:2181", "/com/hongdu/test/test/Client1","/com/hongdu/test/test/Client2");
		System.in.read();
	}

}
