package com.blueinfinite;

import com.alibaba.druid.pool.DruidDataSource;
import com.blueinfinite.Service.MessageService;
import com.blueinfinite.mapper.CustomMapper;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

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
        return ()-> "hello";

//        return new MessageService() {
//            public String getMessage() {
//                return "Hello world.";
//            }
//        };
    }

    @Bean
    public DataSource dataSource(ConfigDBInfo info) {
        System.out.println(info.toString());
        return getDruid(info);
    }

    @Bean
    JdbcTransactionFactory jdbcTransactionFactory() {
        return new JdbcTransactionFactory();
    }

    @Bean
    SqlSessionFactory getSqlSessionFactory(JdbcTransactionFactory jdbcTrans,DataSource dataSource) {
        System.out.println("getSqlSessionFactory");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();


        configuration.setEnvironment(new Environment("test_mybatis", jdbcTrans, dataSource));

//        Resources.getResourceAsStream("mybatis.config")
//
//        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(mapperLocation.getInputStream(),
//                configuration, mapperLocation.toString(), configuration.getSqlFragments());
//        xmlMapperBuilder.parse();

        configuration.addMapper(CustomMapper.class);

        ApplicationContext ctx=new FileSystemXmlApplicationContext();
        Resource[] mapperResource = null;
        try {
            mapperResource=ctx.getResources("classpath*:mapper/*.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(mapperResource.length);
        try {
            for (Resource res : mapperResource) {
                XMLMapperBuilder xmlMapperBuilder = null;

                    xmlMapperBuilder = new XMLMapperBuilder(res.getInputStream(),
                            configuration, res.toString(), configuration.getSqlFragments());
                xmlMapperBuilder.parse();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }

    public static DataSource getDruid(ConfigDBInfo info){
        System.out.println("Create DruidDataSource:"+info.getUrl());

        DruidDataSource dds = new DruidDataSource();
        dds.setUrl(info.getUrl());
        dds.setUsername(info.getUsername());
        dds.setPassword(info.getPassword());
        dds.setDriverClassName(info.getDriver());
        dds.setInitialSize(0);//初始化连接大小
        dds.setMaxActive(1500);//连接池最大使用连接数量
        dds.setMinIdle(0);//连接池最小空闲
        dds.setMaxWait(60000);//获取连接最大等待时间
        dds.setValidationQuery("select 1");//验证数据库连接有效性，要求查询语句

        //建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
        dds.setTestWhileIdle(true);

        //申请连接时执行validationQuery检测连接是否有效，配置true会降低性能。
        dds.setTestOnBorrow(false);

        //归还连接时执行validationQuery检测连接是否有效，配置true会降低性能
        dds.setTestOnReturn(false);

        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dds.setTimeBetweenEvictionRunsMillis(60000);

        //配置一个连接在池中最小生存的时间，单位是毫秒
        dds.setMinEvictableIdleTimeMillis(25200000);

        //对于长时间不使用的连接强制关闭
        dds.setRemoveAbandoned(true);

        //关闭超过30分钟的空闲连接，1800秒，也就是30分钟
        dds.setRemoveAbandonedTimeout(1800);

        //关闭abanded连接时输出错误日志
        dds.setLogAbandoned(true);

        //设置批量更新

        //监控数据库
        //dds.setFilters("mergeStat");
        try {
            dds.setFilters("stat,wall");
        } catch (SQLException e) {
            System.out.println("error:"+e.getMessage());
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(dds.getDriverClassName());
        return dds;
    }


}