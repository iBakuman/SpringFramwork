package com.example.dao;

import com.example.domain.Account;

import java.util.List;

/**
 * 账户持久层接口
 * @author Avarice
 */
public interface AccountDao {
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

    /**
     * 根据名称查找账户
     * @param name      账户名称
     */
    Account findByName(String name);
}
