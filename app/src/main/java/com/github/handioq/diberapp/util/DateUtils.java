package com.github.handioq.diberapp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";

    public static String getStringDateFromTimestamp(long longDate) {
        return new SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
                .format(new Date(longDate * 1000L));
    }

    public static String getStringDate(int year, int month, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);
        Date date = calendar.getTime();

        return sdf.format(date);
    }

    public static String getStringTime(int hourOfDay, int minute) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        Date date = calendar.getTime();

        return sdf.format(date);
    }

    public static Date localTimeToUTC(Date date) {
        // todo
        return null;
    }

    public static Date utcToLocalDate(Date date) {
        String timeZone = Calendar.getInstance().getTimeZone().getID();
        return new Date(date.getTime() + TimeZone.getTimeZone(timeZone).getOffset(date.getTime()));
    }

}
