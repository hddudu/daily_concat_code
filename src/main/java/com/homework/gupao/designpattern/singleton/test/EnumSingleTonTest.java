package com.homework.gupao.designpattern.singleton.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.homework.gupao.designpattern.singleton.rongqi.EnumSingleTon;

public class EnumSingleTonTest {

//	public static void main(String[] args) {
//		try {
//            Class clazz = EnumSingleTon.class;
//            Constructor c = clazz.getDeclaredConstructor(String.class,int.class);
//            c.setAccessible(true);
//            EnumSingleTon enumSingleton = (EnumSingleTon)c.newInstance("Tom",666);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//	}
	
  public static void main(String[] args) {
  try {
      EnumSingleTon instance1 = null;

      EnumSingleTon instance2 = EnumSingleTon.getInstance();
      instance2.setData(new Object());

      FileOutputStream fos = new FileOutputStream("EnumSingleTon.obj");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(instance2);
      oos.flush();
      oos.close();

      FileInputStream fis = new FileInputStream("EnumSingleTon.obj");
      ObjectInputStream ois = new ObjectInputStream(fis);
      instance1 = (EnumSingleTon) ois.readObject();
      ois.close();

      System.out.println(instance1.getData());
      System.out.println(instance2.getData());
      System.out.println(instance1.getData() == instance2.getData());

  }catch (Exception e){
      e.printStackTrace();
  }
}
}
