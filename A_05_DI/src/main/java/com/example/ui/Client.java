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
     * 测试构造函数注入
     */
    @Test
    public void test1() {
        IAccountService service = context.getBean("accountService", IAccountService.class);
        System.out.println(service);
    }

    /**
     * 测试set方法注入
     */
    @Test
    public void test2() {
        IAccountService service2 = context.getBean("accountService2", IAccountService.class);
        System.out.println(service2);
    }

    /**
     * 测试通过set方法注入来进行复杂类型的注入/集合类型的注入
     */
    @Test
    public void test3() {
        IAccountService service3 = context.getBean("accountService3", IAccountService.class);
        service3.saveAccount();
    }
}
