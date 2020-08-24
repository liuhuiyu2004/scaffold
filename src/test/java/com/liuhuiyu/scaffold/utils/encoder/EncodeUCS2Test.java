package com.liuhuiyu.scaffold.utils.encoder;

import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;

import java.io.UnsupportedEncodingException;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-08-24 8:37
 */
@Log4j2
public class EncodeUCS2Test extends TestCase {

    public void testDecodeUCS2() {

    }

    public void testEncodeUCS2(){
        String str="大家好";
        log.debug("编码后："+str);
        String str1 = EncodeUCS2.encodeUCS2(str);
        log.debug("编码后："+str1);
        String str2 = EncodeUCS2.decodeUCS2(str1);
        log.debug("解码后："+str2);
    }
}