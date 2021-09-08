package com.example.jdbctemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcTemplate的最基本用法
 *
 * @author Avarice
 * @date 2021/9/8 21:30
 */
public class JdbcTemplateDemo2 {
    public static void main(String[] args) {
        // 1.获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 2.获取对象
        JdbcTemplate jt = context.getBean("jdbcTemplate", JdbcTemplate.class);
        // 3.执行方法
        jt.execute("delete from account where name='gongsheng'");

/*        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("193196");

        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.execute("insert into account (name, money) values('gongsheng', 1000)");*/
    }
}
