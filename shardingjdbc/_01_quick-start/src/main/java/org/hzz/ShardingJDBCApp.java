package org.hzz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.hzz.mapper")
public class ShardingJDBCApp {
    public static void main(String[] args) {
        SpringApplication.run(ShardingJDBCApp.class,args);
    }
}
