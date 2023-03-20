package org.hzz.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.hzz.mapper.R",sqlSessionFactoryRef = "rSqlSessionFactory")
public class RMyBatisConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.datasource2")
    public DataSource dataSource2(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory rSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource2());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager rTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource2());
        return dataSourceTransactionManager;
    }

    @Bean
    public TransactionTemplate rTransactionTemplate(){
        return new TransactionTemplate(rTransactionManager());
    }
}
