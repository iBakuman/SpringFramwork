package com.example.ui;

import com.example.service.IAccountService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟表现层，用于调用业务层
 */
public class Client {

    private ApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("bean.xml");
    }

    /**
     * 测试使用默认构造函数创建bean对象
     */
    @Test
    public void test1() {
        IAccountService accountService1 = context.getBean("accountService1", IAccountService.class);
        System.out.println(accountService1);
    }

    /**
     * 测试使用其他类的非静态方法创建bean对象
     */
    @Test
    public void test2() {
        IAccountService accountService2 = context.getBean("accountService2", IAccountService.class);
        System.out.println(accountService2);
    }

    /**
     * 测试使用某个类中的静态方法创建bean对象
     */
    @Test
    public void test3() {
        IAccountService accountService3 = context.getBean("accountService3", IAccountService.class);
        System.out.println(accountService3);
    }
}
