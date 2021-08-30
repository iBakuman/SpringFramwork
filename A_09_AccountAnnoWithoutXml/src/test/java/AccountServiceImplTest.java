import com.example.config.JdbcConfig;
import com.example.config.SpringConfig;
import com.example.dao.impl.AccountDaoImpl;
import com.example.domain.Account;
import com.example.service.AccountService;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * 测试数据AccountServiceImpl类
 * @author Avarice
 */
public class AccountServiceImplTest {
    private ApplicationContext context;

    @Before
    public void init() {
        context = new AnnotationConfigApplicationContext(SpringConfig.class, JdbcConfig.class);
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() {
        AccountService service = context.getBean("accountService", AccountService.class);
        List<Account> accounts = service.findAll();
        for (Account account : accounts)
            System.out.println(account);
    }

    /**
     * 测试根据id查询
     */
    @Test
    public void testFindById() {
        AccountService service = context.getBean("accountService", AccountService.class);
        Account account = service.findById(1);
        System.out.println(account);
    }

    /**
     * 测试保存数据
     */
    @Test
    public void testSave() {
        Account account = new Account();
        account.setMoney(new Float(100.0));
        account.setName("dddd");

        AccountService service = context.getBean("accountService", AccountService.class);
        service.saveAccount(account);
    }

    /**
     * 测试更新数据
     */
    @Test
    public void testUpdate() {
        Account account = new Account();
        account.setId(1);
        account.setName("龚胜辉");
        account.setMoney(new Float(100000.0));

        AccountService service = context.getBean("accountService", AccountService.class);
        service.updateAccount(account);
    }

    /**
     * 测试删除数据
     */
    @Test
    public void testDelete() {
        AccountService service = context.getBean("accountService", AccountService.class);
        service.deleteAccount(4);
    }

    /**
     * 测试queryRunner对象是否是单例的
     * 测试多例对象里存储的单例对象是否是单例的
     * 结论：多例对象里存储的单例对象是单例的
     */
    @Test
    public void testRunner() {
        QueryRunner runner1 = context.getBean("runner", QueryRunner.class);
        QueryRunner runner2 = context.getBean("runner", QueryRunner.class);
        System.out.println(runner1 == runner2);
        AccountDaoImpl dao1 = context.getBean("accountDao", AccountDaoImpl.class);
        AccountDaoImpl dao2 = context.getBean("accountDao", AccountDaoImpl.class);
        System.out.println("dao1 == dao2 : " + (dao1 == dao2));
        System.out.println("dao1.getRunner() == dao2.getRunner() : " + (dao1.getRunner() == dao2.getRunner()));
    }
}
