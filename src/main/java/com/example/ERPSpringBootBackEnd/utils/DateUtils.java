package com.example.ERPSpringBootBackEnd.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class DateUtils {
    private static final String format = "yyyy-MM-dd";
    static Logger log = LoggerFactory.getLogger(DateUtils.class);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    public static Date getCurrentDate() {
        try {
            return dateFormat.parse(dateFormat.format(new Date()));
        } catch(ParseException e) {
            log.error(e.getLocalizedMessage());

            return null;
        }
    }

    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }

    public static Date parseDate(String dateString) {
        try {
            return dateFormat.parse(dateString);
        } catch(ParseException e) {
            log.error(e.getLocalizedMessage());

            return null;
        }
    }

    public static String getDateRangeString(Date from, Date to) {
        String toDateString = dateFormat.format(to);
        String fromDateString = dateFormat.format(from);

        return fromDateString + " - " + toDateString;
    }

    public static long getIntervalInDays(Date from, Date to) {
        return TimeUnit.MILLISECONDS.toDays(to.getTime() - from.getTime()) % 365;
    }

    public static Date convertToDate(String dateString) throws ParseException {

        return dateString ==  null ? null : dateFormat.parse(dateString);
    }
}
