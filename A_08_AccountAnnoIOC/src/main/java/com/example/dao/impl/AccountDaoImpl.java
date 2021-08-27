package com.example.dao.impl;

import com.example.dao.AccountDao;
import com.example.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * 账户持久层实现类
 * @author Avarice
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private QueryRunner runner;

    // 使用Autowired来注入，下面函数不再需要
    //public void setRunner(QueryRunner runner) {
    //    this.runner = runner;
    //}

    @Override
    public List<Account> findAll() {
        try {
            return runner.query("select * from account", new BeanListHandler<Account>(Account.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findById(Integer id) {
        try {
            return runner.query("select * from account where id = ?", new BeanHandler<Account>(Account.class), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            runner.update("insert into account(name, money) values(?,?)", account.getName(), account.getMoney());
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            runner.update("update account set name=?,money=? where id=?", account.getName(), account.getMoney(), account.getId());
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAccount(Integer id) {
        try {
            runner.update("delete from account where id = ?", id);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
