package com.example.service.impl;

import com.example.dao.IAccountDao;
import com.example.service.IAccountService;
import org.springframework.stereotype.Service;

/**
 * 账户的业务层实现类
 * 曾经的XML配置
 *  <bean id="accountService" class="com.example.service.impl.AccountServiceImpl"
 *  scope="" init-method="" destroy-method="">
 *      <property name="" value="" / ref = ""></property>

 *  </bean>
 *
 *  1.用于创建对象的
 *      它们的作用就和在XML配置文件中编写一个<bean>标签实现的功能是一样的
 *          Component:
 *              作用：用于把当前类对象存入容器中
 *              属性：
 *                  value：用于指定bean的id。当我们不懈时，它的默认值是当前类名，且首字母改小写
*           Controller：一般用在表现层
 *          Service：一般用在业务层
 *          Repository：一般用在持久层
 *          以上三个注解的作用和属性与Component的完全一样
 *
 * @author Avarice
 */

//@Component
@Service // 对于业务层，使用Service注解
public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao;

    @Override
    public void saveAccount() {
        accountDao.saveAccount();
        System.out.println("Invoking saveAccount in class AccountServiceImpl...");
    }
}
