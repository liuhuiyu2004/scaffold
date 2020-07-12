package com.liuhuiyu.scaffold.utils.repeat_wheel;

import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.LocalDateTime;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-12 9:04
 */
public class StringUtilTest extends TestCase {
    private final static Logger logger = LoggerFactory.getLogger(StringUtilTest.class);

    public void testReplace() {
        String text = "C:\\Program Files\\Java\\jdk1.8.0_91";
        String searchString = "C:\\Program Files\\";
        String replacement = "%basePath%";
        String lineSeparator = System.lineSeparator();
        String separator = File.separator;
        logger.debug(StringUtils.replace(text + lineSeparator + text + lineSeparator + text + lineSeparator + text, searchString, replacement, -1));
        logger.debug("separator = " + separator);

    }
}