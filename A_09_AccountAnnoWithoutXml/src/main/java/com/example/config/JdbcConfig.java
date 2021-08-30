package com.example.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Avarice
 * @date 2021/8/30 20:00
 */
public class JdbcConfig {
    /**
     * 用于创建一个QueryRunner对象
     *
     * @param ds
     * @return
     */
    @Bean(name = "runner")
    //@Scope("prototype")// 设置为多例对象
    public QueryRunner createQueryRunner(DataSource ds) {
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
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
            ds.setUser("root");
            ds.setPassword("193196");
            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
