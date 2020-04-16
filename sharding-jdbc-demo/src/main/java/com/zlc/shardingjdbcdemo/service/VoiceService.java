package com.zlc.shardingjdbcdemo.service;

import com.zlc.shardingjdbcdemo.mapper.VoiceMapper;
import com.zlc.shardingjdbcdemo.po.Voice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : ZLC
 * @create : 2020-04-08 17:44
 * @desc :
 **/
@Service
public class VoiceService {

    @Autowired
    private VoiceMapper voiceMapper;

    public void addVoice(Voice voice){
        voiceMapper.insert(voice);
    }

}
