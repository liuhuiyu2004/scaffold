package com.liuhuiyu.scaffold.utils;

import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-09-21 8:38
 */
@Log4j2
public class FileUtilTest extends TestCase {
    String file = "E:\\temp\\a.txt";
    String file2 = "E:\\temp\\a2.txt";
    String info = "测试录入";

    public void testWriteFile() {
        FileUtil.writeFile(this.file, this.info);
    }
    public void testWriteFile2() throws IOException {
        InputStream inputStream=new FileInputStream(file);
        FileUtil.writeFile(this.file2, inputStream);
    }
}