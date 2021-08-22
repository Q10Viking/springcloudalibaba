package org.hzz.config;

import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.hzz.interceptor.MyFeignAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeginConfig {

    // fegin调用全局日子配置
    // @Bean
    public Logger.Level feginLoggerLevel(){
        return Logger.Level.FULL;
    }

    // 使用Feign默认的来节气
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("hzz","123456");
    }

    // 自定义Feign的拦截器
//    @Bean
//    public MyFeignAuthRequestInterceptor myFeignAuthRequestInterceptor(){
//        return new MyFeignAuthRequestInterceptor();
//    }
}
