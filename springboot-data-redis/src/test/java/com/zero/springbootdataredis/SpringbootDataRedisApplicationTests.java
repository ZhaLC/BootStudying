package com.zero.springbootdataredis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringbootDataRedisApplicationTests {

    @Autowired
    RedisTemplate<String, String> stringRedisTemplate;

    @Test
    void contextLoads() {
        /**
         * 修改db时, 下面打印都正常, 默认配置文件没配置0号库库, 打印输出2号库, 但是在数据库可视化看时, 数据还是在0号库, 并没有在2号库
         */
//        LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) stringRedisTemplate.getConnectionFactory();
//        System.out.println(connectionFactory.getDatabase());
//        connectionFactory.setDatabase(2);
//        System.out.println(connectionFactory.getDatabase());
//        stringRedisTemplate.setConnectionFactory(connectionFactory);


        String key = "test0421";

        // 删除指定key
        //stringRedisTemplate.delete(key);

        // opsForValue 操作String
        stringRedisTemplate.opsForValue().set(key, "ha哈哈哈哈123");
        String x = stringRedisTemplate.opsForValue().get(key);
        System.out.println(x);

        System.out.println(((LettuceConnectionFactory) stringRedisTemplate.getConnectionFactory()).getDatabase());
    }

}
