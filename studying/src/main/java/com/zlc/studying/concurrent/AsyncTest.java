package com.zlc.studying.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @desc :
 **/
@Component
public class AsyncTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Async("task")
    public void testAsync(){
        logger.info("线程名: " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
