package org.hzz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class NacosConfigApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(NacosConfigApplication.class, args);
        while(true){
            String name = context.getEnvironment().getProperty("common.name");
            String age = context.getEnvironment().getProperty("common.age");
            String school = context.getEnvironment().getProperty("common.school");
            String profession = context.getEnvironment().getProperty("common.profession");
            String extName1 = context.getEnvironment().getProperty("common.extName1");
            String extName2 = context.getEnvironment().getProperty("common.extName2");
            log.info("name: {} age {} school {} profession {} extName1 {} extName2 {}",name,age,school,profession,extName1,extName2);
            TimeUnit.SECONDS.sleep(3);
        }
    }
}
