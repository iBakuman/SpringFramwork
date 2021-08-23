package com.example.ui;

import com.example.factory.BeanFactory;
import com.example.service.IAccountService;

/**
 * 模拟表现层，用于调用业务层
 */
public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 5; ++i) {
            IAccountService service = (IAccountService) BeanFactory.getBean("accountService");
            System.out.println(service);
        }
        //service.saveAccount();
    }
}
