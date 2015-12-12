package com.lclizhao.sharebook.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lizhaoz on 2015/12/9.
 */

public class DateUtils {
    public static String dateToStringByFile(Date date) {
        String fDate=new SimpleDateFormat("/yyyy/MM/dd/").format(date);
        return fDate;
    }
}
