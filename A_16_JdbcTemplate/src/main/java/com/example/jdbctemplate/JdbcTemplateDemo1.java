package com.example.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * JdbcTemplate的最基本用法
 *
 * @author Avarice
 * @date 2021/9/8 21:30
 */
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("193196");

        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.execute("insert into account (name, money) values('gongsheng', 1000)");
    }
}
