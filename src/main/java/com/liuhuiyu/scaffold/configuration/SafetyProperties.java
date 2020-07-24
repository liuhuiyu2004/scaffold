package com.liuhuiyu.scaffold.configuration;

import com.liuhuiyu.scaffold.utils.security.RsaUtil;

/**
 * 安全配置
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-24 16:51
 */
public class SafetyProperties {
    public final static SafetyProperties instance = new SafetyProperties();

    public static SafetyProperties getInstance() {
        return instance;
    }

    RsaUtil rsaUtil;

    public SafetyProperties() {
        rsaUtil = new RsaUtil();
    }

    public String getPublicKey() {
        return rsaUtil.getPublicKey();
    }

    public byte[] decryptByPrivateKey(byte[] encryptedData) throws Exception {
        String privateKey = rsaUtil.getPrivateKey();
        return rsaUtil.decryptByPrivateKey(encryptedData, privateKey);
    }

    public byte[] encryptByPublicKey(byte[] data) throws Exception {
        String publicKey = rsaUtil.getPublicKey();
        return rsaUtil.encryptByPublicKey(data, publicKey);
    }
}
