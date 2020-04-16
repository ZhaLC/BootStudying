package com.zlc.shardingjdbcdemo.controller;

import com.zlc.shardingjdbcdemo.po.Voice;
import com.zlc.shardingjdbcdemo.service.VoiceService;
import com.zlc.shardingjdbcdemo.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ZLC
 * @create : 2020-04-08 17:31
 * @desc :
 **/
@RestController
public class VoiceController {

    @Autowired
    private VoiceService voiceService;

    @GetMapping("/add")
    public void add(String createTime) {
        System.out.println("11111: " + createTime);
        Voice voice = new Voice();
        voice.setCreateTime(DateUtils.getDate(createTime,"yyyy-MM-dd HH:mm:ss"));
        voice.setCenterNum(1);
        voiceService.addVoice(voice);
    }

}
