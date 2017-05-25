package org.qydata.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jonhn on 2017/3/8.
 */
public class CalendarAssistTool {

    public static void main(String[] args) {
        System.out.println(getCurrentDateLastMonthFirstDay());
        System.out.println(getCurrentDateLastMonthEndDay());
    }


    /**
     * 取得当前时间的上一个月份第一天
     * @return
     */
    public static String getCurrentDateLastMonthFirstDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        String firstDay = sdf.format(calendar.getTime());
        return firstDay;
    }

    /**
     * 取得当前时间的上一个月份最后一天
     * @return
     */
    public static String getCurrentDateLastMonthEndDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,0);
        String lastDay  = sdf.format(calendar.getTime());
        return lastDay ;
    }


}
