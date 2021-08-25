package com.example.ui;

import com.example.dao.IAccountDao;
import com.example.service.IAccountService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟表现层，用于调用业务层
 *
 * @author Avarice
 */
public class Client {

    private ApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("bean.xml");
    }

    /**
     * 测试使用注解配置来创建bean对象
     */
    @Test
    public void test1() {
        IAccountDao dao = (IAccountDao) context.getBean("accountDaoImpl");
        System.out.println(dao);
        IAccountService service = (IAccountService) context.getBean("accountServiceImpl");
        System.out.println(service);
    }

    /**
     * 测试使用注解来注入数据
     */
    @Test
    public void test2() {
        IAccountService service = context.getBean("accountServiceImpl", IAccountService.class);
        service.saveAccount();
        System.out.println(service);
    }

    /**
     * 测试Autowired注解遇到多个类型匹配的bean对象时的行为
     */
    @Test
    public void test3() {
        IAccountService service = context.getBean("accountServiceImpl", IAccountService.class);
        service.saveAccount();
        System.out.println(service);
    }

    /**
     * 测试Qualifier注解的使用
     */
    @Test
    public void test4() {
        IAccountService service = context.getBean("accountServiceImpl", IAccountService.class);
        service.saveAccount();
        System.out.println(service);
    }

    /**
     * 测试Resource注解和Value注解的使用
     */
    @Test
    public void test5() {
        IAccountService service = context.getBean("accountServiceImpl", IAccountService.class);
        service.saveAccount();
        System.out.println(service);
    }
}
