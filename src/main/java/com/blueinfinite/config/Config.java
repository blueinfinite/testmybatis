package com.blueinfinite.config;

import com.blueinfinite.Service.MessageService;
import com.blueinfinite.utils.ConfigDBUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
public class Config {
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        System.out.println("PropertySourcesPlaceholderConfigurer");
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));

        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
        p.setProperties(yaml.getObject());

        return p;
    }

    @Bean
    MessageService mockMessageService() {
        return () -> "hello";
    }

    @Bean
    public DataSource dataSource(ConfigDBInfo info) {
        System.out.println(info.toString());
        return ConfigDBUtils.getDruid(info);
    }

    @Bean
    JdbcTransactionFactory jdbcTransactionFactory() {
        return new JdbcTransactionFactory();
    }

    @Bean
    SqlSessionFactory getSqlSessionFactory(JdbcTransactionFactory jdbcTrans, DataSource dataSource) {
        System.out.println("getSqlSessionFactory");
        return ConfigDBUtils.getSqlSessionFactory("test_mybatis","classpath*:mapper/*.xml",jdbcTrans, dataSource);
    }
}
