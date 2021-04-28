package com.zero.springbootdataredis.service;

import com.zero.springbootdataredis.po.People;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceTest {


    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    TestService testService;

    private String key = "test0428";
    private String value = "哈哈zzzz123";

    /**
     * RedisConfig配置后结果
     */
    @Test
    public void test1() {

//        redisTemplate.opsForValue().set(key, value);
//        Object o = redisTemplate.opsForValue().get(key);
//        Assert.assertEquals(value, o); //通过

        People people = new People();
        people.setAge(18);
        people.setName(value);
        System.out.println(people); //People(name=哈哈zzzz123, age=18)

        /**
         * redis中
         * {
         *   "@class": "com.zero.springbootdataredis.po.People",
         *   "name": "哈哈zzzz123",
         *   "age": 18
         * }
         */
        redisTemplate.opsForValue().set(key, people);
        Object o = redisTemplate.opsForValue().get(key);
        System.out.println(o); //People(name=哈哈zzzz123, age=18)
    }

    /**
     * RedisConfig注释掉, 使用原生配置, 什么鬼, 注释掉: RedisTemplate<String, Object> redisTemplate;会报错
     */
    @Test
    public void test2(){
        
    }

    /**
     * 缓存测试失败
     */
    @Test
    public void query(){
        People people = testService.query();
        System.out.println(people);
    }
}