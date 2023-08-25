package com.qst.system.util;

import java.util.Calendar;
import java.util.Date;

public class MyDateUtils {


    public  static Date change(Date date,String month){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH,Integer.parseInt(month));

        return cal.getTime();
    }
}
