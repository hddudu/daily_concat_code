package com.hongdu.src.date;

/**
 * https://www.jianshu.com/p/6ef54da8932e
 *
 * Calendar类:
 * Calendar类的成员变量:
 * 这里以下所说的get,set方法指的是instance的get,set方法
 * static int  ALL_STYLES
 *           指示所有风格名称的 getDisplayNames 的风格说明符，比如 "January" 和 "Jan"。
 *
 * static int  AM
 *           指示从午夜到中午之前这段时间的 AM_PM 字段值。
 *
 * static int  AM_PM
 *           get 和 set 的字段数字，指示 HOUR 是在中午之前还是在中午之后。
 *
 * static int  APRIL
 *           指示在格里高利历和罗马儒略历中一年中第四个月的 MONTH 字段值。
 *
 * protected  boolean  areFieldsSet
 *           如果 fields[] 与当前的设置时间同步，则返回 true。
 *
 * static int  AUGUST
 *           指示在格里高利历和罗马儒略历中一年中第八个月的 MONTH 字段值。
 *
 * static int  DATE
 *           get 和 set 的字段数字，指示一个月中的某天。
 *
 * static int  DAY_OF_MONTH
 *           get 和 set 的字段数字，指示一个月中的某天。
 *
 * static int  DAY_OF_WEEK
 *           get 和 set 的字段数字，指示一个星期中的某天。
 *
 * static int  DAY_OF_WEEK_IN_MONTH
 *           get 和 set 的字段数字，指示当前月中的第几个星期。
 *
 * static int  DAY_OF_YEAR
 *           get 和 set 的字段数字，指示当前年中的天数。
 *
 * static int  DECEMBER
 *           指示在格里高利历和罗马儒略历中一年中第十二个月的 MONTH 字段值。
 *
 * static int  DST_OFFSET
 *           get 和 set 的字段数字，以毫秒为单位指示夏令时的偏移量。
 *
 * static int  ERA
 *           指示年代的 get 和 set 的字段数字，比如罗马儒略历中的 AD 或 BC。
 *
 * static int  FEBRUARY
 *           指示在格里高利历和罗马儒略历中一年中第二个月的 MONTH 字段值。
 *
 * static int  FIELD_COUNT
 *           get 和 set 可识别的不同字段的数量。
 *
 * protected  int[]    fields
 *           此日历当前设置时间的日历字段值。
 *
 * static int  FRIDAY
 *           指示 Friday 的 DAY_OF_WEEK 字段值。
 *
 * static int  HOUR
 *           get 和 set 的字段数字，指示上午或下午的小时。
 *
 * static int  HOUR_OF_DAY
 *           get 和 set 的字段数字，指示一天中的小时。
 *
 * protected  boolean[]    isSet
 *           通知是否设置了该日历某一指定日历字段的标志。
 *
 * protected  boolean  isTimeSet
 *           如果 time 值是一个有效值，则返回 true。
 *
 * static int  JANUARY
 *           指示在格里高利历和罗马儒略历中一年中第一个月的 MONTH 字段值。
 *
 * static int  JULY
 *           指示在格里高利历和罗马儒略历中一年中第七个月的 MONTH 字段值。
 *
 * static int  JUNE
 *           指示在格里高利历和罗马儒略历中一年中第六个月的 MONTH 字段值。
 *
 * static int  LONG
 *           指示长名称的 getDisplayName 和 getDisplayNames 的风格说明符，比如 "January"。
 *
 * static int  MARCH
 *           指示在格里高利历和罗马儒略历中一年中第三个月的 MONTH 字段值。
 *
 * static int  MAY
 *           指示在格里高利历和罗马儒略历中一年中第五个月的 MONTH 字段值。
 *
 * static int  MILLISECOND
 *           get 和 set 的字段数字，指示一秒中的毫秒。
 *
 * static int  MINUTE
 *           get 和 set 的字段数字，指示一小时中的分钟。
 *
 * static int  MONDAY
 *           指示 Monday 的 DAY_OF_WEEK 字段值。
 *
 * static int  MONTH
 *           指示月份的 get 和 set 的字段数字。"这里有非常需要注意的一点,这里显示的月份是由0开始的"
 *
 * static int  NOVEMBER
 *           指示在格里高利历和罗马儒略历中一年中第十一个月的 MONTH 字段值。
 *
 * static int  OCTOBER
 *           指示在格里高利历和罗马儒略历中一年中第十个月的 MONTH 字段值。
 *
 * static int  PM
 *           指示从中午到午夜之前这段时间的 AM_PM 字段值。
 *
 * static int  SATURDAY
 *           指示 Saturday 的 DAY_OF_WEEK 字段值。
 *
 * static int  SECOND
 *           get 和 set 的字段数字，指示一分钟中的秒。
 *
 * static int  SEPTEMBER
 *           指示在格里高利历和罗马儒略历中一年中第九个月的 MONTH 字段值。
 *
 * static int  SHORT
 *           指示短名称的 getDisplayName 和 getDisplayNames 的风格说明符，比如 "Jan"。
 *
 * static int  SUNDAY
 *           指示 Sunday 的 DAY_OF_WEEK 字段值。
 *
 * static int  THURSDAY
 *           指示 Thursday 的 DAY_OF_WEEK 字段值。
 *
 * protected  long time
 *           日历的当前设置时间，以毫秒为单位，表示自格林威治标准时间 1970 年 1月 1 日 0:00:00 后经过的时间。
 *
 * static int  TUESDAY
 *           指示 Tuesday 的 DAY_OF_WEEK 字段值。
 *
 * static int  UNDECIMBER
 *           指示一年中第十三个月的 MONTH 字段值。
 *
 * static int  WEDNESDAY
 *           指示 Wednesday 的 DAY_OF_WEEK 字段值。
 *
 * static int  WEEK_OF_MONTH
 *           get 和 set 的字段数字，指示当前月中的星期数。
 *
 * static int  WEEK_OF_YEAR
 *           get 和 set 的字段数字，指示当前年中的星期数。
 *
 * static int  YEAR
 *           指示年的 get 和 set 的字段数字。
 *
 * static int  ZONE_OFFSET
 *           get 和 set 的字段数字，以毫秒为单位指示距 GMT 的大致偏移量。
 *
 *
 *           ** Calendar类的方法: **
 *  abstract void  add(int field, int amount)
 *           根据日历的规则，为给定的日历字段添加或减去指定的时间量。
 *  boolean    after(Object when)
 *           判断此 Calendar 表示的时间是否在指定 Object 表示的时间之后，返回判断结果。
 *  boolean    before(Object when)
 *           判断此 Calendar 表示的时间是否在指定 Object 表示的时间之前，返回判断结果。
 *  void   clear()
 *           将此 Calendar 的所日历字段值和时间值（从历元至现在的毫秒偏移量）设置成未定义。
 *  void   clear(int field)
 *           将此 Calendar 的给定日历字段值和时间值（从历元至现在的毫秒偏移量）设置成未定义。
 *  Object clone()
 *           创建并返回此对象的一个副本。
 *  int    compareTo(Calendar anotherCalendar)
 *           比较两个 Calendar 对象表示的时间值（从历元至现在的毫秒偏移量）。"此函数返回值为三个,分别为-1,0,1;-1指的是当前Calendar比anotherCalendar的时间早,0指的是时间相同,1指的是比anotherCalendar时间晚"
 *  boolean    equals(Object obj)
 *           将此 Calendar 与指定 Object 比较。
 *  int    get(int field)
 *           返回给定日历字段的值。
 *  int    getActualMaximum(int field)
 *           给定此 Calendar 的时间值，返回指定日历字段可能拥有的最大值。
 *  int    getActualMinimum(int field)
 *           给定此 Calendar 的时间值，返回指定日历字段可能拥有的最小值。
 * static Locale[] getAvailableLocales()
 *           返回所有语言环境的数组，此类的 getInstance 方法可以为其返回本地化的实例。
 *  String getDisplayName(int field, int style, Locale locale)
 *           返回给定 style 和 locale 下的日历 field 值的字符串表示形式。
 *  Map<String,Integer>    getDisplayNames(int field, int style, Locale locale)
 *           返回给定 style 和 locale 下包含日历 field 所有名称的 Map 及其相应字段值。
 *  int    getFirstDayOfWeek()
 *           获取一星期的第一天；例如，在美国，这一天是 SUNDAY，而在法国，这一天是 MONDAY。
 * abstract  int   getGreatestMinimum(int field)
 *           返回此 Calendar 实例给定日历字段的最高的最小值。
 * static Calendar getInstance()
 *           使用默认时区和语言环境获得一个日历。
 * static Calendar getInstance(Locale aLocale)
 *           使用默认时区和指定语言环境获得一个日历。
 * static Calendar getInstance(TimeZone zone)
 *           使用指定时区和默认语言环境获得一个日历。
 * static Calendar getInstance(TimeZone zone, Locale aLocale)
 *           使用指定时区和语言环境获得一个日历。
 * abstract  int   getLeastMaximum(int field)
 *           返回此 Calendar 实例给定日历字段的最低的最大值。
 * abstract  int   getMaximum(int field)
 *           返回此 Calendar 实例给定日历字段的最大值。
 *  int    getMinimalDaysInFirstWeek()
 *           获取一年中第一个星期所需的最少天数，例如，如果定义第一个星期包含一年第一个月的第一天，则此方法将返回 1。
 * abstract  int   getMinimum(int field)
 *           返回此 Calendar 实例给定日历字段的最小值。
 *  Date   getTime()
 *           返回一个表示此 Calendar 时间值（从历元至现在的毫秒偏移量）的 Date 对象。
 *  long   getTimeInMillis()
 *           返回此 Calendar 的时间值，以毫秒为单位。
 *  TimeZone   getTimeZone()
 *           获得时区。
 *  int    hashCode()
 *           返回该此日历的哈希码。
 *  boolean    isLenient()
 *           判断日期/时间的解释是否为宽松的。
 *  boolean    isSet(int field)
 *           确定给定日历字段是否已经设置了一个值，其中包括因为调用 get 方法触发内部字段计算而导致已经设置该值的情况。
 * abstract  void  roll(int field, boolean up)
 *           在给定的时间字段上添加或减去（上/下）单个时间单元，不更改更大的字段。
 *  void   roll(int field, int amount)
 *           向指定日历字段添加指定（有符号的）时间量，不更改更大的字段。
 *  void   set(int field, int value)
 *           将给定的日历字段设置为给定值。
 *  void   set(int year, int month, int date)
 *           设置日历字段 YEAR、MONTH 和 DAY_OF_MONTH 的值。
 *  void   set(int year, int month, int date, int hourOfDay, int minute)
 *           设置日历字段 YEAR、MONTH、DAY_OF_MONTH、HOUR_OF_DAY 和 MINUTE 的值。
 *  void   set(int year, int month, int date, int hourOfDay, int minute, int second)
 *           设置字段 YEAR、MONTH、DAY_OF_MONTH、HOUR、MINUTE 和 SECOND 的值。
 *  void   setFirstDayOfWeek(int value)
 *           设置一星期的第一天是哪一天；例如，在美国，这一天是 SUNDAY，而在法国，这一天是 MONDAY。
 *  void   setLenient(boolean lenient)
 *           指定日期/时间解释是否是宽松的。
 *  void   setMinimalDaysInFirstWeek(int value)
 *           设置一年中第一个星期所需的最少天数，例如，如果定义第一个星期包含一年第一个月的第一天，则使用值 1 调用此方法。
 *  void   setTime(Date date)
 *           使用给定的 Date 设置此 Calendar 的时间。
 *  void   setTimeInMillis(long millis)
 *           用给定的 long 值设置此 Calendar 的当前时间值。
 *  void   setTimeZone(TimeZone value)
 *           使用给定的时区值来设置时区。
 *  String toString()
 *           返回此日历的字符串表示形式。
 *
 *==========================================================转化总结=====================
 * a、第一步：
 * 告诉系统你的String的时间，是形如哪种格式的；
 * 这里String转Date格式，是非常容易出错的，因为这里的String格式，必须要是要是形如‘2017-6-21’或者加上时间的‘2017年6月21日11:08’这种格式，具体哪种格式，你要在前面声明一个SimpleDateFormat来告诉系统，这样他才会按照你的格式，给你拆分；
 *
 *         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
 *         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
 *         SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd");
 *         SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
 *
 *         b、第二步：
 * 解析String类型的时间；
 * 这里会报错，如果给你的不是形如yyyy-MM-dd,如果是随便的一个字符串，或者是解析的SimpleDateFormat不一致，就会报错：
 *
 * 因为如果你传入的是String类型时间，要解析成Date格式；
 *
 * 如果②的类型比①精确，就会报错：“Unparseable date”;
 * ①String：“yyyy-MM-dd”
 * ②SimpleDateFormat：“yyyy-MM-dd HH:mm:ss”;
 *
 * 如果是①的格式比②精确，就没有问题；
 * ①String：“yyyy-MM-dd HH:mm:ss”
 * ②SimpleDateFormat：“yyyy-MM-dd”;
 * 但是，系统会自动把你没有的部分默认为0；这里图中11：22就变成11：00了；
 *
 * ====================================================================总结2========================
 * 1.Calendar 转化 String
 * Calendar calendat = Calendar.getInstance();
 * SimpleDateFormat sdf = new SimpleDateFormat(“yyyy-MM-dd”);
 * String dateStr = sdf.format(calendar.getTime());
 *
 * 2.String 转化Calendar
 * String str=”2012-5-27”;
 * SimpleDateFormat sdf= new SimpleDateFormat(“yyyy-MM-dd”);
 * Date date =sdf.parse(str);
 * Calendar calendar = Calendar.getInstance();
 * calendar.setTime(date);
 *
 * 3.Date 转化String
 * SimpleDateFormat sdf= new SimpleDateFormat(“yyyy-MM-dd”);
 * String dateStr=sdf.format(new Date());
 *
 * 4.String 转化Date
 * String str=”2012-5-27”;
 * SimpleDateFormat sdf= new SimpleDateFormat(“yyyy-MM-dd”);
 * Date date= sdf.parse(str);
 *
 * 5.Date 转化Calendar
 * Calendar calendar = Calendar.getInstance();
 * calendar.setTime(new Java.util.Date());
 *
 * 6.Calendar转化Date
 * Calendar calendar = Calendar.getInstance();
 * java.util.Date date =calendar.getTime();
 *
 * 7.String 转成 Timestamp
 * Timestamp ts = Timestamp.valueOf(“2012-1-14 08:11:00”);
 *
 * 8.Date 转 TimeStamp
 * SimpleDateFormat df = new SimpleDateFormat(“yyyy-MM-dd HH:mm:ss”);
 * String time = df.format(new Date());
 * Timestamp ts = Timestamp.valueOf(time);
 *
 */
public class Constants {
    public static final String DATE_FORMAT_YYYY_MM_DD = "YYYY-MM-DD";
    public static final String DATE_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "YYYY-MM-DD HH:MM:SS";
    public static final String DATE_FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";


    public static final String[] weekDay = {"","星期一","星期二","星期三","星期四","星期五","星期六","星期日",};

    /**
     * 2018 法定节假日 : 新增2019
     */
    public static final String[] holidays = {
            "20180101","20180215","20180216","20180217","20180218","20180219","20180220","20180221",
            "20180405","20180406","20180407","20180429","20180430","20180501",
            "20180616","20180617","20180618","20180922","20180923","20180924",
            "20181001","20181002","20181003","20181004","20181005","20181006","20181007","20181230","20181231",
            "20190101","20190204","20190205","20190206","20190207","20190208","20190209","20190210",
            "20190405","20190406","20190407","20190501","20190607","20190608","20190609",
            "20190913","20190914","20190915",
            "20191001","20191002","20191003","20191004","20191005","20191006","20191007"
    };

    /**
     * 2018 法定工作日 : 新增2019
     */
    public  static final String[] fdGzr = {"20180211","20180224","20180408","20180428","20180929","20180930","20181229"
            ,"20190202","20190203","20190929","20191014"};
}
