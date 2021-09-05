package com.example.service.impl;

import com.example.dao.AccountDao;
import com.example.utils.TransactionManager;
import com.example.domain.Account;
import com.example.service.AccountService;

import java.util.List;

/**
 * 账户业务层实现类
 */
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;
    private TransactionManager txManager;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = null;
        try {
            // 1.开启业务
            txManager.beginTransaction();
            // 2.执行操作
            accounts = accountDao.findAll();
            // 3.提交事务
            txManager.commit();
        } catch (Exception e) {
            // 5.回滚操作
            txManager.rollback();
        } finally {
            // 6/释放连接
            txManager.release();
        }
        // 4.返回结果
        return accounts;
    }

    @Override
    public Account findById(Integer id) {
        Account account = null;
        try {
            txManager.beginTransaction();
            account = accountDao.findById(id);
            txManager.commit();
        } catch (Exception e) {
            txManager.rollback();
        } finally {
            txManager.release();
        }
        return account;
    }

    @Override
    public void saveAccount(Account account) {
        try {
            txManager.beginTransaction();
            accountDao.saveAccount(account);
            txManager.commit();
        } catch (Exception e) {
            txManager.rollback();
        } finally {
            txManager.release();
        }

    }

    @Override
    public void updateAccount(Account account) {
        try {
            txManager.beginTransaction();
            accountDao.updateAccount(account);
            txManager.commit();
        } catch (Exception e) {
            txManager.rollback();
        } finally {
            txManager.release();
        }
    }

    @Override
    public void deleteAccount(Integer id) {
        try {
            txManager.beginTransaction();
            accountDao.deleteAccount(id);
            txManager.commit();
        } catch (Exception e) {
            txManager.rollback();
        } finally {
            txManager.release();
        }
    }

    @Override
    public void transfer(String srcName, String destName, Float money) {
        try {
            txManager.beginTransaction();

            // 1.获取转出账户
            Account srcAccount = accountDao.findByName(srcName);
            // 2.获取转入账户
            Account destAccount =accountDao.findByName(destName);
            // 3.转出账户减去转出金额
            srcAccount.setMoney(srcAccount.getMoney() - money);
            // 4.转入账户加上转出金额
            destAccount.setMoney(destAccount.getMoney() + money);
            // 5.更新转出账户
            accountDao.updateAccount(srcAccount);

            /*
            System.out.println(txManager.getConnectionUtils().getThreadConnection().getAutoCommit());// false
            txManager.release();
            System.out.println(txManager.getConnectionUtils().getThreadConnection().getAutoCommit());// true
            */

            // 演示出现异常时会出现的问题
            int a = 1 / 0;
            /**
             * 结果：当产生算术异常时，转入账户已经扣掉了转账金额
             * 但是接收账户并没有收到转账金额，这在现实应用中是绝对
             * 不能够发生的***important
             */
            // 6.更新转入账户
            accountDao.updateAccount(destAccount);

            txManager.commit();
        } catch (Exception e) {
            txManager.rollback();
        } finally {
            txManager.release();
        }
    }
}
