package org.hzz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
@Primary
public class DynamicDataSource extends AbstractRoutingDataSource {

    // 当前使用的数据源标识
    public static ThreadLocal<String> name = new ThreadLocal<>();

    @Autowired
    private DataSource dataSource1;
    @Autowired
    private DataSource dataSource2;

    @Override
    protected Object determineCurrentLookupKey() {
        return name.get();
    }

    @Override
    public void afterPropertiesSet() {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put("W",dataSource1);
        targetDataSource.put("R",dataSource2);

        super.setTargetDataSources(targetDataSource);
        super.setDefaultTargetDataSource(dataSource1);
        super.afterPropertiesSet();
    }
}
