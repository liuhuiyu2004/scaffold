package com.liuhuiyu.scaffold.utils;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-09 9:51
 */
public class DateTimeUtil {
    private final static Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);
    private static final String[] DATE_FORMAT = new String[]{"yyyy-MM-dd", "yyyy-M-d", "yyyy/MM/dd", "yyyy/M/d"};
    private static final String[] TIME_FORMAT = new String[]{"HH:mm:ss", "H:m:s"};

    /**
     * 将字符串转换成 LocalDateTime
     *
     * @param value 字符串
     * @return LocalDateTime
     */
    public static @NotNull LocalDateTime stringToLocalDateTime(@NotNull String value) {
        DateTimeFormatter df;
        char timeDelimiter = ':';
        int timeMaxLength = 8;
        int dateMaxLength = 10;
        if (value.length() <= timeMaxLength && value.indexOf(timeDelimiter) > 0) {
            //时间
            for (String format : TIME_FORMAT) {
                df = DateTimeFormatter.ofPattern("yyyy-MM-dd " + format);
                try {
                    return LocalDateTime.parse("0000-00-00" + value, df);
                } catch (DateTimeParseException e) {
                    logger.debug(e.toString());
                }
            }
        } else if (value.length() <= dateMaxLength) {
            //日期
            for (String format : DATE_FORMAT) {
                df = DateTimeFormatter.ofPattern(format + " HH:mm:ss");
                try {
                    return LocalDateTime.parse(value + " 00:00:00", df);
                } catch (DateTimeParseException e) {
                    logger.debug(e.toString());
                }
            }
        } else {
            //日期时间
            for (String dFormat : DATE_FORMAT) {
                for (String tFormat : TIME_FORMAT) {
                    df = DateTimeFormatter.ofPattern(dFormat + " " + tFormat);
                    try {
                        return LocalDateTime.parse(value, df);
                    } catch (DateTimeParseException e) {
                        logger.debug(e.toString());
                    }
                }
            }
        }
        throw new DateTimeParseException("参数无效。", value, 0);
    }

    /**
     * 将字符串转换成 LocalDate
     *
     * @param value 字符串
     * @return LocalDate
     */
    public static @NotNull LocalDate stringToLocalDate(String value) {
        return stringToLocalDateTime(value).toLocalDate();
    }

    /**
     * 将字符串转换成 LocalTime
     *
     * @param value 字符串
     * @return LocalTime
     */
    public static @NotNull LocalTime stringToLocalTime(String value) {
        return stringToLocalDateTime(value).toLocalTime();
    }

    /**
     * LocalDateTime 转换为毫秒
     *
     * @param dateTime LocalDateTime
     * @return 毫秒
     */
    public static @NotNull Long localDateTimeToMilli(@NotNull LocalDateTime dateTime) {
        ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
        Instant instant = dateTime.toInstant(zoneOffset);
        return instant.toEpochMilli();
    }

    /**
     * LocalDate 转换为毫秒
     *
     * @param date LocalDate
     * @return 毫秒
     */
    public static @NotNull Long localDateToMilli(@NotNull LocalDate date) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = date.atStartOfDay().atZone(zone).toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTimeToMilli(localDateTime);
    }

    /**
     * LocalTime 转换为毫秒
     *
     * @param time LocalTime
     * @return 毫秒
     */
    public static @NotNull Long localTimeToMilli(@NotNull LocalTime time) {
        return time.getLong(ChronoField.MILLI_OF_DAY);
    }
}
