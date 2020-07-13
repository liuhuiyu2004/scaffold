package com.liuhuiyu.scaffold.controller.web.general;

import com.liuhuiyu.scaffold.constant.WebAddressConstant;
import com.liuhuiyu.scaffold.controller.AbsBaseController;
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
}