package com.example.farmerama.data.util;


import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Converters {

    @TypeConverter
    public static LocalDateTime fromLocalDateTime(String changedOn) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDate.parse(changedOn, myFormatObj).atStartOfDay();
        return dateTime;
    }
    @TypeConverter
    public static String fromLocalDateTime(LocalDateTime changedOn) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = changedOn.format(myFormatObj);
        return formattedDate;
    }
}
