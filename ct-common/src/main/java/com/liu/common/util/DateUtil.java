package com.liu.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {
    /**
     * 将日期字符串按照指定格式解析为日期对象
     *
     * @param dateString
     * @param format
     * @return
     */
    public static Date parse(String dateString, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(dateString);
        return date;
    }


    /**
     * 将指定的日期咱找指定的格式解析为日期对象
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

}
