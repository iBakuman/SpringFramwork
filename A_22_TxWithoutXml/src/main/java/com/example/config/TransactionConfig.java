package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author Avarice
 * @date 2021/9/20 20:24
 */
public class TransactionConfig {
    @Bean("transactionManger")
    public PlatformTransactionManager createTransactionManger(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
