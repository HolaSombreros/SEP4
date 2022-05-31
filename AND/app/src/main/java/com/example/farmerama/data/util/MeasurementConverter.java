package com.example.farmerama.data.util;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MeasurementConverter {

    @TypeConverter
    public static LocalDateTime fromStringMeasurement(String date) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, myFormatObj);
        return dateTime;
    }
    @TypeConverter
    public static String fromLocalDateTimeMeasurement(LocalDateTime date) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = date.format(myFormatObj);
        return formattedDate;
    }
}
