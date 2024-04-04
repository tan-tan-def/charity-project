package com.assignment.funix.assignment01.Date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Date {
    public static String date(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }
}
