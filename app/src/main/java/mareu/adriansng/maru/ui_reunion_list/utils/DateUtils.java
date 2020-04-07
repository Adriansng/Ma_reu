package mareu.adriansng.maru.ui_reunion_list.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
     private static final String TAG= "DateUtils";

     public static String formatDateDataFromDateLong(String dateString){
         SimpleDateFormat formatter, FORMATTER;
         Date date= null;
         String myDate="";
         formatter= new SimpleDateFormat("MM/dd/aaaa");
         try{
             date= formatter.parse(dateString);
             FORMATTER=new SimpleDateFormat("dd MMMM aaaa");
             myDate= FORMATTER.format(date);
         } catch (ParseException e) {
             e.printStackTrace();
         }
         return myDate;
     }

    public static String formatDateVeryLong(String dateString){
        SimpleDateFormat formatter, FORMATTER;
        Date date= null;
        String myDate="";
        formatter= new SimpleDateFormat("MM/jj/aaaa");
        try{
            date= formatter.parse(dateString);
            FORMATTER=new SimpleDateFormat("jj MMMM aaaa");
            myDate= FORMATTER.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myDate;
    }

}
