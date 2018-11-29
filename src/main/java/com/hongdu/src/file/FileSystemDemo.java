package com.hongdu.src.file;

/**
 * 文件系统操作demo：
 * ******** Normalization and construction
 * ①public static native FileSystem getFileSystem(); 本地的文件系统本地类
 * ②public abstract char getSeparator();
 * ③public abstract char getPathSeparator();
 * ④ public abstract String normalize(String path);
 *      Convert the given pathname string to normal form.  If the string is
 *      already in normal form then it is simply returned.
 *
 * ******** Path operations
 *    -- Attribute accessors --
 *    -- Constants for simple boolean attributes --
 * -- File operations --
 *  public abstract boolean createFileExclusively(String pathname)
 *         throws IOException;
 *   public abstract boolean delete(File f);
 *   public abstract String[] list(File f);
 *   public abstract boolean createDirectory(File f);
 *   public abstract boolean rename(File f1, File f2);
 *   public abstract boolean setLastModifiedTime(File f, long time);
 *   public abstract boolean setReadOnly(File f);
 *
 * -- Filesystem interface --
 *   public abstract File[] listRoots();
 *
 *    -- Disk usage --
 *
 *    -- Basic infrastructure --
 *    public abstract int compare(File f1, File f2);
 *     public abstract int hashCode(File f);
 *
 *     static boolean useCanonCaches      = true;
 *     static boolean useCanonPrefixCache = true;
 *
 *     static {
 *         useCanonCaches      = getBooleanProperty("sun.io.useCanonCaches",
 *                                                  useCanonCaches);
 *         useCanonPrefixCache = getBooleanProperty("sun.io.useCanonPrefixCache",
 *                                                  useCanonPrefixCache);
 *     }
 */
public class FileSystemDemo {
}
