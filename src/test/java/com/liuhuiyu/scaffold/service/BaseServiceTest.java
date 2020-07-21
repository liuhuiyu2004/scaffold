package com.liuhuiyu.scaffold.service;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-21 9:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseServiceTest {
    private final static Logger logger = LoggerFactory.getLogger(BaseServiceTest.class);
    @Before
    public void login(){

    }

    @After
    public void after(){

    }

    protected void printList(@NotNull List<?> list){
        for (Object obj:list             ) {
            logger.info(obj.toString());
        }
    }
    protected void printObject(Object obj){
        if (obj == null) {
            logger.info("Obj=null");
        }else{
            logger.info(obj.toString());
        }
    }
    protected void printPageImpl(@NotNull Page pageImpl){
        logger.info("总记录数："+pageImpl.getTotalElements());
        logger.info("当前页号索引："+pageImpl.getNumber());

        logger.info("每页数据："+pageImpl.getSize());

        logger.info("当前页记录数："+pageImpl.getNumberOfElements());
        logger.info("当前页号："+(pageImpl.getNumber()+1));

        logger.info("总页数："+pageImpl.getTotalPages());
        printList(pageImpl.getContent());
    }
}