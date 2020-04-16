package com.zlc.shardingjdbcdemo.util;

import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <dl>
 * <dt>DateUtils</dt>
 * <dd>Description:时间工具类</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018-1-19</dd>
 * </dl>
 *
 * @author panyk
 */
public class DateUtils {
    public static String YYYY_MM_DD = "yyyy-MM-dd";
    public static String YYYYMMDD = "yyyyMMdd";
    public static String YYYYMM = "yyyyMM";
    public static String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    //用于存入redis时键
    public static String DD = "dd";
    public static String HH = "HH";
    public static String HHmm = "HHmm";
    //根据日期获得指定格式的字符串
    public static String getDateString(Date date, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    //保留整数
    public static double getDecimal(double num){
        DecimalFormat df = new DecimalFormat("#");
        return Double.parseDouble(df.format(num));
    }

    /**
     * 合法参数返回时间，非法参数返回null
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date getDate(String dateStr, String pattern){
        if(StringUtils.isBlank(dateStr)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            return null;//如果返回空，说明时间格式非法
        }
    }

    /**
     * yyyy-MM-dd格式解析，返回日期
     * @param dateStr
     * @return
     */
    public static Date getDate(String dateStr){
        return getDate(dateStr, YYYY_MM_DD);
    }

    /**
     * @Params : [date]
     * @Description : 获得前一天日期字符串
     * @Author : zlc
     */
    public static String getYesterDayString(String dateString){
        Date date = getDate(dateString,YYYYMMDD);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return getDateString(date,YYYYMMDD);
    }

    public static void main(String[] args) {
        /*Date date1 = DateUtils.getDate("2017-05-01", DateUtils.YYYY_MM_DD);
        System.out.println(date1);
        Date date2 = DateUtils.getDate("2017-05-56", DateUtils.YYYY_MM_DD);
        System.out.println(date2);
        Date date3 = DateUtils.getDate("", DateUtils.YYYY_MM_DD);
        System.out.println(date3);*/

        //System.out.println(getYesterDayString("20190301"));
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            list.add(i);
        }
        /*//0 1 2 3 4 5 6 7 8 10
        List<Integer> list2 = list.subList(0,3);
        System.out.println(list2);*/
        //List<List<Integer>> aa = ListUtils.partList(list,3);
        //System.out.println(aa);
    }
}
