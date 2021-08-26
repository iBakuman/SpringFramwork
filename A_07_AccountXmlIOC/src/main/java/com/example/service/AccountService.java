package com.example.service;

import com.example.domain.Account;

import java.util.List;

/**
 * 业务层接口
 * @author Avarice
 */
public interface AccountService {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAll();

    /**
     * 根据主键查询一个账户的信息
     * @param id
     * @return
     */
    Account findById(Integer id);

    /**
     * 保存账户
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新账户信息
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除指定账户
     * @param id
     */
    void deleteAccount(Integer id);
}
