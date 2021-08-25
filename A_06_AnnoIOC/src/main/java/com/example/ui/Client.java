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

}
