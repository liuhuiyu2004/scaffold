//package com.liuhuiyu.scaffold.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.liuhuiyu.scaffold.configuration.MyConfiguration;
//import com.liuhuiyu.scaffold.configuration.SafetyProperties;
//import com.liuhuiyu.scaffold.utils.security.AesUtil;
//import lombok.extern.log4j.Log4j2;
//import org.apache.tomcat.util.codec.binary.Base64;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.core.annotation.Order;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author LiuHuiYu
// * @version v1.0.0.0
// * Created DateTime 2020-07-24 16:46
// */
////@WebFilter
//@Log4j2
//@Order(1)
//@WebFilter(urlPatterns = "/*", filterName = "AuthHeaderFilter")
//public class AuthHeaderSettingFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, @NotNull FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        RequestParameterWrapper requestParameterWrapper = new RequestParameterWrapper(request);
//        if (MyConfiguration.getInstance().isSafety()) {
//            try {
//                //http请求方法  post get
//                String httpMethod = request.getMethod().toLowerCase();
//                //执行方法之前解密，且只拦截post请求
//                if ("post".equals(httpMethod)) {
//                    //前端公钥
//                    String publicKey = null;
//                    //AES加密后的数据
//                    String data = request.getParameter("data");
//                    //后端RSA公钥加密后的AES的key
//                    String aesKey = request.getParameter("aesKey");
//                    //前端公钥
//                    publicKey = request.getParameter("publicKey");
//                    log.info("前端公钥：" + publicKey);
//
//                    //后端私钥解密的到AES的key
//                    byte[] plaintext = SafetyProperties.getInstance().decryptByPrivateKey(Base64.decodeBase64(aesKey));
//                    aesKey = new String(plaintext);
//                    log.info("解密出来的AES的key：" + aesKey);
//
//                    //RSA解密出来字符串多一对双引号
//                    aesKey = aesKey.substring(1, aesKey.length() - 1);
//
//                    //AES解密得到明文data数据
//                    String decrypt = AesUtil.decrypt(data, aesKey);
//                    log.info("解密出来的data数据：" + decrypt);
//
//                    ObjectMapper objectMapper = new ObjectMapper();//
//                    Map<String, String> preferenceMap = new HashMap<String, String>();
//                    preferenceMap = objectMapper.readValue(decrypt, preferenceMap.getClass());
////                    request.getParameterMap().clear();
//                    Map<String, Object> extraParams = new HashMap<String, Object>();
//                    for (Map.Entry<String, String> entry : preferenceMap.entrySet()) {
//                        extraParams.put(entry.getKey(), entry.getValue());
//                    }
//                    requestParameterWrapper.addParameters(extraParams);
//                    //利用原始的request对象创建自己扩展的request对象并添加自定义参数
//                }
//            }
//            catch (Exception ex) {
//                log.error(ex.getMessage());
//            }
//            filterChain.doFilter(requestParameterWrapper, servletResponse);
//        }
////
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
