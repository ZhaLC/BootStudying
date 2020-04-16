package com.zlc.shardingjdbcdemo.mapper;

import com.zlc.shardingjdbcdemo.po.Voice;
import com.zlc.shardingjdbcdemo.po.VoiceExample;
import java.util.List;

import groovy.util.logging.Commons;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface VoiceMapper {
    long countByExample(VoiceExample example);

    int deleteByExample(VoiceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Voice record);

    int insertSelective(Voice record);

    List<Voice> selectByExample(VoiceExample example);

    Voice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Voice record, @Param("example") VoiceExample example);

    int updateByExample(@Param("record") Voice record, @Param("example") VoiceExample example);

    int updateByPrimaryKeySelective(Voice record);

    int updateByPrimaryKey(Voice record);
}