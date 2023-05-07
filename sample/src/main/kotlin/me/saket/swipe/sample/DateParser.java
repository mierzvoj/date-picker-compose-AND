package me.saket.swipe.sample;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateParser {
    public DateParser(String date) {
            }

    public static String formatDate (String date) throws ParseException {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDate localDate = LocalDate.from(LocalDate.parse(date, myFormatObj).atStartOfDay());
        return localDate.toString();
    }

}

