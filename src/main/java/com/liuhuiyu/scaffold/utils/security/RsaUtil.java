package com.liuhuiyu.scaffold.utils.security;

import com.liuhuiyu.scaffold.utils.ErrorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * RSA加、解密算法工具类
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-24 9:05
 */
@Slf4j
public class RsaUtil {
    /**
     * 加密算法AES
     */
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * 算法名称/加密模式/数据填充方式
     * 默认：RSA/ECB/PKCS1Padding
     */
    private static final String ALGORITHMS = "RSA/ECB/PKCS1Padding";

    /**
     * Map获取公钥的key
     */
    private static final String PUBLIC_KEY = "publicKey";

    /**
     * Map获取私钥的key
     */
    private static final String PRIVATE_KEY = "privateKey";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * RSA 位数 如果采用2048 上面最大加密和最大解密则须填写:  245 256
     */
    private static final int INITIALIZE_LENGTH = 1024;

    /**
     * 后端RSA的密钥对(公钥和私钥)Map，由静态代码块赋值
     */
    private final Map<String, Object> genKeyPair = new LinkedHashMap<>();

    public RsaUtil() {
        try {
            genKeyPair.putAll(genKeyPair());
        }
        catch (Exception e) {
            //输出到日志文件中
            log.error(ErrorUtil.errorInfoToString(e));
        }
    }

    /**
     * 生成密钥对(公钥和私钥)
     */
    private @NotNull Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(INITIALIZE_LENGTH);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<>(2);
        //公钥
        keyMap.put(PUBLIC_KEY, publicKey);
        //私钥
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 私钥解密
     *
     * @param encryptedData 已加密数据
     * @param privateKey    私钥(BASE64编码)
     */
    public byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
        //base64格式的key字符串转Key对象
        Key privateK = KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey)));

        //设置加密、填充方式
        /*
            如需使用更多加密、填充方式，引入
            <dependency>
                <groupId>org.bouncycastle</groupId>
                <artifactId>bcprov-jdk16</artifactId>
                <version>1.46</version>
            </dependency>
            并改成
            Cipher cipher = Cipher.getInstance(ALGORITHMS ,new BouncyCastleProvider());
         */
        Cipher cipher = Cipher.getInstance(ALGORITHMS);
        cipher.init(Cipher.DECRYPT_MODE, privateK);

        //分段进行解密操作
        return encryptAndDecryptOfSubsection(encryptedData, cipher, MAX_DECRYPT_BLOCK);
    }

    /**
     * 公钥加密
     *
     * @param data      源数据
     * @param publicKey 公钥(BASE64编码)
     */
    public byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        //base64格式的key字符串转Key对象
        Key publicK = KeyFactory.getInstance(KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(publicKey)));

        //设置加密、填充方式
        /*
            如需使用更多加密、填充方式，引入
            <dependency>
                <groupId>org.bouncycastle</groupId>
                <artifactId>bcprov-jdk16</artifactId>
                <version>1.46</version>
            </dependency>
            并改成
            Cipher cipher = Cipher.getInstance(ALGORITHMS ,new BouncyCastleProvider());
         */
        Cipher cipher = Cipher.getInstance(ALGORITHMS);
        cipher.init(Cipher.ENCRYPT_MODE, publicK);

        //分段进行加密操作
        return encryptAndDecryptOfSubsection(data, cipher, MAX_ENCRYPT_BLOCK);
    }

    /**
     * 获取私钥
     */
    public String getPrivateKey() {
        Key key = (Key) genKeyPair.get(PRIVATE_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * 获取公钥
     */
    public String getPublicKey() {
        Key key = (Key) genKeyPair.get(PUBLIC_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * 分段进行加密、解密操作
     * @param data         原始字节码
     * @param cipher       加密解密算法
     * @param encryptBlock 最大支持的块大小
     * @return  加密解密后的数据
     * @throws BadPaddingException  如果此 Cipher 为解密模式，并且未请求填充（或不填充），但解密的数据没有用适当的填充字节进行限制
     * @throws IllegalBlockSizeException    如果此 Cipher 为 Cipher 块，未请求任何填充（只针对加密模式），并且由此 Cipher 处理的数据总输入长度不是块大小的倍数；如果此加密算法无法处理所提供的输入数据。
     * @throws IOException  中断I/O处理中出现的一个异常
     */
    private static byte[] encryptAndDecryptOfSubsection(byte @NotNull [] data, Cipher cipher, int encryptBlock) throws BadPaddingException, IllegalBlockSizeException, IOException {
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > encryptBlock) {
                cache = cipher.doFinal(data, offSet, encryptBlock);
            }
            else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * encryptBlock;
        }
        out.close();
        return out.toByteArray();
    }
}
