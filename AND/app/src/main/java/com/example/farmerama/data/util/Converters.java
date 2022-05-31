package com.example.farmerama.data.util;


import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converters {

    /*
    Converts the string to the start of the day in order to send the threshold modifications
     */
    @TypeConverter
    public static LocalDate fromString(String changedOn) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(changedOn, myFormatObj);
        return dateTime;
    }
    @TypeConverter
    public static String fromLocalDateTime(LocalDate changedOn) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = changedOn.format(myFormatObj);
        return formattedDate;
    }
}
