package com.framework.server;


import com.framework.config.springConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ：shengjie.tang
 * @date ：Created in 2019/9/15 14:38
 * @description： 服务端启动类
 * @modified By：
 * @version: $
 */
public class ServiceApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(springConfig.class);
        context.start();
    }
}
