package com.yumo.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yumodev on 1/16/16.
 */
public class YmDateUtil {

    static final int MISEC_UINTS_SECOND = 1000;
    static final int SECOND_UINTS_MINUTE = 60;
    static final int MINUTE_UNINTS_HOUR = 60;
    static final int HOUR_UNINTS_DAY = 24;
    static final int SECOND_UINTS_HOUR = 60 * 60;
    static final int SECOND_UINTS_DAY = 24 * 60 * 60;

    /**
     * 格式化时间 ： 00：00
     * @param duration 时间间隔。 单位是毫秒
     * @return
     */
    public static String formatDuration(int duration) {
        String strTime = "00:00";
        do {
            if (duration <= 0) {
                break;
            }

            int second = duration / MISEC_UINTS_SECOND;
            if (second < SECOND_UINTS_MINUTE) {
                strTime = String.format(Locale.ENGLISH, "00:%02d", second);
                break;
            }

            if (second < SECOND_UINTS_HOUR) {
                strTime = String.format(Locale.ENGLISH, "%02d:%02d", second / MINUTE_UNINTS_HOUR, second % SECOND_UINTS_MINUTE);
                break;
            }

            if (second < SECOND_UINTS_DAY) {
                strTime = String.format(Locale.ENGLISH, "%02d:%02d:%02d",
                        second / SECOND_UINTS_HOUR,
                        (second % (SECOND_UINTS_HOUR)) / MINUTE_UNINTS_HOUR,
                        (second % (SECOND_UINTS_HOUR)) % SECOND_UINTS_MINUTE);
                break;
            }

            if (second > SECOND_UINTS_DAY) {
                strTime = second + "";
                break;
            }

        } while (false);

        return strTime;
    }

    public static String getStrFromTime(long time) {
        return new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.US).format(new Date(time));
    }

    public static String getStrDay() {
        Calendar now = Calendar.getInstance();
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(now.getTime());
    }


    public static int getYear(){
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR);
    }

    public static int getMonth(){
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.MONTH) + 1;
    }

    public static int getDay(){
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.DAY_OF_MONTH);
    }

}
