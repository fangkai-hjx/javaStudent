package com.pdd.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SpringBoot提供的
 */
@ConfigurationProperties(prefix = "jdbc")
public class JdbcProperties {
    private String driver;
    private String url;
    private String username;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }
}
