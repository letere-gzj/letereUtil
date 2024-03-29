package com.letere.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    public static class DateTime {
        private final LocalDateTime localDateTime;

        public DateTime(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
        }

        public DateTime(Date date) {
            this.localDateTime = toLocalDateTime(date);
        }

        public Date getDate() {
            return toDate(localDateTime);
        }

        public LocalDateTime getLocalDateTime() {
            return localDateTime;
        }
    }

    /**
     * LocalDateTime转Date
     * @param localDateTime 时间
     * @return 时间(Date类型)
     */
    public static Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date转LocalDateTime
     * @param date 时间
     * @return 时间(LocalDateTime类型)
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 获取当天的开始时间
     * @param localDateTime 时间
     * @return 当日起始时间
     */
    public static DateTime beginOfDay(LocalDateTime localDateTime) {
        return new DateTime(localDateTime.withHour(0).withMinute(0).withSecond(0).withNano(0));
    }

    /**
     * 获取当天的开始时间（重载）
     * @param date 时间
     * @return 当日起始时间
     */
    public static DateTime beginOfDay(Date date) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        return beginOfDay(localDateTime);
    }

    /**
     * 获取当周开始时间
     * @param localDateTime 时间
     * @return 当周起始时间
     */
    public static DateTime beginOfWeek(LocalDateTime localDateTime) {
        localDateTime = localDateTime.withHour(0).withMinute(0).withSecond(0);
        localDateTime = localDateTime.plusDays(-localDateTime.getDayOfWeek().getValue() + 1);
        return new DateTime(localDateTime);
    }

    /**
     * 获取当周开始时间（重载）
     * @param date 时间
     * @return 当周起始时间
     */
    public static DateTime beginOfWeek(Date date) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        return beginOfWeek(localDateTime);
    }

    /**
     * 获取当月开始时间
     * @param localDateTime 时间
     * @return 当月起始时间
     */
    public static DateTime beginOfMonth(LocalDateTime localDateTime) {
        localDateTime = localDateTime.withHour(0).withMinute(0).withSecond(0).withDayOfMonth(1);
        return new DateTime(localDateTime);
    }

    /**
     * 获取当月开始时间（重载）
     * @param date 时间
     * @return 当月起始时间
     */
    public static DateTime beginOfMonth(Date date) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        return beginOfMonth(localDateTime);
    }
}
