package com.example.factory;

import com.example.service.IAccountService;
import com.example.service.impl.AccountServiceImpl;

public class StaticFactory {
    /**
     * 为测试创建bean对象的第三种方式而编写的方法
     * @return
     */
    public static IAccountService getAccountService() {
        return new AccountServiceImpl();
    }
}
