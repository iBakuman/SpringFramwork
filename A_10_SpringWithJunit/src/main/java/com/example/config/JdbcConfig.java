package com.example.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @author Avarice
 * @date 2021/8/30 20:00
 */
public class JdbcConfig {
    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;


    /**
     * 用于创建一个QueryRunner对象
     *
     * @param ds
     * @return
     */
    @Bean(name = "runner")
    //@Scope("prototype")// 设置为多例对象
    public QueryRunner createQueryRunner( @Qualifier("ds") DataSource ds) {
        return new QueryRunner(ds);
    }

    /**
     * 创建数据源对象
     *
     * @return
     */
    @Bean(name = "ds")
    public DataSource createDataSource() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(username);
            ds.setPassword(password);
            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
