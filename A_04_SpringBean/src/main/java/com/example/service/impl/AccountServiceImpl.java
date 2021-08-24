package com.example.service.impl;

import com.example.service.IAccountService;

public class AccountServiceImpl implements IAccountService {

    @Override
    public void saveAccount() {
        System.out.println("service中的saveAccount执行了...");
    }

    /**
     * 为测试bean对象的声明周期而写的方法
     */
    public void init() {
        System.out.println("init...");
    }

    /**
     * 为测试bean对象的声明周期而写的方法
     */
    public void destroy() {
        System.out.println("destroy...");
    }
}
