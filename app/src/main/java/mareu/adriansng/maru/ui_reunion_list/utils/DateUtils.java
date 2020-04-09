package mareu.adriansng.maru.ui_reunion_list.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    @SuppressLint("SimpleDateFormat")
    public static String formatDateData(String dateString) {
        SimpleDateFormat formatter, FORMATTER;
        Date date;
        String myDate = "";
        formatter = new SimpleDateFormat("M/d/yy");
        try {
            date = formatter.parse(dateString);
            FORMATTER = new SimpleDateFormat("dd MMMM yyyy");
            if (date != null) {
                myDate = FORMATTER.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myDate;
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatTimeLong(String dateString, String timeString) {
        SimpleDateFormat formatter, FORMATTER;
        Date date;
        String myDateAndTime = "";
        formatter = new SimpleDateFormat("M/d/yy'T'HH'H'mm");
        try {
            date = formatter.parse(dateString + "T" + timeString);
            FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            if (date != null) {
                myDateAndTime = FORMATTER.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myDateAndTime;
    }

    @SuppressLint("SimpleDateFormat")
    public static String addMinute(String dateTime, int nbMinute) {
        SimpleDateFormat formatter;
        Date date;
        String myDate = "";
        formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            date = formatter.parse(dateTime);
            Calendar cal = Calendar.getInstance();
            if (date != null) {
                cal.setTime(date);
            }
            cal.add(Calendar.MINUTE, nbMinute);
            myDate = formatter.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myDate;
    }
}
