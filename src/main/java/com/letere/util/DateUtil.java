package com.letere.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    /**
     * LocalDateTime转Date
     * @param localDateTime
     * @return
     */
    public static Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date转LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
