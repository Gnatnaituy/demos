package com.ravooo.common.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public static String now() {
        return "[" + simpleDateFormat.format(new Date()) + "] ";
    }
}
