package com.zero.springbootdataredis.service;

import com.zero.springbootdataredis.po.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author : zlc
 * @create : 2021-04-28 15:31
 * @desc :
 **/
@Component
public class TestService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void test(){

    }

    // 测试缓存 失败
    @Cacheable(value = "sampleCache")
    public People query(){
        System.out.println("模拟查询");
        People people = new People();
        people.setAge(22);
        people.setName("hah哈哈1221");
        return people;
    }

}
