package com.hongdu.src.date;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期处理相关工具类
 * @author Administrator
 *
 */
@Slf4j
public class DateUtil {

	public JSONObject request(String httpArg) {
		BufferedReader reader = null;
		String result = null;
		JSONObject jsonObjectResult = null;
		StringBuffer sbf = new StringBuffer();
		String httpUrl = "http://api.goseek.cn/Tools/holiday?date=" + httpArg;

		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();
			jsonObjectResult = JSONObject.fromObject(result);// 转为JSONObject对象
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObjectResult;
	}

	/**
	 * 在前端校验日期是否是当月最后一天 : 如果是最后一天 不能进行预约申请业务办理
	 * ==>这个可以在我要预约的功能里进行判断 判断可预约日是否顺延后
	 * 判断是否是节假日 每年会更新一次接口 所以可以直接调用接口进行判断是否是节假日
	 * 
	 * @param dateString
	 *            参数格式是 : 20181001
	 * @return
	 */
	public static boolean isJjr2(String dateString) {
		JSONObject obj = new DateUtil().request(dateString);
		if ("2".equals(String.valueOf(obj.get("data"))) || "1".equals(String.valueOf(obj.get("data")))) {// 是节假日或者周六末
			 // 税务局放假
			// 预约日子顺延后
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将字符串转成格式 yyyy-MM-dd的日期
	 * @param strDate
	 * @return
	 */
	public static Date str2Date(String strDate) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (strDate == null || "".equals(strDate)) {
			throw new IllegalArgumentException("非法的参数异常:" + strDate);
		}
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			log.error("日期格式不正确: ============== 解析错误" + e.getMessage());
		}
		return date;
	}

	/**
	 * 判断当前日期是星期几
	 * @param pTime
	 * @return 返回的是数字 : 原来 0 ==》星期日   6==》星期六  1是1 2是2 3是3 4是4 5是5
	 *
	 * @throws Exception
	 */
	public static int dayOfWeek(String pTime) throws Exception {
		int dayForWeek = 0;
		if(pTime.length() == 8) {
			String year = pTime.substring(0,4);
			String month = pTime.substring(4,6);
			String day = pTime.substring(6,8);
			pTime = year + "-" + month + "-" + day;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 判断日期是否是休息日
	 * @param date 20181001 类似这个格式
	 * @return
	 */
	public static boolean isJjr(String date) {
		//先判断日期是否在字符串数组中
		List<String> holidayList = Arrays.asList(Constants.holidays);
		boolean flag = false;
		if(holidayList.contains(date)) {
			flag = true;//是法定节假日
		} else {
			List<String> fdgzrList = Arrays.asList(Constants.fdGzr);
			if(!fdgzrList.contains(date)){//非法定工作日情况下,如果是休息日,返回true
				//判断日期返回值
				try {
					int xq = dayOfWeek(date);
					if(xq == 6 || xq == 7) {
						return true;
					}
				} catch (Exception e) {
					log.error("日期格式不合法");
				}
			}
		}
		return flag;
	}

	/**
	 * 将日期字符串 转为 long型时间戳
	 * @param s
	 * @return
	 */
	public static String dateToStamp(String s){
		String res = "";
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = simpleDateFormat.parse(s);
			long ts = date.getTime();
			res = String.valueOf(ts);
		} catch (ParseException e) {
			log.error("转换日期格式字符异常=============== " + e.getMessage() + "===========" + s);
		}
		return res;
	}
}
