package com.example.service.impl;

import com.example.dao.IAccountDao;
import com.example.domain.Account;
import com.example.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Avarice
 * @date 2021/9/15 21:55
 */
@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public Account findById(Integer id) {
        return transactionTemplate.execute(transactionStatus -> accountDao.findById(id));
    }

    @Override
    public void transfer(String srcName, String destName, Float money) {
        transactionTemplate.execute((TransactionCallback<Account>) transactionStatus -> {
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
            return null;
        });
    }
}
