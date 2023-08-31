package com.jd.configs;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration // 使用java配置的方式配置 druid数据源
public class DruidDataSourceConfig {
    // bean注解，将该方法的返回值，作为一个spring容器的托管对象
    // 使用bean注解将返回值 IOC 给spring容器【反转控制给spring容器】
    @Bean
    @Primary // 如果存在类型相同的数据源对象，则有限使用本springbean
    @ConfigurationProperties(prefix = "spring.datasource") // 自动加载 在springboot配置文件指定前缀的配置信息
    public DataSource getDruidDataSource() {
        return new DruidDataSource();
    }
}
