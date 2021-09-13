package com.example.dao.impl;

import com.example.dao.AccountDao;
import com.example.domain.Account;
import com.example.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 账户持久层实现类
 *
 * @author Avarice
 */
public class AccountDaoImpl implements AccountDao {
    private QueryRunner runner;
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    @Override
    public List<Account> findAll() {
        try {
            return runner.query(connectionUtils.getThreadConnection(), "select * from account", new BeanListHandler<Account>(Account.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findById(Integer id) {
        try {
            return runner.query(connectionUtils.getThreadConnection(), "select * from account where id = ?", new BeanHandler<Account>(Account.class), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(), "insert into account(name, money) values(?,?)", account.getName(), account.getMoney());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(), "update account set name=?,money=? where id=?", account.getName(), account.getMoney(), account.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAccount(Integer id) {
        try {
            runner.update(connectionUtils.getThreadConnection(), "delete from account where id = ?", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findByName(String name) {
        List<Account> accounts = null;
        try {
            accounts = runner.query(connectionUtils.getThreadConnection(), "select * from account where name = ?", new BeanListHandler<>(Account.class), name);
            if (null == accounts || 0 == accounts.size())
                return null;
            if (accounts.size() > 1)
                throw new RuntimeException("结果不唯一，找到多个账户的名字和给定的相同!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null == accounts ? null : accounts.get(0);
    }
}
