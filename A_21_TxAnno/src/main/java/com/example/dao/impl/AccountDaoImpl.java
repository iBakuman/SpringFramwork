package com.example.dao.impl;

import com.example.dao.IAccountDao;
import com.example.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Avarice
 * @date 2021/9/9 18:52
 */
@Repository
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account findById(Integer id) {
        List<Account> accounts = jdbcTemplate.query(
                "select * from account where id = ?",
                new BeanPropertyRowMapper<>(Account.class),
                id
        );
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public Account findByName(String name) {
        List<Account> accounts = jdbcTemplate.query(
                "select * from account where name = ?",
                new BeanPropertyRowMapper<>(Account.class),
                name
        );
        if (accounts.isEmpty()) {
            return null;
        }

        if (accounts.size() > 1) {
            throw new RuntimeException("结果集不唯一!!");
        }
        return accounts.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        if (null == account) {
            return;
        }
        jdbcTemplate.update(
                "update account set name = ?, money = ? where id = ?",
                account.getName(),
                account.getMoney(), account.getId()
        );
    }
}
