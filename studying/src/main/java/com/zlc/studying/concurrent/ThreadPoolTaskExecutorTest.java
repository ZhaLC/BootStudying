package com.zlc.studying.concurrent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @desc :
 **/
@Configuration
public class ThreadPoolTaskExecutorTest {
    @Bean("task")
    public Executor task(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //获取CPU服务器内核数
        int i = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(5);//核心池大小
        executor.setMaxPoolSize(100);//最大线程数
        executor.setQueueCapacity(1000);//队列程度
        executor.setKeepAliveSeconds(1000);//线程空闲时间
        executor.setThreadNamePrefix("tsak-asyn");//线程前缀名称
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());//配置拒绝策略
        return executor;
    }
}
