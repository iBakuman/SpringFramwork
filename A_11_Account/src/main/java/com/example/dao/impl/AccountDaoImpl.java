package com.example.dao.impl;

import com.example.dao.AccountDao;
import com.example.domain.Account;
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

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            runner.update("update account set name=?,money=? where id=?", account.getName(), account.getMoney(), account.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAccount(Integer id) {
        try {
            runner.update("delete from account where id = ?", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void transfer(String srcName, String destName, Float money) {
        // 1.获取转出账户
        Account srcAccount = findByName(srcName);
        // 2.获取转入账户
        Account destAccount = findByName(destName);
        // 3.转出账户减去转出金额
        srcAccount.setMoney(srcAccount.getMoney() - money);
        // 4.转入账户加上转出金额
        destAccount.setMoney(destAccount.getMoney() + money);
        // 5.更新转出账户
        updateAccount(srcAccount);

        // 演示出现异常时会出现的问题
        int a = 1 / 0;
        /**
         * 结果：当产生算术异常时，转入账户已经扣掉了转账金额
         * 但是接收账户并没有收到转账金额，这在现实应用中是绝对
         * 不能够发生的***important
         */

        // 6.更新转入账户
        updateAccount(destAccount);
    }

    @Override
    public Account findByName(String name) {
        List<Account> accounts = null;
        try {
            accounts = runner.query("select * from account where name = ?", new BeanListHandler<>(Account.class), name);
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
