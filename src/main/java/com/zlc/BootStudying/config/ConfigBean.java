package com.zlc.BootStudying.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : ZLC
 * @create : 2018-11-16 9:50
 * @desc : 用于配置的bean, 方便加载配置文件中的属性
 **/
@ConfigurationProperties(prefix = "com.zlc")
public class ConfigBean {

    private String name;

    public ConfigBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
