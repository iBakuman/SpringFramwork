package com.example.service.impl;

import com.example.dao.IAccountDao;
import com.example.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
 *  2.用于注入数据的
 *      他们的作用就和在xml配置文件中的bean标签中写一个property标签的作用是一样的
 *          Autowired：
 *              作用：自动按照类型注入。只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功
 *                   如果ioc容器中没有或有多个bean对象的类型（这些匹配的bean对象在Spring容器中的key与要注入的
 *                   变量的名称都不相同，如果相同则会将次bean对象注入）和要注入的变量类型匹配，
 *                   则注入失败
 *                   如果
 *              出现位置：可以在变量上也可以在方法上
 *
 * @author Avarice
 */

//@Component
@Service // 对于业务层，使用Service注解
public class AccountServiceImpl implements IAccountService {

    // 将变量名改为accountDao再查看test3的运行结果
    @Autowired
    private IAccountDao accountDaoImpl;

    @Override
    public void saveAccount() {
        System.out.println("Invoking saveAccount in class AccountServiceImpl...");
        accountDaoImpl.saveAccount();
    }
}
