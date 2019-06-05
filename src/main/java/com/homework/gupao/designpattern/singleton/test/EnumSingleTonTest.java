package com.homework.gupao.designpattern.singleton.test;

import java.lang.reflect.Constructor;

import com.homework.gupao.designpattern.singleton.rongqi.EnumSingleTon;

public class EnumSingleTonTest {

	public static void main(String[] args) {
		try {
            Class clazz = EnumSingleTon.class;
            Constructor c = clazz.getDeclaredConstructor(String.class,int.class);
            c.setAccessible(true);
            EnumSingleTon enumSingleton = (EnumSingleTon)c.newInstance("Tom",666);

        }catch (Exception e){
            e.printStackTrace();
        }
	}
}
