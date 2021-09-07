package com.example.service.impl;

import com.example.service.IaccountService;

/**
 * 账户的业务层实现类
 * @author Avarice
 * @date 2021/9/7 17:02
 */
public class AccountServiceImpl implements IaccountService {
    @Override
    public void saveAccount() {
        System.out.println("执行了保存");
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("执行了更新");
    }

    @Override
    public int deleteAccount() {
        System.out.println("执行了删除");
        return 0;
    }
}
