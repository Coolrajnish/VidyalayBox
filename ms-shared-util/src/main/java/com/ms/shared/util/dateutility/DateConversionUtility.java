package com.til.shared.util.dateutility;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateConversionUtility {

    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";


    public static OffsetDateTime convertDateToOffsetDateTime(final Date date) {
        return date.toInstant().atOffset(ZoneOffset.UTC);
    }

    public static OffsetDateTime convertStringToOffsetDateTime(final String dateStr) {
        return OffsetDateTime.parse(dateStr);
    }

    public static String convertOffsetDateTimeToString(final OffsetDateTime offsetDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss Z");
        return offsetDateTime.format(dateTimeFormatter);
    }

    public static Date convertOffsetDateTimeToDate(final OffsetDateTime offsetDateTime) {
        return Date.from(offsetDateTime.toInstant());
    }

    public static String convertOffsetDateTimeToStringWithoutTimeZonFormat(final OffsetDateTime offsetDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MM_SS);
        return offsetDateTime.format(dateTimeFormatter);
    }

}
