package com.example.jdbctemplate;

import com.example.dao.IAccountDao;
import com.example.domain.Account;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author Avarice
 * @date 2021/9/9 19:00
 */
public class JdbcTemplateDemo4 {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
    IAccountDao accountDao = context.getBean("accountDao", IAccountDao.class);

    /**
     * 测试根据id来查找账户
     */
    @Test
    public void testFindById() {
        Account account = accountDao.findById(1);
        System.out.println(account);
    }

    /**
     * 测试根据name来查找账户
     */
    @Test
    public void testFindByName() {
        List<Account> accounts = accountDao.findByName("要全瑞");
        for(Account account : accounts) {
            System.out.println(account);
        }
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        Account account = new Account();
        account.setId(1);
        account.setName("郭嘉");
        account.setMoney(1537218f);
        accountDao.updateAccount(account);
    }
}
