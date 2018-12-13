package com.hongdu.src.date;

import com.hongdu.src.file.MyConstants;
import org.junit.Test;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * https://www.cnblogs.com/endtel/p/6018429.html
 * 日期操作工具类
 * 1、使用Calendar类别之前，要先知道Calendar的一些方法取回的数字是对应于Calendar本身的常数，也就是说并不是取回1 这个数字，就表示今天是星期一。
 * a、想要取得时间，首先使用Calendar的getInstance()取得一个Calendar的实例，例如：
 * Calendar rightNow = Calendar.getInstance();
 * b、如果现在您想知道是几年，则可以使用get()方法并指定常数，例如：
 * System.out.println(rightNow.get(Calendar.YEAR));
 * c、如果是2005年，则上例会显示2005的数字，依照这个例子，假设撰写本文的时间是5月份，而现在想使用程式取得月份，则下例可能会有些困惑：
 * System.out.println(rightNow.get(Calendar.MONTH));
 * d、程式会显示4这个数字，而不是预期的5，因为传回的4并不是代表月份，而是对应于Calendar.MAY常数的值， Calendar在月份上的常数值从Calendar.JANUARY开始是0，到Calendar.DECEMBER的11，所以如果想要显示传回值的真正意涵，可以如下撰写：
 * String[] months = {"一月", "二月", "三月", "四月","五月", "六月", "七月", "八月","九月", "十月", "十一月", "十二月"};
 * Calendar rightNow = Calendar.getInstance();
 * System.out.println(months[rightNow.get(Calendar.MONTH)]);
 * 2、初始值
 * 代码如下，值得指出的是由于我们的时区设置是GMT+8,所以打印格林威治时间得到的是1970-01-01 08:00:00.
 * Calendar cal = Calendar.getInstance（）；//得到当前时间
 * cal.setTimeInMillis（0）；//转换成格林威治时间
 * 3、获取值
 * cal.get（Calendar.YEAR）；//年
 * cal.get（Calendar.MONTH） + 1;//月（必须要+1）
 * cal.get（Calendar.DATE）；//日
 * cal.get（Calendar.HOUR_OF_DAY）；//时
 * cal.get（Calendar.MINUTE）；//分
 * cal.get（Calendar.SECOND）；//秒
 * cal.get（Calendar.DAY_OF_WEEK）；//星期（Locale.ENGLISH情况下，周日是1,剩下自己推算）
 * 如果拿时间不是为了计算而是展示出来，肯定用SimpleDateFormart了，模式为yyyy-MM-dd HH:mm:ss
 * 4、设置值
 * cal.set（2013, 5, 4, 13, 44, 51）；//年月日时分秒（月份0代表1月）
 * cal.set（Calendar.YEAR, 2014）；//年
 * cal.set（Calendar.MONTH, 7）；//月（月份0代表1月）
 * cal.set（Calendar.DATE, 11）；//日
 * cal.set（Calendar.HOUR_OF_DAY, 15）；//时
 * cal.set（Calendar.MINUTE, 33）；//分
 * cal.set（Calendar.SECOND, 32）；//秒
 * 5、运算值
 * cal.add（Calendar.YEAR, 1）；//年
 * cal.add（Calendar.MONTH, 1）；//月
 * cal.add（Calendar.DATE, 1）；//日
 * cal.add（Calendar.HOUR_OF_DAY, -1）；//时
 * cal.add（Calendar.MINUTE, 1）；//分
 * cal.add（Calendar.SECOND, 1）；//秒
 * cal.add（Calendar.DATE, 7）；//周
 */
public class DateDemo {

//    enum{
//
//    }

//    public static final String DATE_FORMAT_YYYY_MM_DD = "YYYY-MM-DD";
//    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "YYYY-MM-DD HH:MM:SS";

    /**
     * 控制台打印日期
     * @param date
     */
    public static void printDate(Date date) {
        System.out.println(date);
    }
    public static void printDate(String str) {
        System.out.println(str);
    }

    /**
     * 将时间戳转成日期格式:
     * "beginDate" -> "1506787200000" ==> 转日期格式
     */
    @Test
    public void test() throws ParseException{
//        System.out.println(getSjcFromDate(new Date()));
//        printDate(sjc2Date("1544189689013"));//1544189689013  1506787200000
        printDate(parseDateString("2017-10-01"));//1544189689013  1506787200000
        printDate(date2String(sjc2Date("1544190668586"),Constants.DATE_FORMAT_yyyy_MM_dd));
        printDate(date2String(sjc2Date(sjc2Date("1544190668586")),Constants.DATE_FORMAT_yyyy_MM_dd));
//        printDate(date2String(new Date(),Constants.DATE_FORMAT_yyyy_MM_dd));
    }

    /**
     * 解析成yyyy-MM-dd 的日期
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date parseDateString(String dateString) throws ParseException {
        return parseDateString(Constants.DATE_FORMAT_yyyy_MM_dd,dateString);
    }

    /**
     * 将日期字符串解析成日期 : Sun Oct 01 00:00:00 CST 2017 不带格式的日期是这样的
     * @return
     */
    public static Date parseDateString(String dateFormat,String dateString) throws ParseException {
        return new SimpleDateFormat(dateFormat).parse(dateString);
    }

    /**
     * 将时间戳转成日期格式 : Calendar:
     * @param sjc
     * @return
     */
    public static Date sjc2Date(String sjc) {
        Calendar cal = Calendar.getInstance();//获取实例
        long time = new Long(sjc);
        Date temp = new Date(time);
        cal.setTime(temp);
        return cal.getTime();
    }

    /**
     * 将日期时间戳转化成日期格式
     * @param sjc
     * @return
     */
    public static Date sjc2Date(Date sjc) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(sjc);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        return cal.getTime();
    }

    /**
     * 将日期格式化为字符串
     * @param date 日期
     * @param format 格式化模板 : YYYY-MM-DD 与 yyyy-MM-dd返回的值是不一样的
     * @return 返回格式化的字符串
     */
    public static String date2String(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String retDateString = simpleDateFormat.format(date);
        return retDateString;
    }

    /**
     * @param date
     * @return 获取日期的时间戳
     */
    public static long getSjcFromDate(Date date) {
        long retSjc = date.getTime();
        return retSjc;
    }

    /**
     * 得到现在时间
     * @return
     */
    public static Date getNow() {
        Date currentDate = new Date();
        return currentDate;
    }
    /**------------网上参考 自写api--------------------------**/
    //获取现在时间,这个好用  : yyyy-MM-dd HH:mm:ss
    public static Date getSqlDate() {
        Date sqlDate = new java.sql.Date(new Date().getTime());
        return sqlDate;
    }

    // 获取现在时间  返回长时间格式 yyyy-MM-dd HH:mm:ss
    public static Date getNowDate() {
        Date curTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(curTime);//2018-12-07 22:27:27
        ParsePosition pos = new ParsePosition(0);//用来标明从哪里开始解析 Fri Dec 07 22:28:09 CST 2018
        //0 : Fri Dec 07 22:25:49 CST 2018
        //1:  Wed Dec 07 22:26:24 CST 18
        Date currentTime2 = sdf.parse(dateString,pos);
        return currentTime2;//Fri Dec 07 22:25:49 CST 2018
    }
    // 获取现在时间  返回长时间格式 yyyy-MM-dd HH:mm:ss
    public static Date getNowDateShort() {
        Date curTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(curTime);//2018-12-07 22:27:27
        ParsePosition pos = new ParsePosition(0);//用来标明从哪里开始解析 Fri Dec 07 22:28:09 CST 2018
        //0 : Fri Dec 07 22:25:49 CST 2018
        //1:  Wed Dec 07 22:26:24 CST 18
        Date currentTime2 = sdf.parse(dateString,pos);
        return currentTime2;//Fri Dec 07 00:00:00 CST 2018
    }
    //获取现在时间  返回字符串格式 yyyy-MM-dd HH:mm:ss
    public static String getStringFromDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(date);
        return dateString;
    }
    //获取现在时间  返回短时间字符串格式yyyy-MM-dd
    public static String getStringFromDateShort() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        return dateString;
    }
    //获取时间 小时:分;秒 HH:mm:ss
    public static String getTimeShort() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String dateString = sdf.format(date);
        return dateString;
    }
    //将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strToDate = sdf.parse(strDate,pos);
        return strToDate;
    }
    //https://www.cnblogs.com/endtel/p/6018429.html
    @Test
    public void test02() {
        printDate(getNowDateShort());
        printDate(getStringFromDate());
        printDate(getStringFromDateShort());
        printDate(getTimeShort());
        printDate(strToDateLong("2018-12-07 22:32:57"));
    }

    @Test
    public void testParseOrFormate() {
        String str = "2013-01-21 15:10:20";
        Date date = null;
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(date);//Mon Jan 21 15:10:20 CST 2013
        System.out.println(date);//Mon Jan 21 00:00:00 CST 2013
        System.out.println(date.getTime());//1358752220000
        System.out.println(date.getTime());//1358697600000
    }
    /**--日期 2018-12-10 处理问题 日期显示--**/
    @Test
    public void test01() {
        printDate(date2String(sjc2Date(sjc2Date("1544190668586")),Constants.DATE_FORMAT_yyyy_MM_dd));
        printDate(date2String(sjc2Date("1544190668586"),Constants.DATE_FORMAT_yyyy_MM_dd));//
    }

    /**
     * 将日期增加 n 月
     * @param date
     * @return
     */
    public static Date str2DateAddMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH,n);//增加n个月
        return cal.getTime();
    }
    /**
     * 将日期增加 n 天: n  可以为12 ==》 那么超过12月后 会自动向年进位
     * @param date
     * @param n
     * @return
     */
    public static Date str2DateAddDays(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE,n);//增加n天
        return cal.getTime();
    }

    @Test
    public void test04() {
        System.out.println(date2String(new Date(),Constants.DATE_FORMAT_yyyy_MM_dd));//2018-12-11
        System.out.println(date2String(str2DateAddDays(new Date(),20),Constants.DATE_FORMAT_yyyy_MM_dd));//2019-11-11
    }

}

