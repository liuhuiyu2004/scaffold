package com.liuhuiyu.scaffold.utils.security;

import com.liuhuiyu.scaffold.utils.encoder.BASE64Encoder;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-24 9:06
 */
@Log4j2
public class RsaUtilTest extends TestCase {
    public void testAll() throws Exception {
        rsa("我是要加密的字段。");
        rsa("my string.");
        rsa("ab我要加密的数据。");
        rsa("abc123456我要加密的数据。");
        rsa("我要加密的数据。我要加密的数据。我要加密的数据。我要加密的数据。我要加密的数据。我要加密的数据。我要加密的数据。我要加密的数据。我要加密的数据。我要加密的数据。我要加密的数据。我要加密的数据。我要加密的数据。我要加密的数据。我要加密的数据。我要加密的数据。我要加密的数据。我要加密的数据。我要加密的数据。我要加密的数据。");
    }

    private void rsa(@NotNull String str) throws Exception {
        byte[] oBytes = str.getBytes();
        RsaUtil rsaUtil = new RsaUtil();
        String privateKey = rsaUtil.getPrivateKey();
        String publicKey = rsaUtil.getPublicKey();

        byte[] eBytes = rsaUtil.encryptByPublicKey(oBytes, publicKey);
        byte[] dBytes = rsaUtil.decryptByPrivateKey(eBytes, privateKey);
        String dString = new String(dBytes);
        log.info("-----------------------------------------------------------------");
//        log.info("privateKey:{}", privateKey);
//        log.info("publicKey:{}", publicKey);
        log.info("长度：{}；字节长度{};原始字符串:{}", str.length(),str.getBytes().length, str);
        log.info("长度：{}；加密后的字符串：{}", eBytes.length, new BASE64Encoder().encode(eBytes));
        log.info("字节长度：{}；解密字符串:{}", dBytes.length, dString);
    }
}