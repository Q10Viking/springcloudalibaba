package org.hzz.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.hzz.mapper.W",sqlSessionFactoryRef = "wSqlSessionFactory")
public class WMyBatisConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.datasource1")
    public DataSource dataSource1(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory wSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource1());
        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    @Primary
    public DataSourceTransactionManager wTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource1());
        return dataSourceTransactionManager;
    }


    @Bean
    public TransactionTemplate wTransactionTemplate(){
        return new TransactionTemplate(wTransactionManager());
    }


}
