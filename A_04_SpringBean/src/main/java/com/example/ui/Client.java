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

    /**
     * 测试Spring的IOC创建Bean对象时是多例模式还是单例模式
     * 结论：单例模式
     */
    @Test
    public void test4() {
        IAccountService service1 = context.getBean("accountService1", IAccountService.class);
        IAccountService service2 = context.getBean("accountService1", IAccountService.class);
        System.out.println("service1 = " + service1);
        System.out.println("service2 = " + service2);
        System.out.println(service1 == service2);
    }

    /**
     * 测试bean标签的scope属性为singleton的情况
     * 结论：创建的bean对象是单例的
     */
    @Test
    public void test5() {
        IAccountService service1 = context.getBean("singletonService", IAccountService.class);
        IAccountService service2 = context.getBean("singletonService", IAccountService.class);
        System.out.println("service1 = " + service1);
        System.out.println("service2 = " + service2);
        System.out.println(service1 == service2);
    }

    /**
     * 测试bean标签的scope属性为prototype的情况
     * 结论：创建的bean对象是多例
     */
    @Test
    public void test6() {
        IAccountService service1 = context.getBean("prototypeService", IAccountService.class);
        IAccountService service2 = context.getBean("prototypeService", IAccountService.class);
        System.out.println("service1 = " + service1);
        System.out.println("service2 = " + service2);
        System.out.println(service1 == service2);
    }

    /**
     * 测试单例bean对象的生命周期
     */
    @Test
    public void test7() {
        IAccountService slife = context.getBean("slifeCycle", IAccountService.class);
        System.out.println(slife);
        // 手动销毁容器
        ((ClassPathXmlApplicationContext)context).close();
    }

    /**
     * 测试多例bean对象的生命周期
     * 结论：执行了两次init方法但是只执行了一次destroy方法
     */
    @Test
    public void test8() {
        IAccountService plife = context.getBean("plifeCycle", IAccountService.class);
        System.out.println(plife);
        ((ClassPathXmlApplicationContext)context).close();
    }
}
