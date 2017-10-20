package com.github.handioq.diberapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";

    public static String getStringDate(long timestamp) {
        return new SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
                .format(new Date(timestamp * 1000L));
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

    public static long getTimestamp(String date) {
        long timestamp = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm", Locale.getDefault());
            Date dateStr = sdf.parse(date);
            timestamp = dateStr.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timestamp;
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
