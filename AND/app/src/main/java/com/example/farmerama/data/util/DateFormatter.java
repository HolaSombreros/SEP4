package com.example.farmerama.data.util;

import static java.time.LocalDateTime.parse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
    /**
     *
     * Converts the string date into a proper format so that no "T" is encapsulated
     * @param measuredDate
     * @return
     */
    public static String formatDate(String measuredDate) {
        LocalDateTime datetime = parse(measuredDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = datetime.format(myFormatObj);
        return formattedDate;
    }

}
