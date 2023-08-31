package com.jd.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter implements Converter<String, Date> {
    // 要转换的目标日期类型的格式
    private static final String DATE = "yyyy-MM-dd";
    private static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    private static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";

    @Override
    public Date convert(String s) {
        // 把表单的string类型转换为目标date类型
        return toDate(s);
    }

    /**
     * 将字符串转换为对应格式的Date对象
     *
     * @param value 被转换的值
     * @return Date对象
     */
    private static Date toDate(String value) {
        // 默认返回值为null【也可以为当前系统时间，有业务确定使用那种默认值】
        Date date = null;
        if (value != null && !"".equals(value)) {

            // 判断被转换的对象值是不是String类型
            // 获取转换的对象的字符串
            String dateValue = value.trim();
            // 被转换字符串的长度
            int length = dateValue.length();
            // 判断被转换的类型是不是时间类型
            try {
                DateFormat formatter = null;
                // 转换为“yyyy-MM-dd”格式的Date对象
                if (length <= 10) {
                    formatter = new SimpleDateFormat(DATE, new DateFormatSymbols(Locale.CHINA));
                    date = formatter.parse(dateValue);
                }
                // 转换为“yyyy-MM-dd HH:mm:ss”格式的Date对象
                else if (length <= 19) {
                    formatter = new SimpleDateFormat(DATETIME, new DateFormatSymbols(Locale.CHINA));
                    date = formatter.parse(dateValue);
                }
                // 转换为“yyyy-MM-dd HH:mm:ss.SSS”格式的Date对象
                else if (length <= 23) {
                    formatter = new SimpleDateFormat(TIMESTAMP, new DateFormatSymbols(Locale.CHINA));
                    date = formatter.parse(dateValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return date;
    }

}
