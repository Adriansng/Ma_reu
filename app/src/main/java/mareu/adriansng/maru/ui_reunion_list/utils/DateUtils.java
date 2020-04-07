package mareu.adriansng.maru.ui_reunion_list.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
     private static final String TAG= "DateUtils";

     public static String formatDateData(String dateString){
         SimpleDateFormat formatter, FORMATTER;
         Date date= null;
         String myDate="";
         formatter= new SimpleDateFormat("M/d/yy");
         try{
             date= formatter.parse(dateString);
             FORMATTER=new SimpleDateFormat("dd MMMM yyyy");
             myDate= FORMATTER.format(date);
         } catch (ParseException e) {
             e.printStackTrace();
         }
         return myDate;
     }

    public static String formatDateLong(String dateString, String timeString){
        SimpleDateFormat formatter, FORMATTER;
        Date date= null;
        String myDateAndTime="";
        formatter= new SimpleDateFormat("M/d/yy'T'HH'H'mm");
        try{
            date= formatter.parse(dateString+"T"+timeString);
            FORMATTER=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            myDateAndTime= FORMATTER.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myDateAndTime;
    }

   public static String addMinute(String dateTime, int nbMinute) {
       SimpleDateFormat formatter;
       Date date= null;
       String myDate="";
       formatter= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
       try{
           date=formatter.parse(dateTime);
           Calendar cal = Calendar.getInstance();
           cal.setTime(date);
           cal.add(Calendar.MINUTE,nbMinute);
           myDate=formatter.format(cal.getTime());
       } catch (ParseException e) {
           e.printStackTrace();
       }

       return myDate;
   }
}
