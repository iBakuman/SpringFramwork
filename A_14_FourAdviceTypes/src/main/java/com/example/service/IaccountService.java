package com.example.service;

/**
 * 账户的业务层接口
 * @author Avarice
 * @date 2021/9/7 17:00
 */
public interface IaccountService {
    /**
     * 模拟保存账户
     */
    void saveAccount();

    /**
     * 更新账户
     * @param i
     */
    void updateAccount(int i);

    /**
     * 删除账户
     * @return
     */
    int deleteAccount();
}
