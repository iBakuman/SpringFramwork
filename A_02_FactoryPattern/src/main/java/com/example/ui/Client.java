package com.example.ui;

import com.example.service.IAccountService;
import com.example.service.impl.AccountServiceImpl;

/**
 * 模拟表现层，用于调用业务层
 */
public class Client {
    public static void main(String[] args) {
        IAccountService service = new AccountServiceImpl();
        service.saveAccount();
    }
}
