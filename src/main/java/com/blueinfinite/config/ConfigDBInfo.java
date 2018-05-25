package com.blueinfinite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 数据库配置信息
 */
@Component
public class ConfigDBInfo {
    @Value("${db.driver}")
    private String driver;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    /**
     * 数据库驱动
     */
    public String getDriver() {
        return driver;
    }

    /**
     * 连接地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "ConfigDBInfo{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
