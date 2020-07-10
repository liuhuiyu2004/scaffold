package com.liuhuiyu.scaffold.controller;

import com.liuhuiyu.scaffold.factory.HttpFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-08 11:40
 */
public abstract class AbsBaseController {
    private final static Logger logger = LoggerFactory.getLogger(AbsBaseController.class);

    /**
     * 首页
     */
    protected static final String PATH_INDEX = "/index";
    /**
     * 当前目录默认地址
     */
    protected static final String PATH_DEFAULT = "/*";
    /**
     * 页号属性
     */
    public static final String ATTRIBUTE_PAGE_INDEX = "page_index";
    /**
     * 页面大小
     */
    public static final String ATTRIBUTE_PAGE_SIZE = "page_size";
    /**
     * id
     */
    public static final String ATTRIBUTE_ID = "id";
    /**
     * 默认页面大小
     */
    protected static final int DEF_PAGE_SIZE = 20;

    /**
     * HttpServletRequest获取
     *
     * @return HttpServletRequest
     */
    protected static HttpServletRequest getHttpServletRequest() {
        logger.debug("HttpServletRequest");
        return HttpFactory.getHttpServletRequest();
    }
}