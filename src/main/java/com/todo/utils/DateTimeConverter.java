package com.todo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class DateTimeConverter {
    
    private static final String INCOMING_FORMATE = "dd/MM/yyyy hh:mm:ss";
    private static final String APP_FORMATE = "MM/dd/yyyy @ hh:mm a";
    
    public String calenderToString(Calendar createdDate) {
        if (createdDate != null) {
            Date date = createdDate.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat(APP_FORMATE);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.get(Calendar.YEAR);
            c.get(Calendar.MONTH);
            c.get(Calendar.DATE);
            return sdf.format(c.getTime());
        }
        return null;
    }

    public Calendar stringToCalender(String createdDate) {
        DateFormat formatter;
        formatter = new SimpleDateFormat(INCOMING_FORMATE);
        try {
            Date date1 = (Date) formatter.parse(createdDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
            return cal;
        } catch (ParseException ex) {
            System.out.println("ERROR : "+ex.getMessage());
        }
        return null;
    }
    
}
