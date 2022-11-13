package utils;

import java.text.ParseException;

public class DateFormat {
    public static String format(java.util.Date date) {
        return new java.text.SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public static java.util.Date parse(String date) {
        try {
            return new java.text.SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            System.out.println("Formato de data incorreto em " + date);
            return null;
        }
    }
}
