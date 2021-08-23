package org.hzz.controller;


import org.hzz.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@RestController
@RefreshScope   // 使得@Value动态变化
public class TestController {

    @Value("${common.name}")
    private String name;

    @GetMapping("/common")
    public R hello(){
        return R.ok(name);
    }


    @PreDestroy
    public void destroy(){
        System.out.println("被销毁");
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化");
    }
}
