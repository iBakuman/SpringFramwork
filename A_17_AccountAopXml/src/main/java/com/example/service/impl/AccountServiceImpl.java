package com.example.service.impl;

import com.example.dao.AccountDao;
import com.example.domain.Account;
import com.example.service.AccountService;

import java.util.List;

/**
 * 账户业务层实现类
 */
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Account findById(Integer id) {
        return accountDao.findById(id);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer id) {
        accountDao.deleteAccount(id);
    }

    @Override
    public void transfer(String srcName, String destName, Float money) {
        // 1.获取转出账户
        Account srcAccount = accountDao.findByName(srcName);
        // 2.获取转入账户
        Account destAccount = accountDao.findByName(destName);
        // 3.转出账户减去转出金额
        srcAccount.setMoney(srcAccount.getMoney() - money);
        // 4.转入账户加上转出金额
        destAccount.setMoney(destAccount.getMoney() + money);
        // 5.更新转出账户
        accountDao.updateAccount(srcAccount);

        // 演示出现异常时会出现的问题
        int a = 1 / 0;
        /**
         * 结果：当产生算术异常时，转入账户已经扣掉了转账金额
         * 但是接收账户并没有收到转账金额，这在现实应用中是绝对
         * 不能够发生的***important
         */

        // 6.更新转入账户
        accountDao.updateAccount(destAccount);
    }
}
