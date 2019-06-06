package com.homework.gupao.designpattern.singleton.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.homework.gupao.designpattern.singleton.xuliehua.SerializableSingleTon;

public class SerializableSingleTonTest {

	public static void main(String[] args) {
	SerializableSingleTon s1 = null;
	SerializableSingleTon s2 = SerializableSingleTon.getInstance();

    FileOutputStream fos = null;
    try {
        fos = new FileOutputStream("SerializableSingleTon.obj");//内存输出
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s2);//将对象作为流写到对象文件中： SerializableSingleTon.obj
        oos.flush();
        oos.close();


        FileInputStream fis = new FileInputStream("SerializableSingleTon.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        s1 = (SerializableSingleTon)ois.readObject();//反序列化时重构对象时， 会判断有方法： readResolve， 如果有，会以这个方法的对象为准
        ois.close();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
