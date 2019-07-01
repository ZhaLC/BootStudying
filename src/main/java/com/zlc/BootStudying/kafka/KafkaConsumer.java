package com.zlc.BootStudying.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author : ZLC
 * @create : 2019-02-28 18:44
 * @desc : kafka消费者
 **/
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"app_log"})
    public void receive(String content){
        System.err.println("Receive aaa:" + content);
    }


   //@KafkaListener(topics = {"app_log"})
    public void receive2(String content){
        System.err.println("Receive2 aaa:" + content);
    }

}
