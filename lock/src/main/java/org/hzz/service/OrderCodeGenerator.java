package org.hzz.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service
public class OrderCodeGenerator {
    private static AtomicInteger count = new AtomicInteger(0);

    public String genCode(){
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyyMMddhhmmss");
        return simpleFormatter.format(new Date())+"-"+count.incrementAndGet();
    }
    
}
