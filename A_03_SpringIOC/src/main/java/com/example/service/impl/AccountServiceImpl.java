package com.example.service.impl;

import com.example.dao.IAccountDao;
import com.example.dao.impl.AccountDaoImpl;
import com.example.service.IAccountService;

public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao = null;

    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }
}
