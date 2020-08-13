package com.liuhuiyu.scaffold.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuhuiyu.scaffold.configuration.MyConfiguration;
import com.liuhuiyu.scaffold.constant.AspectPrecedenceConstant;
import com.liuhuiyu.scaffold.domain.model.Result;
import com.liuhuiyu.scaffold.utils.ErrorUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.SimpleDateFormat;

/**
 * RSA 加解密AOP处理
 *
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-08-12 8:06
 */
@Slf4j
@Aspect
@Component
@Order(AspectPrecedenceConstant.SAFETY_ASPECT_PRECEDENCE)
public class SafetyAspect {
    /**
     * Pointcut 切入点
     * 匹配
     * cn.huanzi.qch.baseadmin.sys.*.controller、
     * cn.huanzi.qch.baseadmin.*.controller包下面的所有方法
     */
    @Pointcut(value = "within(com.liuhuiyu.scaffold.controller.web..*)")
    public void safetyAspect() {
    }

    /**
     * 环绕通知
     */
    @Around(value = "safetyAspect()")
    public Object around(ProceedingJoinPoint pjp) {
        try {
            //判断api加密开关是否开启
            if (MyConfiguration.getInstance().isSafety()) {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                assert attributes != null;
                //request对象
//                HttpServletRequest request = attributes.getRequest();

                //http请求方法  post get
//                String httpMethod = request.getMethod().toLowerCase();

                //method方法
//                Method method = ((MethodSignature) pjp.getSignature()).getMethod();

                //method方法上面的注解
//                Annotation[] annotations = method.getAnnotations();

                //方法的形参参数
                Object[] args = pjp.getArgs();

                //是否有@Decrypt
//                boolean hasDecrypt = false;
                //是否有@Encrypt
//                boolean hasEncrypt = false;
//                for (Annotation annotation : annotations) {
////                    if (annotation.annotationType() == Decrypt.class) {
////                        hasDecrypt = true;
////                    }
//                    if (annotation.annotationType() == Encrypt.class) {
//                        hasEncrypt = true;
//                    }
//                }

                //前端公钥
//                String publicKey = null;

                //jackson
                ObjectMapper mapper = new ObjectMapper();
                //jackson 序列化和反序列化 date处理
                mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

                //执行方法之前解密，且只拦截post请求
//                if ("post".equals(httpMethod) && hasDecrypt) {
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
//                    for (Map.Entry<String, String> entry : preferenceMap.entrySet()) {
//                        request.getParameterMap().put(entry.getKey(), new String[]{entry.getValue()});
//                    }
//
////                    //设置到方法的形参中，目前只能设置只有一个参数的情况
////                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////
////
////                    if (args.length > 0) {
////                        args[0] = mapper.readValue(decrypt, args[0].getClass());
////                    }
//                }

                //执行并替换最新形参参数   PS：这里有一个需要注意的地方，method方法必须是要public修饰的才能设置值，private的设置不了
                Object o = pjp.proceed(args);

                //返回结果之前加密
//                if (hasEncrypt) {
//                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//                    //每次响应之前随机获取AES的key，加密data数据
//                    String key = AesUtil.getKey();
//                    log.info("AES的key：" + key);
//                    String dataString = mapper.writeValueAsString(o);
//                    log.info("需要加密的data数据：" + dataString);
//                    String data = AesUtil.encrypt(dataString, key);
//                    //用前端的公钥来解密AES的key，并转成Base64
//                    String aesKey = Base64.encodeBase64String(SafetyProperties.getInstance().encryptByPublicKey(dataString.getBytes()));
//                    log.info("需要加密的data数据：" + dataString);
//                    //转json字符串并转成Object对象，设置到Result中并赋值给返回值o
//                    o = Result.of(mapper.readValue("{\"data\":\"" + data + "\",\"aesKey\":\"" + aesKey + "\"}", Object.class));
//                }
                //返回
                if (o != null) {
                    return o;
                }
                else {
                    return 0;
                }
            }
            else {
                return pjp.proceed(pjp.getArgs());
            }
        }
        catch (Throwable e) {
            //输出到日志文件中
            log.error(ErrorUtil.errorInfoToString(e));
            return Result.error("加解密异常：" + e.getMessage());
        }
    }
}
