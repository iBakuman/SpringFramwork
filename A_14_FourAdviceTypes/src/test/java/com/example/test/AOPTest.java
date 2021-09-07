package com.example.test;

import com.example.service.IaccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试AOP的配置
 * @author Avarice
 * @date 2021/9/7 17:21
 */
public class AOPTest {
    public static void main(String[] args) {
        ApplicationContext context =  new ClassPathXmlApplicationContext("bean.xml");
        IaccountService service = context.getBean("accountService", IaccountService.class);
        //service.saveAccount();
        service.updateAccount(0);
        service.deleteAccount();
    }
}

