package com.example.dao;

import com.example.domain.Account;

import java.util.List;

/**
 * @author Avarice
 * @date 2021/9/9 18:51
 */
public interface IAccountDao {
    /**
     * 通过id查找账户
     * @param id
     * @return
     */
     Account findById(Integer id);

    /**
     * 通过姓名查找用户
     * @param name
     * @return
     */
     List<Account> findByName(String name);

     void updateAccount(Account account);
}
