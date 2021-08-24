package com.example.service.impl;

import com.example.service.IAccountService;

public class AccountServiceImpl implements IAccountService {

    @Override
    public void saveAccount() {
        System.out.println("service中的saveAccount执行了...");
    }
}
