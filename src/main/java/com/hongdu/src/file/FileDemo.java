package com.hongdu.src.file;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/**
 * 这个工具类的功能为：
 * （1）可以压缩文件，也可以压缩文件夹
 * （2）同时支持压缩多级文件夹，工具内部做了递归处理
 * （3）碰到空的文件夹，也可以压缩
 * （4）可以选择是否保留原来的目录结构，如果不保留，所有文件跑压缩包根目录去了，且空文件夹直接舍弃。注意：如果不保留文件原来目录结构，在碰到文件名相同的文件时，会压缩失败。
 * （5）代码中提供了2个压缩文件的方法，一个的输入参数为文件夹路径，一个为文件列表，可根据实际需求选择方法
 */

/**
 * 成员变量:
 * ①FileSystem
 * ②private static enum PathStatus { INVALID, CHECKED };
 * ③path
 * ④private transient PathStatus status = null; 不能序列化 的PathStatus
 * ⑤判断是否有效
 *      final boolean isInvalid() {
 *         if (status == null) {
 *             status = (this.path.indexOf('\u0000') < 0) ? PathStatus.CHECKED
 *                                                        : PathStatus.INVALID;
 *         }
 *         return status == PathStatus.INVALID;
 *     }
 *  ⑥
 *      * The length of this abstract pathname's prefix, or zero if it has no
 *      * prefix.
 *      private transient int prefixLength;
 *  ⑦public static final char separatorChar = fs.getSeparator();
 *  ⑧public static final String separator = "" + separatorChar;
 *  ⑨ public static final char pathSeparatorChar = fs.getPathSeparator();
 *  10： public static final String pathSeparator = "" + pathSeparatorChar;
 *  11： 断言：
 *   private File(String child, File parent) {
 *         assert parent.path != null;
 *         assert (!parent.path.equals(""));
 *         this.path = fs.resolve(parent.path, child);
 *         this.prefixLength = parent.prefixLength;
 *     }
 *  12: public File(String pathname) {
 *         if (pathname == null) {
 *             throw new NullPointerException();
 *         }
 *         this.path = fs.normalize(pathname);
 *         this.prefixLength = fs.prefixLength(this.path);
 *     }
 */
@Slf4j
public class FileDemo {


    @Test
    public void test() throws IOException {
        int i = (int)(Math.random()*900 + 100);
        String myStr = Integer.toString(i);
        System.out.println(myStr);
        File file = new File("test.txt");
        System.out.println("--");
        //创建文件夹 在该目录下
        String path = file.getAbsolutePath();
        System.out.println("--" + path);
        File mulu = new File("mulu");
        if(!mulu.exists())
            mulu.mkdir();
        System.out.println("mulu: " + mulu.getAbsolutePath());
        String temp = mulu.getAbsolutePath() + "/" + "test.txt";
        File file1 = new File(temp);
        if(!file1.exists()) {
            file1.createNewFile();
        }
        if(mulu.isDirectory()) {
            mulu.delete();
//            mulu.deleteOnExit();
        }
    }
    @Test
    public void test2() throws IOException {
        System.out.println(File.separator);
        File mulu = new File("mulu");
        if(!mulu.exists())
            mulu.mkdir();
        System.out.println("mulu: " + mulu.getAbsolutePath());
        //创建多个随机文件
        List<String> filePaths = generatedMultiFiles(mulu,5);
        //打包文件: 创建压缩文件需要的空的zip包
        String zipName = "temp.zip";
        String zipFilePath = mulu.getAbsolutePath() + File.separator + zipName;
        File zip = new File(zipFilePath);
        if(!zip.exists()) {
            zip.createNewFile();
        }
        //创建zip文件输出流
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));

        //将流文件下载到页面 ==》
    }

    /**
     *
     * @param zipBasePath 临时压缩文件基础路径
     * @param zipName 临时压缩文件名称
     * @param zipFilePath 临时压缩文件完整路径
     * @param filePaths 需要压缩的文件路径集合
     * @param zos 文件压缩流 将文件添加到这里i
     * @return
     */
    private String zipFile(String zipBasePath, String zipName, String zipFilePath, List<String> filePaths,ZipOutputStream zos) {
        //循环读取文件路径集合 ： 目前先支持一层路径 ==》扩展为多级目录
        for(String filePath : filePaths) {
            File inputFile = new File(filePath);
            if(inputFile.exists()) {//如果文件存在
                if(inputFile.isFile()) {
                    try{
                        //创建文件输入流
                        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
                        //将文件写入到zip 就是讲文件进行打包
                        zos.putNextEntry(new ZipEntry(inputFile.getName()));

                        //写入文件的方法 同上
                        int size = 0;
                        byte[] buffer = new byte[1024];
                        while ((size = bis.read(buffer)) > 0) {
                            /**
                             *      * @param b the data to be written 一次被写的数据大小
                             *      * @param off the start offset in the data 从哪个位置开始写
                             *      * @param len the number of bytes that are written 实际上被写的数据字节长度
                             */
                            zos.write(buffer,0,size);//
                        }
                        //关闭输入输出流
                        zos.closeEntry();
                        bis.close();
                    } catch (FileNotFoundException e) {
                        log.error("文件未找到!" + e.getMessage());
                    } catch (IOException e2) {
                        log.error("文件流异常!" + e2.getMessage());
                    }
                } else { //如果是文件夹，则使用穷举的方法获取文件，写入zip
                    try{
                        File[] files = inputFile.listFiles();
                        List<String> filePathsTems = new ArrayList<>();
                        for(File fileItem : files) {
                            filePathsTems.add(fileItem.toString());
                        }
                        return zipFile(zipBasePath, zipName, zipFilePath, filePathsTems,zos);
                    } catch (Exception e) {
                        log.error("递归打包文件出错: " + e.getMessage());
                    }
                }
            } else {//如果文件不存在 ：是否需要再进行创建文件： 生成二维码图片 加入到压缩流中 进行打包呢?

            }
        }
        return null;
    }

    /**
     * 在目录文件夹下创建多个文件
     * @param directory 父亲目录
     * @param n 创建文件个数
     * @return 文件路径集合
     * @throws IOException
     */
    private List<String> generatedMultiFiles(File directory,int n) throws IOException{
        if(!directory.exists()) {
            directory.mkdir();
        }
        if(n <= 0) {
            return null;
        }
        String path = directory.getAbsolutePath();
        String path2 = directory.getPath();
        String path3 = directory.getParent();
        String path4 = directory.getCanonicalPath();
        long path5 = directory.getFreeSpace();
        String fileName = directory.getName();
        List<String> filePaths = new ArrayList<>();
        for(;n >= 0; n--) {
            //开始生成文件
            String tempPathName = path + File.separator + generatedUUID() + MyConstants.FILE_PNG;
            filePaths.add(tempPathName);
            File tempFile = new File(tempPathName);
            if(!tempFile.exists()) {
                tempFile.createNewFile();
            }
        }
        return filePaths;
    }

    @Test
    public void testGeneratedMultiFiles() throws IOException{
        generatedMultiFiles( new File("mulu"), 4);
    }

    private String generatedUUID() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    @Test
    public void testUUID () {
        System.out.println(generatedUUID());
    }

    /**
     * 根据路径生成文件: 确保路径的合法性
     * @param path
     */
    private void generatedMultiFiles(String path) {

    }


}
