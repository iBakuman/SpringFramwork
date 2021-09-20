package com.example.dao.impl;

import com.example.dao.IAccountDao;
import com.example.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * @author Avarice
 * @date 2021/9/9 18:52
 */
public class AccountDaoImpl extends JdbcDaoSupport implements IAccountDao {

    @Override
    public Account findById(Integer id) {
        List<Account> accounts = getJdbcTemplate().query(
                "select * from account where id = ?",
                new BeanPropertyRowMapper<>(Account.class),
                id
        );
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public Account findByName(String name) {
        List<Account> accounts = getJdbcTemplate().query(
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
        getJdbcTemplate().update(
                "update account set name = ?, money = ? where id = ?",
                account.getName(),
                account.getMoney(), account.getId()
        );
    }
}
