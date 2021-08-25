package com.example.dao.impl;

import com.example.dao.IAccountDao;
import org.springframework.stereotype.Repository;

/**
 * 账户的持久层实现类
 * @author Avarice
 */

@Repository // 对于持久层，使用Repository注解配置
public class AccountDaoImpl implements IAccountDao {
    @Override
    public void saveAccount() {
        System.out.println("Invoking saveAccount in class AccountDaoImpl...");
    }
}
