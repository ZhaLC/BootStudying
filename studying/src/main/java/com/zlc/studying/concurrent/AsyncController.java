package com.zlc.studying.concurrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc :
 **/
@RestController
//@Component
public class AsyncController {

    @Autowired
    AsyncTest asyncTest;

    @RequestMapping("/testAsync")
    public String test(){
        for (int i = 0; i < 5; i++) {
            asyncTest.testAsync();
        }
        return "11111";
    }
}
