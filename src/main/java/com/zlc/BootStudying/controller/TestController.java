package com.zlc.BootStudying.controller;

import com.zlc.BootStudying.config.ConfigBean;
import com.zlc.BootStudying.dao.test1.Test1Mapper;
import com.zlc.BootStudying.dao.test2.Test2Mapper;
import com.zlc.BootStudying.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ZLC
 * @create : 2018-11-16 10:43
 * @desc : 测试类
 **/
@Controller
@RequestMapping("/test")
public class TestController {

   /* private Logger

    @Autowired
    ConfigBean configBean;

    @RequestMapping("/testConfigBean")
    public String testConfigBean(){
        logger.info("CC");
        return configBean.getName();
    }*/

   @Autowired
   private Test1Mapper test1Mapper;
   @Autowired
   private Test2Mapper test2Mapper;


   @RequestMapping(value = "/testDataSource")
   public void testDataSource(){

       User user1 = test1Mapper.getUser();
       User user2 = test2Mapper.getUser();
       System.out.println("user1 : " + user1);
       System.out.println("user2 : " + user2);

   }



}
