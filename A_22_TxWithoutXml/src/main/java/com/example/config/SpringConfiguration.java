package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Avarice
 * @date 2021/9/20 20:19
 */
@Configuration
@ComponentScan("com.example")
@Import({JdbcConfig.class, TransactionConfig.class})
@PropertySource("classpath:jdbcConfig.properties")
@EnableTransactionManagement // 开启基于注解的声明式事务支持
public class SpringConfiguration {
}
