package org.hzz.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hzz.annotation.WR;
import org.hzz.config.DynamicDataSource;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DynamicDataSourceAspect implements Ordered {

    // 前置
    @Before("within(org.hzz.service.*) && @annotation(wr)")
    public void before(JoinPoint point, WR wr){
        String name = wr.value();
        DynamicDataSource.name.set(name);
        System.out.println(name);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
