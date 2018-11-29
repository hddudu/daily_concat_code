package com.hongdu.src.file;

import org.junit.Test;

import java.io.File;

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

public class FileDemo {
    @Test
    public void test() {
        File file = new File("test.txt");
        System.out.println("--");
    }
}
