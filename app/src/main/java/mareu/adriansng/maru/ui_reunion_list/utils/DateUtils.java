package mareu.adriansng.maru.ui_reunion_list.utils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
     private static final String TAG= "DateUtils";

     public static final String DATE_FORMAT_1="hh'H'mm";
     public static final String DATE_FORMAT_2="dd-MMMM-yyyy";
     public static final String DATE_FORMAT_3="hh'H'mm a dd MMMM yyyy";

     public static String getCurrentDate() {
         SimpleDateFormat dateFormat= new SimpleDateFormat(DATE_FORMAT_1);

         dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
         Date today= Calendar.getInstance().getTime();
         return dateFormat.format(today);
     }

     public static  String getCurrentTime() {
         SimpleDateFormat dateFormat= new SimpleDateFormat(DATE_FORMAT_1);

         dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
         Date today= Calendar.getInstance().getTime();
         return dateFormat.format(today);
     }

     public static String getDateTimeFromTimeStamp(Long time, String mDateFormat) {
         SimpleDateFormat dateFormat= new SimpleDateFormat(mDateFormat);
         dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
         Date dateTime= new Date(time);
         return dateFormat.format(dateTime);
     }

     public static long getTimeStampFromDateTime(String mDateTime, String mDateFormat)
         throws ParseException{
         SimpleDateFormat dateFormat= new SimpleDateFormat(mDateFormat);
         dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
         Date date= dateFormat.parse(mDateTime);
         return date.getTime();
     }

     public static String formatDateTimeFromDate(String mDateFormat, Date date) {
         if (date==null) {
             return  null;
         }
         return  DateFormat.format(mDateFormat, date).toString();
     }

     public static String formatDateFromDateString(String inputDateFormat,String outputDateFormat, String inputDate)
         throws ParseException{
         Date mParsedDate;
         String mOutputDateString;
         SimpleDateFormat mInputDateFormat= new SimpleDateFormat(inputDateFormat,java.util.Locale.getDefault());
         SimpleDateFormat mOutputDateFormat= new SimpleDateFormat(outputDateFormat,java.util.Locale.getDefault());
         mParsedDate= mInputDateFormat.parse(inputDate);
         mOutputDateString= mOutputDateFormat.format(mParsedDate);
         return  mOutputDateString;
         }


}
