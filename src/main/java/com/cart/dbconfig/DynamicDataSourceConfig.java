package com.cart.dbconfig;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源
 */
@Configuration
public class DynamicDataSourceConfig{
    

    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource oneDataSource(){
        return DruidDataSourceBuilder.create().build();
    }
    
    @Bean
    @ConfigurationProperties("spring.datasource.druid.master2")
    public DataSource twoDataSource(){
        return  DruidDataSourceBuilder.create().build();
    }
    
    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource oneDataSource, DataSource twoDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceEnum.MASTER.getName(), oneDataSource);
        targetDataSources.put(DataSourceEnum.MASTER2.getName(), twoDataSource);
        return new DynamicDataSource(oneDataSource, targetDataSources);
    }
}
