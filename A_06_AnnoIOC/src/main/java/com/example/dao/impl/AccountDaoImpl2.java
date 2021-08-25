package com.example.dao.impl;

import com.example.dao.IAccountDao;
import org.springframework.stereotype.Repository;

/**
 * 演示Autowired注入时遇到多个bean类型匹配时的问题
 */
@Repository("accountDaoImpl2")
public class AccountDaoImpl2 implements IAccountDao {
    @Override
    public void saveAccount() {
        System.out.println("Invoking saveAccount in class AccountDaoImpl2");
    }
}
