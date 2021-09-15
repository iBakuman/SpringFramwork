package com.example.service;

import com.example.domain.Account;

/**
 * @author Avarice
 * @date 2021/9/15 21:54
 */
public interface IAccountService {

    /**
     * 根据ID查找账户
     * @param id
     * @return
     */
    Account findById(Integer id);

    /**
     * 转账功能
     * @param srcName
     * @param destName
     * @param money
     */
    void transfer(String srcName, String destName, Float money);
}
