package com.liuhuiyu.scaffold.utils;

import junit.framework.TestCase;

import java.io.IOException;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-08-06 8:19
 */
public class BytesUtilTest extends TestCase {
    public void fileToBytes() throws IOException {
        String file = "d:\\a.txt";
        byte[] bytes = BytesUtil.fileToBytes(file);

    }
}