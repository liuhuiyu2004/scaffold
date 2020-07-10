package com.liuhuiyu.scaffold.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-09 9:58
 */
public class DateTimeUtilTest {
    private final static Logger logger = LoggerFactory.getLogger(DateTimeUtilTest.class);
    @Test
    public void testStringToLocalDateTime() {
        LocalDateTime localDateTime = DateTimeUtil.stringToLocalDateTime("2015-01-01");
    }
    @Test
    public void testStringToLocalDate() {
        LocalDate localDate = DateTimeUtil.stringToLocalDate("2015-01-01");
    }
    @Test
    public void testStringToLocalTime() {
        LocalTime localTime = DateTimeUtil.stringToLocalTime("2015-01-01");
    }
}
