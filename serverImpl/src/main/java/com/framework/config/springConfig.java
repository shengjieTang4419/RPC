package com.framework.config;

import common.util.RpcService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author ：shengjie.tang
 * @date ：Created in 2019/9/15 18:55
 * @description： Spring容器配置类
 * @modified By：
 * @version: $
 */
@Configuration
@ComponentScan(basePackages = "com.framework")
public class springConfig {
    @Bean
    public RpcService rpcService(){
        return new RpcService();
    }
}
