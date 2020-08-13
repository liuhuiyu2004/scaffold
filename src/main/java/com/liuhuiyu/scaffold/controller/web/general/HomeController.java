package com.liuhuiyu.scaffold.controller.web.general;

import com.liuhuiyu.scaffold.constant.PageAddressConstant;
import com.liuhuiyu.scaffold.constant.WebAddressConstant;
import com.liuhuiyu.scaffold.controller.AbsBaseController;
import com.liuhuiyu.scaffold.factory.HttpFactory;
import com.liuhuiyu.scaffold.utils.AddressRoutingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-13 19:34
 */
@Controller
@RequestMapping(HomeController.ROOT)
public class HomeController extends AbsBaseController {
    static final String ROOT = WebAddressConstant.HOME_ADDRESS_ROOT;
    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    //region 地址访问
    public static String getIndexAddress(){
        return AddressRoutingUtil.getFullAddress(HttpFactory.getHttpServletRequest(), ROOT, PATH_INDEX);
    }
    @RequestMapping(value = {PATH_INDEX,PATH_DEFAULT})
    public String index(){
        return PageAddressConstant.HOME_PAGE;
    }
    //endregion
}