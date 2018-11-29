package com.hongdu.src.datastructurejava.tree.ecs;

import org.junit.Test;

import java.io.File;
import java.net.URL;

public class EcsDemo {
    @Test
    public void test() throws Exception{
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println(f);
        File fileDirctory = new File("");//新建当前空文件夹
        String tempFileDirctory = fileDirctory.getAbsolutePath();//获取绝对路径 文件的临时绝对路径
        String filaName = "111";
        File newFile = new File(filaName+".png");//新建当前目录下的空文件
        if(!newFile.exists()) {
            newFile.createNewFile();//不存在就创建新文件
        }
        File directory = new File("");//参数为空
        String courseFile = directory.getCanonicalPath() ;
        System.out.println(courseFile);

        URL xmlpath = this.getClass().getClassLoader().getResource("EcsDemo.java");
        System.out.println(xmlpath);
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        System.out.println( path);
//        System.out.println( System.getProperty("java.class.path"));
        File directorys = new File("");//设定为当前文件夹
            System.out.println(directory.getCanonicalPath());//获取标准的路径
            System.out.println(directory.getAbsolutePath());//获取绝对路径
        newFile.delete();
    }
}
